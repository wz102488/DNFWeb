package cn.ddd.domain;

import java.io.Serializable;

/**
 * 用户实体类
 */
public class User implements Serializable {
    private int uid;//用户id
    private String username;//用户名，账号
    private String password;//密码
    private String email;//邮箱
    private String status;//激活状态，Y代表激活，N代表未激活
    private String code;//激活码（要求唯一）
    private String char_no; //角色id
    private String char_name; // 角色名
    private String item_id;//物品id
    private String item_num; //物品数量


    /**
     * 无参构造方法
     */
    public User() {
    }

    /**
     * 有参构方法
     * @param uid
     * @param username
     * @param password
     * @param name
     * @param birthday
     * @param sex
     * @param telephone
     * @param email
     * @param status
     * @param code
     */
    public User(int uid, String username, String password, String char_name, String char_no, String item_id, String item_num, String email, String status, String code) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.code = code;
        this.char_name = char_name;
        this.char_no = char_no;
        this.item_id = item_id;
        this.item_num = item_num;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChar_no() {
        return char_no;
    }

    public void setChar_no(String char_no) {
        this.char_no = char_no;
    }

    public String getChar_name() {
        return char_name;
    }

    public void setChar_name(String uid) {
        this.char_name = char_name;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String uid) {
        this.item_id = item_id;
    }

    public String getItem_num() {
        return item_num;
    }

    public void setItem_num(String uid) {
        this.item_num = item_num;
    }
}
