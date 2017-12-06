###装饰模式
  说简单就是给对象动态的增加职责的方式

###装饰模式什么时候用？
  >1、需要扩展一个类的功能，或给一个类增加附加责任；
  >2、需要动态的给一个对象增加功能 ，这些功能可以再动态的撤销
  >3、需要增加由一些基本功能的排列组合而产生的非常大量的功能，从而使继承关系变得不现实
  
  
###装饰模式和JAVA I/O库
  #####1、Java I/O的总体设计符合装饰模式和适配器模式。
  #####2、字节流InputStream装饰模式的基本构成为：
      >1、抽象构建(Component)角色：由InputStream 担任，为各种子类型流处理器提供统一入口；
      >2、具体构建（Concrete Component）;由InputStream的四种原始流处理器：
         ByteArrayInputStream,FileInputStream,PipeInputStream,StringBufferInputStream担任。他们实现了抽象构建角色
         规定的接口，可以被链接流处理器所装饰
      >3、抽象装饰类 FilterInputStream,   实现了InputStream所规定的接口
      >4、具体装饰类：流操作优化类。
          DataInputStream, 读入各种不同的原始数据类型和String类型。
          BufferedInputStream,是的流操作使用缓冲机制
          LineNumberInputStream 按照行号读入数据
          PushbackInputStream. 读入过程中后腿一个字符
  #####5、字符流Reader类型中的装饰模式基本构成
      >1、抽象构建：Reader ,为各种子类型流提供统一的接口
      >2、具体构建：CharArrayReader,InputStreamReader ,PipeReader ,StringReader;
      >3、抽象装饰：BufferedReader 和FilterReader 
      >4、具体装饰类：LineNumberReader,PushbackReader
      
                  
  
##装饰模式和代理模式的区别：
  1、代理模式和装饰者模式最重要的区别在于它们的意图和设计目的。
  2、代理模式的目的是,当直接访问本体不方便或者不符合需要时,为这个本体提供一个替代者。本体定义了关键功能,而代理提供或拒绝对它的访问,或者在访问本体之前做一些额外的事情。
  
  3、装饰者模式的作用就是为对 象动态加入行为。换句话说,代理模式强调一种关系(Proxy 与它的实体之间的关系),这种关系 可以静态的表达,也就是说,这种关系在一开始就可以被确定。而装饰者模式用于一开始不能确 定对象的全部功能时。代理模式通常只有一层代理本体的引用,而装饰者模式经常会形成一条 长长的装饰链。