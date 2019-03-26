/**
 * Version 1.0
 * Created by lll on 2019/2/18.
 * Description
 * <pre>
 *     二进制
 * </pre>
 * copyright generalray4239@gmail.com
 */
public class binaryTest {

    /**
     * 通过位运算判断奇偶数
     *
     * 原理：偶数的二进制最后一位总是0，奇数的二进制最后一位总是1，
     *      因此我们可以将某个数的二进制和1的二进制进行与操作。返回的📚要么是0要么是1；
     *
     */
    private static void testNumber(){
        for(int i=0;i<10;i++){
            if((i&1)==0){
                System.out.println(i+"-----是偶数");
            }else{
                System.out.println(i+"-----是奇数");
            }
        }
    }

    /**
     * 交换两个数
     */
    private static void switchNum(){
        int x=1,y=2;
        x = (x^y);
        y = x^y;
        x = x^y;
        System.out.println("x====="+x+"----y==="+y);
    }




    /**
     * @param args
     */
    public static void main(String[] args) {
//        testNumber();
        switchNum();
    }
}
