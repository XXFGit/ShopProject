var vue = new Vue({
    el:"#formModel",
    data:{
        type:{
            'id':'',
            'typeName':'',
            'description':'',
            'stopped':'',
            'sortOrder':'',
            'parentId':'',
            'parentName':''
        },
    },
});


$(function(){
    $("tbody").find("td").css("background","#fff");
    $(".gradeX").on("click",function(){
        $("tbody").find("td").css("background","#fff");
        $(this).find("td").css("background","#f0f3f5");
        var descr = $(this).attr("temp");
        if (descr!=""){
            if (descr.split("_")[0]!="0"){
                vue.$data.type.parentId = descr.split("_")[0];
                vue.$data.type.parentName = descr.split("_")[1];
            }
        }
    });
})

//添加
function add(){
    //清空表单
   /* $("#dataForm  input").each(function(){$(this).val('');});
    $("#dataForm  select").each(function(){$(this).val(''); });*/

    $('#formModel').modal('toggle');
}

//编辑
function edit(id){
    formData(id);
    $('#formModel').modal('toggle');
}

//保存
function save(){
    var data = vue.$data.type;
    $.ajax({
        type: 'POST',
        url: "/type/save.do",
        async: false,
        cache:false,
        data: JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        success:function(json){
            if (json=="success"){
                layer.alert("操作成功！",{icon:0,closeBtn:0},function(){
                    location.reload();
                });
            } else{
                layer.alert("操作失败！",{icon:0,closeBtn:0});
            }
        }
    });
}

function formData(id){
    $.ajax({
        type : 'POST',
        url: "/type/findById.do?id="+id,
        async: false,
        cache:false,
        contentType:"application/json; charset=utf-8",
        datatype:'json',
        success:function(json){
            if (json!=""){
                var json = jQuery.parseJSON(json);
                if(json['type']!=null){
                    vue.$data.type =  json['type'];
                }
            }
        }
    });
}

//删除
function del(id){
    $.ajax({
        type: 'POST',
        url: "/type/del.do?keyId="+id,
        async: false,
        cache:false,
        contentType:"application/json; charset=utf-8",
        success:function(json){
            if (json!=""){
                if (json!="error"){
                    location.reload();
                } else{
                    layer.alert("删除失败。",{icon:0,closeBtn:0})
                }
            }
        }
    });
}