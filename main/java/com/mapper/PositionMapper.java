package com.mapper;

import java.util.List;

import com.entity.Position;
import com.entity.PositionKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface PositionMapper {

    int deleteByPrimaryKey(PositionKey key);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(PositionKey key);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> selectPositionByPortfolio(String portfolioName);
}