package jdk;

import com.google.common.collect.Lists;
import util.Map2BeanUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author dmf
 * @date 2018/4/1
 */
public class StreamTest {


    public static void main(String[] args) {
        StreamTest test = new StreamTest();
        List<User> userList = Lists.newArrayList();
        for(int i=0;i<10;i++){
            User user = test.new User(i,"aaa"+i,i+10);
            userList.add(user);
        }
        Map<String, User> userMap = userList.stream().collect(Collectors.toMap(User::getName, Function.identity()));
        System.out.println(userMap);
    }


    private class User{
        int id;
        String name;
        int age;

        public User(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
