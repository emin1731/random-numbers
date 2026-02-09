import java.util.ArrayList;

// packages for random number generation
import java.util.Random;
import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This is an example of class definition.
 * Generator class: responsible for generating random numbers, calculating statistics, and displaying results. 
 * Has three main methods: populate, statistics, and display, and an execute method that operates the entire process.
 */
public class Generator {

    // This is an example of class attribute and accessibility: Instance variable to hold the random number generator
    private Random random;

    // This is an example of class attribute and accessibility: Array of sample sizes to be used in the execute method
    private static int[] sampleSizes = {10, 100, 1000};

    // Constructor to initialize the random number generator (java.util.Random)
    public Generator() {
        this.random = new Random();
    }

/**
 * This is an example of method definition. 
 * Method creates and returns an ArrayList of n random numbers using one of the three random number generators.
 * The method takes two parameters: n (the number of random numbers to generate) and randNumGen (an index to select the random number generator).
 * The method uses a switch statement to determine which random number generator to use based on the value of randNumGen: 
 * - 0 for java.util.Random, 
 * - 1 for Math.random(), and 
 * - 2 for ThreadLocalRandom.
 * The generated random numbers are stored in an ArrayList and returned at the end of the method.
 * @param n the number of random numbers to generate
 * @param randNumGen the index to select the random number generator (0 for java.util.Random, 1 for Math.random(), 2 for ThreadLocalRandom)
 * @return an ArrayList of generated random numbers
 */
    ArrayList<Double> populate(int n, int randNumGen) {

        // Validate the input parameters
        if(n <= 0) {
            throw new IllegalArgumentException("Sample size must be greater than 0");
        }
        if (!(randNumGen >= 0 && randNumGen <= 2)) {
            throw new IllegalArgumentException("Invalid random number generator index: " + randNumGen + ". Valid values are 0 for java.util.Random, 1 for Math.random(), and 2 for ThreadLocalRandom.");
        }

        ArrayList<Double> randomValues = new ArrayList<Double>();

        for (int i = 0; i < n; i++) {
            double randomValue;

            switch (randNumGen) {
                case 0:
                    randomValue = random.nextDouble();
                    break;
                case 1:
                    randomValue = Math.random();
                    break;
                case 2:
                    randomValue = ThreadLocalRandom.current().nextDouble();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid random number generator index: " + randNumGen + ". Valid values are 0 for java.util.Random, 1 for Math.random(), and 2 for ThreadLocalRandom.");
            }
            randomValues.add(randomValue);
        }

        return randomValues;
    }

/**
 * This is an example of method definition. 
 * Method calculates the number of elements (n), mean, sample standard deviation, minimum, and maximum, and returns the results in the following order: 
 * [n, mean, stddev, min, max]
 * @param randomValues an ArrayList of random numbers for which to calculate statistics
 * @return an ArrayList containing the calculated statistics in the order: [n, mean, stddev, min, max]
 */
    ArrayList<Double> statistics(ArrayList<Double> randomValues)
    {
        double n = randomValues.size();
        double mean = 0.0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        double stddev = 0.0;
        double sumOfValues = 0.0;

        for (Double item : randomValues) {

            // find the sum of all elements in the array
            sumOfValues += item;

            // find the minimum element in the array
            if (item < min) {
                min = item;
            }

            // find the maximum element in the array
            if (item > max) {
                max = item;
            }
        }

        mean = sumOfValues / n;

        // find the variance of the array
        double variance = 0.0;
        for (Double item : randomValues) {
            variance += Math.pow(item - mean, 2);
        }

        // find the sample standard deviation of the array
        stddev = Math.sqrt(variance / (n - 1));

        // create an ArrayList to store the results and return it
        ArrayList<Double> results = new ArrayList<Double>();
        results.add(n);
        results.add(mean);
        results.add(stddev);
        results.add(min);
        results.add(max);

        return results;
    }

    /**
     * This is an example of method definition.
     * Method takes an ArrayList of results and a boolean headerOn as parameters. 
     * If headerOn is true, the method prints a header row before displaying the results. 
     * The results are displayed in a formatted manner, showing n, mean, standard deviation, minimum, and maximum values.
     * @param results
     * @param headerOn
     * @return void
     */
    void display(ArrayList<Double> results, boolean headerOn)
    {
        // Print the header if headerOn is true
        if (headerOn) {
                System.out.println("--------------------------------------------------------------------");
                System.out.printf("%-15s %-15s %-15s %-15s %-15s%n","n", "Mean", "Std Dev", "Min", "Max");
        }
        System.out.println("--------------------------------------------------------------------");

        // Print the results in a formatted manner. the "-15.6f" format specifier is used to left-align the numbers and display them with 6 decimal places. 15 characters are reserved for each number, and if the number has fewer than 15 characters, it will be padded with spaces on the right to ensure proper alignment in the output.
        System.out.printf("%-15.0f %-15.6f %-15.6f %-15.6f %-15.6f%n", results.get(0), results.get(1), results.get(2), results.get(3), results.get(4));
        
        System.out.println("--------------------------------------------------------------------");

    }

    /** 
     * This is an example of method definition.
     * Method iterates through the predefined sample sizes and random number generators, calling the populate, statistics, and display methods for each combination. 
     * It prints the results in a formatted manner, showing the random number generator used, the sample size, and the calculated statistics (n, mean, standard deviation, minimum, and maximum).
     * @return void
     */
    void execute()
    {
        // This variable is used to control the visibility of the header in the display method. 
        boolean headerOn = true;

        for(int sampleSize : sampleSizes) {
            for (int randNumGen = 0; randNumGen < 3; randNumGen++) {
                ArrayList<Double> randomValues = this.populate(sampleSize, randNumGen);
                ArrayList<Double> results = this.statistics(randomValues);
                System.out.printf("Random Number Generator: %d, Sample Size: %d%n", randNumGen, sampleSize);
                this.display(results, headerOn);
                System.out.println("\n");
            }
        }

    }

/**
 * This is an example of method definition and accessibility.
 * The main method is an entry point of the program and is defined as public static void main(String[] args).
 * In the main method, an instance of the Generator class is created, and the execute method is called to run the random number generation and statistics calculation process.
 * @param args command line arguments (it is not used in this program)
 */
    public static void main(String[] args) {

        // This is an example of Object instantiation: Creating an instance of the Generator class and calling the execute method to run the random number generation and statistics calculation process.
        Generator g = new Generator();
        g.execute();

    }    
}
