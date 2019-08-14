package com.mapper;

import java.util.List;

import com.entity.Operation;
import com.entity.OperationKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OperationMapper {

    int deleteByPrimaryKey(OperationKey key);

    int insert(Operation record);

    int insertSelective(Operation record);

    Operation selectByPrimaryKey(OperationKey key);

    int updateByPrimaryKeySelective(Operation record);

    int updateByPrimaryKey(Operation record);

    List<Operation> selectByPortfolioName(String portfolioName);
}