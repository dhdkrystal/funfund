package com.service;

import com.entity.Operation;
import com.entity.Portfolio;
import com.util.Error;

import java.util.List;

public interface PortfolioService {
    /**
     *Insert the portfolio
     * @param portfolio  the entity of Portfolio
     */
    Error addPortfolio(Portfolio portfolio);

    /**
     *Delete the portfolio
     * @param portfolioName  the primary key of Portfolio
     */
    Error deletePortfolio(String portfolioName);

    /*Update the portfolio
     * @param portfolio  the entity of Portfolio
     */
    Error updatePortfolio(Portfolio portfolio);

    /*
    *Select portfolio list of a manager
     */
    List<Portfolio> getPortfolioList(String manager);

    /*
    *Select all portfolio list of admin
     */
    List<Portfolio> getAllPortfolioList();

    void changeSymbol(String portfolioName);

    Error insertPortfolioChange(String portfolioName, double profit);


    /*
     *Select top2 portfolio list of manager
     */
    List<Portfolio> getTopPortfolioList(String manager);


    /*
     *Select worst portfolio list of manager
     */
    List<Portfolio> getWorstPortfolioList(String manager);

    /*
     *Select top2 portfolio and manager list
     */

    List<Portfolio> getTopPortfolioManagerList();

    /*
     *Select worst portfolio and manager list
     */
    List<Portfolio> getWorstPortfolioManagerList();
}
