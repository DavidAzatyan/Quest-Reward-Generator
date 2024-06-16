# **Quest Reward Generator**

## 1. **Introduction**

The Quest Reward Generator is a Java application designed to create a sequence of quest rewards
that mimic a given distribution pattern.
The rewards are either a bronze chest (b) or a silver chest (s).
The distribution of rewards transitions smoothly from predominantly bronze chests at the beginning to
predominantly silver chests towards the end.
This project utilizes sine interpolation to achieve the desired
distribution pattern.

## 2. **Features**

Generates a string of rewards (b and s) for a specified number of quests.
Smoothly transitions from b to s based on a predefined distribution pattern.
Utilizes sine interpolation to achieve the desired smooth transition.

## 3. **Example**

For N = 1000, the program will output a string of 1000 characters, each either b or s, reflecting the distribution
pattern.

### Sample Output

`Generated rewards for N = 1000: bbbbbbbbbbb...sssss`

## 4. **Code Structure**

### Main Class

`QuestRewardGenerator`

This is the main class that contains the entry point of the application.

* #### Methods:

    * `public static void main(String[] args)`: The main method to execute the program.
    * `public static String generateRewards(int N, String distribution)`: Generates a reward string of length N based on
      the
      given distribution pattern.
    * `public static double sineInterpolate(double x, double[] y)`: Performs sine interpolation on the given y values
      based
      on
      the normalized x value.

### Method Descriptions

* ```java public static void main(String[] args)```
  The main method to demonstrate the generation of reward strings.

  **Behavior**:
  Sets the example value for N (1000 in this case).
  Defines the initial distribution string.
  Calls generateRewards to produce a new reward string based on the given N.
  Prints the generated reward string.

* `public static String generateRewards(int N, String distribution)`
  Generates a reward string of length N based on interpolation of the given distribution pattern.

  **Parameters**:
  int N: The number of quests.
  String distribution: The initial distribution string pattern.

  **Returns**:
  String: A string of rewards (b or s) of length N.


* ```public static double sineInterpolate(double x, double[] y)```
  Performs sine interpolation on the given y values based on the normalized x value.

  **Parameters**:
  double x: The normalized position in the range [0, 1].
  double[] y: The array of y values to interpolate from.

  **Returns**:
  double: The interpolated value.


## 5. **Sine Interpolation**

Sine interpolation is used to create a smooth transition between the values in the distribution array. It ensures that the probability of receiving a bronze chest (b) decreases gradually while the probability of receiving a silver chest (s) increases towards the end of the quest sequence. This is achieved by mapping the normalized quest position to a sine function, which provides a smooth weighting factor.

### Sine Interpolation Process

1. Normalization:
  
    * The position x is normalized to the range [0, 1].
2. Angle Calculation:

    * The normalized position x is mapped to an angle in the range [0, π] using x * Math.PI.
3. Weight Calculation:

    * A weight is calculated using the sine function: weight = 0.5 * (1 - Math.cos(angle)).
    Interpolation:

    * The weight is used to determine the indices in the y array.
    Linear interpolation is performed between the values at these indices to obtain the final interpolated value.

## 6. **Detailed Workflow**

1. Initialize and Parse Distribution:

    The given distribution string is converted into an array of yValues where 'b' is mapped to 1.0 and 's' to 0.0.
2. Interpolation:

    * For each quest position from 0 to N-1, the normalized position x is computed.
    * The sineInterpolate method calculates the interpolated value based on the sine function.
3. Generate Reward String:

    * For each interpolated value, 'b' is appended to the reward string if the value is greater than 0.5, otherwise 's' is appended.
