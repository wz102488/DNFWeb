package cn.ddd.service.impl;

import cn.ddd.dao.Dao;
import cn.ddd.dao.impl.DaoImpl;
import cn.ddd.domain.User;
import cn.ddd.service.userService;
import cn.ddd.util.MailUtils;
import cn.ddd.util.UuidUtil;

import java.sql.SQLException;

import static cn.ddd.util.Md5Util.encodeByMd5;

public class userServiceImpl implements userService {
    private Dao dao = new DaoImpl();

    /**
     * 注册用户
     * @return
     */
    @Override
    public boolean regist(User user) throws SQLException {
        //根据email查询数据是否存在
        boolean u = dao.findByUsername(user.getUsername(),user.getEmail());
        if(u == true){
            //email存在，注册失败
            return false;
        }
        //保存用户信息，
        // 生成激活码
        user.setCode(UuidUtil.getUuid());
        //设置激活状态 0是未激活，1是激活
        user.setStatus("0");
        dao.save(user);
        //3.激活邮件发送，邮件正文？

        String content="请在校园网络环境下点击此激活链接。  <a href='http://192.168.20.3:8082/ddd/user/active?code="+user.getCode()+"'>点击激活【校园服D&F】</a>";

        MailUtils.sendMail(user.getEmail(),content,"校园服激活邮件");

        return true;
    }
    public String login(User user){
        //根据用户名和密码查询用户


        try {
            return dao.findByUsernameAndPassword(user.getUsername(),encodeByMd5(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 用户激活
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //调用dao，根据激活码查询用户uid、
        String uid = dao.findByCode(code);
        if(uid != null){
            //调用dao，修改帐号为激活状态
            dao.updateStatus(uid);
            return true;
        }
        return false;
    }

    @Override
    public boolean findPass(User user) {
        //调用dao，根据email查询用户账号和安全码、
        String info = dao.findByEmail(user.getEmail());
        if(info != null){
            //3.激活邮件发送，邮件正文？



            MailUtils.sendMail(user.getEmail(),info,"校园服修改密码邮件");
            return true;
        }

        return false;
    }

    @Override
    public boolean findPass2(User user) {
        //调用dao，根据激活码查询用户uid、
        String uid = dao.findByCode(user.getCode());
        if(uid != null){
            //调用dao，修改帐号
            dao.updatePass(uid,user.getPassword());
            return true;
        }
        return false;
    }

    @Override
    public User findGameUser(String username) {
        //调用dao进行查询
        return dao.findGameUser(username);

    }
}
