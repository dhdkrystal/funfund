package com.service;

import com.entity.Portfolio;
import com.entity.Security;
import com.entity.SecurityKey;
import com.util.Error;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortfolioTest {
	@Autowired
	PortfolioService service;

	@Test
	public void addPortfolioTest() throws Exception{
		Error error;
		Portfolio portfolio = new Portfolio();

		Date date = new Date(2018,6,6);
		portfolio.setPortfolioName("BScombine");
		portfolio.setDate(date);
		portfolio.setManager("Jack");

		error = service.addPortfolio(portfolio);
		System.out.println(error.getMessage());
	}

	@Test
	public void deletePortfolioTest() throws Exception{
		Error error;
		error = service.deletePortfolio("BEcombine");
		System.out.println(error.getMessage());
	}

	@Test
	public void updatePortfolioTest() throws Exception{
		Error error;
		Portfolio portfolio = new Portfolio();

		portfolio.setPortfolioName("ACBN");
		portfolio.setManager("Lin");

		error = service.updatePortfolio(portfolio);
		System.out.println(error.getMessage());
	}

	//when the manager exists
	@Test
	public void getPortfolioListTest(){
		List<Portfolio> portfolioList = new ArrayList<Portfolio>();
		portfolioList = service.getPortfolioList("Lee");
		System.out.println("Portfolio List: name("+portfolioList.get(0).getPortfolioName()+"),manager("+portfolioList.get(0).getManager()+")");
		System.out.println("Portfolio List: name("+portfolioList.get(1).getPortfolioName()+"),manager("+portfolioList.get(1).getManager()+")");
	}

	//when the manager doesn't exist
	@Test
	public void getPortfolioListTest1(){
		List<Portfolio> portfolioList = new ArrayList<Portfolio>();
		portfolioList = service.getPortfolioList("Stu");
		System.out.println("Portfolio List: "+portfolioList);
	}


}
