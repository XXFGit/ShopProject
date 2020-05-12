/**
 * 構建一個sessionStorage,用來存儲臨時信息
 */

//定义全局变量函数
var paStorage = function() {
	var ls = sessionStorage;
	return ls;
};

// 定义全局变量u
var p = {};

// 设置缓存
p.setStorage = function(key, value) {
	var v = value;
	if (typeof v == 'object') {
		v = JSON.stringify(v);
		v = 'obj-' + v;
	} else {
		v = 'str-' + v;
	}
	var ls = paStorage();
	if (ls) {
		ls.setItem(key, v);
	}
};
// 获取缓存
p.getStorage = function(key) {
	var ls = paStorage();
	if (ls) {
		var v = ls.getItem(key);
		if (!v) {
			return;
		}
		if (v.indexOf('obj-') === 0) {
			v = v.slice(4);
			return JSON.parse(v);
		} else if (v.indexOf('str-') === 0) {
			return v.slice(4);
		}
	}
};
//将指定的键名(key)从 sessionStorage 对象中移除
p.removeStorage = function(key) {
	var ls = paStorage();
	if (ls) {
		ls.removeItem(key);
	}
};
//清除 sessionStorage 对象所有的项
p.clearStorage = function(key) {
	var ls = paStorage();
	if (ls) {
		ls.clear();
	}
};

/*$(function(){
		var userEntity = {name: 'tom',age: 22};

		// 存储值：将对象转换为Json字符串

		p.setStorage('user', JSON.stringify(userEntity));

		// 取值时：把获取到的Json字符串转换回对象

		var userJsonStr = p.getStorage('user');

		userEntity = JSON.parse(userJsonStr);

		console.log(userEntity.name); // => tom
})*/
