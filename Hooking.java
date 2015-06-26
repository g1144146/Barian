import java.lang.reflect.Method;
import java.lang.instrument.Instrumentation;
import java.util.Arrays;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;

import xyz.dimension.Premain;

@Aspect
public class Hooking {
	// @Before("call(void hook())")
	@Before("call(void xyz.dimension.Premain.perform(..))")
	public void hookBefore() {
		Class<?> before = Premain.getInstance().getBefore();
		printMethods(before.getDeclaredMethods(), "[before]");
	}

	// @After("call(void hook())")
	@After("call(void xyz.dimension.Premain.perform(..))")
	public void hookAfter() throws Exception {
		Class<?> after = Premain.getInstance().getAfter();
		printMethods(after.getDeclaredMethods(), "[after]");
		printMethods(Class.forName("Main").getDeclaredMethods(), "[after2]");
	}

	private void printMethods(Method[] methods, String timing) {
		System.out.println("");
		System.out.println(timing);
		Arrays.asList(methods).forEach(System.out::println);
		System.out.println("");
	}
}
