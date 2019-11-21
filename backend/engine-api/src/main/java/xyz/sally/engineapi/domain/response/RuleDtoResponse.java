package xyz.sally.engineapi.domain.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.sally.common.api.Response;
import xyz.sally.common.enums.ResponseCode;
import xyz.sally.engineapi.domain.dto.RuleDto;

/**
 * @author daitechang
 * @create: 2019-11-07
 **/
@Data
@NoArgsConstructor
@Builder
public class RuleDtoResponse extends Response {
    private RuleDto data;

    public RuleDtoResponse(RuleDto ruleDto) {
        super(ResponseCode.SUCCESS);
        this.data = ruleDto;
    }
}
