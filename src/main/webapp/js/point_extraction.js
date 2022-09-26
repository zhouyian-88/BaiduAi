$(function () {
    //页面加载完成
    //给“评论观点抽取”按钮绑定单击事件
    $(".btn").click(function () {
        //获取text的值
        var text = $(".text").val();
        var type = $(".input option:selected").val();
        console.log(text)
        console.log(type)
        //验证参数是否合法
        if (text == "") {
            confirm("文本框不能为空！！");
            return;
        }
        //发送ajax请求
        $.ajax({
            url: 'nlp?action=getCommentPoint',
            data: {
                text: text,
                type:type
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.log(data)
                //遍历items
                var len = data.items.length;
                console.log(len)
                var htmlStr = "";
                for (var i = 0; i < len; i++) {
                    htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">属性词:</p><span class=\"right_span\">" + data.items[i].prop + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">描述词:</p><span class=\"right_span\">" + data.items[i].adj + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">情感搭配的极性:</p><span class=\"right_span\">" + data.items[i].sentiment + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">情感搭配在句子中的开始位置:</p><span class=\"right_span\">" + data.items[i].begin_pos + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">情感搭配在句子中的结束位置:</p><span class=\"right_span\">" + data.items[i].end_pos + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">情感搭配的短句摘要:</p><span class=\"right_span\">" + data.items[i].abstract + "</span></div>"
                }
                //拼接到页面上
                $(".show_result").html(htmlStr);
                //给文本框和评论类型框置空
                $(".text").val("");
                $(".input option").val("美食餐饮");
            },
            error: function (data) {
                console.log(data);
                alert("程序繁忙，请稍后再试...")
            }
        });
    });
});