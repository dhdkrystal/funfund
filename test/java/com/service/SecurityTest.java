package com.service;

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
public class SecurityTest {
	@Autowired
	SecurityService service;

	@Test
	public void addSecurityTest() throws Exception{
		Error error;
		Security security = new Security();

		Date date = new Date(2018,6,6);
		security.setSecurityName("Depuo");
		security.setDate(date);
		security.setOpen(18.80);
		security.setHigh(21.50);
		security.setLow(14.58);
		security.setClose(17.45);
		security.setVolume(1290);
		security.setType("Futures");

		error = service.addSecurity(security);
		System.out.println(error.getMessage());
	}

	@Test
	public void deleteSecurityTest() throws Exception{
		Error error;
		Date date = new Date(2018,6,6);
		SecurityKey securityKey = new SecurityKey();
		securityKey.setSecurityName("Depuo");
		securityKey.setDate(date);
		error = service.deleteSecurity(securityKey);
		System.out.println(error.getMessage());
	}

	@Test
	public void updateSecurityTest() throws Exception{
		Error error;
		Security security = new Security();

		Date date = new Date(2001,5,7);
		security.setSecurityName("YAhoo");
		security.setDate(date);
		security.setOpen(18.80);
		security.setHigh(21.50);
		security.setLow(14.58);
		security.setClose(17.45);
		security.setVolume(1270);
		security.setType("Futures");

		error = service.updateSecurity(security);
		System.out.println(error.getMessage());
	}

	@Test
	public void getStockListTest(){
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityList("Stock");
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
	}

	@Test
	public void getBondListTest(){
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityList("Bond");
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
	}

	@Test
	public void getFuturesListTest(){
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityList("Futures");
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
	}

	@Test
	public void getETFsListTest(){
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityList("ETFs");
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
	}

}
