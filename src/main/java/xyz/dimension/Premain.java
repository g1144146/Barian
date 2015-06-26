package xyz.dimension;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class Premain {

	private static Premain premain;
	private Instrumentation inst;
	private Class<?> beforeClass;
	private byte[] data;

	private Premain(Instrumentation inst, String agent) {
		this.inst = inst;
		try {
			this.data = ClassFileReader.getBytes(agent);
			this.beforeClass = Class.forName("Main");
		} catch(IOException | ClassNotFoundException ex) {
			Logger.getLogger(Premain.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
	}

	public static Premain getInstance() {
		return premain;
	}

	public void perform() {
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
		BarianClassVisitor visitor = new BarianClassVisitor(writer, beforeClass.getSimpleName());
		ClassReader reader = new ClassReader(data);
		reader.accept(visitor, ClassReader.SKIP_DEBUG);
		redefine(writer.toByteArray());
	}

	private void redefine(byte[] redefinedData) {
		try {
			inst.redefineClasses(new ClassDefinition[]{new ClassDefinition(beforeClass, redefinedData)});
		} catch(ClassNotFoundException |UnmodifiableClassException ex) {
			Logger.getLogger(Premain.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}
	}

	public Class<?> getBefore() {
		return beforeClass;
	}

	public Class<?> getAfter() {
		return Arrays.asList(inst.getAllLoadedClasses()).stream()
				.filter(this::compare)
				.findFirst()
				.get();
	}

	private boolean compare(Class<?> after) {
		return after.getName().equals(beforeClass.getName());
	}

	public static void premain(String agent, Instrumentation inst) {
		premain = new Premain(inst, agent);
	}

	public static void main(String[] args) {}
}