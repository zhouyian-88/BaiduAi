$(function (){
    //点击选择照片，弹出文件框
    $(".content_btn").click(function (){
        $(".file_input").trigger("click");
    })

    //给file框绑定改变事件
    //获取到file中的图片,显示到预览区
    $(".file_input").change(function (){
        var photo1 =this.files[0];
        var photo2 =this.files[1];
        console.log(photo1);
        $(".prev_photo1").attr("src",URL.createObjectURL(photo1));
        $(".prev_photo2").attr("src",URL.createObjectURL(photo2));
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


        var size1 = $(".file_input")[0].files[0].size;
        var size2 = $(".file_input")[0].files[1].size;
        //封装到formdata中
        var formData = new FormData(form);
        // console.log(formData.get(manypic[1]).size)
        // formData.append("face_field",checkboxVal);
        //发送ajax请求
        $.ajax({
            url:'face?action=getFaceCompare&size1='+size1+"&size2="+size2,
            data:formData,
            type:'post',
            dataType:'json',
            processData:false,
            contentType:false,
            success:function (data){
                var htmlStr = "";
                    htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">相似度:</p><span class=\"right_span\">" +  data.result.score + "</span></div>"
                //拼接到页面上
                $(".show_result").html(htmlStr);
                //把照片制成原来初始照片
                $(".prev_photo1").attr("src","img/girl.jpg");
                $(".prev_photo2").attr("src","img/girl.jpg");
                console.log(data);
            },
            error:function (data){
                console.log(data);
            }
        })


    })

})