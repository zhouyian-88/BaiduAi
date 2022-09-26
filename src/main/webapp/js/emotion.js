//页面全部加载完毕，才开始走js代码
$(function(){


    // $('.show_result').hide();
   $('.btn').bind("click",(function(){
       //1.当用户点击“开始分析”按钮时，获取textarea里面的内容
       var text = $('.text').val();
       //当输入框里面没有输入内容时，点击“开始分析”按钮不发送请求
       if(text==""){
           confirm("不能为空！！！")
           return;
       }
       //2.得到内容，向服务器发起异步请求
       $.ajax({
           url:'nlp?action=emotionAnalysis',
           data:{
               text:text
           },
           type:'post',
           dataType:'json',
           success:function (data){
                   //遍历items，局部刷新页面
                   var htmlStr = "";
                   $.each(data.items,function(index,obj){
                       if(obj.positive_prob===0){
                           htmlStr += "<p>分析结果:<span style='color: #fb2856'>消极<span></p>"
                       }else if(obj.positive_prob===1){
                           htmlStr += "<p>分析结果:<span style='color: #e9f01f'>中性<span></p>"
                       }else{
                           htmlStr += "<p>分析结果:<span style='color : #42f803'>积极<span></p>"
                       }

                       if(obj.confidence>=0&&obj.confidence<=0.1){
                           htmlStr += "<p>自信度:<span style='color: #fb2856'>非常极其不自信</span></p>"
                       }else if(obj.confidence>0.1&&obj.confidence<=0.2){
                           htmlStr += "<p>自信度:<span style='color: #f0641f'>极其不自信<span></p>"
                       }else if(obj.confidence>0.2&&obj.confidence<=0.3){
                           htmlStr += "<p>自信度:<span style='color: #f0951f'>非常不自信</span></p>"
                       }else if(obj.confidence>0.3&&obj.confidence<=0.4){
                           htmlStr += "<p>自信度:<span style='color: #f0b31f'>很不自信</span></p>"
                       }else if(obj.confidence>0.4&&obj.confidence<=0.5){
                           htmlStr += "<p>自信度:<span style='color: #f0d51f'>不自信</span></p>"
                       }else if(obj.confidence>0.5&&obj.confidence<=0.6){
                           htmlStr += "<p>自信度:<span style='color: #e9f01f'>比较自信</span></p>"
                       }else if(obj.confidence>0.6&&obj.confidence<=0.7){
                           htmlStr += "<p>自信度:<span style='color: #aef01f'>很自信</span></p>"
                       }else if(obj.confidence>0.7&&obj.confidence<=0.8){
                           htmlStr += "<p>自信度:<span style='color: #65f803'>非常自信</span></p>"
                       }else if(obj.confidence>0.8&&obj.confidence<=0.9){
                           htmlStr += "<p>自信度:<span style='color: #5ff803'>非常极其自信</span></p>"
                       }else{
                           htmlStr += "<p>自信度:<span  style='color: #42f803'>超级自信</span></p>"
                       }


                       if(obj.positive_prob>=0&&obj.positive_prob<=0.1){
                           htmlStr += "<p>积极程度:<span style='color: #fb2856'>非常极其不积极</span></p>"
                       }else if(obj.positive_prob>0.1&&obj.positive_prob<=0.2){
                           htmlStr += "<p>积极程度:<span style='color: #f0641f'>极其不积极</span></p>"
                       }else if(obj.positive_prob>0.2&&obj.positive_prob<=0.3){
                           htmlStr += "<p>积极程度:<span style='color: #f0951f'>非常不积极</span></p>"
                       }else if(obj.positive_prob>0.3&&obj.positive_prob<=0.4){
                           htmlStr += "<p>积极程度:<span style='color: #f0b31f'>很不积极</span></p>"
                       }else if(obj.positive_prob>0.4&&obj.positive_prob<=0.5){
                           htmlStr += "<p>积极程度:<span style='color: #f0d51f'>不积极</span</p>"
                       }else if(obj.positive_prob>0.5&&obj.positive_prob<=0.6){
                           htmlStr += "<p>积极程度:<span style='color: #e9f01f'>比较积极</span></p>"
                       }else if(obj.positive_prob>0.6&&obj.positive_prob<=0.7){
                           htmlStr += "<p>积极程度:<span style='color: #aef01f'>很积极</span</p>"
                       }else if(obj.positive_prob>0.7&&obj.positive_prob<=0.8){
                           htmlStr += "<p>积极程度:<span style='color: #65f803'>非常积极</span></p>"
                       }else if(obj.positive_prob>0.8&&obj.positive_prob<=0.9){
                           htmlStr += "<p>积极程度:<span style='color: #5ff803'>非常极其积极</span></p>"
                       }else{
                           htmlStr += "<p>积极程度:<span style='color: #42f803'>超级积极</span></p>"
                       }

                       if(obj.negative_prob>=0&&obj.negative_prob<=0.1){
                           htmlStr += "<p>消极程度:<span style='color: #42f803'>非常极其不消极</span></p>"
                       }else if(obj.negative_prob>0.1&&obj.negative_prob<=0.2){
                           htmlStr += "<p>消极程度:<span style='color: #5ff803'>极其不消极</span></p>"
                       }else if(obj.negative_prob>0.2&&obj.negative_prob<=0.3){
                           htmlStr += "<p>消极程度:<span style='color: #65f803'>非常不消极</span></p>"
                       }else if(obj.negative_prob>0.3&&obj.negative_prob<=0.4){
                           htmlStr += "<p>消极程度:<span style='color: #aef01f'>很不消极</span></p>"
                       }else if(obj.negative_prob>0.4&&obj.negative_prob<=0.5){
                           htmlStr += "<p>消极程度:<span style='color: #e9f01f'>不消极</span></p>"
                       }else if(obj.negative_prob>0.5&&obj.negative_prob<=0.6){
                           htmlStr += "<p>消极程度:<span style='color: #f0d51f'>比较消极</span></p>"
                       }else if(obj.negative_prob>0.6&&obj.negative_prob<=0.7){
                           htmlStr += "<p>消极程度:<span style='color: #f0b31f'>很消极</span></p>"
                       }else if(obj.negative_prob>0.7&&obj.negative_prob<=0.8){
                           htmlStr += "<p>消极程度:<span style='color: #f0951f'>非常消极</span></p>"
                       }else if(obj.negative_prob>0.8&&obj.negative_prob<=0.9){
                           htmlStr += "<p>消极程度:<span style='color: #f0641f'>非常极其消极</span></p>"
                       }else{
                           htmlStr += "<p>消极程度:<span style='color: #fb2856'>超级消极</span></p>"
                       }

                       /*
                        htmlStr += "<p>分析结果:"+obj.positive_prob+"</p>"
                           htmlStr += "<p>自信度:"+obj.sentiment+"</p> "
                           htmlStr += "<p>积极概率:"+obj.confidence+"</p> "
                           htmlStr += "<p>消极概率:"+obj.negative_prob+"</p> "*/
                   });
                   $(".show_result").html(htmlStr);
                   //将文本框置空
                    $(".text").val('');
           },
           error:function (data){
               console.log(data);
               alert("程序繁忙，请稍后再试...")
           }

       })
   }));

});