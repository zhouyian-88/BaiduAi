$(function (){
    //当页面加载完成，开始执行js代码
    //给“文章标签”按钮绑定单击事件
    $(".btn").bind("click",function(){
        //获取标题和正文内容
        var title = $(".title").val();
        var text = $(".text").val();
        //判断标题和正文文本框是否为空，如果为空，则不发送异步请求
        if(title==""&&text==""){
            confirm("标题和正文文本框不能为空");
            return;
        }
        //发送异步请求
        $.ajax({
           url:'nlp?action=artLabel',
            data:{
               title:title,
                text:text
            },
            type:'post',
            dataType:'json',
            success:function (data){
                console.log(data);
                //遍历data中的item
                var len = data.items.length;
                console.log(len)
                var htmlStr = "";
                for(var i = 0;i < len;i++){
                   htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">标签:</p><span class=\"right_span\">"+data.items[i].tag+"</span></div>"
                   htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">权重:</p><span class=\"right_span\">"+data.items[i].score+"</span></div>"
                }
                //拼接到页面上
                $(".show_result").html(htmlStr);
                //给标题和文本框置空
                $(".title").val("");
                $(".text").val("");
            },
            error:function (data){
               console.log(data);
               alert("程序繁忙，请稍后重试...");
            }
        });
    });
});