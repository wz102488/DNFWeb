package cn.ddd.service;

import cn.ddd.domain.User;

import java.sql.SQLException;

public interface userService {

    public boolean regist(User user) throws SQLException;
    String login(User user);
    boolean active(String code);
    boolean findPass(User user);
    boolean findPass2(User user);
    User findGameUser(String username);
}
