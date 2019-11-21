package xyz.sally.engineapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xyz.sally.engineapi.domain.response.RuleDtoResponse;

import javax.validation.Valid;

/**
 * @author daitechang
 * @create: 2019-11-06
 **/
@FeignClient(name = "engine-service", path = "/sally/v1/rule")
public interface EngineClient {

    @GetMapping(path = "/{id}")
    RuleDtoResponse getRule(@PathVariable Integer id);
}
