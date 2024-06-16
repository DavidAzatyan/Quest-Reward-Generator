import java.util.ArrayList;
import java.util.List;

public class QuestRewardGenerator {
    /**
     * Main method to demonstrate the generation of reward strings.
     */
    public static void main(String[] args) {
        int N = 100;  // Example N value. You can change this to any number.
        String distribution = "bbbbbbbbbsbbbbbbbbsbbbsbbbsbbbbsbsbbsbsbbsbbsbsbsbsbssbsbsbsbsbsssbsssbssbssbsssssbssssssbssssssssss";

        // Generate interpolated distribution
        String rewards = generateRewards(N, distribution);

        // Print the result
        System.out.println("Generated rewards for N = " + N + ": " + rewards);
    }

    /**
     * Generates a reward string of length N based on interpolation of the given distribution pattern.
     *
     * @param N The number of quests
     * @param distribution The initial distribution string pattern
     * @return A string of rewards ('b' or 's') of length N
     */
    public static String generateRewards(int N, String distribution) {
        int originalLength = distribution.length();
        double[] yValues = new double[originalLength];

        // Generate y values based on the given distribution
        for (int i = 0; i < originalLength; i++) {
            yValues[i] = distribution.charAt(i) == 'b' ? 1.0 : 0.0;
        }

        // Interpolate to generate a new distribution for N points using sine function
        List<Double> newYValues = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            double x = (double) i / (N - 1);
            double interpolatedValue = sineInterpolate(x, yValues);
            newYValues.add(interpolatedValue);
        }

        // Generate the reward string based on interpolated values
        StringBuilder rewards = new StringBuilder();
        for (double value : newYValues) {
            rewards.append(value > 0.5 ? 'b' : 's');
        }

        return rewards.toString();
    }

    /**
     * Sine interpolation function
     * Performs sine interpolation on the given y values based on the normalized x value.
     *
     * @param x The normalized position in the range [0, 1]
     * @param y The array of y values to interpolate from
     * @return The interpolated value
     */
    public static double sineInterpolate(double x, double[] y) {
        double angle = x * Math.PI;  // Map x to range [0, π]
        double weight = 0.5 * (1 - Math.cos(angle));  // Weight based on sine function

        // Interpolate value using weights
        double index = weight * (y.length - 1);
        int lowerIndex = (int) Math.floor(index);
        int upperIndex = (int) Math.ceil(index);

        if (lowerIndex == upperIndex) {
            return y[lowerIndex];
        } else {
            double lowerValue = y[lowerIndex];
            double upperValue = y[upperIndex];
            double fractionalPart = index - lowerIndex;

            return lowerValue + fractionalPart * (upperValue - lowerValue);
        }
    }

}
