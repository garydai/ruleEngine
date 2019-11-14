package xyz.sally.variablesapi.domain.dto;

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
public class VariableDto {
    /**
     * 类型
     */
    private String type;
    /**
     * 变量名
     */
    private String name;
    /**
     * 描述
     */
    private String description;
}
