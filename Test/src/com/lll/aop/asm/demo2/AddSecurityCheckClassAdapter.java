package com.lll.aop.asm.demo2;/**
 * Created by liaoxueyan on 17/6/27.
 */


import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassVisitor;
import com.sun.xml.internal.ws.org.objectweb.asm.MethodVisitor;

/**
 * Version 1.0
 * Created by lll on 17/6/27.
 * Description  ClassAdapter是 ASM 框架提供的一个默认类,负责沟通 ClassReader和 ClassWriter。
 * 如果想要改变 ClassReader处读入的类，然后从 ClassWriter处输出，可以重写相应的 ClassAdapter函数。
 * 这里，为了改变 Account类的 operation 方法，我们将重写 visitMethdod方法。
 * copyright generalray4239@gmail.com
 */
public class AddSecurityCheckClassAdapter extends ClassAdapter {
    public AddSecurityCheckClassAdapter(ClassVisitor cv) {
        super(cv);
    }

    /**
     * 重写visitMethod.访问到"operation"方法时，给出自定义 MethodVisitor，实际改写方法内容
     * @param access
     * @param name
     * @param desc
     * @param signature
     * @param exceptions
     * @return
     */
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access,name,desc,signature,exceptions);
        MethodVisitor wrapperMV =mv;
        if(mv!=null && name.equals("operation")){//operation 方法
            // 使用自定义 MethodVisitor，实际改写方法内容
            wrapperMV = new AddSecurityCheckMethodAdapter(mv);
        }
        return wrapperMV;
    }
}
