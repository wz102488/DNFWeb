/*
     表单校验：
         1.用户名：单词字符，长度8到20位
         2.密码：单词字符，长度8到20位
         3.email：邮件格式
         4.验证码：非空
  */


//校验密码
function checkPassword() {
    //1.获取密码值
    var password = $("#password").val();
    //var password2 = $("#password2").val();
    //2.定义正则
    var reg_password = /^\w{6,20}$/;

    //3.判断，给出提示信息
    var flag = reg_password.test(password);
    //var flag2 = password2===password;
    if(flag){
        //密码合法
        $("#password").css("border","");
        //$("#password2").css("border","");
    }else{
        //密码非法,加一个红色边框
        $("#password").css("border","1px solid red");
        //$("#password2").css("border","1px solid red");
    }

    return flag;
}


$(function () {
    //当表单提交时，调用所有的校验方法
    $("#btn_reg").click(function(){
        //1.发送数据到服务器
        if(checkPassword()){
            //校验通过,发送ajax请求，提交表单的数据   username=zhangsan&password=123
            $("#btn_reg").attr("disabled", true);
            $("#errorMsg").addClass('alert alert-info').html(" Please wait...");
            //alert("正在重置您的密码，请不要关闭页面。。");



            $.post("user/findpass2",$("#registerForm").serialize(),function(data){
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
    $("#password").blur(checkPassword);


});


