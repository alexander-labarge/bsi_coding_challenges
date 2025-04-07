**Company:** Brilliant Solutions, Inc.  
**Author:** Alexander La Barge  
**Date:** 7 Apr 25  

---

## Overview

This **File Encryption Utility** is the second coding challenge, building on your file-handling skills. The goal is to:

1. Accept a **file path** (plaintext) and an **output file path**.  
2. Accept a **password** from the command line.  
3. Encrypt the file using the provided password.  
4. Write the encrypted data to the specified output file.

You can decide whether to use built-in Java libraries (e.g., `javax.crypto`) or third-party libraries. A common secure option is **AES** in either **CBC** or **GCM** mode with **PKCS#5** padding. Implementing decryption is optional but demonstrates a more complete approach.

---

## Instructions

### 1. Requirements
- **Command Line Utility**: The user runs this from a terminal or command prompt.
- **Password Input**: Derive an **encryption key** from the password (e.g., using PBKDF2 with salt and an iteration count).
- **Error Handling**:
  - If the input file doesn’t exist, print an error message and exit.
  - If writing to the output file fails, print an error message and exit.

### 2. Compilation & Execution

#### Windows

1. **Open Command Prompt (cmd) or PowerShell.**  
2. **Navigate to the `FileEncryptor` directory** containing `FileEncryptor.java`.  
3. **Compile**:

```shell
javac FileEncryptor.java
```

4. **Run**, providing the input file, output file, and password. For example:

```shell
java FileEncryptor C:\path\to\plaintext.txt C:\path\to\encrypted.dat MySecretPassword
```

#### Linux

1. **Open a Terminal.**  
2. **Navigate to the `FileEncryptor` directory** containing `FileEncryptor.java`.  
3. **Compile**:

```bash
javac FileEncryptor.java
```

4. **Run**, providing the input file, output file, and password. For example:

```bash
java FileEncryptor /home/user/plaintext.txt /home/user/encrypted.dat MySecretPassword
```

---

## Example Usage

Assume you have a file named **`plaintext.txt`** containing:

```plaintext
This is a top secret message.
```

### Example for Windows

```shell
javac FileEncryptor.java
java FileEncryptor C:\Users\JohnDoe\Documents\plaintext.txt C:\Users\JohnDoe\Documents\encrypted.dat S3cureP@ss
```

### Example for Linux

```bash
javac FileEncryptor.java
java FileEncryptor /home/user/plaintext.txt /home/user/encrypted.dat S3cureP@ss
```

The program should:
1. Read the contents of `plaintext.txt`.  
2. Generate or derive a key from `S3cureP@ss`.  
3. Encrypt the text.  
4. Write the **encrypted data** to `encrypted.dat`.

---

## Considerations & Tips

- **Encryption Method**:
  - A simple approach is using `AES` in **CBC** or **GCM** mode (GCM is recommended for authenticated encryption).
  - Derive the key from the password using `PBKDF2WithHmacSHA256`, typically with a **random salt** and a high **iteration count** (e.g., 65536).
  - Store the salt (and IV, if using CBC or GCM) with the encrypted data so decryption is possible later.

- **Exception Handling**:
  - Catch `FileNotFoundException`, `IOException`, and any `GeneralSecurityException`.

- **Security**:
  - **Never** hardcode passwords in your code.
  - Use a secure random salt (`SecureRandom`).

- **Performance**:
  - For large files, read and write in chunks (e.g., 4KB or 8KB) instead of loading the entire file into memory.

---

## Task Summary

- **Implement**: `FileEncryptor.java`  
- **Objective**: Demonstrate basic encryption on the command line using a password-based approach.  
- **Timebox**: Aim to complete in **30 minutes** or less.

Good luck on this second challenge from **Brilliant Solutions, Inc.**!
