package com.service;

import com.entity.Portfolio;
import com.mapper.PortfolioMapper;
import com.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.util.Constant.*;


@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    PortfolioMapper mapper;

    /*
    *Manager add portfolio
     */
    public Error addPortfolio(Portfolio portfolio){
        Portfolio portfolio1;
        Error error ;
        try{
            portfolio1=mapper.selectByPrimaryKey(portfolio.getPortfolioName());
            if(portfolio1 != null)
            {
                error= new Error(DUPLICATE_NAME,"Service:the portfolio name already exists!");
            }else{
                mapper.insertSelective(portfolio);
                error= new Error(ADD_SUCCESS,"Service:add portfolio successfully!");
            }
        }catch (Exception e){
            e.printStackTrace();
            error= new Error(ADD_ERROR,"Service:failed to add portfolio!");
        }
        return error;
    }


    /*
    Manager delete portfolio
     */
    public Error deletePortfolio(String portfolioName){
        Error error;
        try {
            mapper.deleteByPrimaryKey(portfolioName);
            error = new Error(DELETE_SUCCESS,"Service:delete portfolio successfully!");
        }catch (Exception e){
            e.printStackTrace();
            error = new Error(DELETE_ERROR,"Service:Failed to delete portfolio!");
        }
        return error;
    }

    /*
    *Manager update portfolio
     */
    public Error updatePortfolio(Portfolio portfolio){
        Error error;
        try {
            mapper.updateByPrimaryKeySelective(portfolio);
            error = new Error(UPDATE_SUCCESS,"Service:update portfolio successfully!");
        }catch (Exception e){
            e.printStackTrace();
            error = new Error(UPDATE_ERROR,"Service:Failed to update portfolio!");
        }
        return error;
    }


    /*
    *Manager operation: get portfolio list of a manager
     */
    public List<Portfolio> getPortfolioList(String manager){
        List<Portfolio> portfolioList = mapper.selectPortfolioByManager(manager);
        if(portfolioList == null){
            return null;
        }
        return portfolioList;
    }

    /*
    *Admin operation: get all portfolio list of admin
     */
    public List<Portfolio> getAllPortfolioList(){
        List<Portfolio> portfolioList = mapper.selectAllPortfolio();
        if(portfolioList == null){
            return null;
        }
        return portfolioList;
    }


    public void changeSymbol(String portfolioName){
        Portfolio portfolio = mapper.selectByPrimaryKey(portfolioName);
        if(portfolio.getSymbols() == null){
            portfolio.setSymbols(1);
            mapper.updateByPrimaryKeySelective(portfolio);
        }else {
            portfolio.setSymbols(1+portfolio.getSymbols());
            mapper.updateByPrimaryKeySelective(portfolio);
        }
        System.out.println("Service: Portfolio "+portfolioName+" has symbols of: "+portfolio.getSymbols());
    }

    public Error insertPortfolioChange(String portfolioName, double profit){
        Error error = null;
        Portfolio portfolio = mapper.selectByPrimaryKey(portfolioName);
        if(portfolio.getDayChangePrice() == null && portfolio.getTotalChangePrice() == null){
            portfolio.setDayChangePrice(0.0);
            portfolio.setDayChange(0.0);
            portfolio.setTotalChangePrice(0.0);
            portfolio.setTotalChange(0.0);
            mapper.updateByPrimaryKeySelective(portfolio);
            error = new Error(ADD_SUCCESS,"Update portfolio change first!");
        }else{
            portfolio.setTotalChangePrice(profit+portfolio.getTotalChangePrice());
            mapper.updateByPrimaryKeySelective(portfolio);
        }
        return error;
    }



    /*
     *Manager operation: get TOP2 portfolio list of manager
     */

    @Override
    public List<Portfolio> getTopPortfolioList(String manager) {
        List<Portfolio> portfolioList = mapper.selectTopPortfolioByManager(manager);
        if (portfolioList == null){
            return null;
        }
        return portfolioList;
    }


    /*
     *Manager operation: get worst portfolio list of manager
     */
    @Override
    public List<Portfolio> getWorstPortfolioList(String manager) {
        List<Portfolio> portfolioList = mapper.selectWorstPortfolioByManager(manager);
        if (portfolioList == null){
            return null;
        }
        return portfolioList;
    }

    /*
     *Admin operation: get the top portfolio and manager list
     */
    @Override
    public List<Portfolio> getTopPortfolioManagerList() {
        List<Portfolio> portfolioList = mapper.selectTopPortfolioManager();
        if (portfolioList == null){
            return null;
        }
        return portfolioList;
    }

    /*
     *Admin operation: get the worst portfolio and manager list
     */

    @Override
    public List<Portfolio> getWorstPortfolioManagerList() {
        List<Portfolio> portfolioList = mapper.selectWorstPortfolioManager();
        if (portfolioList == null){
            return null;
        }
        return portfolioList;
    }

}
