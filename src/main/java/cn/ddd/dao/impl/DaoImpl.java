package cn.ddd.dao.impl;



import cn.ddd.dao.Dao;
import cn.ddd.domain.User;
import cn.ddd.util.JdbcUtils;
import cn.ddd.util.UuidUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static cn.ddd.util.Md5Util.encodeByMd5;


public class DaoImpl implements Dao {


    private Connection conn = null;
    private PreparedStatement ps = null;

    {
        try {
            conn = JdbcUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 重新获取数据库连接
     */
    private Connection con(){

        try {
            return   conn = JdbcUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 注册时，检查数据库是否已经有这个Email
     * @param username
     * @return
     */
    @Override
    public boolean findByUsername(String username, String email){
        //User user =null;

        try {
            //1.定义sql
            String sql = "select * from accounts where accountname = ? or email = ?";
            //2.预编译sql，防止注入
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,email);
            //3.执行sql
            ResultSet rs = ps.executeQuery();
            System.out.printf("username is "+ username);
            if(rs.next()){return true;}
            else {return false;}
            //user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username);
        } catch (Exception e) {
            e.printStackTrace();
            this.con();
            this.findByUsername(username,email);
        }


        return false;


    }

    /**
     * 保存用户注册信息
     * @param user
     */
    @Override
    public void save(User user){
        //1.定义sql
        String sql = "insert into accounts(accountname,password,email,status,code) values(?,?,?,?,?)";
//        template.update(sql,user.getUsername(),
//                user.getPassword(),
//                user.getEmail(),
//                user.getStatus(),
//                user.getCode());
        try {
            //2.预编译sql，防止注入
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            try {
                ps.setString(2,encodeByMd5(user.getPassword()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getStatus());
            ps.setString(5,user.getCode());
            //3.执行sql
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            this.con();
            this.save(user);
        }

    }

    /**
     * 根据激活码查找用户
     * @param code
     * @return
     */

    @Override
    public String findByCode(String code){
        //定义sql
        String  sql = "select * from accounts where code = ?";
        //2.预编译sql，防止注入
        //PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,code);

            //3.执行sql
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String uid = rs.getString("UID");
                System.out.printf("要激活的UID 是: "+ uid);
                return uid;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.con();
            this.findByCode(code);
        }
        return null;



    }

    /**
     * 更新用户激活码状态为 “已激活”1
     * 使用的是上面findByCode返回的uid查询
     * @param uid
     */
    @Override
    public void updateStatus(String uid){
        //定义sql
        String sql = " update accounts set status = '1' where uid=?";
        String sql2 = "update accounts set code = '"+ UuidUtil.getUuid()+ "' where uid=?";

        //PreparedStatement ps = null;

        try {
            //2.预编译sql，防止注入
            ps = conn.prepareStatement(sql);
            ps.setString(1,uid);
            ps.executeUpdate();

            ps = conn.prepareStatement(sql2);
            ps.setString(1,uid);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            this.con();
            this.updateStatus(uid);
        }


    }

    /**
     * 用户登录，根据帐号密码查找用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public String findByUsernameAndPassword(String username,String password){
        User user = null;

            //定义sql
            String sql = "select * from accounts where accountname = ? and password = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            //3.执行sql
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String status = rs.getString("status");
                if(status.equals("1")){
                    return "y";//代表登陆成功，并且帐号是激活的
                }else{
                    return "n";//代表登陆成功，但是帐号没有激活
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            this.con();
            this.findByUsernameAndPassword(username, password);
        }
        return "x";//代表没有这个账号，或者帐号密码错误





    }

    @Override
    public String findByEmail(String email) {
        //定义sql
        String  sql = "select * from accounts where email = ?";
        //2.预编译sql，防止注入
        //PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,email);
            String info = null;

            //3.执行sql
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String accountname = rs.getString("accountname");
                String code = rs.getString("code");
                System.out.printf("要修改密码的账号 是: "+ accountname+" code "+code);
                info = "您的账号是："+accountname+"  安全码是："+code;
                //return "您的账号是："+accountname+"  安全码是："+code;
            }
            return info;
        } catch (SQLException e) {
            e.printStackTrace();
            this.con();
            this.findByCode(email);
        }
        return null;
    }

    @Override
    public boolean updatePass(String uid,String pass) {
        //String uid = this.findByCode(code);
        //定义sql
        String sql = " update accounts set password =? where uid=?";
        String sql2 = "update accounts set code = '"+ UuidUtil.getUuid()+ "' where uid=?";

        //PreparedStatement ps = null;

        try {
            //2.预编译sql，防止注入
            ps = conn.prepareStatement(sql);
            try {
                ps.setString(1,encodeByMd5(pass));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps.setString(2,uid);
            ps.executeUpdate();

            ps = conn.prepareStatement(sql2);
            ps.setString(1,uid);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            this.con();
            this.updateStatus(uid);
        }

        return false;
    }

    @Override
    public User findGameUser(String username) {
        User user = null;
        String uid = null;
        String char_name = null;

        //定义sql
        String sql = "select * from accounts where accountname = ?";
        String sql2 = "select * from charac_info where m_id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            //3.执行sql
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                uid = rs.getString("UID");


            }
            ps = conn.prepareStatement(sql2);
            ps.setString(1,uid);
            //3.执行sql
            ResultSet rs2 = ps.executeQuery();
            while(rs2.next()){
                char_name = rs2.getString("charac_name");

            }
            user.setChar_no(uid);
            user.setChar_name(char_name);
            return user;


        } catch (SQLException e) {
            e.printStackTrace();
            this.con();
            this.findGameUser(username);
        }

        return null;
    }
}
