package xyz.sally.variables.service;

import xyz.sally.common.api.Response;
import xyz.sally.variablesapi.domain.dto.VariableDto;
import xyz.sally.variablesapi.domain.request.AddVariableRequest;

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

    /**
     * 新增变量
     *
     * @param addVariableRequest
     * @return
     */
    Response addVariable(AddVariableRequest addVariableRequest);

    /**
     * 删除变量
     *
     * @param id
     * @return
     */
    Response deleteVariable(Integer id);
}
