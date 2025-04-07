# README for File Stats Reader

**Company:** Brilliant Solutions, Inc.  
**Author:** Alexander La Barge  
**Date:** 7 Apr 25  

---

## Overview

**File Stats Reader** is a simple Java console application that calculates basic statistics from a text file, including:

- **Line Count**  
- **Word Count**  
- **Average Word Length** (rounded to 2 decimal places)

The program also gracefully handles errors such as file-not-found situations or empty files.

---

## Instructions

### 1. File Contents & Basic Requirements
- The program reads from a **plain text file**.
- Each line of the file can contain one or more words.
- Words are separated by whitespace (spaces, tabs, etc.).

### 2. Provided Files
- A sample text file named **`sample.txt`** is included in this **`FileStatsReader`** directory.

### 3. Compilation & Execution

#### Windows

1. **Open Command Prompt (cmd) or PowerShell.**  
2. **Navigate to the `FileStatsReader` directory** containing `FileStatsReader.java`.  
3. **Compile** the Java file:

```shell
javac FileStatsReader.java
```

4. **Run** the program, passing the path to the text file as an argument. For example:

```shell
java FileStatsReader C:\path\to\your\sample.txt
```

*(Make sure the path is properly escaped, e.g., `C:\\path\\to\\sample.txt` if necessary.)*

#### Linux

1. **Open a Terminal.**  
2. **Navigate to the `FileStatsReader` directory** containing `FileStatsReader.java`.  
3. **Compile** the Java file:

```bash
javac FileStatsReader.java
```

4. **Run** the program, passing the path to the text file as an argument. For example:

```bash
java FileStatsReader /home/user/sample.txt
```

---

## Usage Example

Assume the sample text file **`sample.txt`** contains the following:

```bash
Hello world
This is a test file.
```

### Example for Windows

```shell
javac FileStatsReader.java
java FileStatsReader C:\Users\JohnDoe\Documents\sample.txt
```

### Example for Linux

```bash
javac FileStatsReader.java
java FileStatsReader /home/user/sample.txt
```

**Expected Output**:

```plaintext
Line Count: 2
Word Count: 6
Average Word Length: 3.67
```

---

## Error Handling

- If the specified file path does not exist or is unreadable:

```plaintext
File not found or cannot be read.
```

- If the file has **no content** (0 words), the program should handle the division by zero for average word length, e.g., by printing:

```plaintext
Average Word Length: 0
```

or

```plaintext
Average Word Length: N/A
```

Please ensure you **compile** the program before running it. Use the **`sample.txt`** located in this **`FileStatsReader`** directory to confirm your solution is working as expected.
