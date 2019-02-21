/**
 * 问题：
 *      由于异步原因，如果调用getOrgSelect()方法，会导致getOrg()还没执行完，返回的data为null
 * 解决：
 *      1、ajax请求改为同步 async: false
 */


/**
 * 获取组织架构
 */
function getOrg() {
    var data = null;
    $.ajax({
        url: '/org',
        type: 'GET',
        async: false,
        success: function (result) {
            data = result.list;
        }
    });
    return data;
}
/**
 * 获取组织架构下拉框
 * @param dom   指定某个ID选择器
 */
function getOrgSelect(dom) {
    var data = getOrg();

    $("select[name='"+dom+"']").html("");
    $("select[name='"+dom+"']").html("<option value=\"\">请选择</option>");
    if(data){
        for(var i in data){
            $("select[name='"+dom+"']").append("<option value=\""+data[i].id+"\">"+data[i].name+"</option>")
        }
    }
}

/**
 * 获取角色
 */
function getRole() {
    var data = null;
    $.ajax({
        url: '/role',
        type: 'GET',
        async: false,
        success: function (result) {
            data = result.list;
        }
    });
    return data;
}

/**
 * 获取组织架构下拉框
 * @param dom   指定某个name选择器
 */
function getRoleSelect(dom) {
    var data = getRole();

    $("select[name='"+dom+"']").html("");
    $("select[name='"+dom+"']").html("<option value=\"\">请选择</option>");
    if(data){
        for(var i in data){
            $("select[name='"+dom+"']").append("<option value=\""+data[i].id+"\">"+data[i].name+"</option>")
        }
    }
}