# Book Catalog Management System

## Description
The **Book Catalog Management System** is a Java-based application that provides functionality for managing, validating, and navigating a large dataset of book records. Designed as part of a university assignment for practicing **Exception Handling**, **File I/O**, and **Object-Oriented Programming**, the system enables users to process book records categorized by genres, validate records for syntax and semantic errors, and navigate through serialized data in an interactive manner.

## Features
- **Record Validation**:
  - Validates book records for both **syntax** (e.g., missing fields, unknown genres) and **semantics** (e.g., invalid ISBN, year, or price).
  - Supports custom exception handling with detailed error reporting.

- **Data Serialization**:
  - Converts valid book records into serialized binary files categorized by genre.
  - Deserializes binary files into arrays of `Book` objects for further processing.

- **Interactive Navigation**:
  - Provides a menu-driven interface to browse, view, and select book records interactively.
  - Allows navigation through book records in any genre, with features to view specific ranges of records.

- **Menu System**:
  - Intuitive main menu and sub-menu for selecting and viewing files.
  - Graceful error handling for invalid user inputs.

## Key Classes
### 1. **Book**
- Represents a book record with attributes such as `title`, `authors`, `price`, `ISBN`, `genre`, and `year`.  
- Implements data validation methods for semantic checks like valid ISBN and year.

### 2. **Record**
- Handles operations for processing and validating book records from CSV files.  
- Includes methods to detect syntax errors, split records, and create `Book` objects.

### 3. **Menu**
- Provides utilities for displaying menus, getting user inputs, and navigating book records interactively.  
- Supports user-friendly error messages and formatted output.

### 4. **Driver**
- Main entry point for the program.  
- Orchestrates the flow through the three parts of the assignment:
  1. Syntax validation and genre-based categorization.
  2. Semantic validation and serialization.
  3. Interactive navigation of book records.

## Input and Output
### Input
CSV files containing book records, with fields:  
`title`, `authors`, `price`, `ISBN`, `genre`, `year`.

- Example of a valid record:  
  `"Manilow, Barry - Biography",Patricia Butler,3.95,0711991979,MRB,2006`

### Output
- Valid records categorized into CSV files for each genre.
- Error logs for syntax and semantic errors in plain text files.
- Serialized binary files for valid records.

## How to Run
1. Place the input CSV files in the appropriate directory (e.g., `book_dir/`).
2. Compile the Java files:
   ```bash
   javac *.java
3. Run the Program:
   ```bash
   javac Driver

