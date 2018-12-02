package reflect;

import org.junit.Test;

/**
 * @author dmf
 * @date 2018/4/6
 */
public class ReflectTest {

    @Test
    public void test1(){
        BaseModel model = new User();
        //java运行时获取的是实际类型，通过动态链接解析实现
        System.out.println(model.getClass());
    }
}
