package xyz.sally.engine.service.impl;

import org.kie.api.definition.rule.Rule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.sally.common.api.Response;
import xyz.sally.engine.dao.RuleDao;
import xyz.sally.engine.service.RuleService;
import xyz.sally.engineapi.domain.dto.RuleDto;
import xyz.sally.engineapi.domain.response.RuleDtoResponse;

/**
 * @author daitechang
 * @create: 2019-11-21
 **/
@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    RuleDao ruleDao;

    @Autowired
    private ModelMapper modelMapper;

    public Response insertRule() {
        return null;
    }

    public RuleDtoResponse findRuleById(Integer id) {
        return new RuleDtoResponse(modelMapper.map(ruleDao.selectById(id), RuleDto.class));
    }
}
