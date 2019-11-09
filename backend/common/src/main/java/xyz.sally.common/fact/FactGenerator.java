package xyz.sally.common.fact;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.lang.reflect.Field;

/**
 * @author daitechang
 * @create: 2019-11-08
 **/
public class FactGenerator {

    public static void gen() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("xyz.sally.commom.fact.Input");
        ctClass.addField(CtField.make("public int age;", ctClass));
        Class clz = ctClass.toClass();

        Object object = clz.newInstance();


        Field field = clz.getField("age");
        field.setAccessible(true);
        field.setInt(object, 111);


        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");


        kSession.insert(object);//插入
        kSession.fireAllRules();//执行规则
        kSession.dispose();


        System.out.println(object);
    }

    public static void main(String[] args) throws Exception {
        gen();
    }
}
