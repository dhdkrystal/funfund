package com.service;

import com.entity.Portfolio;
import com.entity.Security;
import com.entity.SecurityKey;
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
}
