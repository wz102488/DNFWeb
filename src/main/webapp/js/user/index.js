//查询用户信息
$(function () {
    $.get("user/findOne",{},function (data) {
        //{uid:1,name:'w123'}
        if(data != null){
            var info = "<A href=\"#\" class=\"nav_a\">" + data+"</A>";
            var exit = "<A href=\"http://192.168.20.3:8082/ddd/user/exit\" class=\"nav_a\">EXIT</A>";

            $("#login_status").html(info);
            $("#span_username").html(exit);
        }

    });});
//3.处理响应结果