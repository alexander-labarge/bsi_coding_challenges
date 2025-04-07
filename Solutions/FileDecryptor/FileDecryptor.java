/*
 * FileDecryptor.java
 * Author: Alexander La Barge
 * Date: 7 Apr 25
 * Company: Brilliant Solutions, Inc.
 *
 * SAMPLE UTILITY to Demonstrate AES-GCM File Decryption
 * -----------------------------------------------------
 * Usage: java FileDecryptor <inputFile> <outputFile> <password>
 *
 * - inputFile  : the encrypted file produced by FileEncryptor
 * - outputFile : path where the decrypted data will be written
 * - password   : the same password used to encrypt (derive key)
 *
 * Note: This sample code complements FileEncryptor.java. It reads
 * the salt (16 bytes) and IV (12 bytes) from inputFile, derives
 * the key using PBKDF2WithHmacSHA256, and decrypts the remaining
 * data using AES/GCM/NoPadding.
 */

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class FileDecryptor {
    private static final int AES_KEY_SIZE = 128;      // 128-bit key
    private static final int GCM_TAG_LENGTH = 128;    // 128-bit auth tag
    private static final int SALT_SIZE = 16;          // 16 bytes (must match encryptor)
    private static final int IV_SIZE = 12;            // 12 bytes recommended for GCM
    private static final int PBKDF2_ITERATIONS = 65536;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: java FileDecryptor <inputFile> <outputFile> <password>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];
        String password = args[2];

        File inputFile = new File(inputFilePath);
        if (!inputFile.exists()) {
            System.err.println("Encrypted file does not exist: " + inputFilePath);
            return;
        }

        try {
            decryptFile(inputFilePath, outputFilePath, password);
            System.out.println("Decryption successful. Decrypted file: " + outputFilePath);
        } catch (Exception e) {
            System.err.println("Error decrypting file: " + e.getMessage());
        }
    }

    private static void decryptFile(String inputFilePath, String outputFilePath, String password) throws Exception {
        try (FileInputStream fis = new FileInputStream(inputFilePath);
             FileOutputStream fos = new FileOutputStream(outputFilePath)) {

            // 1. Read salt
            byte[] salt = new byte[SALT_SIZE];
            if (fis.read(salt) != SALT_SIZE) {
                throw new IOException("Invalid file format: unable to read salt.");
            }

            // 2. Read IV
            byte[] iv = new byte[IV_SIZE];
            if (fis.read(iv) != IV_SIZE) {
                throw new IOException("Invalid file format: unable to read IV.");
            }

            // 3. Derive key from password
            SecretKeySpec keySpec = deriveKeyFromPassword(password, salt);

            // 4. Initialize Cipher for AES/GCM decryption
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);

            // 5. Decrypt remaining data in chunks
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                byte[] decrypted = cipher.update(buffer, 0, bytesRead);
                if (decrypted != null && decrypted.length > 0) {
                    fos.write(decrypted);
                }
            }
            // 6. Finalize decryption
            byte[] finalBytes = cipher.doFinal();
            if (finalBytes != null && finalBytes.length > 0) {
                fos.write(finalBytes);
            }
        }
    }

    private static SecretKeySpec deriveKeyFromPassword(String password, byte[] salt) throws Exception {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, PBKDF2_ITERATIONS, AES_KEY_SIZE);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES");
    }
}
