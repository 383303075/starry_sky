function serializeArrayToObject(formId) {
    //序列化成数组。
    var test = $("#"+formId).serializeArray();
    //序列化后的结果
    var temp = new Object();
    //嵌套循环：
    //1,大循环是为了找到所有的name     username    password     age  ......
   //2小循环是为了拿到对应的值。
    //3,解析为json对象：
   // {username='',password='',age='',}
    for (var i = 0; i < test.length; i++) {
        var arr = test[i].name.split(".");//对名称进行分解,为处理属性是对象打下基础
        var objName = "temp";
        for (var j = 0; j < arr.length - 1; j++) {
            if (eval("temp." + arr[j]) == undefined || eval("temp." + arr[j]) == null)
                eval("temp." + arr[j] + "={}");//当前属性是对象，进行对象实例化
            objName = objName + "." + arr[i];
        }
        //实例化成JSON对象。
        //temp[test[i].name]=stringToJson(test[i].value);
        eval("(" + "temp." + test[i].name + "='" + stringToJson(test[i].value) + "'" + ")");
        //  {“name='张三',password='12345',}
    }
    return temp;
}
/***********************过滤json字符串开始*********************************/
function stringToJson(v) {
    if (v.indexOf("\"") != -1) {
        v = v.toString().replace(new RegExp('(["\"])', 'g'), "\\\"");
    }
    else if (v.indexOf("\\") != -1)
        v = v.toString().replace(new RegExp("([\\\\])", 'g'), "\\\\");
    return v;
}
//获取到地址栏上的参数 ，传入参数的键名
function getURLParamValue(pName) {
    var url = window.location.search;
    if (url.indexOf("?") != -1) {
        var start = url.indexOf("?");
        var str = url.substr(start + 1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            if ([strs[i].split("=")[0]] == pName)
                return decodeURI(strs[i].split("=")[1]);
        }
    }
    else
        return "";
}
//时间戳转日期
function createTime(v){
    var date = new Date(v);
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    m = m<10?'0'+m:m;
    var d = date.getDate();
    d = d<10?("0"+d):d;
    var h = date.getHours();
    h = h<10?("0"+h):h;
    var M = date.getMinutes();
    M = M<10?("0"+M):M;
    var str = y+"-"+m+"-"+d+" "+h+":"+M;
    return str;
}
