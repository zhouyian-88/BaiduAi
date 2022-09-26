$(function (){
    //当页面加载完成，开始执行js代码
    //给“新闻摘要”按钮绑定单击事件
    $(".btn").bind("click",function(){
        //获取标题和正文内容和摘要结果的最大长度
        var title = $(".title").val();
        var text = $(".text").val();
        var maxSummary = $(".max_summary_len").val();
        //判断标题和正文文本框是否为空，如果为空，则不发送异步请求
        if(text==""&&maxSummary==""){
            confirm("正文和结果长度不能为空");
            return;
        }
        //发送异步请求
        $.ajax({
           url:'nlp?action=headline',
            data:{
               title:title,
                text:text,
                maxSummary:maxSummary
            },
            type:'post',
            dataType:'json',
            success:function (data){
                console.log(data);
                $("#result_p").html(data.summary);
                //给标题和文本框置空
                $(".title").val("");
                $(".text").val("");
                $(".max_summary_len").val();
            },
            error:function (data){
               console.log(data);
               alert("程序繁忙，请稍后重试...");
            }
        });
    });
});