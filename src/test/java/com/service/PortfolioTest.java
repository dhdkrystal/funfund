package com.service;

import com.entity.Portfolio;
import com.util.Error;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortfolioTest {
	@Autowired
	PortfolioService service;

	//test add the portfolio record(right record).
	@Test
	public void addPortfolioTest() throws Exception{
		Error error;
		Portfolio portfolio = new Portfolio();

		portfolio.setPortfolioName("BFcombine");
		portfolio.setDate("2018-04-29");
		portfolio.setManager("Jack");

		error = service.addPortfolio(portfolio);
		System.out.println(error.getMessage());

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		jsonObject.put("data",null);
		System.out.println("json: "+jsonObject);
		System.out.println("json string: "+jsonObject.toString());
	}

	//test add the portfolio record(wrong record).
	@Test
	public void addPortfolioTest1() throws Exception{
		Error error;
		Portfolio portfolio = new Portfolio();

		portfolio.setDate("2018-04-29");
		portfolio.setManager("Jack");

		error = service.addPortfolio(portfolio);
		System.out.println(error.getMessage());

	}

	//test delete the portfolio record(right record).
	@Test
	public void deletePortfolioTest() throws Exception{
		Error error;
		error = service.deletePortfolio("ACBN");
		System.out.println(error.getMessage());
	}

	//test update the portfolio record(right record).
	@Test
	public void updatePortfolioTest() throws Exception{
		Error error;
		Portfolio portfolio = new Portfolio();

		portfolio.setPortfolioName("BFcombine");
		portfolio.setManager("Lin");

		error = service.updatePortfolio(portfolio);
		System.out.println(error.getMessage());
	}

	//when the manager exists, get the portfolios of a manager
	@Test
	public void getPortfolioListTest(){
		List<Portfolio> portfolioList = new ArrayList<Portfolio>();
		portfolioList = service.getPortfolioList("Lee");
		System.out.println("Portfolio List: name("+portfolioList.get(0).getPortfolioName()+"),manager("+portfolioList.get(0).getManager()+")");
		System.out.println("Portfolio List: name("+portfolioList.get(1).getPortfolioName()+"),manager("+portfolioList.get(1).getManager()+")");

		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObject1 = new JSONObject();
		jsonObject.put("success", true);
		jsonObject1.put("portfolioList", portfolioList);
		jsonObject.put("data",jsonObject1);
		System.out.println("json: "+jsonObject);
	}

	//when the manager doesn't exist
	@Test
	public void getPortfolioListTest1(){
		List<Portfolio> portfolioList = new ArrayList<Portfolio>();
		portfolioList = service.getPortfolioList("Stu");
		System.out.println("Portfolio List: "+portfolioList);
	}


	@Test
	public void getTopPortfolioList(){
		List<Portfolio> portfolioList = service.getTopPortfolioList("Lee");
		System.out.println("The Top portfolio List: "+portfolioList);

	}

	@Test
	public void getWorstPortfolioList(){
		List<Portfolio> portfolioList = service.getWorstPortfolioList("Lee");
		System.out.println("The Worst portfolio List: "+portfolioList);

	}

	@Test
	public void getTopPortfolioManager(){
		List<Portfolio> portfolioList = service.getTopPortfolioManagerList();
		System.out.println("The Top portfolio and manager List : "+portfolioList);

	}

	@Test
	public void getWorstPortfolioManager(){
		List<Portfolio> portfolioList = service.getWorstPortfolioManagerList();
		System.out.println("The Worst portfolio and manager List: "+portfolioList);

	}


}
