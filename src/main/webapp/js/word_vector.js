$(function () {
    //页面加载完成
    //给“词向量表示”按钮绑定单击事件
    $(".btn").click(function () {
        //获取text的值
        var word = $(".text").val();
        console.log(word)
        //验证参数是否合法
        if (word == "") {
            confirm("文本框不能为空！！");
            return;
        }
        //发送ajax请求
        $.ajax({
            url: 'nlp?action=getWordVector',
            data: {
                word: word
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                var htmlStr = "";
                for(var i = 0;i < 10;i++){
                    htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">词向量结果"+i+":</p><span class=\"right_span\">"+data.vec[i]+"</span></div>"
                }
                //拼接到页面上
                $(".show_result").html(htmlStr);
                $(".word").val("");


            },
            error: function (data) {
                console.log(data);
                alert("程序繁忙，请稍后再试...")
            }
        });
    });
});