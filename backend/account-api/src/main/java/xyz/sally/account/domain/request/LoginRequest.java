package xyz.sally.account.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
    @NotBlank
    private String username;
    /**
     * 密码
     */
    @NotBlank
    private String password;
}
