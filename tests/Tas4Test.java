package jom.com.softserve.s3.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class Tas4Test {

	private static String LINE_TYPE = "jom.com.softserve.s3.task4.LineType";

	@DisplayName("Check that LineType is enum")
	@Test
	public void isLineTypeEnumTest() {
		try {
			Class<?> clazz = Class.forName(LINE_TYPE);
			assertTrue(clazz.isEnum());
		} catch (ClassNotFoundException e) {
			fail("there is no LineType enum");
		}
	}

	@DisplayName("Check that LineType contains appropriate value")
	@ParameterizedTest
	@ValueSource(strings = { "SOLID", "DASHED", "DOTTED", "DOUBLE" })
	public void hasFieldTest(String name) {
		try {
			Class<?> clazz = Class.forName(LINE_TYPE);
			Field[] fields = clazz.getDeclaredFields();
			boolean isField = false;
			for (Field field : fields) {
				if (name.equals(field.getName())) {
					isField = true;
				}
			}
			assertTrue(isField);
		} catch (ClassNotFoundException e) {
			fail("there is no LineType enum");
		}
	}

	@DisplayName("Check that type of line matches its message")
	@ParameterizedTest
	@MethodSource("provideStringsForIsEnum")
	void drawLineTest(LineType type, String expected) {
		assertEquals(expected, Line.drawLine(type));
	}

	private static Stream<Arguments> provideStringsForIsEnum() {
		return Stream.of(Arguments.of(LineType.DASHED, "The line is dashed type"),
				Arguments.of(LineType.DOTTED, "The line is dotted type"),
				Arguments.of(LineType.SOLID, "The line is solid type"));
	}
}
