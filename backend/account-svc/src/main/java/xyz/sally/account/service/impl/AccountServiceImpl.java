package xyz.sally.account.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.sally.account.client.AccountClient;
import xyz.sally.account.dao.AccountDao;
import xyz.sally.account.domain.dmo.Account;
import xyz.sally.account.domain.dto.AccountDto;
import xyz.sally.account.service.AccountService;
import xyz.sally.account.domain.request.LoginRequest;

/**
 * @author daitechang
 * @create: 2019-11-05
 **/
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountClient accountClient;

    public AccountDto getAccountByUsername(final String username) {
        return this.getAccountDto(accountDao.findAccountByName(username));
    }

    public AccountDto getAccountByUsernamePwd(LoginRequest loginRequest) {
        AccountDto accountDto = (AccountDto) accountClient.login(loginRequest).getData();
        return accountDto;
    }

    private AccountDto getAccountDto(Account account) {
        if (null == account) {
            return new AccountDto();
        }

        return modelMapper.map(account, AccountDto.class);
    }
}
