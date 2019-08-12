package com.mapper;

import java.util.Date;
import java.util.List;

import com.entity.Security;
import com.entity.SecurityKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
@Component
@Mapper
public interface SecurityMapper {
    int deleteByPrimaryKey(SecurityKey key);

    int insert(Security record);

    int insertSelective(Security record);

    Security selectByPrimaryKey(SecurityKey key);

    int updateByPrimaryKeySelective(Security record);

    int updateByPrimaryKey(Security record);

    List<Security> selectAllSecurity();

    List<Security> selectSecurityByType(String type);

    List<Security> selectSecurityByName(String securityName);
}