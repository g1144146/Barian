package xyz.dimension;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class BarianClassVisitor extends ClassVisitor {

	private String targetClass;
	private String currentClass;

	public BarianClassVisitor(ClassVisitor visitor, String targetClass) {
		super(Opcodes.ASM5, visitor);
		this.targetClass = targetClass;
	}

	@Override
	public void visit(final int version, final int access,
					  final String name, final String signature,
					  final String superName, final String[] interfaces) {
		this.currentClass = name;
		super.visit(version, access, name, signature, superName, interfaces);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String descriptor
									, String signature, String[] exceptions) {
		MethodVisitor visitor = super.visitMethod(access, name, descriptor, signature, exceptions);
		System.out.println("[class visitor] " + currentClass + "." + name + descriptor);
		if((name + descriptor).equals("method()D")) {
			return new BarianMethodVisitor(visitor, currentClass);
		}
		return visitor;
	}

	@Override
	public void visitEnd() {
		System.out.println("*** visit end ClassVisitor ***");
		super.visitEnd();
	}

	private static final class BarianMethodVisitor extends MethodVisitor {
		
		private String currentClass;
		
		public BarianMethodVisitor(MethodVisitor mv, String currentClass) {
			super(Opcodes.ASM5, mv);
			System.out.println("\t[method visitor] current class is " + currentClass);
			this.currentClass = currentClass;
		}
		
		@Override
		public void visitEnd() {
			System.out.println("\t*** visit end MethodVisitor ***");
			super.visitEnd();
		}

		@Override
		public void visitLdcInsn(Object object) {
			super.visitLdcInsn((double)10000.0);
		}
	}
}
