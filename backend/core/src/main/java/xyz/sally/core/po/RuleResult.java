package xyz.sally.core.po;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daitechang
 * @create: 2019-11-15
 **/
@Data
public class RuleResult {

    private LinkedHashMap<String, Boolean> hitRules;

    public RuleResult() {
        hitRules = new LinkedHashMap<String, Boolean>();
    }

    public void hitRule(String ruleName) {
        hitRules.put(ruleName, true);
    }
}
