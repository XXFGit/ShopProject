
//将form表单数据封装成json对象name：value
$.fn.serializeFormToJson = function(){
    var arr = $(this).serializeArray();
    var param = {};
    $.each(arr,function(i,obj){
        param[obj.name] = obj.value;
    })
    return param;
}

//判断是否json
function jsonParse(json){
    var resultJson = "";
    try {
        resultJson = jQuery.parseJSON(json);
    } catch (e) {
        resultJson = json;
    }
    return resultJson;
}


//jquery 日期选择中文汉化
jQuery(function($){
    $.datepicker.regional['zh-CN'] = {
        closeText: '关闭',
        prevText: '&#x3c;上月',
        nextText: '下月&#x3e;',
        currentText: '今天',
        monthNames: ['一月','二月','三月','四月','五月','六月',
            '七月','八月','九月','十月','十一月','十二月'],
        monthNamesShort: ['一','二','三','四','五','六',
            '七','八','九','十','十一','十二'],
        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
        dayNamesMin: ['日','一','二','三','四','五','六'],
        weekHeader: '周',
        dateFormat: 'yy-mm-dd',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: true,
        yearSuffix: '年'};
});

//时间戳转日期 参数：fmt:日期格式
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + ""));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

time();
$('.liMarqueepmd').liMarquee();


/*获取当前时间，星期几*/
function time() {
    var today = new Date();
    var hou;
    var time;
    var day;
    if (today.getHours() < 12 && today.getHours() >= 0) {
        hou = today.getHours();
        time = "AM";
    } else if (today.getHours() <= 24 && today.getHours() > 12) {
        hou = today.getHours() - 12;
        time = "PM";
    }
    var d = today.getDay();
    if (d == 0) {day = "日";}
    else if (d == 1) {day = "一";
    } else if (d == 2) {day = "二";
    } else if (d == 3) {day = "三";
    } else if (d == 4) {day = "四";
    } else if (d == 5) {day = "五";
    } else if (d == 6) {day = "六";}
    $(".liMarqueepmd").text(today.getFullYear() + "年" + (today.getMonth() + 1) + "月" + today.getDate() + "日 " + time + " 星期" + day+" 数据无价请谨慎操作。");
}