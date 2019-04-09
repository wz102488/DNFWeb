/*
     表单校验：
         1.用户名：单词字符，长度8到20位
         2.密码：单词字符，长度8到20位
         3.email：邮件格式
         4.验证码：非空
  */

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

//校验邮箱
function checkEmail(){
    //1.获取邮箱
    var email = $("#email").val();
    //2.定义正则		itcast@163.com
    var reg_email = /^\w+@\w+\.\w+$/;

    //3.判断
    var flag = reg_email.test(email);
    if(flag){
        $("#email").css("border","");
    }else{
        $("#email").css("border","1px solid red");
    }

    return flag;
}

$(function () {
    //当表单提交时，调用所有的校验方法
    $("#btn_reg").click(function(){
        //1.发送数据到服务器
        if(checkUsername() && checkPassword() && checkEmail()){
            //校验通过,发送ajax请求，提交表单的数据   username=zhangsan&password=123
            $("#btn_reg").attr("disabled", true);
            $("#errorMsg").addClass('alert alert-info').html("Registing ,  Please wait...");
            alert("注册中，请不要关闭页面。。");



            $.post("user/regist",$("#registerForm").serialize(),function(data){
                //处理服务器响应的数据  data  {flag:true,errorMsg:"注册失败"}

                if(data.flag){
                    //注册成功，跳转成功页面
                   //location.href="register_ok.html";


                    $("#registSuccess").addClass('alert alert-success').html(data.errorMsg);
                }else{
                    //注册失败,给errorMsg添加提示信息
                    //$("#errorMsg").html(data.errorMsg);


                    $("#btn_reg").attr("disabled", false);
                    $("#errorMsg").addClass('alert alert-danger').html(data.errorMsg);

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
    $("#email").blur(checkEmail);


});


