$(function () {
    //页面加载完成
    //给“文本纠错”按钮绑定单击事件
    $(".btn").click(function () {
        //获取text的值
        var text = $(".text").val();
        console.log(text)
        //验证参数是否合法
        if (text == "") {
            confirm("文本框不能为空！！");
            return;
        }
        //发送ajax请求
        $.ajax({
            url: 'nlp?action=getTextCorrection',
            data: {
                text: text
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.log(data)
                //遍历items
                var len = data.item.vec_fragment.length;
                console.log(len)
                var htmlStr = "";
                for (var i = 0; i < len; i++) {
                    htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">原始内容:</p><span class=\"right_span\" style='color: lightgoldenrodyellow'>" + data.item.vec_fragment[i].ori_frag + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">纠错内容:</p><span class=\"right_span\">" + data.item.vec_fragment[i].correct_frag + "</span></div>"
                }
                //拼接到页面上
                $(".show_result").html(htmlStr);
                //给标题和文本框置空
                $(".text").val("");
            },
            error: function (data) {
                console.log(data);
                alert("程序繁忙，请稍后再试...")
            }
        });
    });
});