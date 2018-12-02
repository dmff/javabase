package jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author dmf
 * @date 2018/4/23
 * <p>
 * 通过字节码指令调用方法
 */
public class MethodInvokeTest {

    class GrandFather {
        void say() {
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather {
        @Override
        void say() {
            System.out.println("i am father");
        }
    }


    class Son extends Father {
        /**
         * 反射和methodHandles有什么区别
         */
        @Override
        void say() {
            try {
                //调用GrandFather的方法
                //申明方法类型，返回值，参数类型
                MethodType type = MethodType.methodType(void.class);
                //调用的类，方法名称，方法类型，
                MethodHandle method = MethodHandles.lookup().findSpecial(GrandFather.class, "say", type, getClass());
                method.invoke(this);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        //(new MethodInvokeTest().new Son()).say();

        String str = "hello world";
        Object invoke = getHandler().invoke(str, 1, 3);
        System.out.println(invoke);
        //要求更严格，需要返回string类型
        Object o = (String) getHandler().invokeExact(str, 1, 3);
        System.out.println(o);
    }

    public static MethodHandle getHandler() throws Throwable {
        MethodType type = MethodType.methodType(String.class, int.class, int.class);
        return MethodHandles.lookup().findVirtual(String.class, "substring", type);
        //Object invoke = getHandler().invoke(str, 1, 3);
    }


    /**
     * 查看字节码指令
     * @return
     */
    public int func() {
        int a = 10;
        int b = 20;
        int c = 30;
        return (a + b) * c;
    }


}
