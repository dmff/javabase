package jvm;

import java.io.Serializable;

/**
 * @author dmf
 * @date 2018/4/23
 */
public class Overload {
    
    /*public static void say(Object arg){
        System.out.println("hello object");
    }*/

    /*public static void say(int arg){
        System.out.println("hello int");
    }*/

   /* public static void say(long arg){
        System.out.println("hello long");
    }*/

    /*public static void say(Character arg){
        System.out.println("hello Character");
    }*/

    /*public static void say(char arg){
        System.out.println("hello char");
    }*/

    public static void say(char... arg){
        System.out.println("hello char...");
    }

  /*  public static void say(Serializable arg){
        System.out.println("hello Serializable");
    }*/

    //char->int->long->Character->Serializable->object->char...
    public static void main(String[] args){
        say('a');
    }
    
}
