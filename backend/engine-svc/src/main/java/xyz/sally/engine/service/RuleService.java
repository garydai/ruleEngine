package xyz.sally.engine.service;

import xyz.sally.common.api.Response;
import xyz.sally.engineapi.domain.response.RuleDtoResponse;

/**
 * @author daitechang
 * @create: 2019-11-15
 **/
public interface RuleService {

    public Response insertRule();

    /**
     * 根据id查找rule
     *
     * @param id
     * @return
     */
    public RuleDtoResponse findRuleById(Integer id);
}
