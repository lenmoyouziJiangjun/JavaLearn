package algorithms.utils;

/**
 * Version 1.0
 * Created by lll on 09/04/2018.
 * Description
 * copyright generalray4239@gmail.com
 */
public class PrintUtils {

    public static <T> void printArray(T[] ts) {
        int index=0;
        for(T t :ts){
            System.out.println("ts["+index+"]=="+t.toString());
            index++;
        }
    }

    public static <T> void printArray(T[] ts,int index) {
        System.out.println("index==="+index);
        for(int i=0;i<index;i++){
            T t = ts[i];

            System.out.println("ts["+index+"]=="+(t==null?"null":t.toString()));
            index++;
        }
    }

}
