package xyz.sally.variablesapi.domain.request;

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
public class AddVariableRequest {
    /**
     * 变量名
     */
    @NotBlank
    private String name;
    /**
     * 类型
     */
    @NotBlank
    private String type;
    /**
     * 描述
     */
    @NotBlank
    private String description;
}
