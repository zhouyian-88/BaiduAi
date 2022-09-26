$(function (){
    //页面加载完成

    //给input框绑定改变事件
    $(".input").bind("change",function (){
        //获取语速，语调，音量和音色人的值
        var spd = $(".spd").val();
        var pit = $(".pit").val();
        var vol = $(".vol").val();
        console.log(spd)
        console.log(pit)
        console.log(vol)
        //获取a标签的对象
        $("#spd").html(spd);
        $("#pit").html(pit);
        $("#vol").html(vol);
    });

    $(".btn").bind("click",function (){
        var text = $(".text").val();
        var spd = $(".spd").val();
        var pit = $(".pit").val();
        var vol = $(".vol").val();
        var per = $(".per option:selected").val();
        //验证文本框是否为空，如果为空，则不发送ajax请求
        if(text==""){
            confirm("文本框不能为空!!!")
            return;
        }
        //发送ajax请求
        $.ajax({
            url:'speech?action=getVoiceMerge',
            data:{
                text:text,
                spd:spd,
                pit:pit,
                vol:vol,
                per:per
            },
            type:'post',
            dataType:'json',
            success:function (data){
                console.log(data)
                var audio = $("#audio")[0];
                audio.setAttribute("src","data:audio/mp3;base64,"+data.data);
                //加载音频
                audio.load();
                // audio.play();
                console.log("成功")
            },
            error:function (data){
                console.log(data)
                console.log("失败")
            }
        });
    });
});