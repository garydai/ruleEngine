package xyz.sally.variablesapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.sally.variablesapi.domain.response.VariableDtoResponse;

import javax.validation.Valid;

/**
 * @author daitechang
 * @create: 2019-11-06
 **/
@FeignClient(name = "variable-service", path = "/sally/v1/variables")
public interface VariablesClient {

    /**
     * 获取所有变量
     *
     * @return
     */
    @GetMapping()
    VariableDtoResponse getVariables();
}
