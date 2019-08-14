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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityTest {
	@Autowired
	SecurityService service;

	//test add the security record(right record).
	@Test
	public void addSecurityTest() throws Exception{
		Error error;
		Security security = new Security();

		security.setSecurityName("Ehkn");
		security.setDate("2018-05-04");
		security.setOpen(18.80);
		security.setHigh(21.50);
		security.setLow(14.58);
		security.setClose(17.45);
		security.setVolume(1290);
		security.setType("ETF");
		security.setChangeRate(3.56);
		security.setChangePrice(0.0034);

		error = service.addSecurity(security);
		System.out.println(error.getMessage());
	}

	//test add the security record(wrong record).
	@Test
	public void addSecurityTest1() throws Exception{
		Error error;
		Security security = new Security();

//		security.setSecurityName("Ehkn");
		security.setDate("2018-05-04");
		security.setOpen(18.80);
		security.setHigh(21.50);
		security.setLow(14.58);
		security.setClose(17.45);
		security.setVolume(1290);
		security.setType("ETF");
		security.setChangeRate(3.56);
		security.setChangePrice(0.0034);

		error = service.addSecurity(security);
		System.out.println(error.getMessage());
	}

	//test delete the security record(right record).
	@Test
	public void deleteSecurityTest() throws Exception{
		Error error;

		SecurityKey securityKey = new SecurityKey();
		securityKey.setSecurityName("Ehkn");
		securityKey.setDate("2018-05-04");
		error = service.deleteSecurity(securityKey);
		System.out.println(error.getMessage());
	}

	//test update the security record(right record).
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
		security.setType("Future");

		error = service.updateSecurity(security);
		System.out.println(error.getMessage());
	}

	//test get the stock list of one day
	@Test
	public void getStockListTest(){
		String date = "2019-08-05";
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityList("Stock",date);
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
		System.out.println("List size: "+securityList.size());
	}

	//test get the bond list of one day
	@Test
	public void getBondListTest(){
		String date = "2019-08-05";
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityList("Bond", date);
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
		System.out.println("List size: "+securityList.size());
	}

	//test get the futures list of one day
	@Test
	public void getFuturesListTest(){
		String date = "2018-08-13";
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityList("Future",date);
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
		System.out.println("List size: "+securityList.size());
	}

	//test get the etfs list of one day
	@Test
	public void getETFsListTest(){
		String date = "2019-08-06";
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityList("ETF",date);
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")"+"),type("+securityList.get(0).getChangePrice()+")");
		System.out.println("List size: "+securityList.size());
	}


	//test get the all information list of one security.
	@Test
	public void getSecurityHistoryTest(){
		List<Security> securityList = new ArrayList<Security>();
		securityList = service.getSecurityHistory("BAC");
		System.out.println("Security List: name("+securityList.get(0).getSecurityName()+"),type("+securityList.get(0).getType()+")");
		System.out.println("List size: "+securityList.size());
	}


	//test upload the security price.
	@Test
	public void uploadSecurityTest() throws Exception{

		List<List<String>> strs  = new ArrayList<>();
		List str = new ArrayList();
		str.add("TEST");
		str.add("2018-01-01");
		str.add("20");
		str.add("20");
		str.add("20");
		str.add("20");
		str.add("2000");
		str.add("Bond");
		str.add("0");
		str.add("0");
		strs.add(str);
		Boolean result= service.uploadSecurity(strs);
		System.out.println(result);


	}


	/*
	*get security name list test
	 */
	@Test
	public void getSecurityNameListTest(){
		List<String> securityNameList = service.getSecurityNameList();
		for(String name : securityNameList){
			System.out.println("SecurityName: "+name);
		}

	}

}
