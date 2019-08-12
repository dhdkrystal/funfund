package com.service;

import com.entity.Portfolio;
import com.entity.Security;
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
public class DataMaintenanceTest {
	@Autowired
	DataMaintenanceService service;

	@Test
	public void volumeFilterTest() throws Exception{
		List<Security> securityList = service.volumeFilter("Bond","2019-08-09");
		System.out.println("volume: "+securityList.get(0).getVolume());
		System.out.println("volume: "+securityList.get(1).getVolume());
		System.out.println("size: "+securityList.size());
	}







}
