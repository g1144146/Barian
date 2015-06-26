package xyz.dimension;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class BarianClassVisitor extends ClassVisitor {

	private String targetClass;
	private String currentClass;

	public BarianClassVisitor(ClassVisitor visitor, String targetClass) {
		super(Opcodes.ASM4, visitor);
		this.targetClass = targetClass;
	}

	@Override
	public void visit(final int version, final int access,
					  final String name, final String signature,
					  final String superName, final String[] interfaces) {
//		this.className = name;
		this.currentClass = name;
		super.visit(version, access, name, signature, superName, interfaces);
//		System.out.println("   + " + name);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String description
									, String signature, String[] exceptions) {
		MethodVisitor visitor = super.visitMethod(access, name, description, signature, exceptions);
		System.out.println(">> " + name + description);
		return new BarianMethodVisitor(visitor);
	}

	@Override
	public void visitEnd() {
		System.out.println("*** visit end ***");
		super.visitEnd();
	}

	private static final class BarianMethodVisitor extends MethodVisitor {
		public BarianMethodVisitor(MethodVisitor mv) {
			super(Opcodes.ASM4, mv);
		}
		
		@Override
		public void visitMethodInsn(int opcode, String owner, String name, String desc) {
			System.out.println(">>>> " + owner+"."+name + desc);
			if(owner.length() == 0) {
				super.visitMethodInsn(opcode, owner, name, "()I");
			} else {
				super.visitMethodInsn(opcode, owner, name, desc);
			}
		}
	}
}
