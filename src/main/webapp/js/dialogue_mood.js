$(function () {
    //页面加载完成
    //给“对话情绪分析”按钮绑定单击事件
    $(".btn").click(function () {
        //获取text和scene的值
        var text = $(".text").val();
        var scene = $(".input option:selected").val()

        console.log(text)
        console.log(scene)
        //验证参数是否合法
        if (text == "") {
            confirm("文本框不能为空！！");
            return;
        }
        //发送ajax请求
        $.ajax({
            url: 'nlp?action=getEmotionRecognize',
            data: {
                text: text,
                scene: scene
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.log(data)
                //遍历items
                var len = data.items.length;
                var htmlStr = "";
                for (var i = 0; i < len; i++) {
                    if (data.items[i].label == "neutral") {
                        htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">情绪类型:</p><span class=\"right_span\" style='color: lightgoldenrodyellow'>中性情绪</span></div>"
                    } else if (data.items[i].label == "optimistic") {
                        htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">情绪类型:</p><span class=\"right_span\" style='color: green'>正向情绪</span></div>"
                    } else {
                        htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">情绪类型:</p><span class=\"right_span\" style='color: red'>负向情绪</span></div>"
                    }

                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">概率:</p><span class=\"right_span\">" + data.items[i].prob + "</span></div>"
                }
                //拼接到页面上
                $(".show_result").html(htmlStr);
                //给标题和文本框置空
                $(".scene").val("");
                $(".text").val("");
            },
            error: function (data) {
                console.log(data);
                alert("程序繁忙，请稍后再试...")
            }
        });
    });
});