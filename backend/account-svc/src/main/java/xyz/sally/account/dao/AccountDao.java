package xyz.sally.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import xyz.sally.account.domain.dmo.Account;

/**
 * @author daitechang
 * @create: 2019-11-05
 **/
@Component
public interface AccountDao extends BaseMapper<Account> {
    /**
     * 根据姓名获取用户信息
     *
     * @param username
     * @return
     */
    Account findAccountByName(String username);
}
