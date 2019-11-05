package xyz.sally.account.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.sally.account.dao.AccountDao;
import xyz.sally.account.domain.dmo.Account;
import xyz.sally.account.domain.dto.AccountDto;
import xyz.sally.account.service.AccountService;

import java.util.HashMap;
import java.util.Map;

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

    public AccountDto getAccountByUsername(final String username) {
        return this.getAccountDto(accountDao.findAccountByName(username));
    }

    private AccountDto getAccountDto(Account account) {
        if (null == account) {
            return new AccountDto();
        }

        return modelMapper.map(account, AccountDto.class);
    }
}
