package ie.murph.activity;


public class DecimalConverter 
{

	public static void main(String[] args)
	{
		// Converting a whole number to binary string
		int num = 63;
		String binary1;
		binary1 = convertToBinaryString(num);
		System.out.println("Binary of this integer: " + num + " is " + binary1);
		
		int integer1 = convert6bitBinaryToInteger(binary1);
		System.out.println("Integer of this binary: " + binary1 + " is " + integer1);
		
		// Converting a decimal number to binary string
		double num2 = 23.375;
		String binary2 = convertDecimalToBinaryString(num2);
		System.out.println("Binary of this decimal: " + num2 + " is " + binary2);
		
		double decimal1 = convertBinaryToDecimal(binary2);
		System.out.println("Decimal of this binary: " + binary2 + " is " + decimal1);
		
		double octal1 = 437.021;
		double decimal2 = convertOctalToDecimal(octal1);
		System.out.println("Decimal of this octal: " + octal1 + " is " + decimal2);
		double octal2 = 237.61;
		double decimal3 = convertOctalToDecimal(octal2);
		System.out.println("Decimal of this octal: " + octal2 + " is " + decimal3);
		
		String hex = "23A.4D";
		double decimal4 = convertHexadecimalToDecimal(hex);
		System.out.println("Decimal of this hexidecimal: " + hex + " is " + decimal4);
	}
	
	
	
//******************************************************************IntegerToBinary*******************************************************************************	
	
	
	
	// Method to find the (6 bit) binary equivalent of an integer - or change the length of loops to whatever size bit you need
	// A number between 0-63
		private static String convertToBinaryString(int numValue) 
		{
			String binary = "";
			for(int x = 0; x < 6; x++)
			{
				//If the value has a remainder print '1' bit
				if(numValue % 2 == 1)
				{
					binary = "1" + binary;
				}
				//If the value has no remainder print '0' bit
				if(numValue % 2 == 0)
				{
					binary = "0" + binary;
				}
				numValue = numValue/2;
			}
			return binary;
		}// END OF..
		
		
		
//******************************************************************BinaryToInteger*******************************************************************************
		
		
		
		//Converting my binary bits back to Integer
		public static int convertBinary7bitToInteger(String binary)
		{
			int x = 128; // for 8 bit we use 128      (i.e. 7 bit = 128)
			int result = 0;
			for(int y = 0; y < 8; y++)
			{
				//Converting the binary string to a character so we can check all the bits
				char character = binary.charAt(y);
				// Single quotations needed when using 'char'
				if(character == '1')
				{
					// Moving threw the bits calculating the necessary steps
					result = result + (x*1);
				}
				// Half each time..
				x=x/2;
			}
			return result;
		}// END OF..	
		
	    //Converting my binary bits back to Integer
		public static int convert6bitBinaryToInteger(String binary)
		{
			int z = 32; // for 6 bit we use 32 etc.   (i.e. 5 bit = 32)
			int result = 0;
			for(int y = 0; y < 6; y++)
			{
				char character = binary.charAt(y);
				// Single quotations needed when using 'char'
				if(character == '1')
				{
					result = result + (z*1);
				}
				z=z/2;
			}
			return result;
		}// END OF..
		
		//Converting my binary bits back to Integer
		public static int convert5bitBinaryToInteger(String binary)
		{
			int a = 16; // for 5 bit we use 32 etc.   (i.e. 4 bit = 32)
			int result = 0;
			for(int y = 0; y < 5; y++)
			{
				char character = binary.charAt(y);
				// Single quotations needed when using 'char'
				if(character == '1')
				{
					result = result + (a*1);
				}
				a=a/2;
			}
			return result;
		}// END OF..
	


//******************************************************************DecimalToBinary*******************************************************************************



        //Method to find the (6 bit) binary equivalent of an integer - or change the length of loops to whatever size bit you need
		private static String convertDecimalToBinaryString(double numValue) 
		{
			String binary1 = "";
			String binary2 = "";
			String[] numbers = splittingADecimal(numValue);
			String integerPartStr = numbers[0];
			String decimalPartStr = numbers[1];
			int integerPartInt = Integer.parseInt(integerPartStr);
			double decimalPart = numValue-integerPartInt;
			int lenghtOfDecimal = decimalPartStr.length();
			//So, 5 is the number of bits that I want the integer part of my binary string, to be..
			for(int x = 0; x < 5; x++)
			{
				//If the value has a remainder print out '1'
				if(integerPartInt % 2 == 1)
				{
					binary1 = "1" + binary1;
				}
				if(integerPartInt % 2 == 0)
				{
					binary1 = "0" + binary1;
				}
				integerPartInt = integerPartInt/2;
			}
			
			//Now trying the decimal part of the double
			double fraction = 0.5;
			for(int x = 0; x < lenghtOfDecimal; x++)
			{
				//If the value is a minus number we use the 0 bit
				if(decimalPart-fraction < 0.0)
				{
					// because we read the decimal conversion backwards, I added the bit last to the already created string
					binary2 =  binary2 + "0";
				}
				else
				{
					binary2 =  binary2 + "1";
					//The value is still plus number after subtraction, so we use the 1bit and loop again with next test with new value
					decimalPart = decimalPart-fraction;
				}
				fraction = fraction/2;
			}
			String binaryString = binary1 + "." + binary2;
			return binaryString;
		   }// END OF..

		// Method to split a decimal point number into two separate parts
		public static String[] splittingADecimal(double num)
		{
			//Converting a double to a string
			String numberAsText = Double.toString(num);
			int decimalPosition = 0;
			for(int i = 0; i < numberAsText.length(); i++)
			{
				// Getting the position of the decimal
				if(numberAsText.charAt(i) == '.')
				{
					decimalPosition = i;
				}
			}
			// Splitting the decimal into two separate parts to do calculations 
			String integerPart = numberAsText.substring(0, decimalPosition);
			String decimalPart = numberAsText.substring(decimalPosition+1);
			String[] split = {integerPart, decimalPart};
			return split;
		}
		
		// Method to split a String, with a decimal point in the middle, into two separate parts
		public static String[] splittingAString(String num)
		{
			//Converting a double to a string
			String numberAsText = num;
			int decimalPosition = 0;
			for(int i = 0; i < numberAsText.length(); i++)
			{
				// Getting the position of the decimal
				if(numberAsText.charAt(i) == '.')
				{
					decimalPosition = i;
				}
			}
			// Splitting the decimal into two separate parts to do calculations 
			String integerPart = numberAsText.substring(0, decimalPosition);
			String decimalPart = numberAsText.substring(decimalPosition+1);
			String[] split = {integerPart, decimalPart};
			return split;
		}
		
		

//******************************************************************BinaryToDecimal*******************************************************************************

		
		
		//Converting my binary bits back to Decimal
		public static double convertBinaryToDecimal(String binary)
		{
			double result = 0.0;
			double binaryDou = Double.parseDouble(binary);
			String[] numbers = splittingADecimal(binaryDou);
			String integerPartStr = numbers[0];
			String decimalPartStr = numbers[1];
			int lengthOfDecimal = decimalPartStr.length();
			int lengthOfInteger = integerPartStr.length();
			
			// For the decimal right side part of the double number
			double decimalTotal = 0;
			// Putting the Decimal part (right side of string) String into a character array to be manipulated
			char[] decimalCharArray = decimalPartStr.toCharArray();
			int bitDecimal = 0;
			// I use 0.5 because it is equal to 2 to the power of -1 and so on 2pow-2, 2pow-3 etc.
			double negativePower = 0.5;
			for(int x = 0; x < lengthOfDecimal; x++)
			{
				//Converting the character bits to integers each loop..
				bitDecimal = Integer.parseInt(decimalCharArray[x] + "");
				//Doing the necessary calculations for the decimal part
				decimalTotal = decimalTotal + (bitDecimal * negativePower);
				// Once calculation is done, move down to next negative power and so on..
				negativePower = negativePower/2;
			}			
			// Now for the integer left side part of the double number
			double integerTotal = 0;
			// Putting the Integer part (left side of string) String into a character array to be manipulated or checked
			char[] integerCharArray = integerPartStr.toCharArray();
			int bitInteger = 0;
			// I use 1 because it is equal to 2 to the power of -1 and so on 2pow-2, 2pow-3
			double positivePower = 1;
			int xxx = lengthOfInteger-1;
			// I am looping backwards threw the string so the correct calculation is done on the correct binary bit
			// Also, it allows you to use any length binary conversion you want instead of having a set length binary string.
			for(int x = xxx; x > -1; x--)
			{
				bitInteger = Integer.parseInt(integerCharArray[x] + "");
				integerTotal = integerTotal + (bitInteger * positivePower);
				// Once calculation is done, move up to next positive power and so on..
				positivePower = positivePower*2;
			}
			
			// Now for the conversion and join the two numbers together to get the entire double result
			result = convertStringToDouble(integerTotal, decimalTotal);
			
			return result;
		}// END OF..	
		
		// Now for the conversion and join the two numbers together to get the entire double result
		private static double convertStringToDouble(double integerPart, double decimalPart)
		{
			double result = 0.0;
			String integerAsText = Double.toString(integerPart);
			String decimalAsText = Double.toString(decimalPart);
			
			String intSub = integerAsText.substring(0, integerAsText.indexOf("."));
			String decSub = decimalAsText.substring(decimalAsText.indexOf(".")+1);
			
			String ResultStr = intSub + "." + decSub;
			result = Double.parseDouble(ResultStr);
			return result;
		}
		
//******************************************************************OctalToDecimal*******************************************************************************
		
		
		
		 //Method to return the decimal number equivalent of an octal decimal number (X8)
		private static double convertOctalToDecimal(double numValue) 
		{
			// Take the Octal decimal a split it into integer side and decimal side
			String[] numbers = splittingADecimal(numValue);
			String integerSideStr = numbers[0];
			String decimalSideStr = numbers[1];
			int lengthOfInt = integerSideStr.length();
			int lengthOfDec = decimalSideStr.length();
			
			// Putting the Integer part (left side of string) String into a character array to be manipulated
			char[] integerCharArray = integerSideStr.toCharArray();
			int eachIntergerNum = 0;
			int lastPositionOfString = lengthOfInt-1;
			double integerTotal = 0;
			double positivePower = 1;
			//For the left side number using positive power numbers
			for(int x = lastPositionOfString; x > -1; x--)
			{
				eachIntergerNum = Integer.parseInt(integerCharArray[x] + "");
				integerTotal = integerTotal + (eachIntergerNum * positivePower);
				// Once calculation is done, move up to next positive power and so on..
				positivePower = positivePower*8;
			}
			//For the right side number using negative power numbers
			char[] decimalCharArray = decimalSideStr.toCharArray();
			int eachDecimalNum = 0;
			double decimalTotal = 0;
			double negativePower = 0.125; // 8 to the power of minus 1
			for(int y = 0; y < lengthOfDec; y++)
			{
				eachDecimalNum = Integer.parseInt(decimalCharArray[y] + "");
				decimalTotal = decimalTotal + (eachDecimalNum * negativePower);
				negativePower = negativePower/8;
			}
			// Now for the conversion and join the two numbers together to get the entire double result
			double result = convertStringToDouble(integerTotal, decimalTotal);
			return result;
		}// END OF..
		
		
		
//******************************************************************HexadecimalDecimalToBinary*************************************************************************
		

		
		 //Method to return the decimal number equivalent of an Hexadecimal number (X16)
		private static double convertHexadecimalToDecimal(String numValue) 
		{
			String[] hexStrings = splittingAString(numValue);
			String leftPartHexStr = hexStrings[0];
			String rightPartHexStr = hexStrings[1];
			int lengthOfLeftHex = leftPartHexStr.length();
			int lengthOfRightHex = rightPartHexStr.length();
			
			// Putting the Integer part (left side of string) String into a character array to be manipulated
			char[] integerCharArray = leftPartHexStr.toCharArray();
			char checkThisChar1;
			int eachIntergerNum = 0;
			int lastPositionOfIntString = lengthOfLeftHex-1;
			double integerTotal = 0;
			double positivePower = 1;
			//For the left side number using positive power numbers
			for(int x = lastPositionOfIntString; x > -1; x--)
			{
				checkThisChar1 = integerCharArray[x];
				eachIntergerNum = hexToDecimal(checkThisChar1);
				integerTotal = integerTotal + (eachIntergerNum * positivePower);
				// Once calculation is done, move up to next positive power and so on..
				positivePower = positivePower*16;
			}
			
			// Putting the Integer part (left side of string) String into a character array to be manipulated
			char[] decimalCharArray = rightPartHexStr.toCharArray();
			char checkThisChar2;
			int eachDecimalNum = 0;
			double decimalTotal = 0;
			double negativePower = 0.0625; // 16 to the power of minus 1
			//For the left side number using positive power numbers
			for(int y = 0; y < lengthOfRightHex; y++)
			{
				checkThisChar2 = decimalCharArray[y];
				eachDecimalNum = hexToDecimal(checkThisChar2);
				decimalTotal = decimalTotal + (eachDecimalNum * negativePower);
				// Once calculation is done, move down to next negative 16 power and so on..
				negativePower = negativePower/16;
			}
			double total = integerTotal+decimalTotal;
			return total;
		}// END OF..
		
		private static int hexToDecimal(char charToCheck)
		{
			if(charToCheck == 'A')
			{
				return 10;
			}
			else if(charToCheck == 'B')
			{
				return 11;
			}
			else if(charToCheck == 'C')
			{
				return 12;
			}
			else if(charToCheck == 'D')
			{
				return 13;
			}
			else if(charToCheck == 'E')
			{
				return 14;
			}
			else if(charToCheck == 'F')
			{
				return 15;
			}
			// This return was giving back the ASCII number equivalent of the integer passed to it
			// So, when subtracted by 48, we get the correct number passed
			return charToCheck - 48;
			
		}
		
		
}// END OF CLASS..
