package datasturctures.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Version 1.0
 * Created by lll on 17/5/11.
 * Description 堆测试文件
 * copyright generalray4239@gmail.com
 */
public class HeapApp {
    HeapApp() {
    }

    public static void main(String[] var0) throws IOException {
        Heap var3 = new Heap(31);
        var3.insert(70);

        while(true) {
            while(true) {
                System.out.print("Enter first letter of ");
                System.out.print("show, insert, remove, change: ");
                char var5 = getChar();
                int var1;
                boolean var4;
                switch(var5) {
                    case 'c':
                        System.out.print("Enter current index of item: ");
                        var1 = getInt();
                        System.out.print("Enter new key: ");
                        int var2 = getInt();
                        var4 = var3.change(var1, var2);
                        if(!var4) {
                            System.out.println("Invalid index");
                        }
                        break;
                    case 'i':
                        System.out.print("Enter value to insert: ");
                        var1 = getInt();
                        var4 = var3.insert(var1);
                        if(!var4) {
                            System.out.println("Can\'t insert; heap full");
                        }
                        break;
                    case 'r':
                        if(!var3.isEmpty()) {
                            var3.remove();
                        } else {
                            System.out.println("Can\'t remove; heap empty");
                        }
                        break;
                    case 's':
                        var3.displayHeap();
                        break;
                    default:
                        System.out.println("Invalid entry\n");
                }
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader var0 = new InputStreamReader(System.in);
        BufferedReader var1 = new BufferedReader(var0);
        String var2 = var1.readLine();
        return var2;
    }

    public static char getChar() throws IOException {
        String var0 = getString();
        return var0.charAt(0);
    }

    public static int getInt() throws IOException {
        String var0 = getString();
        return Integer.parseInt(var0);
    }
}
