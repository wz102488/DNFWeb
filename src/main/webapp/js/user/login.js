//


//校验用户名
//单词字符，长度8到20位
function checkUsername() {
    //1.获取用户名值
    var username = $("#username").val();
    //2.定义正则
    var reg_username = /^\w{6,20}$/;

    //3.判断，给出提示信息
    var flag = reg_username.test(username);
    if(flag){
        //用户名合法
        $("#username").css("border","");
    }else{
        //用户名非法,加一个红色边框
        $("#username").css("border","1px solid red");
    }

    return flag;
}

//校验密码
function checkPassword() {
    //1.获取密码值
    var password = $("#password").val();
    //2.定义正则
    var reg_password = /^\w{6,20}$/;

    //3.判断，给出提示信息
    var flag = reg_password.test(password);
    if(flag){
        //密码合法
        $("#password").css("border","");
    }else{
        //密码非法,加一个红色边框
        $("#password").css("border","1px solid red");
    }

    return flag;
}

$(function () {
    //当表单提交时，调用所有的校验方法
    $("#btn_sub").click(function () {
        //1.发送数据到服务器
        if(checkUsername() && checkPassword()){
            //校验通过,发送ajax请求，提交表单的数据   username=zhangsan&password=123
            $.post("user/login",$("#loginForm").serialize(),function (data) {
                //data : {flag:false,errorMsg:''}
                if(data.flag){
                    //登录成功
                    location.href="http://192.168.20.3:8082/ddd/index.html";
                    //$("#errorMsg").html("登陆成功");
                }else{
                    //登录失败
                    $("#errorMsg").html(data.errorMsg).modal("show");
                }
            });


        }
        //2.不让页面跳转
        return false;
        //如果这个方法没有返回值，或者返回为true，则表单提交，如果返回为false，则表单不提交
    });
    //当某一个组件失去焦点是，调用对应的校验方法
    $("#username").blur(checkUsername);
    $("#password").blur(checkPassword);
});