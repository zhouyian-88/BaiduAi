$(function () {
    //页面加载完成
    //给“词义相似度”按钮绑定单击事件
    $(".btn").click(function () {
        //获取所有输入框的值
        var originalWord = $(".originalWord").val();
        var compareWord = $(".compareWord").val();

        console.log(originalWord)
        console.log(compareWord)
        //验证参数是否合法
        if (originalWord == ""||compareWord=="") {
            confirm("文本框不能为空！！");
            return;
        }
        //发送ajax请求
        $.ajax({
            url: 'nlp?action=getWordSimilarity',
            data: {
                originalWord: originalWord,
                compareWord:compareWord
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.log(data)
                var htmlStr = "";
                htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">词语一:</p><span class=\"right_span\">" + data.words.word_1 + "</span></div>"
                htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">词语二:</p><span class=\"right_span\">" + data.words.word_2 + "</span></div>"
                htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">词义相似度:</p><span class=\"right_span\">" + data.score + "</span></div>"
                $(".show_result").html(htmlStr);
                $(".originalWord").val("");
                $(".compareWord").val("");
            },
            error: function (data) {
                console.log(data);
                alert("程序繁忙，请稍后再试...")
            }
        });
    });
});