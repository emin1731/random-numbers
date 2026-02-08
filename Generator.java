import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;


public class Generator {
    private Random random;
    private static final int[] sampleSizes = {10, 100, 1000};

    Generator() {
        this.random = new Random();
    }

    // Creates and returns an ArrayList of n random numbers using one of the three random number generators.
    ArrayList<Double> populate(int n, int randNumGen) {
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
                    throw new IllegalArgumentException("Invalid random number generator index: " + randNumGen);
            }
            randomValues.add(randomValue);
        }

        return randomValues;
    }

    // Calculates the number of elements (n), mean, sample standard deviation, minimum, and maximum, and returns the results in the following order: 
    // [n, mean, stddev, min, max]
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

    // Displays the results in a tabular format in the system console, with or without a header.
    void display(ArrayList<Double> results, boolean headerOn)
    {
        if (headerOn) {
                System.out.println("n | \tmean | \tstddev | \tmin | \tmax ");
            }

        for (Double item : results) {
            System.out.print(item + "\t");
        }

        System.out.println();

    }

    // Calls the populate, statistics, and display methods for all combinations of n values and random number generators, producing a total of nine results.
    void execute()
    {
        boolean headerOn = true;

        for(int sampleSize : sampleSizes) {
            for (int randNumGen = 0; randNumGen < 3; randNumGen++) {
                ArrayList<Double> randomValues = this.populate(sampleSize, randNumGen);
                ArrayList<Double> results = this.statistics(randomValues);
                this.display(results, headerOn);
            }
        }

    }
}
