#适配器模式
 * 就是将一个类的接口变换成客户端所期待的另一种接口.
    简单的说就是，客户端需要A接口，可是现在有一个B接口。创建一个C类，负责将B接口转换成需要用的A。
 * 什么时候使用适配器？
   >1、系统需要使用现有的类，而此类的接口不符合系统的需要。
   >2、想要建立一个可以重复使用的类，用于与一些彼此没有太大关联的一些类。
   
   
####java IO中适配器模式
   1、InputStream 类型的原始流处理器是适配器模式的应用。ByteArrayInputStream是一个适配器类。
      ByteArrayInputStream继承了InputStream的接口。而封装了一个Byte数组，换句话说，它将一个byte数组的接口适配成InputStream流处理器接口。
   2、从byte流到char流的适配：InputStreamReader 是从byte流到char流的一个桥梁。它读入byte数据并根据指定的编码将之翻译成char数据
      