package jom.com.softserve.s3.task3;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task3Test {

	@DisplayName("Check the result of work of 'addAtoB' method")
	@Test
	void addAtoBTest() {
		assertTrue(Operation.addAtoB(7, 2) == 9);
	}

	@DisplayName("Check the result of work of 'subtractBfromA' method")
	@Test
	void subtractBfromATest() {
		assertTrue(Operation.subtractBfromA(8, 3) == 5);
	}

	@DisplayName("Check the result of work of 'multiplyAbyB' method")
	@Test
	void multiplyAbyBTest() {
		assertTrue(Operation.multiplyAbyB(4, 7) == 28);
	}

	@DisplayName("Check the result of work of 'divideAbyB' method")
	@Test
	void divideAbyBTest() {
		assertTrue(Operation.divideAbyB(9, 2) == 4);
	}
}
