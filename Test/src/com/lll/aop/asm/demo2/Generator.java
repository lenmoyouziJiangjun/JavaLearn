package com.lll.aop.asm.demo2;/**
 * Created by liaoxueyan on 17/6/27.
 */

import com.lll.aop.demo.account.Account;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassReader;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


/**
 * Version 1.0
 * Created by lll on 17/6/27.
 * Description 生成器，生成一个我们修改后的class文件
 * copyright generalray4239@gmail.com
 */
public class Generator {

  public static void main(String[] args) throws Exception {
    //第一步：加载class 文件
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("Account.class");

    ClassReader cr = new ClassReader(is); //读取class文件流

    /*
     *栈帧包含三个重要内容：
     *本地变量表。
     *    在使用过程中，可以认为本地变量表是存放临时数据的，
     *    并且本地变量表有个很重要的功能就是用来传递方法调用时的参数，当调用方法的时候，
     *    参数会依次传递到本地变量表中从 0 开始的位置上，并且如果调用的方法是实例方法，
     *    那么我们可以通过第 0 个本地变量中获取当前实例的引用，也就是 this 所指向的对象。
     *操作数栈。
     *    可以认为操作数栈是一个用于存放指令执行所需要的数据的位置，指令从操作数栈中取走数据并将操作结构重新入栈。
     *
     *常量池：
     *    常量池可以理解成Class文件中的资源仓库。主要存放的是两大类常量：字面量(Literal)和符号引用(SymbolicReferences)。字面量类似于java中的常量概念，如文本字符串，final常量等，而符号引用则属于编译原理方面的概念，包括以下三种:
     *    1）、类和接口的全限定名(Fully Qualified Name)
     *    2）、字段的名称和描述符号(Descriptor)
     *    3）、方法的名称和描述符
     *
     *
     *
     *由于本地变量表的最大数和操作数栈的最大深度是在编译时就确定的，
     *所以在使用 ASM 进行字节码操作后需要调用 ASM 提供的 visitMaxs 方法来设置 maxLocal 和 maxStack 数。
     *不过，ASM 为了方便用户使用，已经提供了自动计算的方法，在实例化 ClassWriter 操作类的时候传入 COMPUTE_MAXS 后，ASM 就会自动计算本地变量表和操作数栈
     *
     */
    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);//表示自动计算本地变量表和操作数栈
    ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
    cr.accept(classAdapter, ClassReader.SKIP_DEBUG);

    byte[] data = cw.toByteArray();
    File file = new File("Account.class");//生成一个Account.class文件
    FileOutputStream fos = new FileOutputStream(file);
    fos.write(data);
    fos.close();
  }

}
