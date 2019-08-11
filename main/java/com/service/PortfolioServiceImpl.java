package com.service;

import com.entity.Portfolio;
import com.entity.Security;
import com.entity.SecurityKey;
import com.mapper.PortfolioMapper;
import com.mapper.SecurityMapper;
import com.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
//        List<Portfolio> targetList = new ArrayList<Portfolio>();
//        for(Portfolio portfolio : portfolioList){
//            if(portfolio.getManager().equals(manager)) {
//                targetList.add(portfolio);
//            }else{
//                System.out.println("The manager doesn't exist!");
//                return null;
//            }
//        }
        if(portfolioList == null){
            return null;
        }
        return portfolioList;
    }

}
