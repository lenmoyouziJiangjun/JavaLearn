package javapattern.chainofresp.interceptor;/**
 * Created by liaoxueyan on 17/6/27.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Version 1.0
 * Created by lll on 17/6/27.
 * Description
 * copyright generalray4239@gmail.com
 */
public class NetUtil {
    public List<NetInterceptor> interceptors = new ArrayList<>();


    public void doHttpRequest(){
        if(interceptors!=null){
            for(NetInterceptor interceptor:interceptors){
//                javapattern.chainofresp.interceptor.intercept();
            }
        }
    }


}
