package cn.ddd.dao;


import cn.ddd.domain.User;

import java.sql.SQLException;

public interface Dao {
    //根据用户名查询用户信息
    public boolean findByUsername(String username,String email);

    //保存信息
    void save(User user);

    //根据激活码查询用户
    String findByCode(String code);

    //更新用户激活状态
    void updateStatus(String uid);

    //登陆
    String findByUsernameAndPassword(String username , String password);

    //找回密码1 ： 查找邮箱是否存在，并返回账号
    String findByEmail(String email);
    //找回密码2 ：根据安全码(激活码)查找用户，若找到，则更新其密码.
    boolean updatePass(String code, String pass);

    //根据账号 查询角色信息
    User findGameUser(String username);

}
