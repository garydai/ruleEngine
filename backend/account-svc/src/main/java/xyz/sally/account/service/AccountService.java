package xyz.sally.account.service;

import xyz.sally.account.domain.dto.AccountDto;

/**
 * @author daitechang
 * @create: 2019-11-05
 **/
public interface AccountService {
    /**
     * 根据用户名查找账户
     *
     * @param username
     * @return
     */
    AccountDto getAccountByUsername(String username);
}
