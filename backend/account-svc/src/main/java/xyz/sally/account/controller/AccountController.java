package xyz.sally.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.sally.account.domain.dto.AccountDto;
import xyz.sally.account.domain.response.AccountDtoResponse;
import xyz.sally.account.service.AccountService;
import xyz.sally.account.domain.request.LoginRequest;
import xyz.sally.common.api.Response;

/**
 * @author daitechang
 * @create: 2019-11-05
 **/

@RestController
@RequestMapping("/sally/v1/account")
public class AccountController {
    @Autowired
    AccountService accountService;

//    @GetMapping
//    public AccountDto getAccountByUsername(String name) {
//        return accountService.getAccountByUsername(name);
//    }

    @PostMapping
    public AccountDtoResponse login(@RequestBody LoginRequest request) {
        return new AccountDtoResponse(accountService.getAccountByUsernamePwd(request));
    }

    @PostMapping(path = "/login")
    public AccountDtoResponse login2(@RequestBody LoginRequest request) {
        AccountDto accountDto = AccountDto.builder().username("test").build();

        return new AccountDtoResponse(accountDto);
    }
}
