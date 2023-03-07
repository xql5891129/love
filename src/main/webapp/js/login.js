function register(){
    layui.use(['element', 'layer', 'util','laydate'], function() {
        var element = layui.element
            , layer = layui.layer
            , util = layui.util
            , $ = layui.$
            , laydate = layui.laydate;
    });
    layer.open({
        type: 1
        ,title: '注册' //不显示标题栏
        ,closeBtn: false
        ,area: ['400px','450px']  // 宽和高
        ,shade: 0.8
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,btn: ['注册', '取消']
        ,btnAlign: 'c'
        ,moveType: 1 //拖拽模式，0或者1
        ,content: $('#register').html()         // 引入自定义表单
        ,btn1: function(index, layero){         // 这个是新增按钮点击事件
            let username = $("#insert_username").val();
            let password = $("#insert_password").val();
            let passwordRepeat = $("#insert_passwordRepeat").val();
            let sex = $("input[type=\"radio\"]").val();
            let tel = $("#insert_tel").val();
            let email = $("#insert_email").val();
            if(username === "" || username===null ||password === "" || password===null ||passwordRepeat === "" || passwordRepeat===null ||tel === "" || tel===null ||email === "" || email===null) {
                layer.msg("信息填写不完整");
            }
            else if (password!=passwordRepeat){
                layer.msg("两次密码不一致");
            }
            else if (tel.length!=11){
                layer.msg("手机号必须为11位");
            }
            else if(isNaN(tel)==true){
                layer.msg("手机号必须是数字");
            }
            else if(email.indexOf("@")==-1||email.charAt(0)=="@"||email.endsWith("@")==true||(email.split('@')).length>2){
                layer.msg("邮件格式不正确");
            }
            else {
                $.ajax({
                    url:"/user/insert",   // 请求路径
                    type:"post",            // 请求的方式，不区分大小写
                    cache:false,            // 关闭缓存，目的是为了避免部分浏览器缓存加载出错(IE)
                    contentType:"application/json;charset=utf-8",
                    data:JSON.stringify({
                        username:username,
                        password:calcMD5(password),
                        sex:sex,
                        tel:tel,
                        email:email
                    }),
                    datatype:"json",        // 返回类型，text文本、html页面、json数据
                    success:function(response){
                        console.log("返回: " + response);
                        layer.msg(response.msg);
                        if(response.code === 200) {
                            layer.close(index);
                        }
                    },
                    error:function(response){
                        console.log("出错返回: " + response);
                        console.log("出错数据: " + JSON.stringify(response));
                    }
                });
            }
        }
        ,success: function(layero){
            layui.use('form', function(){
                var form = layui.form;
                form.render('radio');
            });
        }
    });
}

function save(){
    if($("#username").val()===""||$("#password").val()===""||$("#username").val()===null||$("#password").val()===null)
        alert("请输入用户名和密码");
    else {
        $.ajax({
            type: "post",
            url: "/user/login",
            data: {
                username: $("#username").val(),
                password: calcMD5($("#password").val())
            },
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.code === 200){
                    window.location.href = "home.html";
                    sessionStorage.setItem("user_name",data.username)
                    sessionStorage.setItem("mate_name",data.matename)
                }
                else alert(data.msg);
            }
        });
    }
}