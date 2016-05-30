package negaDecimalPack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NegadecimalNumberTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNegadecimalNumberString() {
		NegadecimalNumber ndn = new NegadecimalNumber("692");
		assertEquals(ndn.decimalValue, 512);
		NegadecimalNumber ndn2 = new NegadecimalNumber("19192");
		assertEquals(ndn2.decimalValue, 1012);
		NegadecimalNumber ndn3 = new NegadecimalNumber("71025");
		assertEquals(ndn3.decimalValue, 68985);
		try{
			NegadecimalNumber ndn4 = new NegadecimalNumber("");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Cannot create a NDN from an empty string.");
		}
		try{
			NegadecimalNumber ndn5 = new NegadecimalNumber("6a2");
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "NDNs must be composed entirely of digits.");
		}
		NegadecimalNumber ndn6 = new NegadecimalNumber("00000010");
		assertEquals(ndn6.decimalValue, -10);
	}
	
	@Test
	public void testNegadecimalNumberInt() {
		NegadecimalNumber ndn = new NegadecimalNumber(512);
		assertEquals(ndn.negadecimalValue, "692");
		NegadecimalNumber ndn2 = new NegadecimalNumber(1012);
		assertEquals(ndn2.negadecimalValue, "19192");
		NegadecimalNumber ndn3 = new NegadecimalNumber(68985);
		assertEquals(ndn3.negadecimalValue, "71025");
	}

	@Test
	public void testAdd() {
		NegadecimalNumber ndn1 = new NegadecimalNumber("500");
		NegadecimalNumber ndn2 = new NegadecimalNumber("692");
		assertEquals(ndn1.add(ndn2).negadecimalValue, "19192");
		
		NegadecimalNumber ndn3 = new NegadecimalNumber("71025");
		NegadecimalNumber ndn4 = new NegadecimalNumber("100");
		assertEquals(ndn3.add(ndn4).negadecimalValue, "71125");
		
		NegadecimalNumber ndn5 = new NegadecimalNumber(7304);
		NegadecimalNumber ndn6 = new NegadecimalNumber(568);
		assertEquals(ndn5.add(ndn6).decimalValue, 7872);
		
		NegadecimalNumber ndn7 = new NegadecimalNumber(356);
		NegadecimalNumber ndn8 = new NegadecimalNumber(9822);
		assertEquals(ndn7.add(ndn8).decimalValue, 10178);
	}

	@Test
	public void testSubtract() {
		NegadecimalNumber ndn1 = new NegadecimalNumber("19192");
		NegadecimalNumber ndn2 = new NegadecimalNumber("692");
		assertEquals(ndn1.subtract(ndn2).negadecimalValue, "500");
		
		NegadecimalNumber ndn3 = new NegadecimalNumber("71125");
		NegadecimalNumber ndn4 = new NegadecimalNumber("100");
		assertEquals(ndn3.subtract(ndn4).negadecimalValue, "71025");
		
		NegadecimalNumber ndn5 = new NegadecimalNumber(7872);
		NegadecimalNumber ndn6 = new NegadecimalNumber(568);
		assertEquals(ndn5.subtract(ndn6).decimalValue, 7304);
		
		NegadecimalNumber ndn7 = new NegadecimalNumber(10178);
		NegadecimalNumber ndn8 = new NegadecimalNumber(9822);
		assertEquals(ndn7.subtract(ndn8).decimalValue, 356);
	}

	@Test
	public void testMultiply() {
		NegadecimalNumber ndn1 = new NegadecimalNumber("2");
		NegadecimalNumber ndn2 = new NegadecimalNumber("5");
		assertEquals(ndn1.multiply(ndn2).negadecimalValue, "190");
		
		NegadecimalNumber ndn3 = new NegadecimalNumber("190");
		NegadecimalNumber ndn4 = new NegadecimalNumber("190");
		assertEquals(ndn3.multiply(ndn4).negadecimalValue, "100");
		
		NegadecimalNumber ndn5 = new NegadecimalNumber(7);
		NegadecimalNumber ndn6 = new NegadecimalNumber(8);
		assertEquals(ndn5.multiply(ndn6).decimalValue, 56);
		
		NegadecimalNumber ndn7 = new NegadecimalNumber(101);
		NegadecimalNumber ndn8 = new NegadecimalNumber(9);
		assertEquals(ndn7.multiply(ndn8).decimalValue, 909);
	}

	@Test
	public void testDivide() {
		NegadecimalNumber ndn1 = new NegadecimalNumber("00193");
		NegadecimalNumber ndn2 = new NegadecimalNumber("5");
		assertEquals(ndn1.divide(ndn2).negadecimalValue, "2");
		
		NegadecimalNumber ndn3 = new NegadecimalNumber("106");
		NegadecimalNumber ndn4 = new NegadecimalNumber("190");
		assertEquals(ndn3.divide(ndn4).negadecimalValue, "190");
		
		NegadecimalNumber ndn5 = new NegadecimalNumber(60);
		NegadecimalNumber ndn6 = new NegadecimalNumber(8);
		assertEquals(ndn5.divide(ndn6).decimalValue, 7);
		
		NegadecimalNumber ndn7 = new NegadecimalNumber(909);
		NegadecimalNumber ndn8 = new NegadecimalNumber(9);
		assertEquals(ndn7.divide(ndn8).decimalValue, 101);
		
		NegadecimalNumber ndn9 = new NegadecimalNumber(909);
		NegadecimalNumber ndn10 = new NegadecimalNumber(0);
		try{
			assertEquals(ndn9.divide(ndn10).decimalValue, 101);
		} catch (ArithmeticException e) {
			assertEquals(e.getMessage(), "Impossible to divide by zero.");
		}

	}

	@Test
	public void testRemainder() {
		NegadecimalNumber ndn1 = new NegadecimalNumber("193");
		NegadecimalNumber ndn2 = new NegadecimalNumber("5");
		assertEquals(ndn1.remainder(ndn2).negadecimalValue, "3");
		
		NegadecimalNumber ndn3 = new NegadecimalNumber("106");
		NegadecimalNumber ndn4 = new NegadecimalNumber("190");
		assertEquals(ndn3.remainder(ndn4).negadecimalValue, "6");
		
		NegadecimalNumber ndn5 = new NegadecimalNumber(60);
		NegadecimalNumber ndn6 = new NegadecimalNumber(8);
		assertEquals(ndn5.remainder(ndn6).decimalValue, 4);
		
		NegadecimalNumber ndn7 = new NegadecimalNumber(909);
		NegadecimalNumber ndn8 = new NegadecimalNumber(9);
		assertEquals(ndn7.remainder(ndn8).decimalValue, 0);
		
		NegadecimalNumber ndn9 = new NegadecimalNumber(909);
		NegadecimalNumber ndn10 = new NegadecimalNumber(0);
		try{
			assertEquals(ndn9.remainder(ndn10).decimalValue, 101);
		} catch (ArithmeticException e) {
			assertEquals(e.getMessage(), "Impossible to divide by zero.");
		}
	}

	@Test
	public void testNegate() {
		NegadecimalNumber ndn1 = new NegadecimalNumber("25");
		assertEquals(ndn1.negate().negadecimalValue, "195");
		NegadecimalNumber ndn2 = new NegadecimalNumber("1");
		assertEquals(ndn2.negate().negadecimalValue, "19");
		NegadecimalNumber ndn3 = new NegadecimalNumber(-68985);
		assertEquals(ndn3.negate().decimalValue, 68985);
		NegadecimalNumber ndn4 = new NegadecimalNumber(100);
		assertEquals(ndn4.negate().decimalValue, -100);
	}

	@Test
	public void testDecimalValue() {
		NegadecimalNumber ndn1 = new NegadecimalNumber("25");
		assertEquals(ndn1.decimalValue(), -15);
		NegadecimalNumber ndn2 = new NegadecimalNumber("190");
		assertEquals(ndn2.decimalValue(), 10);
		NegadecimalNumber ndn3 = new NegadecimalNumber("692");
		assertEquals(ndn3.decimalValue(), 512);
		NegadecimalNumber ndn4 = new NegadecimalNumber("70000");
		assertEquals(ndn4.decimalValue(), 70000);
	}

	@Test
	public void testEqualsNegadecimalNumber() {
		NegadecimalNumber ndn1 = new NegadecimalNumber("25");
		NegadecimalNumber ndn2 = new NegadecimalNumber(-15);
		assertTrue(ndn1.equals(ndn2));
		NegadecimalNumber ndn3 = new NegadecimalNumber(10);
		NegadecimalNumber ndn4 = new NegadecimalNumber("10");
		assertFalse(ndn3.equals(ndn4));
	}

	@Test
	public void testToString() {
		NegadecimalNumber ndn1 = new NegadecimalNumber(-15);
		assertEquals(ndn1.toString(), "25");
		NegadecimalNumber ndn2 = new NegadecimalNumber(10);
		assertEquals(ndn2.toString(), "190");
		NegadecimalNumber ndn3 = new NegadecimalNumber(512);
		assertEquals(ndn3.toString(), "692");
		NegadecimalNumber ndn4 = new NegadecimalNumber(70000);
		assertEquals(ndn4.toString(), "70000");
	}

}
