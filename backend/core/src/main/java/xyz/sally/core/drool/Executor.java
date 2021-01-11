package xyz.sally.core.drool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.drools.core.impl.KnowledgeBaseImpl;
import org.kie.api.io.ResourceType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderConfiguration;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import xyz.sally.core.po.InputMeta;
import xyz.sally.core.po.RuleResult;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;

/**
 * @author daitechang
 * @create: 2019-11-15
 **/
@Slf4j
public class Executor {

    public static RuleResult execute(InputMeta inputMeta) throws Exception {

        // 根据变量自动生成drools的输入类
        FactGenerator factGenerator = new FactGenerator(inputMeta);
        //TODO 动态生成的class多，元空间会不断变大
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

        KnowledgeBuilderConfiguration knowledgeBuilderConfiguration =
                KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration(null, object.getClass().getClassLoader());
        knowledgeBuilderConfiguration.setProperty("drools.dump.dir", "./");
        knowledgeBuilderConfiguration.setProperty("drools.dialect.default", "JAVA");

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(knowledgeBuilderConfiguration);


        try (Reader strReader = new StringReader(inputMeta.getDrl())) {
            kbuilder.add(ResourceFactory.newReaderResource(strReader), ResourceType.DRL);
        }

        if (kbuilder.hasErrors()) {
            log.error("execute {}", kbuilder.getErrors().toString());
            throw new RuntimeException("Unable to compile drool rules.");
        }


        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addPackages(kbuilder.getKnowledgePackages());

        StatefulKnowledgeSession kSession = (StatefulKnowledgeSession) kbase.newKieSession();

        kSession.insert(object);
        RuleResult ruleResult = new RuleResult();
        kSession.insert(ruleResult);
        kSession.fireAllRules();//执行规则
        kSession.dispose();

        return ruleResult;
    }

    public static void main(String[] args) throws Exception {
        InputMeta inputMeta = new InputMeta();
        inputMeta.setUuid("4cad725e71f144d7b65f094d07710afe");
        inputMeta.setDrl("package xyz.sally.core.rules.uuid4cad725e71f144d7b65f094d07710afe\n" +
                "dialect \"java\"\n" +
                "import xyz.sally.core.fact.*\n" +
                "import xyz.sally.core.po.*\n" +
                "rule \"test\" \n" +
                "when \n" +
                "$ruleResult:RuleResult() \n" +
                "$input:xyz.sally.core.fact.input.Input4cad725e71f144d7b65f094d07710afe(sex  >=  1) \n" +
                "\n" +
                "then \n" +
                "\n" +
                " System.out.println($input);\n" +
                "$ruleResult.hitRule(drools.getRule().getName()) ;\n" +
                "end\n" +
                "rule \"test2\" \n" +
                "when \n" +
                "$ruleResult:RuleResult() \n" +
                "$input:xyz.sally.core.fact.input.Input4cad725e71f144d7b65f094d07710afe(sex  >  2  &&  age < 12) \n" +
                "then $ruleResult.hitRule(drools.getRule().getName()) ;\n" +
                "end\n");
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "age");
        jsonObject.put("type", "int");
        jsonObject.put("value", 4);
        jsonArray.add(jsonObject);
        jsonObject = new JSONObject();
        jsonObject.put("name", "sex");
        jsonObject.put("type", "int");
        jsonObject.put("value", 1);
        jsonArray.add(jsonObject);
        inputMeta.setVar(jsonArray);
        System.out.println(Executor.execute(inputMeta));

    }
}
