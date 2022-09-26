$(function () {
    //页面加载完成
    //给“依存句法分析”按钮绑定单击事件
    $(".btn").click(function () {
        //获取text的值
        var text = $(".text").val();
        var model = $("model option:selected").val();
        //验证参数是否合法
        if (text == "") {
            confirm("文本框不能为空！！");
            return;
        }
        //发送ajax请求
        $.ajax({
            url: 'nlp?action=getWordParsing',
            data: {
                word: text,
                model:model
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
                    htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">词:</p><span class=\"right_span\">" + data.items[i].word + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">词性:</p><span class=\"right_span\">" + data.items[i].postag + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">词的父节点ID:</p><span class=\"right_span\">" + data.items[i].head + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">词与父节点的依存关系:</p><span class=\"right_span\">" + data.items[i].deprel + "</span></div>"
                }
                //拼接到页面上
                $(".show_result").html(htmlStr);
                $(".text").val("");
                $("model option").val("web");
            },
            error: function (data) {
                console.log(data);
                alert("程序繁忙，请稍后再试...")
            }
        });
    });
});