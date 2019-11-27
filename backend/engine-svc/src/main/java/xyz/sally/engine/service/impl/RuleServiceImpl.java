package xyz.sally.engine.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.drools.core.impl.KnowledgeBaseImpl;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.kie.internal.utils.KieHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.sally.common.api.Response;
import xyz.sally.common.enums.ResponseCode;
import xyz.sally.core.drool.DrlGenerator;
import xyz.sally.core.drool.DslrGenerator;
import xyz.sally.core.drool.Executor;
import xyz.sally.core.po.InputMeta;
import xyz.sally.core.po.RuleResult;
import xyz.sally.engine.dao.RuleDao;
import xyz.sally.engine.dmo.Rule;
import xyz.sally.engine.service.RuleService;
import xyz.sally.engineapi.domain.dto.RuleDto;
import xyz.sally.engineapi.domain.dto.RuleResultDto;
import xyz.sally.engineapi.domain.request.ExecuteRequest;
import xyz.sally.engineapi.domain.request.RuleRequest;
import xyz.sally.engineapi.domain.response.RuleDtoResponse;
import xyz.sally.engineapi.domain.response.RuleResultResponse;
import xyz.sally.variablesapi.client.VariablesClient;
import xyz.sally.variablesapi.domain.dto.VariableDto;
import xyz.sally.variablesapi.domain.response.VariableDtoResponse;

import java.io.Reader;
import java.io.StringReader;
import java.util.*;

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

    @Autowired
    private VariablesClient variablesClient;

    //@Autowired
    //private DslrGenerator dslrGenerator;

    @Override
    public Response insertRule(RuleRequest ruleRequest) throws Exception {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        String dslr = DslrGenerator.genDslr(((JSONObject) JSONObject.parse(ruleRequest.getInput())).getJSONArray("rules"));

        String drl = DrlGenerator.genDrl(dslr, uuid);

        Rule rule = Rule.builder()
                .input(ruleRequest.getInput())
                .uuid(uuid)
                .dsrl(dslr)
                .drl(drl)
                .build();

        return ruleDao.insert(rule) == 1 ? new Response(ResponseCode.SUCCESS) : new Response(ResponseCode.FAIL);
    }

    @Override
    public RuleDtoResponse findLastRule() {
        QueryWrapper<Rule> qryWrapper = new QueryWrapper<Rule>();
        qryWrapper.orderByDesc("update_time");
        qryWrapper.last("limit 1");
        Rule rule = ruleDao.selectOne(qryWrapper);
        if (null == rule) {
            return new RuleDtoResponse(new RuleDto());
        }

        return new RuleDtoResponse(modelMapper.map(rule, RuleDto.class));
    }

    @Override
    public Response saveRuleById(Integer id, RuleRequest ruleRequest) throws Exception {
        return null;
    }

    @Override
    public Response execute(ExecuteRequest executeRequest) throws Exception {
        if (null == executeRequest) {
            return new Response(ResponseCode.FAIL);
        }

        VariableDtoResponse variableDtoResponse = variablesClient.getVariables();
        List<VariableDto> list = variableDtoResponse.getData().get("list");
        HashMap<String, VariableDto> variableMap = new HashMap<String, VariableDto>();
        for (VariableDto variableDto : list) {
            variableMap.put(variableDto.getName(), variableDto);
        }

        JSONObject vars = JSONObject.parseObject(executeRequest.getVar());
        JSONArray varJsonArray = new JSONArray();
        for (Map.Entry<String, Object> entry : vars.entrySet()) {
            JSONObject v = new JSONObject();
            v.put("name", entry.getKey());
            v.put("value", entry.getValue());
            v.put("type", variableMap.get(entry.getKey()).getType());
            varJsonArray.add(v);
        }

        RuleDtoResponse ruleDtoResponse = findLastRule();

        InputMeta inputMeta = new InputMeta();
        inputMeta.setDrl(ruleDtoResponse.getData().getDrl());
        inputMeta.setUuid(ruleDtoResponse.getData().getUuid());
        inputMeta.setVar(varJsonArray);

        return new RuleResultResponse(RuleResultDto.builder().hitRules(Executor.execute(inputMeta).getHitRules()).build());
    }

    public static void main(String[] args) throws Exception {
        new RuleServiceImpl().execute(null);
    }
}
