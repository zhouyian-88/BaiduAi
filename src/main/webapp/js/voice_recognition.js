$(function (){
    //点击选择照片，弹出文件框
    $(".content_btn").click(function (){
        $(".file_input").trigger("click");
    })

    $(".file_input").change(function (){
        var file =this.files[0].name;
        var result = "已选择:"+file+"文件";
        $(".show_p").html(result);
        // $(".prev_photo").attr("src",URL.createObjectURL(photo));
    })

    //给“开始检测”绑定点击事件
    $(".btn").click(function (){
        var file =$(".file_input").val();
        if(file==""){
            confirm("请选择文件！！！")
            return
        }
        //获取表单
        var form = $(".form")[0];
        //封装到formdata中
        var formData = new FormData(form);
        // formData.append("face_field",checkboxVal);
        //发送ajax请求
        $.ajax({
            url:'speech?action=getVoiceRecognition',
            data:formData,
            type:'post',
            dataType:'json',
            processData:false,
            contentType:false,
            success:function (data){
                var htmlStr = "";
                htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">识别结果:</p><span class=\"right_span\">" +  data.result[0] + "</span></div>"
                // htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">准确度:</p><span class=\"right_span\">" + data.result[0].score + "</span></div>"
                //拼接到页面上
                $(".show_result").html(htmlStr);
                //把文件置空
                $(".file_input").val("");
                $(".show_p").html("未选择文件");
                console.log(data);
            },
            error:function (data){
                console.log(data);
            }
        })


    })

})