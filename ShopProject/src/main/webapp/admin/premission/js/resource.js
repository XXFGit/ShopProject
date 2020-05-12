/**
 * Created by 熊显付 on 2020/4/13.
 */

/**
 *  构建vue渲染实例
 */
var vue = new Vue({
    el:"#formModel",
    data:{
        'pname':'pname',
        'resources':{
            'id':'',
            'name':'',
            'url':'',
            'permission':'',
            'type':'',
            'pid':''
        }
    }
});

var $table = $('#table');
//加载所有菜单资源
function loadData(){
    $.ajax({
        type: 'POST',
        url: "/menu/list.do",
        async: false,
        cache:false,
        contentType:"application/json; charset=utf-8",
        success:function(json){
            if (json!=""){
                data = jQuery.parseJSON(json);
            }
        }
    });
}

//构建树table开始
$(function() {
    //加载数据
    loadData();
    $table.bootstrapTable({
        data:data,
        idField: 'id',
        dataType:'jsonp',
        columns: [
            { field: 'check',  checkbox: true, formatter: function (value, row, index) {
                if (row.check == true) {
                    // console.log(row.serverName);
                    //设置选中
                    return {  checked: true };
                }
            }
            },
            { field: 'name',  title: '资源名称', align: 'center', },
            { field: 'type', title: '资源类型', align: 'center', events : operateEvents, formatter: 'typeFormatter' },
            { field: 'url', title: '路径' , align: 'center', },
            { field: 'permission', title: '权限值', align: 'center',  },
            { field: 'operate', title: '操作', align: 'center', events : operateEvents, formatter: 'operateFormatter' },
        ],
        treeShowField: 'name', //在哪一列展开树形
        pidField: 'pid', //指定父id列
        onResetView: function(data) {
            $table.treegrid({
                //initialState: 'collapsed',// 所有节点都折叠
                initialState: 'expanded',// 所有节点都展开，默认展开
                treeColumn: 1,
                // expanderExpandedClass: 'glyphicon glyphicon-minus',  //图标样式
                // expanderCollapsedClass: 'glyphicon glyphicon-plus',
                onChange: function() {
                    $table.bootstrapTable('resetWidth');
                }
            });
            //只展开树形的第一级节点
            $table.treegrid('getRootNodes').treegrid('expand');
        },
        onCheck:function(row){
            var datas = $table.bootstrapTable('getData');
            selectChilds(data,row,"id","pid",true); // 勾选子类
            selectParentChecked(data,row,"id","pid") // 勾选父类
            $table.bootstrapTable('load', data); // 刷新数据
        },
        onUncheck:function(row){
            var datas = $table.bootstrapTable('getData');
            selectChilds(data,row,"id","pid",false);
            $table.bootstrapTable('load', datas);
        },
    });
});

// 格式化按钮
function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="btn btn-small btn-info RoleOfadd" style="margin-right:15px;"><i class="fa fa-plus" ></i>&nbsp;新增</button>',
        '<button type="button" class="btn btn-small btn-primary RoleOfedit" style="margin-right:15px;"><i class="fa fa-pencil-square-o" ></i>&nbsp;修改</button>',
        '<button type="button" class="btn btn-small btn-danger RoleOfdelete" style="margin-right:15px;"><i class="fa fa-trash-o" ></i>&nbsp;删除</button>'
    ].join('');

}
// 格式化类型
function typeFormatter(value, row, index) {
    if (value === '1') {  return '菜单';  }
    if (value === '2') {  return '按钮'; }
    return '';
}
// 格式化状态
function statusFormatter(value, row, index) {
    if (value === 1) {
        return '<span class="label label-success">正常</span>';
    } else {
        return '<span class="label label-default">锁定</span>';
    }
}

//初始化操作按钮的方法
window.operateEvents = {
    'click .RoleOfadd': function (e, value, row, index) {
        add(row.id);
    },
    'click .RoleOfdelete': function (e, value, row, index) {
        del(row.id);
    },
    'click .RoleOfedit': function (e, value, row, index) {
        update(row.id,row.name,row.pid);
    }
};


/**
 * 选中父项时，同时选中子项
 * @param datas 所有的数据
 * @param row 当前数据
 * @param id id 字段名
 * @param pid 父id字段名
 */
    function selectChilds(datas,row,id,pid,checked) {
        for(var i in datas){
        if(datas[i][pid] == row[id]){
            datas[i].check=checked;
            selectChilds(datas,datas[i],id,pid,checked);
        };
    }
}

function selectParentChecked(datas,row,id,pid){
    for(var i in datas){
        if(datas[i][id] == row[pid]){
            datas[i].check=true;
            selectParentChecked(datas,datas[i],id,pid);
        };
    }
}

function add(pid) {
    $("#dataForm  input").each(function(){$(this).val('');});
    $("#dataForm  select").each(function(){$(this).val(''); });
    if(pid=="" || pid==undefined){
        vue.$data.pname='';
    }else{
        vue.$data.resources.pid = pid;
        findById(pid,'add');
    }
    $('.modal').modal('toggle');
}
function del(id) {
    $.ajax({
        type: 'POST',
        url: "/menu/del.do?id="+id,
        async: false,
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
function update(id,name,pid) {
    $("#dataForm  input").each(function(){$(this).val('');});
    $("#dataForm  select").each(function(){$(this).val(''); });
    if(id!="" && id!=undefined && name!="" && name!=undefined){
        vue.$data.resources.id = id;
    }
    vue.$data.resources.pid = pid;
    findById(pid,'add');
    findById(id,'edit');
    if(pid==0){
       vue.$data.pname = '';
    }
    $('.modal').modal('toggle');
}

function save(){
    var data = vue.$data.resources;
    $.ajax({
        type: 'POST',
        url: "/menu/save.do",
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


function findById(id,type){
    $.ajax({
        type : 'POST',
        url: "/menu/findById.do?id="+id,
        async: false,
        cache:false,
        contentType:"application/json; charset=utf-8",
        datatype:'json',
        success:function(json){
            if (json!=""){
                var json = jQuery.parseJSON(json);
                if(type=="edit"){
                    vue.$data.resources = json;
                }else{
                    vue.$data.pname = json.name;
                }
            }
        }
    });
}