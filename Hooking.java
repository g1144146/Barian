import java.lang.reflect.Method;
import java.lang.instrument.Instrumentation;
import java.util.Arrays;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;

import xyz.dimension.Premain;

@Aspect
public class Hooking {

	@Before("call(void xyz.dimension.Premain.perform(..))")
	public void hookBefore() throws Exception {
		Class<?> before = Premain.getInstance().getBefore();
		printMethods(before.getDeclaredMethods(), "[before]");
	}

	@After("call(void xyz.dimension.Premain.perform(..))")
	public void hookAfter() throws Exception {
		Class<?> after = Premain.getInstance().getAfter();
		printMethods(after.getDeclaredMethods(), "[after]");
		// printMethods(Class.forName("Main").getDeclaredMethods(), "[after2]");
	}

	private void printMethods(Method[] methods, String timing) throws Exception {
		System.out.println("");
		System.out.println(timing);
		Arrays.asList(methods).stream()
			.filter((Method m) -> m.getName().equals("method"))
			.peek(this::output)
			.forEach(System.out::println);
		System.out.println("");
	}

	private void output(Method m) {
		try {
			System.out.println("return value: " + m.invoke(new Main()));
			System.out.println("hash code: " + m.hashCode());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
