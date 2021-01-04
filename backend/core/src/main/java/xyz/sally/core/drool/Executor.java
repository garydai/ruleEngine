package xyz.sally.core.drool;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import xyz.sally.core.po.InputMeta;
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
public class Executor {

    public static RuleResult execute(InputMeta inputMeta) throws Exception {

        // 根据变量自动生成drools的输入类
        FactGenerator factGenerator = new FactGenerator(inputMeta);
        Class clz = factGenerator.getClz();
        Object object = clz.newInstance();

        // 设置field值
        for (Object ob : inputMeta.getVar()) {
            String type = ((JSONObject) ob).getString("type");
            Field field = clz.getField(((JSONObject) ob).getString("name"));
            field.setAccessible(true);
            switch (type) {
                case "int":
                    field.setInt(object, ((JSONObject) ob).getInteger("value"));
                    break;
                case "float":
                    field.setFloat(object, ((JSONObject) ob).getFloatValue("value"));
                    break;
                case "string":
                    field.set(object, ((JSONObject) ob).getString("value"));
                    break;
                default:
                    field.set(object, ((JSONObject) ob).get("value"));
                    break;
            }
        }

        log.debug("fact: {}", object);

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        try (Reader strReader = new StringReader(inputMeta.getDrl())) {
            kbuilder.add(ResourceFactory.newReaderResource(strReader), ResourceType.DRL);
        }

        if (kbuilder.hasErrors()) {
            log.error("execute {}", kbuilder.getErrors().toString());
            throw new RuntimeException("Unable to compile drool rules.");
        }

        Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(pkgs);
        StatefulKnowledgeSession kSession = kbase.newStatefulKnowledgeSession();

        kSession.insert(object);
        RuleResult ruleResult = new RuleResult();
        kSession.insert(ruleResult);
        kSession.fireAllRules();//执行规则
        kSession.dispose();

        return ruleResult;
    }
}
