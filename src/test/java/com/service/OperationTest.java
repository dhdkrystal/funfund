package com.service;


import com.entity.Operation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.util.Error;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationTest {

    @Autowired
    OperationService service;

    //test add the operation record(right record).
    @Test
    public void addOperationTest() throws Exception{
        Error error;
        Operation operation = new Operation();

        operation.setPortfolioName("ECNB");
        operation.setSecurityName("AMD");
        operation.setDate("2019-08-08");
        operation.setOperation(1);
        operation.setAmount(1000);
        operation.setPrice(33.919998);    //当前买入的价格  买入那天的close_price

        error = service.addOperation(operation);
        System.out.println(error.getMessage());
    }

    @Test
    public void addOperationTest1() throws Exception{
        Error error;
        Operation operation = new Operation();

        operation.setPortfolioName("BCCM");
        operation.setSecurityName("AMD");
        operation.setDate("2019-08-09");
        operation.setOperation(0);
        operation.setAmount(500);
        operation.setPrice(34.189999);    //当前买入的价格  今天的close_price

        error = service.addOperation(operation);
        System.out.println(error.getMessage());
    }


}
