package negaDecimalPack;

public class NegadecimalNumber {

	/* Instance variables to hold decimal value and negadecimal representation.
	 * The convention is that all decimal values will be of type "int" and all
	 * negadecimal values will be represented by a String.
	 */
	int decimalValue = 0;
	String negadecimalValue;
	
	/* Constructs a negadecimal number based on negadecimal value input */
	public  NegadecimalNumber(String s){
		/* Check that user input is valid */
		if ("".equals(s)){
			throw new IllegalArgumentException("Cannot create a NDN from an empty string.");
		}
		for (char ch: s.toCharArray()){
			if (!Character.isDigit(ch)){
				throw new IllegalArgumentException("NDNs must be composed entirely of digits.");
			}
		}
		
		/* Remove leading zeroes */
		while (s.charAt(0) == '0' && !"0".equals(s)){
			s = s.substring(1, s.length());
		}
		
		negadecimalValue = s;

		/* Determine decimal value of this number, save it in instance variable */
		int sLength = s.length();
		int nD = Integer.parseInt(s);
		int i = 1;
		while (i <= sLength){
			int digit = nD % 10;
			nD = nD / 10;
			decimalValue = (int) (decimalValue + Math.pow(-1, i - 1) * (digit) * Math.pow(10, i - 1));
			i += 1;
		}
	}
	
	/* Constructs a negadecimal number based on a decimal input from the user */
	public NegadecimalNumber(int n){
		decimalValue = n;
		
		if (n == 0){
			negadecimalValue = "0";
			return;
		}
		
		/* Determines the negadecimal representation of this number and stores it in
		 * an instance variable.
		 * This code is paraphrased from the C code on the "Negadecimal Number" Wikipedia
		 * page.
		 */
		String result = "";
		while (n != 0){
			 int remainder = n % -10;
			 n = n / -10;
			 if (remainder < 0){
				 remainder += 10;
				 n += 1;
			 }
			 result = Integer.toString(remainder) + result;
		}
		negadecimalValue = result;
	}
	
	public NegadecimalNumber add(NegadecimalNumber ndn){
		return new NegadecimalNumber(this.decimalValue + ndn.decimalValue);
	}
	
	public NegadecimalNumber subtract(NegadecimalNumber ndn){
		return new NegadecimalNumber(this.decimalValue - ndn.decimalValue);
	}
	
	public NegadecimalNumber multiply(NegadecimalNumber ndn){
		return new NegadecimalNumber(this.decimalValue * ndn.decimalValue);
	}
	
	public NegadecimalNumber divide (NegadecimalNumber ndn){
		if (ndn.decimalValue == 0){
			throw new ArithmeticException("Impossible to divide by zero.");
		} else {
			return new NegadecimalNumber(this.decimalValue / ndn.decimalValue);
		}
	}
	
	public NegadecimalNumber remainder(NegadecimalNumber ndn){
		if (ndn.decimalValue == 0){
			throw new ArithmeticException("Impossible to divide by zero.");
		} else {
			return new NegadecimalNumber(this.decimalValue % ndn.decimalValue);
		}
	}	
	public NegadecimalNumber negate(){
		return new NegadecimalNumber(-this.decimalValue);
	}
	
	public int decimalValue(){
		return this.decimalValue;
	}
	
	public boolean equals(NegadecimalNumber ndn){
		return (this.decimalValue == ndn.decimalValue);
	}
	
	public String toString(){
		return this.negadecimalValue;
	}
}