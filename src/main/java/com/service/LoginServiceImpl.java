package com.service;

import com.entity.User;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.util.Error;

import static com.util.Constant.*;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper mapper;

    public Error login(User userInfo){
        Error error;
        User user = mapper.selectByUserName(userInfo.getUserName());
//        System.out.println("ceshi:"+user.getUserName()+' '+user.getUserPassword());
        if(user == null){
            error = new Error(NOT_EXIST, "The user doesn't exist!");
        }
        else{
            try{

                    if(user.getUserPassword().equals(userInfo.getUserPassword()) && user.getRole() == userInfo.getRole()) {
//                        mapper.updateByPrimaryKeySelective(userInfo);
                        error=new Error(LOGIN_SUCCESS, "Login success!");
                    }else {
                        error = new Error(WRONG_PASSWORD, "Name, password or type is wrong!");
                    }
            }catch (Exception e){
                e.printStackTrace();
                error = new Error(LOGIN_ERROR, "Login error, please try again next time!");
            }
        }
        return error;
    }



//    public Error loginAdmin(Admin adminInfo){
//        Error error;
//        System.out.println("ceshi:adminName"+adminInfo.getAdminName());
//        Admin admin = adminMapper.selectByPrimaryKey(adminInfo.getAdminName());
//        System.out.println("ceshi:"+admin.getAdminName()+' '+admin.getAdminPwd());
//        if(admin == null){
//            error = new Error(NOT_EXIST, "管理员不存在");
//        }
//        else{
//            try{
//
//                if(admin.getAdminPwd().equals(adminInfo.getAdminPwd())) {
//                    adminMapper.updateByPrimaryKeySelective(adminInfo);
//                    error=new Error(LOGIN_SUCCESS, "管理员登录成功");
//                }else {
//                    error = new Error(WRONG_PASSWORD, "账号或者密码错误");
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//                error = new Error(LOGIN_ERROR, "登录失败请稍后重试");
//            }
//        }
//        return error;
//    }





//    public int logout(User userInfo) {
//        mapper.updateByPrimaryKeySelective(userInfo);
//        return 0;
//    }


//    public int logoutAdmin(Admin adminInfo) {
//        adminMapper.updateByPrimaryKeySelective(adminInfo);
//        return 0;
//    }

}
