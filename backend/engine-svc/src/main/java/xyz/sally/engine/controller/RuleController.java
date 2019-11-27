package xyz.sally.engine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.sally.common.api.Response;
import xyz.sally.engine.service.RuleService;
import xyz.sally.engineapi.domain.request.ExecuteRequest;
import xyz.sally.engineapi.domain.request.RuleRequest;
import xyz.sally.engineapi.domain.response.RuleDtoResponse;

import javax.validation.Valid;

/**
 * @author daitechang
 * @create: 2019-11-21
 **/
@RestController
@RequestMapping("/sally/v1/rule")
public class RuleController {

    @Autowired
    RuleService ruleService;

    @GetMapping(path = "/latest")
    RuleDtoResponse getRule() {
        return ruleService.findLastRule();
    }

    @PostMapping()
    Response insertRule(@RequestBody @Valid RuleRequest ruleRequest) throws Exception {
        return ruleService.insertRule(ruleRequest);
    }

    @PostMapping("/execute")
    Response execute(@RequestBody @Valid ExecuteRequest executeRequest) throws Exception {
        return ruleService.execute(executeRequest);
    }
}
