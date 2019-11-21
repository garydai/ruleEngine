package xyz.sally.core.drool;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.sally.core.po.RuleResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author daitechang
 * @create: 2019-11-15
 **/
@Slf4j
@Component
public class Executor {

    @Autowired
    FactGenerator factGenerator;

    //public RuleResult execute(JSONArray rule) throws Exception {
    public static void main(String[] args) {

        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes); //通过自定义类加载器读取class文件的二进制流
                    return defineClass(name, bytes, 0, bytes.length);

                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };


        Object obj = null;
        try {
            obj = myLoader.loadClass("xyz.sally.core.drool.Executor").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object obj2 = new Executor();

        System.out.println(obj.getClass());
        System.out.println(obj2.getClass());
        System.out.println(obj instanceof Executor);
        System.out.println(obj2 instanceof Executor);

//        FactGenerator factGenerator = new FactGenerator(rule);
//
//        factGenerator.addField("age", "int");
//        Class clz = factGenerator.getClz();
//        Object object = clz.newInstance();
//
//        Field field = clz.getField("age");
//        field.setAccessible(true);
//        field.setInt(object, 111);
//
//        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//
//        try (Reader strReader = new StringReader(drl)) {
//            kbuilder.add(ResourceFactory.newReaderResource(strReader), ResourceType.DRL);
//        }
//
//        if (kbuilder.hasErrors()) {
//            log.error("execute", kbuilder.getErrors().toString());
//            throw new RuntimeException("Unable to compile drool rules.");
//        }
//
//        Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
//        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
//        kbase.addKnowledgePackages(pkgs);
//        StatefulKnowledgeSession kSession = kbase.newStatefulKnowledgeSession();
//
//        kSession.insert(object);
//        RuleResult ruleResult = new RuleResult();
//        kSession.insert(ruleResult);
//        kSession.fireAllRules();//执行规则
//        kSession.dispose();
    }

}
