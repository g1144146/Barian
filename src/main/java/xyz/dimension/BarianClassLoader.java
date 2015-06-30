package xyz.dimension;

import java.net.URL;
import java.net.MalformedURLException;
import javax.management.loading.MLet;

public class BarianClassLoader extends MLet {

	public BarianClassLoader(URL url) throws MalformedURLException {
		super(new URL[]{url});
	}
	
	public Class<?> invokeLoadClass(String name) throws ClassNotFoundException {
		return this.loadClass(name, false);
	}

	public Class<?> invokeFindSystemClass(String name) throws ClassNotFoundException {
		return this.findSystemClass(name);
	}

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		return super.findClass(name);
	}
}
