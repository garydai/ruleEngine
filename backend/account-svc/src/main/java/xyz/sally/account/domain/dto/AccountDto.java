package xyz.sally.account.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.sally.account.domain.dmo.Account;

/**
 * @author daitechang
 * @create: 2019-11-05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    /**
     * 用户名
     */
    private String username;
    /**
     * 主建id
     */
    private Integer id;

}
