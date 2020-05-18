var vue = new Vue({
    el:"#formModel",
    data:{
        item:{
            'id':'',
            'itemTitle':'',
            'summary':'',
            'itemTypeId':'',
            'itemTypeName':'',
            'price':'',
            'stock':'',
            'isRemm':0,
            'viewCount':0,
            'buyCount':0,
            'content':''
        },
    },
    methods:{
        typeChange:function(event){
            var typeName = $("#itemTypeId option:selected").text();
            $("input[name='itemTypeName']").val(typeName);
            this.item.itemTypeName = typeName;
        }
    }
});

$(function(){
    if ($("#keyId").val()!=""){
        formData($("#keyId").val());
    }
})


function formData(id){
    $.ajax({
        type : 'POST',
        url: "/item/findById.do?id="+id,
        async: true,
        cache:false,
        contentType:"application/json; charset=utf-8",
        datatype:'json',
        success:function(json){
            if (json!=""){
                var json = jQuery.parseJSON(json);
                if(json['item']!=null){
                    vue.$data.item =  json['item'];
                    $('.summernote').summernote('code', vue.$data.item.content);
                }
            }
        }
    });
}

//新增修改保存
function save() {
    var data = vue.$data.item;
    $.ajax({
        type : 'POST',
        url: "/item/save.do",
        data:JSON.stringify(data),
        async: true,
        cache:false,
        contentType:"application/json; charset=utf-8",
        datatype:'json',
        success:function(json){
            if (json!="error"){
                layer.alert("保存成功。",{icon:0,closeBtn:0},function(){
                    location.href = "/item/index.do"
                })
            }else{
                layer.alert("保存失败。",{icon:0,closeBtn:0})
            }
        }
    });
}


//删除
function del(id){
    $.ajax({
        type: 'POST',
        url: "/item/del.do?keyId="+id,
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