package com.service;

import com.entity.*;
import com.mapper.OperationMapper;
import com.mapper.PortfolioMapper;
import com.mapper.PositionMapper;
import com.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

import static com.util.Constant.*;


@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionMapper mapper;
    @Autowired
    SecurityService securityService;
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    PortfolioMapper portfolioMapper;
    @Autowired
    OperationMapper operationMapper;

    /*
    Admin add security
     */
    public Error addPosition(Position position){
        Position position1;
        Error error ;
        PositionKey positionKey = new PositionKey();
        positionKey.setPortfolioName(position.getPortfolioName());
        positionKey.setSecurityName(position.getSecurityName());
        try{
            position1=mapper.selectByPrimaryKey(positionKey);
            if(position1 != null)
            {
                error= new Error(DUPLICATE_NAME,"Service:the position exists in the portfolio!");
            }else{
                mapper.insertSelective(position);
                error= new Error(ADD_SUCCESS,"Service:add position successfully!");
            }
        }catch (Exception e){
            e.printStackTrace();
            error= new Error(ADD_ERROR,"Service:failed to add position!");
        }
        return error;
    }


    /*
    Manager delete position
     */
    public Error deletePosition(PositionKey positionKey){
        Error error;
        try {
            mapper.deleteByPrimaryKey(positionKey);
            error = new Error(DELETE_SUCCESS,"Service:delete position successfully!");
        }catch (Exception e){
            e.printStackTrace();
            error = new Error(DELETE_ERROR,"Service:Failed to delete position!");
        }
        return error;
    }

    /*
    *Manager update position
     */
    public Error updatePosition(Position position){
        Error error;
        try {
            mapper.updateByPrimaryKeySelective(position);
            error = new Error(UPDATE_SUCCESS,"Service:update position successfully!");
        }catch (Exception e){
            e.printStackTrace();
            error = new Error(UPDATE_ERROR,"Service:Failed to update position!");
        }
        return error;
    }


    /*
    *Manager operation: get position list of a portfolio
     */
    public List<Position> getPositionList(String portfolioName){
        List<Position> positionList = mapper.selectPositionByPortfolio(portfolioName);
        if(positionList == null){
            return null;
        }
       return positionList;
    }


    /*
    *Position change By Operation
     */
    public void positionChangeByOperation(Operation operation){
        Error error = null;
        Position position = new Position();
        PositionKey positionKey = new PositionKey();
        positionKey.setPortfolioName(operation.getPortfolioName());
        positionKey.setSecurityName(operation.getSecurityName());
        //insert the type and new day's close_price
        List<Security> securityList = securityService.getSecurityHistory(operation.getSecurityName());
        String type = securityList.get(securityList.size()-1).getType();
//        double close = securityList.get(securityList.size()-1).getClose();
        try {
            position = mapper.selectByPrimaryKey(positionKey);
            if(position == null){
                Position position1 = new Position();
                position1.setPortfolioName(operation.getPortfolioName());
                position1.setSecurityName(operation.getSecurityName());
                position1.setAmount(operation.getAmount());
                position1.setClose(operation.getPrice());
                double money;
                if(operation.getOperation() == 1){
                    money = operation.getAmount()*operation.getPrice()*(-1);
                }else{
                    money = operation.getAmount()*operation.getPrice();
                }
                money = (double) Math.round(money * 100) / 100;
                position1.setMoney(money);

                position1.setType(type);
                mapper.insertSelective(position1);
                error = new Error(ADD_SUCCESS,"Service: insert position by operation successfully!");
                System.out.println(error.getMessage());
//                profit = close*operation.getAmount() + money;

                //add portfolio symbols
                portfolioService.changeSymbol(operation.getPortfolioName());

                double profit = 0;
                portfolioService.insertPortfolioChange(operation.getPortfolioName(), profit);


            }else{
                double money;
                int amount;
                if(operation.getOperation() == 1){
                    money = operation.getAmount()*operation.getPrice()*(-1);
                    amount = operation.getAmount()*1;
                }else{
                    money = operation.getAmount()*operation.getPrice();
                    amount = operation.getAmount()*(-1);
                }
                int amountPosition = position.getAmount();
                double moneyPosition = position.getMoney();

                int newAmount =  amountPosition + amount;
                double newMoney = moneyPosition + money;

                newMoney = (double) Math.round(newMoney * 100) / 100;

                Position position1 = new Position();
                position1.setPortfolioName(operation.getPortfolioName());
                position1.setSecurityName(operation.getSecurityName());
                position1.setAmount(newAmount);
                position1.setMoney(newMoney);

                mapper.updateByPrimaryKeySelective(position1);
                error = new Error(ADD_ERROR,"Service:update position by operation successfully!");
                System.out.println(error.getMessage());

//                profit = close * newAmount + newMoney;

            }

        }catch (Exception e){
            e.printStackTrace();
            error = new Error(ADD_ERROR,"Service: failed to do position by operation!");
            System.out.println(error.getMessage());
        }

    }


    /*
    *Update position close.price
     */
    public Error updatePositionClose(){
        Error error;
        List<Position> positionList = mapper.selectAllPositions();
        List<Portfolio> portfolioList = portfolioService.getAllPortfolioList();  //to get the total price change of yesterday
        for(Position position : positionList){
            String securityName = position.getSecurityName();
            String portfolioName = position.getPortfolioName();
            double profit = 0;
            List<Security> securityList = securityService.getSecurityHistory(securityName);
            System.out.println("securityName: "+securityName);
            System.out.println("size: "+securityList.size());
            System.out.println("last: "+securityList.get(securityList.size()-1));
            double newClose = securityList.get(securityList.size()-1).getClose();
            position.setClose(newClose);
            mapper.updateByPrimaryKeySelective(position);

            System.out.println("newClose: "+newClose);

            //cal the profit of every position
            profit = position.getClose()* position.getAmount() + position.getMoney();

            //add portfolio day_change and total change
            portfolioService.insertPortfolioChange(portfolioName,profit);
        }

        System.out.println("Service: update close price successfully!");


        //set the portfolio's day change and total change in the database
        List<Portfolio> newPortfolioList = portfolioService.getAllPortfolioList();
        for(int i = 0; i < portfolioList.size(); i++){
            if(portfolioList.get(i).getTotalChangePrice() == null){
                portfolioList.get(i).setTotalChangePrice(0.0);
            }else{
                double dayChangePrice = newPortfolioList.get(i).getTotalChangePrice()-portfolioList.get(i).getTotalChangePrice();
                newPortfolioList.get(i).setDayChangePrice(dayChangePrice);

                //get the origin buy price
                double buyPrice;
                List<Operation> operationList = operationMapper.selectByPortfolioName(portfolioList.get(i).getPortfolioName());
                buyPrice = operationList.get(0).getPrice()* operationList.get(0).getAmount();
                for(int j = 1; j < operationList.size(); j++){
                    String securityName = operationList.get(j-1).getSecurityName();
                    String securityName1 = operationList.get(j).getSecurityName();
                    if(!(securityName.equals(securityName1))){
                        buyPrice += operationList.get(j).getPrice()* operationList.get(j).getAmount();
                    }
                }

                System.out.println("buyPrice: "+buyPrice);
                newPortfolioList.get(i).setDayChange(dayChangePrice / buyPrice);
                newPortfolioList.get(i).setTotalChange(newPortfolioList.get(i).getTotalChangePrice() / buyPrice);

                portfolioMapper.updateByPrimaryKeySelective(newPortfolioList.get(i));
            }
        }
        System.out.println("Service: update day_change and total_change in the portfolio successfully!");

        error = new Error(UPDATE_SUCCESS,"Service: execute successfully!");
        return error;
    }

}
