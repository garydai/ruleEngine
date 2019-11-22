package xyz.sally.core.drool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import xyz.sally.core.po.InputMeta;

import java.util.UUID;

/**
 * @author daitechang
 * @create: 2019-11-15
 **/
//@Component
public class DslrGenerator {
    public static String DSLR_TEMPLATE =
            "rule \"%s\" \n" +
                    "when \n" +
                    "resultInit \n" +
                    "input \n" +
                    "- %s\n" +
                    "then result ;\n" +
                    "end\n";

    public static String genDslr(InputMeta inputMeta) throws Exception {
        StringBuilder sb = new StringBuilder();

        for (Object item : inputMeta.rules) {
            sb.append(String.format(DSLR_TEMPLATE, ((JSONObject) item).getString("name"),
                    getCondition(((JSONObject) item).getJSONArray("rule"))));
        }

        return sb.toString();
    }

    private static String getCondition(JSONArray input) {
        StringBuilder sb = new StringBuilder();

        for (Object item : input) {
            JSONObject object = (JSONObject) item;
            String left = object.getString("l");
            String right = object.getString("r");
            sb.append(String.format("%s %s %s ", left, object.get("o"), right));
        }
        return sb.toString();
    }
}
