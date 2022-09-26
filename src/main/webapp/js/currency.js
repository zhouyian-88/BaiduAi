$(function (){
    //点击选择照片，弹出文件框
    $(".content_btn").click(function (){
        $(".file_input").trigger("click");
    })

    //给file框绑定改变事件
    //获取到file中的图片,显示到预览区
    $(".file_input").change(function (){
        var photo =this.files[0];
        console.log(photo);
        $(".prev_photo").attr("src",URL.createObjectURL(photo));
    })


    //给“开始检测”绑定点击事件
    $(".btn").click(function (){
        //获取请求参数
        if($(".prev_photo").attr("src")=="girl.jpg"){
            confirm("请选择照片！！！")
            return
        }
        //获取表单
        var form = $(".form")[0];
        //封装到formdata中
        var formData = new FormData(form);
        // formData.append("face_field",checkboxVal);
        //发送ajax请求
        $.ajax({
            url:'image?action=getCurrency',
            data:formData,
            type:'post',
            dataType:'json',
            processData:false,
            contentType:false,
            success:function (data){
                var htmlStr = "";
                 htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">货币名称:</p><span class=\"right_span\">" +  data.result.currencyName + "</span></div>"
                 htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">货币代码:</p><span class=\"right_span\">" + data.result.currencyCode + "</span></div>"
                 htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">货币年份:</p><span class=\"right_span\">" + data.result.year + "</span></div>"
                 htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">货币面值:</p><span class=\"right_span\">" + data.result.currencyDenomination + "</span></div>"
                //拼接到页面上
                $(".show_result").html(htmlStr);
                //把照片制成原来初始照片
                $(".prev_photo").attr("src","img/girl.jpg");
                console.log(data);
            },
            error:function (data){
                console.log(data);
            }
        })


    })

})