package xyz.sally.variables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.sally.common.api.Response;
import xyz.sally.variables.service.VariableService;
import xyz.sally.variablesapi.domain.response.VariableDtoResponse;


/**
 * @author daitechang
 * @create: 2019-11-05
 **/

@RestController
@RequestMapping("/sally/v1/variables")
public class VariablesController {

    @Autowired
    VariableService variableService;

    @GetMapping()
    public Response getVariables() {
        return new VariableDtoResponse(variableService.listVariable());
    }
}
