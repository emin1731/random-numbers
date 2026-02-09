# Generator.java - Program Documentation

## Assignment for the OOAD course at ADA University

## Overview

This Java program demonstrates random number generation and statistical analysis using three different random number generators built into Java.

## Key component of the code:

1. **Class Definition**s

```java
    public class Generator {...}
```

The Generator class encapsulates all functionality for random number generation and statistical analysis.

2. **Method Definitions**

- Constructor (Line 33): **public Generator()**
- **populate()** (Line 45): Generates random numbers
- **statistics()** (Line 81): Calculates descriptive statistics
- **display()** (Line 130): Displays results in tabular format
- **execute()** (Line 153): Orchestrates the entire analysis

3. **Class Attributes**

```java
private static final int[] SAMPLE_SIZES = {100, 1000, 10000};
private Random random;
```

## Program Flow

**main()** method creates a Generator object and calls execute() method which loops through:

- 3 random number generators
- 3 sample sizes (100, 1000, 10000)

For each combination:

- **populate()** generates n random numbers
- **statistics()** calculates mean, std dev, min, max
- **display()** outputs results in table format

## Statistical Formulas Used

Mean: `mean = (sum of all values) / n`
Sample Standard Deviation: `stddev = sqrt(sum((x_i - mean)^2) / (n - 1))`

### Expected Results

As sample size increases:

- Mean -> 0.5
- Standard Deviation -> 0.289 (theoretical: 1/√12 ≈ 0.2887)
- Minimum -> 0
- Maximum -> 1

## Sample Output Format

```java
Random Number Generator: 1, Sample Size: 10
--------------------------------------------------------------------
n               Mean            Std Dev         Min             Max
--------------------------------------------------------------------
10              0.486577        0.228104        0.158672        0.957184
--------------------------------------------------------------------
```

This table will be printed 9 times for each size n and for all three random number generators

### Random Number Generators Used:

- java.util.Random: Thread-safe, uses linear congruential formula
- Math.random(): Static method, internally uses java.util.Random
- ThreadLocalRandom: High-performance, thread-local random generator

### Notes regarding the Assignment:

1. Highlights of examples of class definition, method definition, class attribute, object instantiation and accessibility are displayed in the comments using the "This is an example of..." comment
2. The additional validation check for populate method was added. It checks the validity of the method parameters for not being negative or out of range in the case of `randNumGen` parameter
3. The class attribute `sampleSizes` was used by the `execute()` method. Although the class could be extended to support selecting the sample size with command line arguments, this functionality was omitted to avoid deviating from the main assignment.
