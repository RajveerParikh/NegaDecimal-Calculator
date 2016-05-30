package negaDecimalPack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NegadecimalCalculatorTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testCreateNewDisplayNum() {
		NegadecimalCalculator calc = new NegadecimalCalculator();
		assertEquals(calc.createNewDisplayNum("692"), "692");
		assertEquals(calc.currentNum.toString(), "692");
		assertEquals(calc.createNewDisplayNum("1000"), "1000");
		assertEquals(calc.currentNum.toString(), "1000");
		assertEquals(calc.createNewDisplayNum(""), "Error");
		assertEquals(calc.createNewDisplayNum("56t"), "Error");
		assertEquals(calc.createNewDisplayNum("00012"), "12");
	}

	@Test
	public void testMakeNdnFromDecimal() {
		NegadecimalCalculator calc = new NegadecimalCalculator();
		assertEquals(calc.makeNdnFromDecimal("5"), "5");
		assertEquals(calc.currentNum.toString(), "5");
		assertEquals(calc.makeNdnFromDecimal("15"), "195");
		assertEquals(calc.makeNdnFromDecimal("56y6"), "Error");
		assertEquals(calc.makeNdnFromDecimal(""), "Error");
		assertEquals(calc.makeNdnFromDecimal("g"), "Error");
	}

	@Test
	public void testPerformArithmetic() {
		NegadecimalCalculator calc = new NegadecimalCalculator();
		calc.currentNum = new NegadecimalNumber(35);
		NegadecimalNumber operandNdn = new NegadecimalNumber(78);
		assertEquals(calc.performArithmetic(operandNdn, '+'), "293");
		calc.currentNum = new NegadecimalNumber(35);
		NegadecimalNumber operandNdn1 = new NegadecimalNumber(78);
		assertEquals(calc.performArithmetic(operandNdn1, '-'), "57");
		calc.currentNum = new NegadecimalNumber(35);
		NegadecimalNumber operandNdn2 = new NegadecimalNumber(78);
		assertEquals(calc.performArithmetic(operandNdn2, '*'), "18870");
		calc.currentNum = new NegadecimalNumber(35);
		NegadecimalNumber operandNdn3 = new NegadecimalNumber(78);
		assertEquals(calc.performArithmetic(operandNdn3, '/'), "0");
		calc.currentNum = new NegadecimalNumber(35);
		NegadecimalNumber operandNdn4 = new NegadecimalNumber(78);
		assertEquals(calc.performArithmetic(operandNdn4, '%'), "175");
		calc.currentNum = new NegadecimalNumber(35);
		NegadecimalNumber operandNdn5 = new NegadecimalNumber(-35);
		assertEquals(calc.performArithmetic(operandNdn5, '+'), "0");
	}

	@Test
	public void testEvaluate() {
		NegadecimalCalculator calc = new NegadecimalCalculator();
		calc.currentNum = new NegadecimalNumber(5);
		assertEquals(calc.evaluate("*2"), "190");
		calc.currentNum = new NegadecimalNumber(5);
		assertEquals(calc.evaluate("-6"), "19");
		calc.currentNum = new NegadecimalNumber(100);
		assertEquals(calc.evaluate("/ 00190"), "190");
		calc.currentNum = new NegadecimalNumber(5);
		assertEquals(calc.evaluate("%2   "), "1");
		calc.currentNum = new NegadecimalNumber(15);
		assertEquals(calc.evaluate("?"), "195 (decimal 15)");
		calc.currentNum = new NegadecimalNumber(5);
		assertEquals(calc.evaluate("~"), "15");
		assertEquals(calc.evaluate("c"), "0");
		assertEquals(calc.evaluate("decimal 78"), "138");
		assertEquals(calc.evaluate("d 77"), "137");
		assertEquals(calc.evaluate(""), "Error");
		assertEquals(calc.evaluate("hi"), "Error (you must 'clear' before continuing)");
		assertEquals(calc.evaluate("clear"), "0");
	}
}
