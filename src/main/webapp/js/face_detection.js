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

    //用于拼接复选框所选中的值
    var checkboxVal = "";
    //给“开始检测”绑定点击事件
    $(".btn").click(function (){
        //获取请求参数
        $("input:checkbox:checked").each(function(index,element){
            checkboxVal+=$(element).val()+",";
        });
        if($(".prev_photo").attr("src")=="img/girl.jpg"){
            confirm("请选择照片！！！")
            return
        }
        //获取表单
        var form = $(".form")[0];
        console.log(form)
        //去掉CheckBoxVal多余的逗号
        checkboxVal= checkboxVal.substr(0,checkboxVal.length-1);
        //封装到formdata中
       var formData = new FormData(form);
       // formData.append("face_field",checkboxVal);
       //发送ajax请求
      $.ajax({
          url:'face?action=getFaceDetection&face_field='+checkboxVal,
          data:formData,
          type:'post',
          dataType:'json',
          processData:false,
          contentType:false,
          success:function (data){
              var len = data.result.face_list.length;
              var result =data.result.face_list;
              // console.log(result);
              var htmlStr = "";
              for (var i = 0; i < len; i++) {
                  if(result[i].age!=undefined){
                      htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">年龄:</p><span class=\"right_span\">" +  result[i].age + "</span></div>"
                  }
                  if(result[i].beauty!=undefined) {
                      htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">颜值:</p><span class=\"right_span\">" + result[i].beauty + "</span></div>"
                  }
                  if(result[i].expression!=undefined){
                      htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">表情:</p><span class=\"right_span\">" + result[i].expression.type + "</span></div>"
                  }
                  if(result[i].face_shape!=undefined) {
                      htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">脸型:</p><span class=\"right_span\">" + result[i].face_shape.type + "</span></div>"
                  }
                  if(result[i].gender!=undefined) {
                      htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">性别:</p><span class=\"right_span\">" + result[i].gender.type + "</span></div>"
                  }
                  if(result[i].glasses!=undefined) {
                      htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">眼镜:</p><span class=\"right_span\">" + result[i].glasses.type + "</span></div>"
                  }
                  if(result[i].quality!=undefined) {
                      htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">人脸模糊程度:</p><span class=\"right_span\">" + result[i].quality.blur + "</span></div>"
                  }
                  if(result[i].eye_status!=undefined) {
                      htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">左眼:</p><span class=\"right_span\">" + result[i].eye_status.left_eye + "</span></div>"
                  }
                  if(result[i].eye_status!=undefined) {
                      htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">右眼:</p><span class=\"right_span\">" + result[i].eye_status.right_eye + "</span></div>"
                  }
                  if(result[i].emotion!=undefined) {
                      htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">情绪:</p><span class=\"right_span\">" + result[i].emotion.type + "</span></div>"
                  }
                  if(result[i].face_type!=undefined) {
                      htmlStr += " <div class=\"bottom_div\"><p class=\"left_p\">真假脸:</p><span class=\"right_span\">" + result[i].face_type.type + "</span></div>"
                  }
              }
              //拼接到页面上
              $(".show_result").html(htmlStr);
              //把照片制成原来初始照片
              $(".prev_photo").attr("src","img/girl.jpg");
              // $(".checkbox_ipt").prop("checked",false);
              //把checkboxVal置空
              checkboxVal="";
              console.log(data);
          },
          error:function (data){
              console.log(data);
          }
      })


    })

})