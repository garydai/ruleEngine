package xyz.sally.variables.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xyz.sally.variables.domain.dmo.Variable;
import xyz.sally.variablesapi.domain.dto.VariableDto;

import java.util.List;

/**
 * @author daitechang
 * @create: 2019-11-05
 **/
@Component
public interface VariableDao extends BaseMapper<Variable> {
    /**
     * 获取所有变量
     *
     * @return
     */
    List<Variable> listVariable();

    /**
     * 插入变量
     * @param variableDto
     * @return
     */
    boolean addVariable(@Param("variableDto") VariableDto variableDto);
}
