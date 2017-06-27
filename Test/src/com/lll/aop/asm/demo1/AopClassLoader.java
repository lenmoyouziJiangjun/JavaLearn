package com.lll.aop.asm.demo1;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.InputStream;

/**
 * Version 1.0
 * Created by lll on 17/6/26.
 * Description 代理加载类
 * copyright generalray4239@gmail.com
 */
public class AopClassLoader extends ClassLoader implements Opcodes{
    public AopClassLoader(ClassLoader loader){
        super(loader);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if(!name.contains("_Proxy")){//不是代理类
            return super.loadClass(name);
        }else{//替换类
            try {
                ClassWriter cw = new ClassWriter(0);
                //加载class文件
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com.lll.aop.asm.proxy.TestAsm.class");
                ClassReader reader = new ClassReader(is);
                reader.accept(new AopClassAdaptor(ASM4, cw),ClassReader.SKIP_DEBUG);
                byte[] code = cw.toByteArray();
                return this.defineClass(name,code,0,code.length);
            }catch (Exception e){
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }
    }
}
