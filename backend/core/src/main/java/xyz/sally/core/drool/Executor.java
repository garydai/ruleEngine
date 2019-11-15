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

    public RuleResult execute(JSONArray rule) throws Exception {

        return null;
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
