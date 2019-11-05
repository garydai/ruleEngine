package xyz.sally.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;
import xyz.sally.account.domain.dto.AccountDto;
import xyz.sally.account.domain.request.LoginRequest;
import xyz.sally.account.service.AccountService;

import javax.validation.Valid;

/**
 * @author daitechang
 * @create: 2019-11-05
 **/

@RestController
@RequestMapping("/v1/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping
    public AccountDto getAccountByUsername(String name) {
        return accountService.getAccountByUsername(name);
    }

    @PostMapping
    public AccountDto getAccountByUsername(@RequestBody @Valid LoginRequest request) {
        //return accountService.getAccountByUsername(name);
    }

}
