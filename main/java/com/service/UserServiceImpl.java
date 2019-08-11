package com.service;

import com.entity.User;
import com.mapper.UserMapper;
import com.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.util.Constant.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    /*
    Admin add manager
     */
    public Error addManager(User manager){
        User user;
        Error error ;
        try{
            user=mapper.selectByUserName(manager.getUserName());
            if(user != null)
            {
                error= new Error(DUPLICATE_NAME,"Service:the user name already exists!");
            }else{
                mapper.insertSelective(manager);
                error= new Error(ADD_SUCCESS,"Service:add manager successfully!");
            }

        }catch (Exception e){
            e.printStackTrace();
            error= new Error(ADD_ERROR,"Service:failed to add manager!");
        }
        return error;
    }


    /*
    Admin delete manager
     */
    public Error deleteManager(String userName){
        Error error;
        try {
            mapper.deleteByUserName(userName);
            error = new Error(DELETE_SUCCESS,"Service:delete manager successfully!");
        }catch (Exception e){
            e.printStackTrace();
            error = new Error(DELETE_ERROR,"Service:Failed to delete manager!");
        }
        return error;
    }

    /*
    Admin update manager
     */
    public Error updateManager(User manager){
        User user = new User();
        Error error;
        int userId;
        try {
            user = mapper.selectByUserName(manager.getUserName());
            userId = user.getUserId();
            manager.setUserId(userId);
            System.out.println("At the moment, the manager include a userId!");

            mapper.updateByPrimaryKeySelective(manager);
            error = new Error(UPDATE_SUCCESS,"Service:update manager successfully!");
        }catch (Exception e){
            e.printStackTrace();
            error = new Error(UPDATE_ERROR,"Service:Failed to update manager!");
        }
        return error;
    }

    public List<User> getAllManager(){
        List<User> userList = mapper.selectAllUser();
        List<User> managerList = new ArrayList<User>();
        for(User user : userList){
            if(user.getRole() == 0){
                managerList.add(user);
            }
        }
        return managerList;
    }

}
