package xyz.sally.core.drool;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import xyz.sally.core.po.InputMeta;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * @author daitechang
 * @create: 2019-11-08
 **/
public class FactGenerator {

    private CtClass ctClass;
    private Class clz;

    public FactGenerator(InputMeta inputMeta) {
        ClassPool pool = ClassPool.getDefault();
        ctClass = pool.makeClass(String.format(inputMeta.getClassName(), UUID.randomUUID().toString().replaceAll("-", "")));
    }

    public void addField(String name, String type) throws Exception {
        ctClass.addField(CtField.make("public " + type + " " + name + "; ", ctClass));
    }

    public Class getClz() throws Exception {
        return ctClass.toClass();
    }


//    public void gen() throws Exception {
//
//        ctClass.addField(CtField.make("public int age;", ctClass));
//        Class clz = ctClass.toClass();
//
//        Object object = clz.newInstance();
//
//
//        Field field = clz.getField("age");
//        field.setAccessible(true);
//        field.setInt(object, 111);
//
//
//        KieServices ks = KieServices.Factory.get();
//        KieContainer kContainer = ks.getKieClasspathContainer();
//        KieSession kSession = kContainer.newKieSession("ksession-rules");
//
//
//        kSession.insert(object);//插入
//        kSession.fireAllRules();//执行规则
//        kSession.dispose();
//
//
//        System.out.println(object);
//    }

    public static void main(String[] args) throws Exception {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);

        String str1 = "abc";
        String str2 = new String("def");
        String str3 = "abc";
        String str4 = str2.intern();
        String str5 = "def";
        System.out.println(str1 == str3);//true
        System.out.println(str2 == str4);//false
        System.out.println(str4 == str5);//true


    }
}
