package xyz.sally.account.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
