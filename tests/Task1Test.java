package jom.com.softserve.s3.task1;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task1Test {

	final private static String PIZZA = "jom.com.softserve.s3.task1.Pizza";
	final private static String PIZZA_BUILDER = "jom.com.softserve.s3.task1.Pizza$PizzaBuilder";

	@DisplayName("Check that Pizza class contains nested static class PizzaBuilder")
	@Test
	void hasPizzaClassStaticNestedPizzaBuilderClassTest() {
		try {
			Class<?> outerClazz = Class.forName(PIZZA);
			Class<?> innerClazz = Class.forName(PIZZA_BUILDER);
			assertTrue(outerClazz.getEnclosingClass() == null && innerClazz.getEnclosingClass() != null
					&& outerClazz.equals(innerClazz.getEnclosingClass())
					&& Modifier.isStatic(innerClazz.getModifiers()));
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		}
	}

	@DisplayName("Check that PizzaBuilder class is public")
	@Test
	void isPizza$PizzaBuilderPublicTest() {
		try {
			Class<?> clazz = Class.forName(PIZZA_BUILDER);
			assertTrue(Modifier.isPublic(clazz.getModifiers()));
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		}
	}

	@DisplayName("Check that PizzaBuilder class has a constructor")
	@Test
	void hasPizza$PizzaBuilderDeclaredConstructorTest() {
		try {
			Class<?> clazz = Class.forName(PIZZA_BUILDER);
			Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
			assertNotNull(declaredConstructors);
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		}
	}

	@DisplayName("Check that the constructor in PizzaBuilder class is private")
	@Test
	void isConstructorPrivateTest() {
		try {
			Class<?> clazz = Class.forName(PIZZA_BUILDER);
			Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
			assertTrue(declaredConstructor != null && Modifier.isPrivate(declaredConstructor.getModifiers()));
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			fail("ClassNotFoundException or there is no private constructor");
		}
	}

	@DisplayName("Check that PizzaBuilder class contains 'build' method")
	@Test
	void hasPizza$PizzaBuilderClassMethodBuildTest() {
		try {
			Class<?> clazz = Class.forName(PIZZA_BUILDER);
			Method[] declaredMethods = clazz.getDeclaredMethods();
			boolean isBuildMethod = false;
			for (Method method : declaredMethods) {
				if ("build".equals(method.getName())) {
					assertTrue("build".equals(method.getName()) && method.getParameters().length == 0);
					isBuildMethod = true;
				}
			}
			assertTrue(isBuildMethod);
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		}
	}

	@DisplayName("Check that method 'build' returns Pizza object")
	@Test
	void returnTypeClassMethodBuildTest() {
		try {
			Class<?> clazz = Class.forName(PIZZA_BUILDER);
			Method[] declaredMethods = clazz.getDeclaredMethods();
			boolean isBuildMethod = false;
			for (final Method method : declaredMethods) {
				if ("build".equals(method.getName())) {
					assertTrue(method.getReturnType().getSimpleName().equals("Pizza"));
					isBuildMethod = true;
				}
			}
			assertTrue(isBuildMethod);
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		}
	}

	@DisplayName("Check that 'build' method is public")
	@Test
	void isMethodBuildPublicTest() {
		try {
			Class<?> clazz = Class.forName(PIZZA_BUILDER);
			Method[] declaredMethods = clazz.getDeclaredMethods();
			boolean isBuildMethod = false;
			for (final Method method : declaredMethods) {
				if ("build".equals(method.getName())) {
					assertTrue(Modifier.isPublic(method.getModifiers()));
					isBuildMethod = true;
				}
			}
			assertTrue(isBuildMethod);
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		}
	}

	@DisplayName("Check that 'addSeaFood' method returns object of PizzaBuilder type")
	@Test
	void returnTypeClassMethodAddSeafoodTest() {
		try {
			Class<?> clazz = Class.forName(PIZZA_BUILDER);
			Method[] declaredMethods = clazz.getDeclaredMethods();
			boolean isAddSeaFoodMethod = false;
			for (final Method method : declaredMethods) {
				if ("addSeafood".equals(method.getName())) {
					assertTrue(method.getReturnType().getSimpleName().equals("PizzaBuilder"));
					isAddSeaFoodMethod = true;
				}
			}
			assertTrue(isAddSeaFoodMethod);
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		}
	}

	@DisplayName("Check that 'addSeaFood' method takes one parameter of String type")
	@Test
	void checkAddSeafoodParametersTest() {
		try {
			Class<?> clazz = Class.forName(PIZZA_BUILDER);
			Method[] declaredMethods = clazz.getDeclaredMethods();
			boolean isAddSeaFoodMethod = false;
			for (final Method method : declaredMethods) {
				if ("addSeafood".equals(method.getName())) {
					assertTrue(method.getParameterCount() == 1, "There is addSeafood method with only one parameters");
					Class<?>[] parameters = method.getParameterTypes();
					assertTrue(parameters[0].getSimpleName().equals("String"));
					isAddSeaFoodMethod = true;
				}
			}
			assertTrue(isAddSeaFoodMethod);
		} catch (ClassNotFoundException e) {
			fail("ClassNotFoundException");
		}
	}

	@DisplayName("Check that 'cook' methods does not return null")
	@Test
	void isCookMethodReturnsNotNullTest() {
		assertNotNull(Oven.cook());

	}

	@DisplayName("Check that 'cook' method returns object of Pizza type")
	@Test
	void isCookMethodReturnsPizzaTest() {
		assertTrue(Oven.cook() instanceof Pizza);

	}

	@DisplayName("Check that created pizza has at least three ingredients")
	@Test
	void hasPizzaAtLeastThreeIngredientsTest() {
		assertTrue(hasPizzaAtLeastThreeIngredients());
	}

	private static boolean hasPizzaAtLeastThreeIngredients() {
		Pizza pizza = Oven.cook();
		return Arrays
				.stream(new String[] { Oven.cook().getCheese(), pizza.getMeat(), pizza.getSeafood(),
						pizza.getVegetable(), pizza.getMushroom() })
				.filter(ingredient -> ingredient != null && !ingredient.equals("")).count() >= 3;
	}
}
