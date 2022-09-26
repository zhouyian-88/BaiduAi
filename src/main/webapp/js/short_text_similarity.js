$(function () {
    //页面加载完成
    //给“短文本相似度”按钮绑定单击事件
    $(".btn").click(function () {
        //获取所有输入框的值
        var originalText = $(".originalText").val();
        var compareText = $(".compareText").val();
       var model = $(".input option:selected").val();
        //验证参数是否合法
        if (originalText == ""||compareText=="") {
            confirm("文本框不能为空！！");
            return;
        }
        //发送ajax请求
        $.ajax({
            url: 'nlp?action=getTextSimilarity',
            data: {
                originalText: originalText,
                compareText:compareText,
                model:model
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.log(data)
                var htmlStr = "";
                htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">相似度结果:</p><span class=\"right_span\">" + data.score + "</span></div>"
                $(".show_result").html(htmlStr);
                $(".originalText").val("");
                $(".compareText").val("");
                $(".input option").val("BOW");
            },
            error: function (data) {
                console.log(data);
                alert("程序繁忙，请稍后再试...")
            }
        });
    });
});