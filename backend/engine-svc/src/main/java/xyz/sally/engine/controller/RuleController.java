package xyz.sally.engine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.sally.engine.service.RuleService;
import xyz.sally.engineapi.domain.response.RuleDtoResponse;

/**
 * @author daitechang
 * @create: 2019-11-21
 **/
@RestController
@RequestMapping("/sally/v1/rule")
public class RuleController {

    @Autowired
    RuleService ruleService;

    @GetMapping(path = "/{id}")
    RuleDtoResponse getRule(@PathVariable Integer id) {
        return ruleService.findRuleById(id);
    }
}
