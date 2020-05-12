/**
 *  构建vue渲染实例
 */
var vue = new Vue({
    el:"#formModel",
    data:{
        role:{
            'id':'',
            'name':'',
            'roleDescription':''
        }
    }
});

//添加
function add(){
    //加载角色list
    formData("");
    //清空表单
    $("#dataForm  input").each(function(){$(this).val('');});
    $("#dataForm  select").each(function(){$(this).val(''); });

    $('#formModel').modal('toggle');
}

//编辑
function edit(id){
    formData(id);
    $('#formModel').modal('toggle');
}

/**
 * 保存
 */
function save(){
    var type = $("#type").val();
    var data = vue.$data.role;
    $.ajax({
        type: 'POST',
        url: "/role/save.do",
        async: false,
        data: JSON.stringify(data),
        cache:false,
        contentType:"application/json; charset=utf-8",
        success:function(json){
            if (json!=""){
                var json = jsonParse(json);
                if (json.code==200){
                    layer.alert("操作成功！",{icon:0,closeBtn:0},function(){
                        location.reload();
                    });
                } else if (json.code==501) {
                    layer.alert("权限不足！",{icon:0,closeBtn:0},function(){
                        location.reload();
                    });
                }else{
                    layer.alert("系统异常！",{icon:0,closeBtn:0},function(){
                        location.reload();
                    });
                }
            }
        }
    });
}

//删除
function del(id){
    $.ajax({
        type: 'POST',
        url: "/role/del.do?keyId="+id,
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

//添加和编辑是加载用户信息和角色列表（添加不加载用户信息）
function formData(id){
    $.ajax({
        type : 'POST',
        url: "/role/findById.do?id="+id,
        async: false,
        cache:false,
        contentType:"application/json; charset=utf-8",
        datatype:'json',
        success:function(json){
            if (json!=""){
                var json = jQuery.parseJSON(json);
                if(json['role']!=null){
                    vue.$data.role =  json['role'];
                }
            }
        }
    });
}

//分配权限
function premission(id,rolename) {
    var zTreeOjb = $.fn.zTree.getZTreeObj("treeDemo");
    $.ajax({
        type : 'POST',
        url: "/role/getResourceByRoleId.do?roleId="+id,
        async: false,
        cache:false,
        contentType:"application/json; charset=utf-8",
        datatype:'json',
        success:function(json){
            if (json!=""){
                var json = jQuery.parseJSON(json);
                for(var i=0 ; i<json.length;i++){
                    var node=zTreeOjb.getNodeByParam('id',json[i].resourcesId);
                    zTreeOjb.checkNode(node, true, true);
                }
            }
        }
    });

    $("#roleId").val(id);
    /* var id=12;//设置指定的ID
     var node=zTreeOjb.getNodeByParam('id',id);
     zTreeOjb.checkNode(node, true, true);*/
    $(".roleName").text(rolename);
    $("#premissModel").modal('toggle');
}

//保存角色权限
function savePremission(){
    var id = $("#roleId").val();
    //获取选中节点
    var data = getNodeIds();
    if(data==""){
        layer.alert("请至少选择一个。"),{icon:0};
        return false;
    }
    $.ajax({
        type : 'POST',
        url: "/role/savePremission.do?roleId="+id+"&pres="+data,
        async: false,
        cache:false,
        contentType:"application/json; charset=utf-8",
        datatype:'json',
        success:function(json){
            if (json!=""){
                if (json!="error"){
                    layer.alert("保存成功。",{icon:0},function(){
                        location.reload();
                    })
                } else{
                    layer.alert("删除失败。",{icon:0,closeBtn:0})
                }
            }
        }
    });
}

//获取勾选的权限资源id
function getNodeIds(){
    var nodeIds="";
     var zTreeOjb = $.fn.zTree.getZTreeObj("treeDemo");
     var nodes = zTreeOjb.getCheckedNodes()
     if(nodes!=null && nodes.length>0){
         for (var i=0;i<nodes.length;i++){
             nodeIds += nodes[i].id+",";
         }
     }
     return nodeIds;
}
