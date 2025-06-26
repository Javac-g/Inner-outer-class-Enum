package jom.com.softserve.s3.task6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task6Test {
	private static String ADDRESS_BOOK = "jom.com.softserve.s3.task6.AddressBook";
	private static String NAME_ADDRESS_PAIR = "jom.com.softserve.s3.task6.AddressBook$NameAddressPair";
	private static String PERSON = "jom.com.softserve.s3.task6.AddressBook$NameAddressPair$Person";
	private static String ADDRESS_BOOK_ITERATOR = "jom.com.softserve.s3.task6.AddressBook$AddressBookIterator";
	private static String SORT_ORDER = "jom.com.softserve.s3.task6.SortOrder";

	@DisplayName("Check that a class is present")
	@ParameterizedTest
	@MethodSource("provideClass")
	void isTypeClass(String type, String className) {
		try {
			Class<?> clazz = Class.forName(type);
			assertTrue(clazz.getSimpleName().equals(className));
		} catch (ClassNotFoundException e) {
			fail("There is no " + className + " class");
		}
	}

	@DisplayName("Check that a class has a properly field")
	@ParameterizedTest
	@MethodSource("provideFieldType")
	void hasTypeDeclaredField(String type, String fieldName) {
		try {
			Class<?> clazz = Class.forName(type);

			Field[] declaredFields = clazz.getDeclaredFields();
			boolean isField = false;
			for (Field field : declaredFields) {
				if (fieldName.equals(field.getName())) {
					isField = true;
				}
			}
			assertTrue(isField);
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}

	}

	@DisplayName("Check that a class has a properly type of fields")
	@ParameterizedTest
	@MethodSource("provideFieldType")
	void checkFieldType(String classType, String fieldName, Class fieldType) {
		try {
			Class<?> clazz = Class.forName(classType);

			Field[] declaredFields = clazz.getDeclaredFields();
			boolean isField = false;
			for (Field field : declaredFields) {
				if (fieldName.equals(field.getName())) {
					assertEquals(field.getType(), fieldType);
					isField = true;
				}
			}
			assertTrue(isField);
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}

	}

	@DisplayName("Check that NameAddressPair class has 'person' field of Person type")
	@Test
	void checkFieldType() {
		try {
			Class<?> clazz = Class.forName(NAME_ADDRESS_PAIR);
			Class<?> personClass = Class.forName(PERSON);
			Field[] declaredFields = clazz.getDeclaredFields();
			boolean isField = false;
			for (Field field : declaredFields) {
				if ("person".equals(field.getName())) {
					assertEquals(field.getType(), personClass);
					isField = true;
				}
			}
			assertTrue(isField);
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that a class has a properly methods")
	@ParameterizedTest
	@MethodSource("provideTypeDeclaredMethod")
	void hasTypeDeclaredMethod(String classType, String methodName, Class[] paramTypes) {
		try {
			Class<?> clazz = Class.forName(classType);
			Method[] methods = clazz.getDeclaredMethods();
			boolean isMethod = false;
			Class<?>[] types = paramTypes;
			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
					Class<?>[] parameterTypes = method.getParameterTypes();
					if (Arrays.equals(types, parameterTypes)) {
						isMethod = true;
					}
				}
			}
			assertTrue(isMethod);
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}

	}

	@DisplayName("Check that a metod returns an approprite type")
	@ParameterizedTest
	@MethodSource("provideMethodReturnType")
	void hasMethodReturnType(String classType, String methodName, Class returnType) {
		try {
			Class<?> clazz = Class.forName(classType);
			Method[] methods = clazz.getDeclaredMethods();
			boolean isMethod = false;
			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
					assertEquals(method.getReturnType(), returnType);
					isMethod = true;
				}
			}
			assertTrue(isMethod);
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that class contains ovveridden methods")
	@ParameterizedTest
	@MethodSource("provideOverrideTypeMethod")
	void overridesTypeMethod(String parent, String child, String methodName) {
		try {
			Class<?> parentClass = Class.forName(parent);
			Class<?> childClass = Class.forName(child);
			Method[] childMethods = childClass.getDeclaredMethods();
			Method[] parentMethods = parentClass.getDeclaredMethods();
			boolean isMethod = false;
			for (final Method parentMethod : parentMethods) {
				for (final Method childMethod : childMethods) {
					if (equalParamTypes(parentMethod.getParameterTypes(), childMethod.getParameterTypes())
							&& !Modifier.isPrivate(parentMethod.getModifiers())) {
						isMethod = true;
					}
				}
			}
			assertTrue(isMethod);
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that a class implements an interface")
	@ParameterizedTest
	@MethodSource("provideImplementTypeInterface")
	public void implementsTypeInterface(final String parentName, final String childName) {
		try {
			final Class<?> parentClazz = Class.forName(parentName);
			final Class<?> childClazz = Class.forName(childName);
			assertTrue(parentClazz.isAssignableFrom(childClazz) && parentClazz.isInterface());
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	private static Stream<Arguments> provideImplementTypeInterface() {
		return Stream.of(Arguments.of("java.lang.Iterable", ADDRESS_BOOK),
				Arguments.of("java.util.Iterator", ADDRESS_BOOK_ITERATOR));
	}

	@DisplayName("Check that NameAddressPair class is private")
	@Test
	void isNameAddressPairClassPrivate() {
		try {
			Class<?> clazz = Class.forName(NAME_ADDRESS_PAIR);
			assertTrue(Modifier.isPrivate(clazz.getModifiers()));
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that 'person' field is final")
	@Test
	void isPersonFieldFinal() {
		try {
			Class<?> clazz = Class.forName(NAME_ADDRESS_PAIR);
			Field[] fields = clazz.getFields();
			boolean isPersonField = false;
			for (Field field : fields) {
				if ("person".equals(field.getName())) {
					assertTrue(Modifier.isFinal(clazz.getModifiers()));
					isPersonField = true;
				}
				assertTrue(isPersonField);
			}
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that NameAddressPair class has a private constructor")
	@Test
	void isConstructorPrivate() {
		try {
			Class<?> clazz = Class.forName(NAME_ADDRESS_PAIR);
			Constructor<?>[] constructors = clazz.getConstructors();
			for (Constructor<?> constructor : constructors) {
				assertTrue(Modifier.isPrivate(constructor.getModifiers()));
			}
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that SortOrder is enum")
	@Test
	public void isSortOrderEnum() {
		try {
			Class<?> clazz = Class.forName(SORT_ORDER);
			assertTrue(clazz.isEnum());
		} catch (ClassNotFoundException e) {
			fail("There is no SortOrder enum");
		}
	}

	@DisplayName("Check that AddressBookIterator class is a non static nested class")
	@Test
	public void isAddressBookIteratorNonStaticNestedType() {
		try {
			Class<?> parent = Class.forName(ADDRESS_BOOK);
			Class<?> child = Class.forName(ADDRESS_BOOK_ITERATOR);
			assertTrue(parent.getEnclosingClass() == null && child.getEnclosingClass() != null
					&& parent.equals(child.getEnclosingClass()) && !Modifier.isStatic(child.getModifiers()));
		} catch (ClassNotFoundException e) {
			fail("There is no such class");
		}
	}

	@DisplayName("Check that object of AdressBook class is not null")
	@Test
	public void checkBookCreationTest() {
		AddressBook addressBook = new AddressBook(150);
		assertNotNull(addressBook);
	}

	@DisplayName("Check the number of added records to addressBook")
	@Test
	public void createFewBooksTest() {
		int counter = 0;
		AddressBook addressBook = new AddressBook(4);
		counter += addressBook.create("John", "Brown", "Address #1") ? 1 : 0;
		counter += addressBook.create("Karen", "Davis", "Address #2") ? 1 : 0;
		counter += addressBook.create("Steven", "Taylor", "Address #1") ? 1 : 0;
		assertTrue(counter == 3);
	}

	@DisplayName("Check the number of records when adding several identical contacts to addressBook")
	@Test
	public void createFewTheSameBooksTest() {
		int counter = 0;
		AddressBook addressBook = new AddressBook(4);
		counter += addressBook.create("John", "Brown", "Address #1") ? 1 : 0;
		counter += addressBook.create("John", "Brown", "Address #1") ? 1 : 0;
		assertTrue(counter == 1);
	}

	@DisplayName("Check the number of records when adding several contacts with identical addresses to addressBook")
	@Test
	public void createThreeBooksWithTwoAddressesTest() {
		int counter = 0;
		AddressBook addressBook = new AddressBook(2);
		counter += addressBook.create("John", "Brown", "Address #1") ? 1 : 0;
		counter += addressBook.create("Karen", "Davis", "Address #2") ? 1 : 0;
		counter += addressBook.create("Steven", "Taylor", "Address #1") ? 1 : 0;
		assertTrue(counter == 3);
	}

	@DisplayName("Check that 'read' method returns null when trying to read non existing record")
	@Test
	public void readNonExistingBookTest() {
		AddressBook addressBook = new AddressBook(4);
		addressBook.create("John", "Brown", "Address #1");
		addressBook.create("Karen", "Davis", "Address #2");
		addressBook.create("Steven", "Taylor", "Address #1");
		assertNull(addressBook.read("Susan", "Brown"));
	}

	@DisplayName("Check updating of existing record")
	@Test
	public void updateAddressByExistingKeyTest() {
		boolean status = false;
		AddressBook addressBook = new AddressBook(4);
		addressBook.create("John", "Brown", "Address #1");
		addressBook.create("Karen", "Davis", "Address #2");
		addressBook.create("Steven", "Taylor", "Address #1");
		status = addressBook.update("Steven", "Taylor", "Address #3");
		assertTrue(status && addressBook.read("Steven", "Taylor").equals("Address #3"));
	}

	@DisplayName("Check that cannot update non existing record")
	@Test
	public void updateAddressByNotExistingKeyTest() {
		AddressBook addressBook = new AddressBook(4);
		addressBook.create("John", "Brown", "Address #1");
		addressBook.create("Karen", "Davis", "Address #2");
		addressBook.create("Steven", "Taylor", "Address #1");
		assertFalse(addressBook.update("Susan", "Brown", "Address #3"));
	}

	@DisplayName("Check deleting of existing record")
	@Test
	public void deleteRecordByExistingKeyTest() {
		boolean status = false;
		AddressBook addressBook = new AddressBook(4);
		addressBook.create("John", "Brown", "Address #1");
		addressBook.create("Karen", "Davis", "Address #2");
		addressBook.create("Steven", "Taylor", "Address #1");
		status = addressBook.delete("Steven", "Taylor");
		assertTrue(status);
		assertNull(addressBook.read("Steven", "Taylor"));
	}

	@DisplayName("Check that cannot delete non existing record")
	@Test
	public void deleteRecordByNotExistingKeyTest() {
		AddressBook addressBook = new AddressBook(4);
		addressBook.create("John", "Brown", "Address #1");
		addressBook.create("Karen", "Davis", "Address #2");
		addressBook.create("Steven", "Taylor", "Address #1");
		assertFalse(addressBook.delete("Susan", "Brown"));
	}

	@DisplayName("Check size of empty addressBook")
	@Test
	public void checkSizeForEmptyAddressBook() {
		AddressBook addressBook = new AddressBook(4);
		assertTrue(addressBook.size() == 0);
	}

	@DisplayName("Check changing of addressBook size")
	@Test
	public void checkChangingAddressBookSize() {
		AddressBook addressBook = new AddressBook(4);
		addressBook.create("John", "Brown", "Address #1");
		addressBook.create("Karen", "Davis", "Address #2");
		addressBook.create("Steven", "Taylor", "Address #1");
		assertTrue(addressBook.size() == 3);
	}

	@DisplayName("Check changing of addressBook size after removing records")
	@Test
	public void checkChangingAddressBookSizeWhenRemovingRecord() {
		AddressBook addressBook = new AddressBook(4);
		addressBook.create("John", "Brown", "Address #1");
		addressBook.create("Karen", "Davis", "Address #2");
		addressBook.create("Steven", "Taylor", "Address #1");
		addressBook.delete("John", "Brown");
		addressBook.delete("Karen", "Davis");
		assertTrue(addressBook.size() == 1);
	}

	@DisplayName("Check iteration across records")
	@Test
	public void checkItarationAcrossRecords() {
		String[] expected = { "First name: Karen, Last name: Davis, Address: Address #2",
				"First name: Steven, Last name: Taylor, Address: Address #3",
				"First name: Susan, Last name: Brown, Address: Address #4" };
		String[] actual = new String[3];
		AddressBook addressBook = new AddressBook(4);
		addressBook.create("John", "Brown", "Address #1");
		addressBook.create("Karen", "Davis", "Address #2");
		addressBook.create("Steven", "Taylor", "Address #1");
		addressBook.create("Susan", "Brown", "Address #4");
		addressBook.update("Steven", "Taylor", "Address #3");
		addressBook.delete("John", "Brown");
		int counter = 0;
		for (Object record : addressBook)
			actual[counter++] = record.toString();
		assertTrue(Arrays.equals(expected, actual));
	}

	@DisplayName("Check sorting of records")
	@Test
	public void checkSortingRecords() {
		String[] expected = { "First name: John, Last name: Brown, Address: Address #1",
				"First name: John, Last name: Taylor, Address: Address #1",
				"First name: Karen, Last name: Davis, Address: Address #2",
				"First name: Susan, Last name: Brown, Address: Address #4" };
		String[] actual = new String[4];
		AddressBook addressBook = new AddressBook(4);
		addressBook.create("John", "Brown", "Address #1");
		addressBook.create("Susan", "Brown", "Address #4");
		addressBook.create("Karen", "Davis", "Address #2");
		addressBook.create("John", "Taylor", "Address #1");
		addressBook.sortedBy(SortOrder.ASC);
		int counter = 0;
		for (Object record : addressBook)
			actual[counter++] = record.toString();
		assertTrue(Arrays.equals(expected, actual));
	}

	@DisplayName("Check sorting of records by descending")
	@Test
	public void checkSortingRecordsByDesc() {
		String[] expected = { "First name: Susan, Last name: Brown, Address: Address # 4",
				"First name: Karen, Last name: Davis, Address: Address #2",
				"First name: John, Last name: Taylor, Address: Address #1",
				"First name: John, Last name: Brown, Address: Address #1" };
		String[] actual = new String[4];
		AddressBook addressBook = new AddressBook(4);
		addressBook.create("John", "Brown", "Address #1");
		addressBook.create("Susan", "Brown", "Address # 4");
		addressBook.create("Karen", "Davis", "Address #2");
		addressBook.create("John", "Taylor", "Address #1");
		addressBook.sortedBy(SortOrder.DESC);
		int counter = 0;
		for (Object record : addressBook)
			actual[counter++] = record.toString();
		assertTrue(Arrays.equals(expected, actual));
	}

	private static Stream<Arguments> provideClass() {
		return Stream.of(Arguments.of(ADDRESS_BOOK, "AddressBook"), Arguments.of(NAME_ADDRESS_PAIR, "NameAddressPair"),
				Arguments.of(PERSON, "Person"), Arguments.of(ADDRESS_BOOK_ITERATOR, "AddressBookIterator"));
	}

	private static Stream<Arguments> provideFieldType() {
		return Stream.of(Arguments.of(ADDRESS_BOOK, "counter", int.class),
				Arguments.of(NAME_ADDRESS_PAIR, "address", String.class),
				Arguments.of(PERSON, "firstName", String.class));
	}

	private static Stream<Arguments> provideTypeDeclaredMethod() {
		return Stream.of(Arguments.of(ADDRESS_BOOK, "create", new Class[] { String.class, String.class, String.class }),
				Arguments.of(ADDRESS_BOOK, "delete", new Class[] { String.class, String.class }),
				Arguments.of(ADDRESS_BOOK, "size", new Class[] {}));
	}

	private static Stream<Arguments> provideMethodReturnType() {
		return Stream.of(Arguments.of(ADDRESS_BOOK, "create", boolean.class),
				Arguments.of(ADDRESS_BOOK, "delete", boolean.class), Arguments.of(ADDRESS_BOOK, "size", int.class),
				Arguments.of(ADDRESS_BOOK, "read", String.class));
	}

	private static Stream<Arguments> provideOverrideTypeMethod() {
		return Stream.of(Arguments.of("java.lang.Iterable", ADDRESS_BOOK, "iterator"),
				Arguments.of("java.lang.Object", PERSON, "equals"),
				Arguments.of("java.util.Iterator", ADDRESS_BOOK_ITERATOR, "hasNext"),
				Arguments.of("java.util.Iterator", ADDRESS_BOOK_ITERATOR, "next"));
	}

	private static boolean equalParamTypes(final Class<?>[] params1, final Class<?>[] params2) {
		if (params1.length == params2.length) {
			for (int i = 0; i < params1.length; ++i) {
				if (params1[i] != params2[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
