package xyz.sally.engineapi.domain.dto;

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
public class RuleDto {
    /**
     * 前端输入
     */
    private String input;
    /**
     * 主建id
     */
    private Integer id;
    /**
     * drl
     */
    private String drl;
    /**
     * uuid
     */
    private String uuid;

}
