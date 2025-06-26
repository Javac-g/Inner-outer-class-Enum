package jom.com.softserve.s3.task5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class Task5Test {
	private static String CLIENT_TYPE = "jom.com.softserve.s3.task5.ClientType";

	@DisplayName("Check that ClientType is enum")
	@Test
	public void isClientTypeEnumTest() {
		try {
			Class<?> clazz = Class.forName(CLIENT_TYPE);
			assertTrue(clazz.isEnum());
		} catch (ClassNotFoundException e) {
			fail("There is no ClientType enum");
		}
	}

	@DisplayName("Check that ClientType contains appropriate value")
	@ParameterizedTest
	@ValueSource(strings = { "NEW", "SILVER", "GOLD", "PLATINUM", "months" })
	public void hasFieldTest(String name) {
		try {
			Class<?> clazz = Class.forName(CLIENT_TYPE);
			Field[] fields = clazz.getDeclaredFields();
			boolean isField = false;
			for (Field field : fields) {
				if (name.equals(field.getName())) {
					isField = true;
				}
			}
			assertTrue(isField);
		} catch (ClassNotFoundException e) {
			fail("There is no ClientType enum");
		}
	}

	@DisplayName("Check that 'mounths' field is private")
	@Test
	void isMonthsFieldPrivateTest() {
		try {
			Class<?> clazz = Class.forName(CLIENT_TYPE);
			Field field = clazz.getDeclaredField("months");
			assertTrue(Modifier.isPrivate(field.getModifiers()));
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		} catch (NoSuchFieldException e) {
			fail("There is no mounths field");
		}
	}

	@DisplayName("Check that 'months' field is int")
	@Test
	void isMonthsFieldIntTest() {
		try {
			Class<?> clazz = Class.forName(CLIENT_TYPE);
			Field field = clazz.getDeclaredField("months");
			assertTrue(field.getType().equals(int.class));
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		} catch (NoSuchFieldException e) {
			fail("There is no mounths field");
		}
	}

	@DisplayName("Check that 'discount' method is public")
	@Test
	void isDiscountMethodPublic() {
		boolean isDiscountMethod = false;
		try {
			Class<?> clazz = Class.forName(CLIENT_TYPE);
			Method[] declaredMethods = clazz.getDeclaredMethods();
			for (Method method : declaredMethods) {
				if ("discount".equals(method.getName())) {
					assertTrue(Modifier.isPublic(method.getModifiers()));
					isDiscountMethod = true;
				}
			}
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		}
		assertTrue(isDiscountMethod);
	}

	@DisplayName("Check that ClientType enum has a constructor")
	@Test
	void hasClientTypeConstructorTest() {
		try {
			Class<?> clazz = Class.forName(CLIENT_TYPE);
			Constructor<?>[] constructors = clazz.getDeclaredConstructors();
			assertNotNull(constructors);
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		}
	}

	private static Stream<Arguments> providePriceWithDiscountForClientType() {
		return Stream.of(Arguments.of(1294.48, ClientType.NEW), 
				Arguments.of(1255.6456, ClientType.SILVER),
				Arguments.of(1177.9768000000001, ClientType.GOLD),
				Arguments.of(1022.6392000000001, ClientType.PLATINUM));
	}

	@DisplayName("Check that discount is calculated properly for special type of client")
	@ParameterizedTest
	@MethodSource("providePriceWithDiscountForClientType")
	void drawLineTest(double price, ClientType clientType) {
		assertEquals(price, clientType.discount() * 1294.48);
	}

}
