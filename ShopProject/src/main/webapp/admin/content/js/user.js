/**
 *  构建vue渲染实例
 */
var vue = new Vue({
    el:"#formModel",
    data:{
        adminInfo:{
            'id':'',
            'useName':'',
            'email':'',
            'sex':'',
            'age':'',
            'cellphone':'',
            'email':''
        },
        roleList:[], //角色list
    },
    methods:{
        roleChange:function(e){
            var ss = e.target.value;
            //拿到选中的option的text值
            var roleName = $("select[name='"+e.target.getAttribute('name')+"'] option[value='"+ss+"']").text();
            vue.$data.adminInfo.roleName = roleName;
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
    $('.modal').modal('toggle');
}

//编辑
function edit(id){
    formData(id);
    $('.modal').modal('toggle');
}

/**
 * 保存
 */
function save(){
    var type = $("#type").val();
    var data = vue.$data.adminInfo;
    $.ajax({
        type: 'POST',
        url: "/admin/save.do",
        async: false,
        data: JSON.stringify(data),
        cache:false,
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

//删除
function del(id){
    $.ajax({
        type: 'POST',
        url: "/admin/del.do?keyId="+id,
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
        url: "/admin/findById.do?id="+id,
        async: false,
        cache:false,
        contentType:"application/json; charset=utf-8",
        datatype:'json',
        success:function(json){
            if (json!=""){
                var json = jQuery.parseJSON(json);
                vue.$data.roleList =  json["roleList"];
                if(json['admin']!=null){
                    vue.$data.adminInfo =  json['admin'];
                    //时间戳转日期赋值
                    $("input[name='birthday']").val(new Date(vue.$data.adminInfo.birthday).Format('yyyy-MM-dd'));
                }
            }
        }
    });
}