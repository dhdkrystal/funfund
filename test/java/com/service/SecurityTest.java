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

		security.setSecurityName("Ehkn");
		security.setDate("2018-05-01");
		security.setOpen(18.80);
		security.setHigh(21.50);
		security.setLow(14.58);
		security.setClose(17.45);
		security.setVolume(1290);
		security.setType("ETFs");

		error = service.addSecurity(security);
		System.out.println(error.getMessage());
	}

	@Test
	public void deleteSecurityTest() throws Exception{
		Error error;

		SecurityKey securityKey = new SecurityKey();
		securityKey.setSecurityName("Ehkn");
		securityKey.setDate("2018-05-01");
		error = service.deleteSecurity(securityKey);
		System.out.println(error.getMessage());
	}

	@Test
	public void updateSecurityTest() throws Exception{
		Error error;
		Security security = new Security();

		security.setSecurityName("YAhoo");
		security.setDate("2018-06-06");
		security.setOpen(18.80);
		security.setHigh(21.50);
		security.setLow(14.58);
		security.setClose(17.45);
		security.setVolume(2000);
		security.setType("Futures");

		error = service.updateSecurity(security);
		System.out.println(error.getMessage());
	}

	@Test
	public void getStockListTest(){
		String date = "2019-03-21";
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityList("Stock",date);
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
		System.out.println("List size: "+securityList.size());
	}

	@Test
	public void getBondListTest(){
		String date = "2019-03-21";
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityList("Bond", date);
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
		System.out.println("List size: "+securityList.size());
	}

	@Test
	public void getFuturesListTest(){
		String date = "2019-03-21";
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityList("Futures",date);
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
		System.out.println("List size: "+securityList.size());
	}

	@Test
	public void getETFsListTest(){
		String date = "2019-03-21";
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityList("ETFs",date);
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
		System.out.println("List size: "+securityList.size());
	}


	@Test
	public void getSecurityHistoryTest(){
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityHistory("Stuck");
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
		System.out.println("List size: "+securityList.size());
	}

}
