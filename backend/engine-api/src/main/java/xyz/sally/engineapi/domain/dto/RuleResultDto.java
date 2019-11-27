package xyz.sally.engineapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

/**
 * @author daitechang
 * @create: 2019-11-27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RuleResultDto {
    private LinkedHashMap<String, Boolean> hitRules;
}
