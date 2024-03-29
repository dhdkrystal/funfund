package com.mapper;

import java.util.List;

import com.entity.Portfolio;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
@Component
@Mapper
public interface PortfolioMapper {

    int deleteByPrimaryKey(String portfolioName);

    int insert(Portfolio record);

    int insertSelective(Portfolio record);

    Portfolio selectByPrimaryKey(String portfolioName);

    int updateByPrimaryKeySelective(Portfolio record);

    int updateByPrimaryKey(Portfolio record);

    List<Portfolio> selectAllPortfolio();

    List<Portfolio> selectPortfolioByManager(String manager);

    List<Portfolio> selectTopPortfolioByManager(String manager);

    List<Portfolio> selectWorstPortfolioByManager(String manager);

    List<Portfolio> selectTopPortfolioManager();

    List<Portfolio> selectWorstPortfolioManager();
}