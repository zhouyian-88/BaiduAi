$(function (){
    //当页面加载完成，开始执行js代码
    //给“文章分类”按钮绑定单击事件
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
            url:'nlp?action=artClassification',
            data:{
                title:title,
                text:text
            },
            type:'post',
            dataType:'json',
            success:function (data){
                console.log(data);
                //遍历data中的item
                var lv1 = data.item.lv1_tag_list.length;
                var lv2 = data.item.lv2_tag_list.length;
                console.log(data.item.lv1_tag_list);
                var htmlStr = "";
                for(var i = 0;i < lv1;i++){
                    htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">标签:</p><span class=\"right_span\">"+data.item.lv1_tag_list[i].tag+"</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">权重:</p><span class=\"right_span\">"+data.item.lv1_tag_list[i].score+"</span></div>"
                }
                for(var i = 0;i < lv2;i++){
                    htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">标签:</p><span class=\"right_span\">"+data.item.lv2_tag_list[i].tag+"</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">权重:</p><span class=\"right_span\">"+data.item.lv2_tag_list[i].score+"</span></div>"
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