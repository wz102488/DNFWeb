package cn.ddd.util;

import java.sql.*;

public class JdbcUtils {
    private static final String USER = "game";
    private static final String PWD = "uu5!^%jg";
    private static final String URL = "jdbc:mysql://192.168.20.6:3306/travel";
    private static final String DRIVER ="com.mysql.jdbc.Driver";

    /**
     * 注册驱动
     */
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 得到数据库的连接
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PWD);
    }
    /**
     * 关闭所有打开的资源
     */
    public static void close(Connection conn, Statement stmt){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 关闭所有打开资源
     */
    public static void close(Connection conn , Statement stmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(conn, stmt);
    }
}
