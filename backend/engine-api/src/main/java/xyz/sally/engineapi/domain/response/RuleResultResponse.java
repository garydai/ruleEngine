package xyz.sally.engineapi.domain.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.sally.common.api.Response;
import xyz.sally.common.enums.ResponseCode;
import xyz.sally.engineapi.domain.dto.RuleDto;
import xyz.sally.engineapi.domain.dto.RuleResultDto;

/**
 * @author daitechang
 * @create: 2019-11-27
 **/
@Data
@NoArgsConstructor
@Builder
public class RuleResultResponse extends Response {
    private RuleResultDto data;

    public RuleResultResponse(RuleResultDto ruleDto) {
        super(ResponseCode.SUCCESS);
        this.data = ruleDto;
    }
}
