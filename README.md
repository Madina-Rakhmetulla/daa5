# KMP String Matching Algorithm (Java)

This project contains an implementation of the **Knuth–Morris–Pratt (KMP)** string matching algorithm written in Java.  
The program searches for all occurrences of a pattern inside a text and demonstrates the efficiency of the KMP approach.

##  Project Contents
- `KMPDemo.java` — full implementation of the KMP algorithm with comments.
- Three test cases:
  - Short string
  - Medium-length string
  - Long 100k-character generated string
- Sample output included in `sample-output.txt`.
- Detailed report: `KMP_Report_Style_D.pdf`.

## How to Run
Go to the `src` folder and run:

javac KMPDemo.java
java KMPDemo
The program will print results for all three test cases.

Algorithm Summary
	•	Time Complexity:
	•	Preprocessing (LPS): O(m)
	•	Search: O(n)
	•	Total: O(n + m)
	•	Space Complexity:
	•	LPS array: O(m)

Description

The KMP algorithm improves string searching by avoiding unnecessary comparisons.
It uses the LPS (Longest Prefix–Suffix) array to determine how far the pattern can shift after a mismatch.

Author

Madina Rakhmetulla, SE-2430
