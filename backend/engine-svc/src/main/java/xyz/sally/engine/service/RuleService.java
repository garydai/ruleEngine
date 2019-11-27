package xyz.sally.engine.service;

import xyz.sally.common.api.Response;
import xyz.sally.engineapi.domain.request.ExecuteRequest;
import xyz.sally.engineapi.domain.request.RuleRequest;
import xyz.sally.engineapi.domain.response.RuleDtoResponse;

/**
 * @author daitechang
 * @create: 2019-11-15
 **/
public interface RuleService {

    public Response insertRule(RuleRequest ruleRequest) throws Exception;

    public RuleDtoResponse findLastRule();

    public Response saveRuleById(Integer id, RuleRequest ruleRequest) throws Exception;

    Response execute(ExecuteRequest executeRequest) throws Exception;
}
