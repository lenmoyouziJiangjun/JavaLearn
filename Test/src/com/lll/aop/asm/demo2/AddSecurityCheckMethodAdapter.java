package com.lll.aop.asm.demo2;

import com.sun.xml.internal.ws.org.objectweb.asm.MethodAdapter;
import com.sun.xml.internal.ws.org.objectweb.asm.MethodVisitor;
import com.sun.xml.internal.ws.org.objectweb.asm.Opcodes;

/**
 * Version 1.0
 * Created by lll on 17/6/27.
 * Description  定义一个继承自 MethodAdapter的 AddSecurityCheckMethodAdapter，在“operation”方法首部插入对 SecurityChecker.checkSecurity()的调用
 * copyright generalray4239@gmail.com
 */
public class AddSecurityCheckMethodAdapter extends MethodAdapter {
  public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
    super(mv);
  }

  @Override
  public void visitCode() {
    visitMethodInsn(Opcodes.INVOKESTATIC, "SecurityChecker", "checkSecurity", "()V");
  }
}
