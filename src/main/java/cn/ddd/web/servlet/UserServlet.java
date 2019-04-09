package cn.ddd.web.servlet;

import cn.ddd.domain.ResultInfo;
import cn.ddd.domain.User;
import cn.ddd.service.impl.userServiceImpl;
import cn.ddd.service.userService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private userService service = new userServiceImpl();

    /**
     * 注册功能
     * @param request
     * @param response
     */
    public void regist(HttpServletRequest request, HttpServletResponse response){
        //验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            try {
                writeValue(info,response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        //获取数据 map格式
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            //封装数据
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用service进行注册
        boolean flag = false;
        try {
            flag = service.regist(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //响应信息
        ResultInfo info = new ResultInfo();
        if(flag){
            //注册成功
            info.setFlag(true);
            info.setErrorMsg("注册成功！请登录您的注册邮箱激活您的账号，激活后才能登录。");
        }else{
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败,帐号或邮箱已被使用！");
        }
        try {
            writeValue(info,response);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 找回密码
     */
    public void findpass(HttpServletRequest request, HttpServletResponse response){
        //验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            try {
                writeValue(info,response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        //获取数据 map格式
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            //封装数据
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用service进行注册
        boolean flag = false;

        flag = service.findPass(user);

        //响应信息
        ResultInfo info = new ResultInfo();
        if(flag){
            //注册成功
            info.setFlag(true);
            info.setErrorMsg("邮件发送成功！请登录您的注册邮箱查询激活码。");
        }else{
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("发送失败！邮箱不存在，请注册！");
        }
        try {
            writeValue(info,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void findpass2(HttpServletRequest request, HttpServletResponse response){
        //验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            try {
                writeValue(info,response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        //获取数据 map格式
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            //封装数据
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用service进行注册
        boolean flag = false;

        flag = service.findPass2(user);

        //响应信息
        ResultInfo info = new ResultInfo();
        if(flag){
            //注册成功
            info.setFlag(true);
            info.setErrorMsg("密码修改成功！请重新登录。");
        }else{
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("修改失败！安全码不存在！");
        }
        try {
            writeValue(info,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登陆功能
     */
    public void login(HttpServletRequest request,HttpServletResponse response){
        //验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            try {
                writeValue(info,response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        //获取用户数据
        Map<String, String[]> map = request.getParameterMap();
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service查询
        String u = service.login(user);

        ResultInfo info = new ResultInfo();

        System.out.printf("数据库登陆情况 :  "+u+" ----------------------");
        if(u.equals("y")){
            //System.out.printf("登陆成功");
            info.setFlag(true);
            request.getSession().setAttribute("user",user.getUsername());//登录成功标记
        }
        if(u.equals("n")){
            //System.out.printf("帐号没有激活");
            info.setFlag(false);
            info.setErrorMsg("请先激活你的帐号再登陆！");
        }
        if(u.equals("x")){
            //System.out.printf("帐号或者密码不对");
            info.setFlag(false);
            info.setErrorMsg("帐号或密码错误！");
        }
//           相应数据
        try {
            writeValue(info,response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 查询单个对象
     */
    public void findOne(HttpServletRequest request,HttpServletResponse response){
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端
        try {
            writeValue(user,response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public void findGameUser(HttpServletRequest request,HttpServletResponse response){
//        Object user = request.getSession().getAttribute("user");
//        try {
//            String a = writeValueAsString(user);
//           // System.out.printf(a);
//            if(a != null){
//            User u = service.findGameUser(a);
//            ResultInfo info = new ResultInfo();
//            info.setErrorMsg(u.getChar_name());
//            writeValue(info,response);
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
    /**
     * 退出功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session
        request.getSession().invalidate();

        //2.跳转页面
        response.sendRedirect(request.getContextPath()+"/index.html");
    }
    /**
     * 激活功能
     */
    public void active(HttpServletRequest request,HttpServletResponse response)throws IOException{
        String code = request.getParameter("code");
        System.out.printf("code = "+code);
        if(code != null){
            //调用service进行激活
            boolean flag = service.active(code);
            //3.判断标记
            String msg = null;
            if(flag){
                //激活成功
                msg = "激活成功，请<a href='http://192.168.20.3:8082/ddd/index.html'>登录</a>";
            }else{
                //激活失败
                msg = "激活失败，请联系管理员!";
            }

                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write(msg);

        }

    }
}
