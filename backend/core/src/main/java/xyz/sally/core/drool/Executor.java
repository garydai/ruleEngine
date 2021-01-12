package xyz.sally.core.drool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.io.ResourceType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import xyz.sally.core.po.InputMeta;
import xyz.sally.core.po.RuleResult;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;

/**
 * @author daitechang
 * @create: 2019-11-15
 **/
@Slf4j
public class Executor {

    public static RuleResult execute(InputMeta inputMeta) throws Exception {

        HashMap<String, Object> fact = new HashMap<>();
        // 设置field值
        for (Object ob : inputMeta.getVar()) {
            String type = ((JSONObject) ob).getString("type");
            String varName = ((JSONObject) ob).getString("name");
            switch (type) {
                case "int":
                    fact.put(varName, ((JSONObject) ob).getInteger("value"));
                    break;
                case "float":
                    fact.put(varName, ((JSONObject) ob).getFloatValue("value"));
                    break;
                case "string":
                    fact.put(varName, ((JSONObject) ob).getString("value"));
                    break;
                default:
                    // TODO
                    fact.put(varName, ((JSONObject) ob).get("value"));
                    break;
            }
        }

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

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

        kSession.insert(fact);
        RuleResult ruleResult = new RuleResult();
        kSession.insert(ruleResult);
        // 执行规则
        kSession.fireAllRules();
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
                "import java.util.HashMap;\n" +
                "import java.util.Map;" +
                "\n" +
                "\n" +
                "rule \"test\" \n" +
                "when \n" +
                "$ruleResult:RuleResult() \n" +
                "$map:HashMap(this[\"sex\"]  >=  1)\n" +

                //"eval($map[\"sex\"]  >=  1) \n" +
                "\n" +
                "then \n" +
                "\n" +
                "System.out.println($map);" +
                "$ruleResult.hitRule(drools.getRule().getName()) ;\n" +
                "end\n" +
                "rule \"test2\" \n" +
                "when \n" +
                "$ruleResult:RuleResult() \n" +
                //"$map:HashMap()\n" +
                "$map:HashMap(this[\"sex\"]  >  2  &&  this[\"age\"] < 12) \n" +
                "then $ruleResult.hitRule(drools.getRule().getName()) ;\n" +
                "end\n"
        );
        System.out.println();

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
