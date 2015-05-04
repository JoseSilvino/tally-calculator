package ie.murph.activity;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Calculator 
{
	private static final Scanner M_SCANNER = new Scanner(System.in);
	private static int m_amountOfNumbers;
	public static void main(String[] args)
	{
		System.out.println("How many numbers do you want to enter!");
		boolean isValid = true;
		
		// Validity Check to make sure the user enters an integer value.
		while(isValid)
		{
			try
			{
				if(M_SCANNER.hasNextInt())
				{
					m_amountOfNumbers = M_SCANNER.nextInt();
					isValid = false;
				}
				else 
				{
					M_SCANNER.next();
					System.out.println("Invalid entry! \nPlease try again with an Integer!");
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("Number Error: " + e);
			}
		}
		
		// User choosing their numbers.
		double[] numbers = new double[m_amountOfNumbers];
		for(int x = 0; x < numbers.length; x++)
		{
			System.out.println("Choose your numbers, please!");
			numbers[x] = M_SCANNER.nextDouble();
		}
		
		// Adding all the numbers together!
        double total = 0;
        for(int y = 0; y < numbers.length; y++)
        {
        	total = total + numbers[y];
        }
        System.out.println("Total is: " + total);
        System.out.println("");
        // Calculating the average
        double average = total/m_amountOfNumbers;
        double mathAverage = (double) Math.round(average * 1000) / 1000;
        System.out.println("Average/Mean: " + average);
        System.out.println("Average/Mean: " + mathAverage);
        
        // Getting the maximum & minimum values from array.
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for(int z = 0; z < m_amountOfNumbers; z++)
        {
        	if(max < numbers[z]) max = numbers[z];
        	if(min > numbers[z]) min = numbers[z];
        }
        System.out.println("");
        System.out.println("MAXIMUM: " + max);
        System.out.println("MINIMUM: " + min);
        
        System.out.println("");
        int factorialResult = factorialOf(m_amountOfNumbers);
        System.out.println("Factorial: " + factorialResult);
        
        System.out.println("");
        double median = medianOf(numbers);
        System.out.println("Median: " + median);
        
        System.out.println("");
        double mode = modeOf(numbers);
        System.out.println("Mode: " + mode);
        
        System.out.println("");
        double variance = varianceOf(numbers);
        double mathVariance = (double) Math.round(variance * 1000) / 1000;
        System.out.println("Variance: " + variance);
        System.out.println("Variance 3 Decimal Places: " + mathVariance);
        
        System.out.println("");
        double varianceForDeviation = variance;
        double deviation = standardDeviationOf(varianceForDeviation);
        System.out.println("Standard Deviation is: " + deviation);
        
        System.out.println("");
        convertIntegerToBinary(m_amountOfNumbers);
        
        System.out.println("");
        System.out.println("Unsorted Array.. ");
        displayArray(numbers);
        
        System.out.println("");
        System.out.println("Sorted Array.. ");
        Arrays.sort(numbers);
        displayArray(numbers);
        
        System.out.println("");
        // Calling method to repeat the process or not
        askToContinue();
	}
	
	// Method to ask the user if they want to try again.
	public static void askToContinue()
	{
		System.out.println("Do you want to run it again: (y/n)");
		
		String s_continue = M_SCANNER.nextLine();
		if(s_continue.equalsIgnoreCase("y") || s_continue.equalsIgnoreCase("yes"))
		{
			// Reseting and Starting again..
			main(new String[0]);
		}
		else if (s_continue.equalsIgnoreCase("n") || s_continue.equalsIgnoreCase("no"))
		{
			System.out.println("Good bye!");
			System.exit(0);
		}
		else
			askToContinue();
	}
	
	// Method just for printing out the elements of the array.
	public static void displayArray(double[] numbers)
	{
		for(int x = 0; x < numbers.length; x++)
		{
			System.out.println(numbers[x]);
		}
		System.out.println("");
	}
	
	//Recursive method to show the factorial of a set of numbers
	public static int factorialOf(int number)
	{
		if(number == 0)
		{
			//No calculations to be done so, send back same number
			return number;
		}
		else if(number == 1)
		{
			//No calculations to be done so, send back same number
			return number;
		}
		else
		{
			// So, this is calling the same method again
			// If number is 5 - Calculation is: 5 * (5 * (5-1)) ==> 5 * (5 * 4) ==> 5 * 20 = Answer: 120
			return number * factorialOf(number-1);
		}
	}
	
	// Basically find the average of all the numbers - Add all number
	public static double meanOf(double[] dataSet)
	{
		 double total = 0;
	        for(int y = 0; y < m_amountOfNumbers; y++)
	        {
	        	// Add all numbers
	        	total = total + dataSet[y];
	        }
	        // Calculating the average by dividing by the amount of numbers
	        double average = total/m_amountOfNumbers;
	        return average;
	}
	
	// Method to calculate the average median
	public static double medianOf(double[] numbers)
	{
		double mean = 0;
		double[] newArr = new double[m_amountOfNumbers];
		// Copying from the original array to a new array
		System.arraycopy(numbers, 0, newArr, 0, m_amountOfNumbers);
			// If the number is even there is no middle number, so..
			if(m_amountOfNumbers % 2 == 0)
			{
				// Get the position of the two most central numbers
				int pos1 = (m_amountOfNumbers/2) - 1;
				int pos2 = m_amountOfNumbers/2;
				
				// Get the numbers using the position.
				double num1 = newArr[pos1];
				double num2 = newArr[pos2];
				
				// We then calculate the two most middle numbers
				double total = num1 + num2;
				mean = total/2;
			}
			else
			{
				//Get the central number
			   mean = numbers[m_amountOfNumbers / 2];
			}
		return mean;
	}
	
	// Method to find the mode of a data set i.e. the most popular number in that data set
	public static double modeOf(double[] dataSetNumbers)
	{
		double[] mostPopularNum = new double[m_amountOfNumbers];
		Random randomNum = new Random();
		double maxNumMode = 0;
		double num1 = 0;
		double num2 = 0;
		int count;
		int maxCount = 1;
		// First loop to get the first number and check it against the rest of the numbers to see if they are the same number.
		for(int x = 0; x < m_amountOfNumbers; x++)
		{
			//Number to check each loop
			num1 = dataSetNumbers[x];
			
			//Put count back to zero each check
			count = 0;
			// Might have a bug here, starting the count at one I am missing one CHECK..
			for(int y = 1; y < m_amountOfNumbers; y++)
			{
				num2 = dataSetNumbers[y];
				// If the numbers are the same count it
				if(num1 == num2)
				{
					count++;
				}
			}
			// Store the largest data set count in the max count variable each time
			if(count > maxCount )		
			{
				maxCount = count;
				mostPopularNum[x] = dataSetNumbers[x]; // CHECK to see if there is a need for this..
				maxNumMode = mostPopularNum[x];
			}
		}
		//Just a check to make sure we choose a number and don't leave it blank or zero to give an error
		if(maxNumMode == 0)
		{
			int fitness = randomNum.nextInt(m_amountOfNumbers);
			maxNumMode = dataSetNumbers[fitness];
		}
		return maxNumMode;
	}
	
	// Method to find the variance of a data set - Variance measures how far a set of numbers is spread out 
	public static double varianceOf(double[] dataSet)
	{
		double mean = meanOf(dataSet);
		double variance = 0;
		for(int x = 0; x < m_amountOfNumbers; x++)
		{
			variance = (dataSet[x] - mean) * 2;
		}
		variance = variance / m_amountOfNumbers;
		return variance;
	}
	//Work out the Square Root of the Variance
	public static double standardDeviationOf(double variance)
	{
		String var = String.valueOf(645);
		
		StringBuilder answer = squareRootOf(var);
		System.out.println("XZXZZXZXZX: " + answer);
		
		double deviation = 0;
		return deviation;
	}
	
		// Finding the square root of a number
		public static StringBuilder squareRootOf(String variance)
		{
			//Converting the string to string builder object because easy to alter or manipulate binary bits
			StringBuilder varianceBuilder = new StringBuilder(variance);
			StringBuilder newbieInStringFormat = new StringBuilder();
			System.out.println("varianceBuilder" + varianceBuilder);
			int lenghtOfVarianceNumber = variance.length()-1;
			String varianceSubString = variance.substring(1, 3);
			System.out.println("lenghtOfVarianceNumber " + lenghtOfVarianceNumber);
			
			//
			int newbies, powerValue, number, power;
			char charNumber;
			for(int position = 0; position < 1; position++)
			{
				//getting the position of the first number in the number to be square rooted
			    charNumber = varianceBuilder.charAt(position);
			    //casting it from character to integer value
			    number = Character.getNumericValue(charNumber);
			    //working backwards in power from 4 to zero to find a number lower or equal to the number above
			    power=4;
			    for(int x=0; x < 5; x++)
			    {
			    	//get the power of 2 until it is equal or less than number
				    powerValue = (int) Math.pow(power, 2);
						if(powerValue <= number)
						{
							newbies = number - powerValue;
							Object[] part2 = getSecondPhaseOfSquareRoot(varianceSubString, newbies*2, lenghtOfVarianceNumber*100);
							newbieInStringFormat.append(newbies);
							break;
						}
						power--;
			    }
			}
			
			return newbieInStringFormat;
		}// END OF..
	
	private static Object[] getSecondPhaseOfSquareRoot(String varianceSubString, int i, int j) 
	{
		int number = Integer.parseInt(varianceSubString);
		int result = 0, multiple = 10;
		int total = number+j;
		int limit = (i * 10) + 9;
		for(int x = limit; x>=limit-9; x--)
		{
			System.out.println("result: " + result + " limit: " + limit + " multiple: " + multiple);
			if((limit*multiple) <= number)
			{
				result = limit - (i*10);
				System.out.println("result: " + result);
				break;
			}
			multiple--;
			limit--;
		}
		System.out.println("getSecondPhaseOfSquareRoot method: " + total + " " + i);
		return null;
	}

	// Method to find the 8 bit binary equivalent of an integer
	private static void convertIntegerToBinary(int numValue) 
	{
		String s = "";
		for(int x = 0; x < 8; x++)
		{
			//If the value has a remainder use the '1' binary bit
			//Meaning if the value doesn't divide into it, use '1' binary bit, if it has a remainder
			if(numValue % 2 == 1)
			{
				//As the binary answer is written backwards I do the same here by adding the bit first
				s = "1" + s;
			}
			if(numValue % 2 == 0)
			{
				s = "0" + s;
			}
			//Half the value converted each time
			numValue = numValue/2;
			System.out.println("Each step in the 8bit binary loop to: " + x + "). " + s);
		}
		System.out.println("Binary equivalent of " + m_amountOfNumbers + " is " + s);
	}
	
}
