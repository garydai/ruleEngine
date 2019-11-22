package xyz.sally.engine.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.sally.common.api.Response;
import xyz.sally.common.enums.ResponseCode;
import xyz.sally.core.drool.DslrGenerator;
import xyz.sally.core.po.InputMeta;
import xyz.sally.engine.dao.RuleDao;
import xyz.sally.engine.dmo.Rule;
import xyz.sally.engine.service.RuleService;
import xyz.sally.engineapi.domain.dto.RuleDto;
import xyz.sally.engineapi.domain.request.RuleRequest;
import xyz.sally.engineapi.domain.response.RuleDtoResponse;

import java.util.UUID;

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

    //@Autowired
    //private DslrGenerator dslrGenerator;


    public Response insertRule(RuleRequest ruleRequest) throws Exception {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        InputMeta inputMeta = new InputMeta();
        inputMeta.rules = ((JSONObject) JSONObject.parse(ruleRequest.getInput())).getJSONArray("rules");
        String dslr = DslrGenerator.genDslr(inputMeta);

        Rule rule = Rule.builder()
                .input(ruleRequest.getInput())
                .uuid(uuid)
                .drl(dslr)
                .build();

        return ruleDao.insert(rule) == 1 ? new Response(ResponseCode.SUCCESS) : new Response(ResponseCode.FAIL);
    }

    /**/
    public RuleDtoResponse findLastRule() {
        QueryWrapper<Rule> qryWrapper = new QueryWrapper<Rule>();
        qryWrapper.orderByDesc("update_time");
        qryWrapper.last("limit 1");
        Rule rule = ruleDao.selectOne(qryWrapper);

        return new RuleDtoResponse(modelMapper.map(rule, RuleDto.class));
    }

    public Response saveRuleById(Integer id, RuleRequest ruleRequest) throws Exception {
        return null;
    }
}
