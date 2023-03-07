$(function (){
    $.ajax({
        type: "get",
        url: "/user/show",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $("#username").val(data.username);
            $("#mate").val(data.matename);
            $("#tel").val(data.tel);
            $("#email").val(data.email);
            if(data.matename !== "") {
                $("#time").val(new Date(data.time).toLocaleString());
            }
        }
    });
    $.ajax({
        type: "get",
        url: "/user/showAvatar",
        dataType: "json",
        success: function (data) {
            $("#avatar").attr('src',data.userAvatar);
        }
    });
})

function findmate() {

    layer.confirm('本网站只允许您一生绑定一位伴侣，是否继续？', {
        btn: ['我很坚定','我再想想'] //按钮
    }, function(){

        layui.use(['element', 'layer', 'util', 'laydate'], function () {
            var element = layui.element
                , layer = layui.layer
                , util = layui.util
                , $ = layui.$
                , laydate = layui.laydate;
        });
        layer.open({
            type: 1
            , title: '寻找另一半' //不显示标题栏
            , closeBtn: false
            , area: ['400px', '450px']  // 宽和高
            , shade: 0.8
            , id: 'LAY_layuipro' //设定一个id，防止重复弹出
            , btn: ['绑定为伴侣', '取消']
            , btnAlign: 'c'
            , moveType: 1 //拖拽模式，0或者1
            , content: $('#findmate').html()         // 引入自定义表单
            , btn1: function (index, layero) {            // 这个是新增按钮点击事件
                let username = $("#username").val();
                let matename = $("#matename").val();
                layer.msg(username+matename);
                if(matename === "" || matename===null) {
                    layer.msg("请输入需要绑定的用户名");
                }
                else {
                    $.ajax({
                        url:"/user/findmate",   // 请求路径
                        type:"post",            // 请求的方式，不区分大小写
                        data:{
                            username:username,
                            matename:matename
                        },
                        datatype:"json",        // 返回类型，text文本、html页面、json数据
                        success:function(response){
                            console.log("返回: " + response);
                            layer.msg(response.msg);
                            if(response.code === 200) {
                                layer.close(index);
                                $("#mate").val(response.matename);
                                $("#time").val(new Date(response.time).toLocaleString());
                                sessionStorage.setItem("mate_name",response.matename);
                            }
                        },
                        error:function(response){
                            console.log("出错返回: " + response);
                            console.log("出错数据: " + JSON.stringify(response));
                        }
                    });
                }

            }

        })

    }, function(){
    });
}




