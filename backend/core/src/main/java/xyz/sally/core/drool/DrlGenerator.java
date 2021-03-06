package xyz.sally.core.drool;

import lombok.extern.slf4j.Slf4j;
import org.drools.compiler.compiler.DrlParser;

import java.io.StringReader;

/**
 * @author daitechang
 * @create: 2019-11-15
 **/
@Slf4j
public class DrlGenerator {

    public static String PACKAGE_TEMPLATE =
            "package xyz.sally.core.rules.uuid%s\n" +
                    "\n" +
                    "import xyz.sally.core.fact.*\n" +
                    "import xyz.sally.core.po.*\n" +
                    "import java.util.HashMap;\n" +
                    "import java.util.Map;\n";

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
                    "[when]input = $map:HashMap()\n" +
                    "[when]- {field:.+} {operator} {value:.+} = {field} {operator} {value}\n" +
                    "[when]resultInit = $ruleResult:RuleResult()\n" +
                    "[then]result=$ruleResult.hitRule(drools.getRule().getName())";

    public static final String INPUT = "xyz.sally.core.fact.input.Input";

    public static String genDrl(String dslr, String uuid) throws Exception {
        DrlParser parser = new DrlParser();
        String drl = parser.getExpandedDRL(dslr, new StringReader(String.format(DSL, INPUT + uuid)));

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
