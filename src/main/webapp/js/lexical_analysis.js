$(function () {
    //页面加载完成
    //给“词法分析”按钮绑定单击事件
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
            url: 'nlp?action=getLexAnalysis',
            data: {
                text: text
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.log(data)
                var len = data.items.length;
                var items = data.items;
                var htmlStr = "";
                for (var i = 0; i < len; i++) {
                    htmlStr += "<div class=\"bottom_div\"><p class=\"left_p\">字节级length:</p><span class=\"right_span\">" + items[i].byte_length + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">text中的字节级offset:</p><span class=\"right_span\">" + items[i].byte_offset + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">词汇的标准化表达，主要针对时间、数字单位，没有归一化表达的，此项为空串:</p><span class=\"right_span\">" + items[i].formal + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">词汇的字符串:</p><span class=\"right_span\">" + items[i].item + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">命名实体类型:</p><span class=\"right_span\">" + items[i].ne + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">词性:</p><span class=\"right_span\">" + items[i].pos + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">链指到知识库的URI:</p><span class=\"right_span\">" + items[i].uri + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">地址成分:</p><span class=\"right_span\">" + items[i].loc_details[i] + "</span></div>"
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">基本词成分:</p><span class=\"right_span\">" + items[i].basic_words[i] + "</span></div>"
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