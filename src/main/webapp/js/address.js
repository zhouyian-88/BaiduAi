$(function(){
    //当页面加载完成时，开始执行js代码
    //给“开始识别”按钮绑定单击事件
    $(".btn").on("click",function(){
        //获取到文本框的内容
        var text = $.trim($(".text").val());
        //判断文件框是否为空，做验证
        if(text==null&&text==""){
            //当输入为空，不发送请求
            confirm("输入框不能为空");
            return;
        }
        //发送异步请求
        $.ajax({
           url:'nlp?action=addressRecognize',
           data:{
               text:text
           },
           type:'post',
           dataType:'json',
           success:function(data){
               //获取后台传过来的值，并把它写入到页面中
               var htmlStr = "";
                 htmlStr +="<div class=\"bottom_div\"><p class=\"left_p\">省份:</p><span class=\"right_span\">"+data.province+"</span></div>"
                 htmlStr +="<div class=\"bottom_div\"><p class=\"left_p\">城市:</p><span class=\"right_span\">"+data.city+"</span></div>"
                 htmlStr +="<div class=\"bottom_div\"><p class=\"left_p\">区:</p><span class=\"right_span\">"+data.county+"</span></div>"
                 htmlStr +="<div class=\"bottom_div\"><p class=\"left_p\">乡镇:</p><span class=\"right_span\">"+data.town+"</span></div>"
                 htmlStr +="<div class=\"bottom_div\"><p class=\"left_p\">姓名:</p><span class=\"right_span\">"+data.person+"</span></div>"
                 htmlStr +="<div class=\"bottom_div\"><p class=\"left_p\">电话号码:</p><span class=\"right_span\">"+data.phonenum+"</span></div>"
                 htmlStr +="<div class=\"bottom_div\"><p class=\"left_p\">省国标:</p><span class=\"right_span\">"+data.province_code+"</span></div>"
                 htmlStr +="<div class=\"bottom_div\"><p class=\"left_p\">城市国标:</p><span class=\"right_span\">"+data.city_code+"</span></div>"
                 htmlStr +="<div class=\"bottom_div\"><p class=\"left_p\">区县国标:</p><span class=\"right_span\">"+data.county_code+"</span></div>"
                 htmlStr +="<div class=\"bottom_div\"><p class=\"left_p\">乡镇国标:</p><span class=\"right_span\">"+data.town_code+"</span></div>"
                htmlStr +="<div class=\"bottom_div\"><p class=\"left_p\">详细地址:</p><span class=\"right_span\">"+data.detail+"</span></div>"
               //拼接到页面上
               $(".show_result").html(htmlStr);
                 //清除文本框里面的内容
               $(".text").val("");
           },
            error:function (data){
                console.log(data);
                alert("程序繁忙，请稍后再试...")
            }
        });
    });
});