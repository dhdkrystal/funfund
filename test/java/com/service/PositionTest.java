package com.service;

import com.entity.Position;
import com.entity.PositionKey;
import com.entity.Security;
import com.entity.SecurityKey;
import com.util.Error;
import javafx.geometry.Pos;
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
public class PositionTest {
	@Autowired
	PositionService service;

	@Test
	public void addPositionTest() throws Exception{
		Error error;
		Position position = new Position();

		Date date = new Date(2018,6,6);
		position.setPortfolioName("ACBN");
		position.setSecurityName("Depuo");
		position.setDate(date);
		position.setOpen(18.80);
		position.setHigh(21.50);
		position.setLow(14.58);
		position.setClose(17.45);
		position.setType("Futures");

		error = service.addPosition(position);
		System.out.println(error.getMessage());
	}

	@Test
	public void deletePositionTest() throws Exception{
		Error error;
		PositionKey positionKey = new PositionKey();
		positionKey.setPortfolioName("BCNN");
		positionKey.setSecurityName("Cons");
		error = service.deletePosition(positionKey);
		System.out.println(error.getMessage());
	}

	@Test
	public void getPositionListTest(){
		List<Position> positionList = new ArrayList<Position>();
		positionList = service.getPositionList("ACBN");
		System.out.println("Position List: name("+positionList.get(0).getPortfolioName()+"),type("+positionList.get(0).getSecurityName()+")");
		System.out.println("Position List: name("+positionList.get(1).getPortfolioName()+"),type("+positionList.get(1).getSecurityName()+")");
	}



}
