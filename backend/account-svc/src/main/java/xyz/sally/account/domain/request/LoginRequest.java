package xyz.sally.account.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daitechang
 * @create: 2019-11-05
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
