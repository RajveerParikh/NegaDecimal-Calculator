package negaDecimalPack;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

public class NegadecimalCalculator {

	/* Global variables to hold current display number and calculator status */
	NegadecimalNumber currentNum = new NegadecimalNumber(0);
	boolean inErrorState = false;
	
	public static void main(String[] args) {
		NegadecimalCalculator calculator = new NegadecimalCalculator();
		calculator.REPL();
	}

	/* Updates display number to what user just entered */
	public String createNewDisplayNum(String newString){
		try{
			currentNum = new NegadecimalNumber(newString);
			return currentNum.toString();
		} catch (IllegalArgumentException e){
			inErrorState = true;
			return "Error";
		}
	}
	
	/* Creates a negadecimal number from a decimal user input */
	public String makeNdnFromDecimal(String decString){
		try{
			int decInt = Integer.parseInt(decString);
			currentNum = new NegadecimalNumber(decInt);
			return currentNum.toString();
		} catch (IllegalArgumentException e){
			inErrorState = true;
			return "Error";
		}
	}
	
	/* Handles the addition, subtraction, multiplication, etc. for evaluate method */
	public String performArithmetic(NegadecimalNumber operandNdn, char op) {
		if (op == '+'){
			currentNum = currentNum.add(operandNdn);
			return currentNum.toString();
		}
		if (op == '-'){
			currentNum = currentNum.subtract(operandNdn);
			return currentNum.toString();
		}
		if (op == '*'){
			currentNum = currentNum.multiply(operandNdn);
			return currentNum.toString();
		}
		if (op == '/'){
			currentNum = currentNum.divide(operandNdn);
			return currentNum.toString();
		} else {
			currentNum = currentNum.remainder(operandNdn);
			return currentNum.toString();
		}
	}
	
	/* Takes in user input, interprets it, performs operation, returns string to display */
	public String evaluate(String s){
		String newString = s.replace(" ", "");
		
		if (newString.length() == 0){
			inErrorState = true;
			return "Error";
		}
		
		/* Handling the case in which the user has previously caused an "Error"
		 * and they have not yet 'cleared'.
		 */
		if (inErrorState && (!"c".equals(newString) && !"clear".equals(newString))){
			return "Error (you must 'clear' before continuing)";
		} else if (inErrorState && ("c".equals(newString) || "clear".equals(newString))){
			inErrorState = false;
			currentNum = new NegadecimalNumber("0");
			return currentNum.toString();
		}
		
		if (Character.isDigit(newString.charAt(0))){
			return createNewDisplayNum(newString);
		}
		if(newString.charAt(0) == '~' && newString.length() == 1){
			currentNum = currentNum.negate();
			return currentNum.toString();
		}
		if(newString.charAt(0) == '?' && newString.length() == 1){
			String result = currentNum.toString() + " (decimal " + currentNum.decimalValue() + ")";
			return result;
		}
		if ("c".equals(newString) || "clear".equals(newString)){
			currentNum = new NegadecimalNumber(0);
			return currentNum.toString();
		}
		if (newString.charAt(0) == 'd'){
			if (Character.isDigit(newString.charAt(1))){
				String decString = newString.substring(1, newString.length());
				return makeNdnFromDecimal(decString);
			} else if (newString.length() >= 7 && "decimal".equals(newString.substring(0,7))) {
				String decString = newString.substring(7, newString.length());
				return makeNdnFromDecimal(decString);
			}
		}
		
		String operand = newString.substring(1, newString.length());
		NegadecimalNumber operandNdn;
		try{
			operandNdn = new NegadecimalNumber(operand);
		} catch (IllegalArgumentException e){
			inErrorState = true;
			return "Error";
		}
		
		char op = newString.charAt(0);
		
		if (op == '+' || op == '-' || op == '*' || op == '/' || op == '%' ){
			return performArithmetic(operandNdn, op);
		}
		
		/* If program reaches this point, the user must have entered an illegal input */
		inErrorState = true;
		return "Error";
	}

	/* Creates a calculator display that user can input commands into */
	public void REPL(){
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("Compute: ");
		String input = scan.nextLine();
		while (!"q".equals(input) && !"quit".equals(input)){
			String Output = evaluate(input);
			System.out.println(Output);
			System.out.print("Compute: ");
			input = scan.nextLine();
		}
		System.out.println("Done.");
	}
}