package java8;

/**
 * 方法和构造函数的引用
 */
public interface Java8Reference<P extends Java8Reference.Person> {

    P create(String firstName, String lastName);

    public static void main(String[] args){
        //使用new执行构造方法
        Java8Reference<Person> reference = Person::new;
        Person person = reference.create("1", "2");
        System.out.println(person.firstName+":"+person.lastName);
    }


    static class Person {
        String firstName;
        String lastName;

        Person() {}

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }



}
