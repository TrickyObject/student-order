package mts.dao;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimpleRunner {
    public static void main(String[] args) {
        SimpleRunner sr = new SimpleRunner();

        sr.rynTests();
    }

    private void rynTests() {

        try {
            Class cl = Class.forName("mts.dao.DictionaryDaoImplTest");

            Constructor cst =  cl.getConstructor();
            Object entity = cst.newInstance();

            Method[] methods = cl.getMethods();
            for (Method m : methods) {

                Test annotation = m.getAnnotation(Test.class);
                if (annotation != null) {
                    m.invoke(entity);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
