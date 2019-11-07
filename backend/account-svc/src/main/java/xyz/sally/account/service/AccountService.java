package xyz.sally.account.service;

import xyz.sally.account.domain.dto.AccountDto;
import xyz.sally.account.domain.request.LoginRequest;

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

    /**
     * 根据用户名、密码查找账户
     *
     * @param loginRequest
     * @return
     */
    AccountDto getAccountByUsernamePwd(LoginRequest loginRequest);

    /**
     * 登陆
     *
     * @param loginRequest
     * @return
     */
    boolean login(LoginRequest loginRequest);
}
