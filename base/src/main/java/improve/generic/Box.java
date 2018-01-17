package improve.generic;

/**
 * @author dmf
 * @date 2018/1/7
 */
public class Box<T> {

    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args){
        Box<String> box = new Box<String>();
        box.setT("aaa");
        System.out.println(box.getT());
    }
}
