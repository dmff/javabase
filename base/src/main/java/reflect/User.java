package reflect;

/**
 * @author dmf
 * @date 2018/4/6
 */
public class User extends BaseModel {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void say(){
        System.out.println("my name is "+this.name);
    }
}
