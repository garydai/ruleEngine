package xyz.sally.variables.service;

import xyz.sally.variablesapi.domain.dto.VariableDto;

import java.util.List;

/**
 * @author daitechang
 * @create: 2019-11-05
 **/
public interface VariableService {
    /**
     * 获取所有变量
     *
     * @return
     */
    List<VariableDto> listVariable();
}
