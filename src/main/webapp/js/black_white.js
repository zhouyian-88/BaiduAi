$(function (){
    //点击选择照片，弹出文件框
    $(".content_btn").click(function (){
        $(".file_input").trigger("click");
    })

    $(".show_photo").hide();
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
            url:'imageProcess?action=getBlackWhite',
            data:formData,
            type:'post',
            dataType:'json',
            processData:false,
            contentType:false,
            success:function (data){
                var base64img = data.image;
               console.log(base64img)
                var image = 'data:image/jpg;base64,'+base64img;
                $(".show_photo").attr("src",image);
                $(".show_photo").show();
                // $(".show_photo").attr("src","img/girl.jpg");
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
