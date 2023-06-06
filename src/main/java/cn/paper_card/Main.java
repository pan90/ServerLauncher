package cn.paper_card;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;


public class Main {
    public static void main(String[] args) {

        // Purpur
        // Main-Class: io.papermc.paperclip.Main

        final CustomClassLoader customClassLoader;
        try {
            customClassLoader = new CustomClassLoader();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        final Class<?> aClass;
        try {
            aClass = Class.forName("io.papermc.paperclip.Main", true, customClassLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        final Method main;
        try {
            main = aClass.getMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Server Main Begin.");
        try {
            main.invoke(null, (Object) args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Server Main End.");
    }
}
