package xyz.sally.core.drool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.drools.compiler.compiler.DrlParser;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.core.util.StringUtils;
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
import xyz.sally.core.po.InputMeta;
import xyz.sally.core.po.RuleResult;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.UUID;

/**
 * @author daitechang
 * @create: 2019-11-15
 **/
@Slf4j
@Component
public class DrlGenerator {

    @Autowired
    DslrGenerator dslrGenerator;

    public static String PACKAGE_TEMPLATE =
            "package xyz.sally.core.rules.uuid%s\n" +
                    "\n" +
                    "import xyz.sally.core.fact.*\n" +
                    "import xyz.sally.core.po.*\n";

    public static String DSL =
            "[when][]lt = <\n" +
                    "[when][]le = <=\n" +
                    "[when][]ge = >=\n" +
                    "[when][]gt = >\n" +
                    "[when][]eq = ==\n" +
                    "[when][]ne = !=\n" +
                    "[when][]and = &&\n" +
                    "[when][]or = ||\n" +
                    "[when][]contains = contains\n" +
                    "[when][]notcontains = not contains\n" +
                    "[when]input = $input:%s()\n" +
                    "[when]- {field:[\\w\\.]+} {operator} {value:.+} = {field} {operator} {value}\n" +
                    "[when]resultInit = $ruleResult:RuleResult()\n" +
                    "[then]result=$ruleResult.hitRule(drools.getRule().getName())";

    private static final String INPUT = "xyz.sally.core.fact.input.Input";

    public String genDrl(JSONArray rule) throws Exception {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        InputMeta inputMeta = new InputMeta();
        inputMeta.setRule(rule);
        inputMeta.setClassName(INPUT + uuid);

        String dslr = dslrGenerator.genDslr(inputMeta);

        DrlParser parser = new DrlParser();
        String drl = parser.getExpandedDRL(dslr, new StringReader(String.format(DSL, inputMeta.className)));

        return String.format(PACKAGE_TEMPLATE, uuid) + drl;
    }

    public static void main(String[] args) {
//        String dsl = DSL;
//
//        String dslr = String.format(DSLR_TEMPLATE, "test", "age gt 12");
//        DrlParser parser = new DrlParser();
//        try {
//            String drl = parser.getExpandedDRL(dslr, new StringReader(dsl));
//            drl = PACKAGE_TEMPLATE + drl;
//
//           // FactGenerator factGenerator = new FactGenerator();
//            factGenerator.addField("age", "int");
//            Class clz = factGenerator.getClz();
//            Object object = clz.newInstance();
//
//            Field field = clz.getField("age");
//            field.setAccessible(true);
//            field.setInt(object, 111);
//
//            KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//
//            try (Reader strReader = new StringReader(drl)) {
//                kbuilder.add(ResourceFactory.newReaderResource(strReader), ResourceType.DRL);
//            }
//
//            if (kbuilder.hasErrors()) {
//                log.error("execute", kbuilder.getErrors().toString());
//                throw new RuntimeException("Unable to compile drool rules.");
//            }
//
//            Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
//            KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
//            kbase.addKnowledgePackages(pkgs);
//            StatefulKnowledgeSession kSession = kbase.newStatefulKnowledgeSession();
//
//            kSession.insert(object);
//            RuleResult ruleResult = new RuleResult();
//            kSession.insert(ruleResult);
//            kSession.fireAllRules();//执行规则
//            kSession.dispose();
//
//            System.out.println(drl);
//
//        } catch (DroolsParserException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
