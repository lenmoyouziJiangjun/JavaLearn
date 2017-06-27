package com.lll.aop.asm.demo1;/**
 * Created by liaoxueyan on 17/6/26.
 */

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * Version 1.0
 * Created by lll on 17/6/26.
 * Description classVisitor:
 *             参考网站：https://my.oschina.net/u/1166271/blog/162796
 * copyright generalray4239@gmail.com
 */
public class AopClassAdaptor extends ClassVisitor implements Opcodes {

    public AopClassAdaptor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        MethodVisitor mv = super.visitMethod(ACC_PUBLIC,"<init>","()V",null,null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD,0);
        mv.visitMethodInsn(INVOKESPECIAL, name, "<init>", "()V");
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if("<init>".equals(name)){
            return null;
        }
        if(!name.equals("testAsm")){
            return null;
        }
        MethodVisitor mv = super.visitMethod(access,name,desc,signature,exceptions);
        return new AopMedhod(this.api,mv);
    }
}

class AopMedhod extends MethodVisitor implements Opcodes{

    public AopMedhod(int i, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        visitMethodInsn(INVOKESTATIC, "com.lll.aop.asm.proxy.AopInterceptor", "beforeInvoke", "()V");
    }

    @Override
    public void visitInsn(int opcode) {
        if(opcode == RETURN){
            mv.visitMethodInsn(INVOKESTATIC,"","afterInvoke","()V");
        }
        super.visitInsn(opcode);
    }
}
