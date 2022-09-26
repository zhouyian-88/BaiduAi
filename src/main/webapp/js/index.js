$(function(){
   //当页面加载完成，运行js代码
    //把图像技术的字体颜色变成蓝色
    $(".content section").hide();
    $("#ocr_tec").show();
    //获取要绑定的事件源
    $(".ocr_tec").click(function(){
        // $(this).attr("id","active");
        // $(this).siblings().removeAttr("id");
        // $("#ocr_tec").show();
        // $("#ocr_tec").siblings().hide();
        switchTo(this,"#ocr_tec");
    });
    $(".face_body_detect").click(function(){
        /*$(this).siblings().removeAttr("id");
        $(this).attr("id","active");
        $("#face_body_detect").show();
        $("#face_body_detect").siblings().hide();*/
        switchTo(this,"#face_body_detect");
    });

    $(".voice_tec").click(function(){
       /* $(this).siblings().removeAttr("id");
        $(this).attr("id","active");
        $("#voice_tec").show();
        $("#voice_tec").siblings().hide();*/
        switchTo(this,"#voice_tec");
    });
    $(".nlp").click(function(){
       /* $(this).siblings().removeAttr("id");
        $(this).attr("id","active");
        $("#npl").show();
        $("#npl").siblings().hide();*/
        switchTo(this,"#nlp");
    });

});
function switchTo(nav_class,sectionId){
    $(nav_class).attr("id","active");
    $(nav_class).siblings().removeAttr("id");
    $(sectionId).show();
    $(sectionId).siblings().hide();
}
