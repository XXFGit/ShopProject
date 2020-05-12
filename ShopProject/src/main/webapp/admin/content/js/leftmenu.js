
$(document).ready(function() {
    /**
     * 左侧菜单加载
     */
    selMenu();
    /**
     * 左侧菜单选中active
     * 获取地址栏，和li上匹配，相等给自己和父元素添加active类，
     */
    var pathName = window.location.pathname;
    if ($("#leftNavUl li").length>0){
        $("#leftNavUl li").each(function(){
            if($(this).attr("temp")==pathName){
                $(this).addClass('active');
                if ($(this).parent().hasClass('sub-menu')){
                    $(this).parent().parent().addClass('active');
                }
            }
        });
    }

});

//左侧菜单(){}
function selMenu(){
    $.ajax({
        type : 'POST',
        url: "/menu/selMenu.do",
        async: false,
        cache:false,
        contentType:"application/json; charset=utf-8",
        datatype:'json',
        success:function(json){
            if (json!=""){
                var json = jQuery.parseJSON(json);
                //组装菜单。
                $("#leftNavUl").html(leftNav(json));
            }
        }
    });
}

//组装左侧菜单html内容
function leftNav(data){
    var ulHtml = "";
    for(var i=0;i<data.length;i++){
        //判断有没有子菜单
        if(data[i].children != undefined && data[i].children.length > 0){
            //有子菜单
            ulHtml += '<li class="has-sub"> <a href="javascript:;"> <b class="caret pull-right"></b> <i class="fa fa-file-o"></i> <span>'+data[i].name+'</span> </a>';
            ulHtml += '<ul class="sub-menu">';
            for(var j=0;j<data[i].children.length;j++){
                ulHtml += ' <li temp="'+data[i].children[j].url+'"><a href="'+data[i].children[j].url+'">'+data[i].children[j].name+'</a></li>';
            }
            ulHtml += '</ul>';
            ulHtml += "</li>";
        }else{
            //没有子菜单
            ulHtml += '<li  temp="'+data[i].url+'">' +
                '<a href="'+data[i].url+'"><i class="fa fa-calendar"></i> <span>'+data[i].name+'</span></a>' +
                '</li>';
        }

    }
    return ulHtml;
}

