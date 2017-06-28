package chainofresp.interceptor;/**
 * Created by liaoxueyan on 17/6/27.
 */

/**
 * Version 1.0
 * Created by lll on 17/6/27.
 * Description
 * copyright generalray4239@gmail.com
 */
public class NetOpe {

    Request mRequest;

    public NetOpe(){}

    public void setRequest(Request request){

    }

    public Request getRequest(){
       return mRequest;
    }

    public Response doHttpRequest(){
        return new Response();//测试代码
    }
}
