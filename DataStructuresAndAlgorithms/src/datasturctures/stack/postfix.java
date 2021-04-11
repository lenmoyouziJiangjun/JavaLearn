package datasturctures.stack;

import java.io.*;              // for I/O


/**
 * 定义栈结构
 */
class Stack {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public Stack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int j) {
        stackArray[++top] = j;
    }

    public int pop() {
        return stackArray[top--];
    }

    public int peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public int size() {
        return top + 1;
    }

    public int peekN(int n) {
        return stackArray[n];
    }

    public void displayStack(String s) {
        System.out.print(s);
        System.out.print("Stack (bottom-->top): ");
        for (int j = 0; j < size(); j++) {
            System.out.print(peekN(j));
            System.out.print(' ');
        }
        System.out.println("");
    }
}

/**
 * 计算后缀表达式的值
 */
class ParsePost {
    private Stack theStack;
    private String input;

    public ParsePost(String s) {
        input = s;
    }

    public int doParse() {
        theStack = new Stack(20);
        char ch;
        int j;
        int num1, num2, interAns;

        for (j = 0; j < input.length(); j++) {
            ch = input.charAt(j);              // read from input
            theStack.displayStack("" + ch + " ");  // *diagnostic*
            if (ch >= '0' && ch <= '9') {         // if it's a number
                theStack.push((int) (ch - '0'));
            } else {                              // it's an operator

                num2 = theStack.pop();          // pop operands
                num1 = theStack.pop();
                switch (ch) {                      // do arithmetic
                    case '+':
                        interAns = num1 + num2;
                        break;
                    case '-':
                        interAns = num1 - num2;
                        break;
                    case '*':
                        interAns = num1 * num2;
                        break;
                    case '/':
                        interAns = num1 / num2;
                        break;
                    default:
                        interAns = 0;
                }  // end switch
                theStack.push(interAns);        // push result
            }  // end else
        }  // end for
        interAns = theStack.pop();            // get answer
        return interAns;
    }  // end doParse()
}  // end class ParsePost

////////////////////////////////////////////////////////////////
class PostfixApp {
    public static void main(String[] args) throws IOException {
        String input;
        int output;

        while (true) {
            System.out.print("Enter postfix: ");
            System.out.flush();
            input = getString();         // read a string from kbd
            if (input.equals(""))       // quit if [Enter]
                break;
            // make a parser
            ParsePost aParser = new ParsePost(input);
            output = aParser.doParse();  // do the evaluation
            System.out.println("Evaluates to " + output);
        }  // end while
    }  // end main()

    //--------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
//--------------------------------------------------------------
}  // end class PostfixApp
////////////////////////////////////////////////////////////////
