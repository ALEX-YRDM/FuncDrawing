# FuncDrawing

## 开发环境

* java Jdk1.8

* windows 10

* maven 3.6.3 (引入第三方库JFreechart用来语义分析绘图)

  ```xml
  <dependency>
      <groupId>org.jfree</groupId>
      <artifactId>jfreechart</artifactId>
      <version>1.5.3</version>
  </dependency>
  ```



* Idea

## 项目结构

### 词法分析

* Token_Type 枚举类,存储记号类型

  ```java
  public enum Token_Type {
      COMMENT,
      ID,
  
      ORIGIN, SCALE, ROT, IS, TO,
      STEP, DRAW, FOR, FROM,
      T,
  
      SEMICO, L_BRACKET, R_BRACKET, COMMA,
      PLUS,MINUS,MUL,DIV,POWER,
      FUNC,
      CONST_ID,
  
      NONTOKEN,
      ERRTOKEN
  }
  ```



* MathType 枚举类, 函数类型  sin,cos,tan ...

  ```java
  public enum MathType {
      SIN,
      COS,
      TAN,
      LN,
      EXP,
      SQRT
  }
  ```



* Token 存储记号

  ```java
  	private Token_Type type;
      private String lexeme;
      private double value;
      private MathType funcType;
      private Position where;
  ```



* 符号表:

  ![image-20221221162851049](C:\Users\zbq\AppData\Roaming\Typora\typora-user-images\image-20221221162851049.png)

* DFA

​	![5E2A30B208CB84E64EBDDAE0721AE213](F:\杂记文档\编译原理实验\5E2A30B208CB84E64EBDDAE0721AE213.png)

* interface Dfa

  ```java
  public interface Dfa {
      int get_start_state(); 
      int move(int start_src,char ch);
      Token_Type state_is_final(int state);
  }
  ```

* interface Scanner

  ```java
  public interface Scanner {
  
      void readFile(); //对FileReader对象进行初始化,通过该字符流对象读取字符
      Token getToken(); //返回一个记号
  
  }
  ```

### 语法分析

* interface Parser

  ```java
  public interface Parser {
      void initParser(String fileName); //初始化
      void parser();//执行语法分析
  }
  ```

* ExprNode 节点

  ```java
  	private Token_Type opCode;
      private ExprNode left,right;
      private ExprNode child;
  
      private MathType func;
      private double CaseConst;
      private Double CaseParmPtr;
  ```

* T_parser_stuff 保存语法分析中的数据

  ```java
  	private int indent;
      private ExprNode start;
      private ExprNode end;
      private ExprNode step;
      private ExprNode x;
      private ExprNode y;
      private ExprNode angle;
      private Token curr_token;
      private double parameter;
  ```

* 递归下降分析器

    * 为每个语句构造一个函数

    * ```java
    void for_statement(){}
    void origin_statement(){}
    void rot_statement(){}
    void scale_statement(){}
    ```

* 构造表达式节点

  ```java
  //方法重载
  ExprNode MakeExprNode(Token_Type opcode,ExprNode left,ExprNode right){}
  ExprNode MakeExprNode(Token_Type opcode,double value){}
  ExprNode MakeExprNode(Token_Type opcode){}
  ExprNode MakeExprNode(Token_Type opcode, MathType mtype,ExprNode node){}
  ```

### 语义分析

* interface Semantics

  ```java
  public interface Semantics {
      Point setOrigin(ExprNode x, ExprNode y);
      Point setScale(ExprNode x,ExprNode y);
  
      Double setRotate(ExprNode root);
      LinkedList<Point> DrawLoop(ExprNode start,
                          ExprNode end,
                          ExprNode step,
                          ExprNode x,
                          ExprNode y);
  }
  ```

* DrawingData类

  ```java
  	private Point origin; //原点
  
      private Double rot; //旋转角度 弧度
  
      private Point scale; //比例设置
      private LinkedList<Point> points; //要绘制的点集合
  ```

### ui

* Draw类

  ```java
  void addData()
  void show()
  调用Jfreechart,java的第三方绘图库
  
  ```

* Window类

    * 使用JFrame类,来作出窗口化界面,通过输入文件路径来执行绘图

