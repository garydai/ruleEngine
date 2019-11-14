package xyz.sally.variablesapi.domain.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.sally.common.api.Response;
import xyz.sally.common.enums.ResponseCode;
import xyz.sally.variablesapi.domain.dto.VariableDto;

import java.util.HashMap;
import java.util.List;

/**
 * @author daitechang
 * @create: 2019-11-07
 **/
@Data
public class VariableDtoResponse extends Response {
    private HashMap<String, List<VariableDto>> data;

    public VariableDtoResponse(List<VariableDto> idata) {
        super(ResponseCode.SUCCESS);
        data = new HashMap<String, List<VariableDto>>();
        data.put("list", idata);
    }
}
