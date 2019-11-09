package xyz.sally.account.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.sally.account.domain.dto.AccountDto;
import xyz.sally.common.api.Response;
import xyz.sally.common.enums.ResponseCode;

/**
 * @author daitechang
 * @create: 2019-11-07
 **/
@Data
@NoArgsConstructor
@Builder
public class AccountDtoResponse extends Response {
    private AccountDto data;

    public AccountDtoResponse(AccountDto accountDto){
        super(ResponseCode.SUCCESS);
        this.data = accountDto;
    }
}
