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
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("Account.class");

        ClassReader cr = new ClassReader(is);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
        cr.accept(classAdapter,ClassReader.SKIP_DEBUG);

        byte[] data = cw.toByteArray();
        File file = new File("Account.class");//生成一个Account.class文件
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();
    }

}
