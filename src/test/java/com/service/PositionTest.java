package com.service;

import com.entity.Position;
import com.entity.PositionKey;
import com.util.Error;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionTest {
	@Autowired
	PositionService service;

	//test add the portfolio record(right record).
	@Test
	public void addPositionTest() throws Exception{
		Error error;
		Position position = new Position();

		position.setPortfolioName("ACBN");
		position.setSecurityName("Depuo1");
		position.setAmount(500);
		position.setClose(18.80);
		position.setMoney(21.50);
		position.setType("Bond");


		error = service.addPosition(position);
		System.out.println(error.getMessage());
	}

	//test add the portfolio record(wrong record).
	@Test
	public void addPositionTest1() throws Exception{
		Error error;
		Position position = new Position();

		position.setPortfolioName(null);
		position.setSecurityName("Depuo2");
		position.setAmount(500);
		position.setClose(null);
		position.setMoney(21.50);
		position.setType("Bond");

		error = service.addPosition(position);
		System.out.println(error.getMessage());
	}

	//test delete the portfolio record(right record).
	@Test
	public void deletePositionTest() throws Exception{
		Error error;
		PositionKey positionKey = new PositionKey();
		positionKey.setPortfolioName("ACBN");
		positionKey.setSecurityName("Depuo1");
		error = service.deletePosition(positionKey);
		System.out.println(error.getMessage());
	}

	//test get the positions of a portfolio
	@Test
	public void getPositionListTest(){
		List<Position> positionList = new ArrayList<Position>();
		positionList = service.getPositionList("ACBN");
		System.out.println("Position List: name("+positionList.get(0).getPortfolioName()+"),type("+positionList.get(0).getSecurityName()+")");
		System.out.println("Position List: name("+positionList.get(1).getPortfolioName()+"),type("+positionList.get(1).getSecurityName()+")");
	}


	@Test
	public void updatePositionTest(){
		Error error;
		error = service.updatePositionClose();
		System.out.println(error.getMessage());
	}
}
