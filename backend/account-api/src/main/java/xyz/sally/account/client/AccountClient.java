package xyz.sally.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xyz.sally.account.domain.dto.AccountDto;
import xyz.sally.account.domain.request.LoginRequest;
import xyz.sally.account.domain.response.AccountDtoResponse;
import xyz.sally.common.api.Response;

import javax.validation.Valid;

/**
 * @author daitechang
 * @create: 2019-11-06
 **/
@FeignClient(name = "account-service", path = "/sally/v1/account")
public interface AccountClient {

    /**
     * 登陆
     *
     * @param loginRequest
     * @return
     */
    @PostMapping(path = "/login")
    AccountDtoResponse login(@RequestBody @Valid LoginRequest loginRequest);
}
