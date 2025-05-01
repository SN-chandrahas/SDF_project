# SDF_project
SDF project 2025


1 Introduction
This project, myinfarith, implements arbitrary-precision integer and floating-point arithmetic in Java.
It features two classes: AInteger and AFloat, made to perform calculations on numbers of big size and
precision.


2 AInteger Class Overview
The AInteger class handles operations on arbitrarily large integers using strings of digits to do basic
operations without using java packages.

2.1 Addition
The addition method in AInteger uses traditional way of addition.
• The numbers are added in reversed format digits from least place value to the most.
• A loop iterates over each digit, adding corresponding digits and a carry . with carry storing 1 when
the sum exceeds 9.
• The result is stored in a string

2.2 Subtraction
Subtraction is done similarly, with borrow:
• The larger number is determined to avoid negative results.
• Digits are processed from least place value to most place value.
• If the digit in the first is smaller than the digit in the second, a borrow is made from the next digit.
• The result is constructed by appending digits to result after each subtraction.

2.3 Multiplication
The multiplication method follows the traditional multiplication:
• Each digit of the second number is multiplied with all digits of the first.
• Partial results are appended with zeros based on digit position.
• All partial results are summed using the addition logic.

2.4 Division
Division is handled through repeated subtraction:
• A substring of the dividend (equal in length to the divisor) is repeatedly subtracted by the divisor.
• The count of subtractions is appended as a digit in the quotient.
• The window slides across the remaining digits of the dividend and repeats.
It is similar to manual division

3 AFloat Class Overview
The AFloat class extends the logic of AInteger to support floating-point numbers with arbitrary pre-
cision. It separates the integer and decimal parts of the numbers and processes them separately for the
operations(except div).
All the operations here are done in a similar way.
