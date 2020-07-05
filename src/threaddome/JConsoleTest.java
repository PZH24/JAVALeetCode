package threaddome;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengzhihui
 */
public class JConsoleTest {
        static class OOMObject{
            public byte[] placeholder = new byte[64 * 1024];//一个对象为64K
        }
        public static void main(String[] args){
            List<OOMObject> list = new ArrayList<>();
            //添加1000次
            for (int i = 0;i < 1000 ;i++ ) {
                //稍作延迟，使得监控曲线容易观察
                try{
                    Thread.sleep(50);
                }catch(Exception e){

                }

                list.add(new OOMObject());

            }
            System.gc();
        }
}
