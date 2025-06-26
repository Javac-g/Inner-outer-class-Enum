package jom.com.softserve.s3.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task2Test {

	final private static String NAMED_LIST = "jom.com.softserve.s3.task2.NameList";
	final private static String ITERATOR = "jom.com.softserve.s3.task2.NameList$Iterator";

	@DisplayName("Check that NameList class contains Iterator class")
	@Test
	void hasNameListNonStaticNestedIteratorTest() {
		try {
			Class<?> outerClazz = Class.forName(NAMED_LIST);
			Class<?> innerClazz = Class.forName(ITERATOR);
			assertTrue(outerClazz.getEnclosingClass() == null && innerClazz.getEnclosingClass() != null
					&& outerClazz.equals(innerClazz.getEnclosingClass())
					&& !Modifier.isStatic(innerClazz.getModifiers()));
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that Iterator class has 'counter' field")
	@Test
	void hasIteratorClassCounterFieldTest() {
		try {
			Class<?> clazz = Class.forName(ITERATOR);
			Field[] declaredFields = clazz.getDeclaredFields();
			boolean isCounter = false;
			for (Field field : declaredFields) {
				if ("counter".equals(field.getName())) {
					isCounter = true;
				}
			}
			assertTrue(isCounter);
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that 'counter' field is private")
	@Test
	void isCounterFieldPrivateTest() {
		try {
			Class<?> clazz = Class.forName(ITERATOR);
			Field[] declaredFields = clazz.getDeclaredFields();
			boolean isCounter = false;
			for (Field field : declaredFields) {
				if ("counter".equals(field.getName())) {
					assertTrue(Modifier.isPrivate(field.getModifiers()));
					isCounter = true;
				}
			}
			assertTrue(isCounter);
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that type of 'counter' field is int")
	@Test
	void isCounterFieldIntTest() {
		try {
			Class<?> clazz = Class.forName(ITERATOR);
			Field[] declaredFields = clazz.getDeclaredFields();
			boolean isCounter = false;
			for (Field field : declaredFields) {
				if ("counter".equals(field.getName())) {
					assertTrue(field.getType().equals(int.class));
					isCounter = true;
				}
			}
			assertTrue(isCounter);
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that Iterator class has 'hasNext' method")
	@Test
	void hasIteratorHasNextMethodTest() {
		try {
			Class<?> clazz = Class.forName(ITERATOR);
			Method[] methods = clazz.getDeclaredMethods();
			boolean isHasNext = false;
			for (Method method : methods) {
				if ("hasNext".equals(method.getName())) {
					isHasNext = true;
				}
			}
			assertTrue(isHasNext);
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that 'hasNext' method is public")
	@Test
	void isHasNextMethodPublicTest() {
		try {
			Class<?> clazz = Class.forName(ITERATOR);
			Method[] methods = clazz.getDeclaredMethods();
			boolean isHasNext = false;
			for (Method method : methods) {
				if ("hasNext".equals(method.getName())) {
					assertTrue(Modifier.isPublic(method.getModifiers()));
					isHasNext = true;
				}
			}
			assertTrue(isHasNext);
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that 'hasNext' method returns boolean value")
	@Test
	void hasHasNextMethodReturnTypeTest() {
		try {
			Class<?> clazz = Class.forName(ITERATOR);
			Method[] methods = clazz.getDeclaredMethods();
			boolean isHasNext = false;
			for (Method method : methods) {
				if ("hasNext".equals(method.getName())) {
					assertEquals(method.getReturnType(), boolean.class);
					isHasNext = true;
				}
			}
			assertTrue(isHasNext);
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that Iterator class contains a constructor")
	@Test
	void hasIteratorClassConstructorTest() {
		try {
			Class<?> clazz = Class.forName(ITERATOR);
			Constructor<?>[] constructors = clazz.getDeclaredConstructors();
			assertNotNull(constructors);
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		}
	}

	@DisplayName("Check that Itarator class has private constructor")
	@Test
	void hasIteratorClassPrivateConstructorTest() {
		try {
			Class<?> clazz = Class.forName(ITERATOR);
			Constructor<?>[] constructors = clazz.getDeclaredConstructors();
			boolean isPrivateConstructor = false;
			for (Constructor<?> constructor : constructors) {
				if (Modifier.isPrivate(constructor.getModifiers())) {
					isPrivateConstructor = true;
				}
			}
			assertTrue(isPrivateConstructor);
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException or there is no private constructor");
		}
	}

	@DisplayName("Check that Iterator works properly")
	@Test
	public void checkIfCorrectIteratorImplements() {
		int counter = 0;
		String[] expected = { "Mike", "Emily", "Nick", "Patric", "Sara" };
		String[] actual = new String[5];
		for (NameList.Iterator iterator = new NameList().getIterator(); iterator.hasNext();) {
			actual[counter++] = iterator.next();
		}
		assertTrue(Arrays.equals(expected, actual) && counter == expected.length);
	}
}
