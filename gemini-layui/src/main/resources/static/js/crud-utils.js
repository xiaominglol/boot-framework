/**
 * 增删查改工具类
 * @author 小明不读书
 * @date 2018-10-31
 */
/**
 * 添加/修改 保存
 * @param param
 */
function saveOrUpdate(param) {
    var saveData = null;
    $.ajax({
        url: param.url
        , type: param.type
        , data: param.data
        , async: param.isTreeTable == true ? false : true
        , contentType: param.contentType == null ? 'application/x-www-form-urlencoded' : param.contentType
        , success: function (data) {
            if (param.isTreeTable) {
                saveData = data;
            } else {
                if (data.status == "success") {
                    refreshTable({
                        id: param.tableId
                        , where: ""
                        , done: function (res, curr, count) {
                            // 是否多选
                            if (param.isMultiSelect) {
                                checkboxMultiSelect($, param.dom);
                            } else {
                                checkboxRadio($, param.dom);
                            }
                        }
                    });
                    $('.reset').click();
                    layer.msg('保存成功', {icon: 1});
                    layer.closeAll('page');
                } else {
                    layer.msg(data.message, {icon: 5});
                }
            }
        },
        error: function (data) {
            layer.msg('保存失败', {icon: 5});
        }
    });
    return saveData;
}

/**
 * 删除
 * @param param
 */
function del(param) {
    layer.msg('确定删除？', {
        time: 0
        , btn: ['确定', '取消']
        , yes: function (index) {
            $.ajax({
                url: param.url
                , data: param.data
                , type: 'DELETE'
                , success: function (data) {
                    if (data.status == "success") {
                        refreshTable({
                            id: param.tableId
                            , where: ""
                        });
                        $('.reset').click();
                        layer.msg('删除成功', {icon: 1});
                        layer.close(index);
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                }
                , error: function (data) {
                    layer.msg("删除异常", {icon: 5});
                }
            });
        }
    });
}


/**
 * 是否确定更新
 * @param param
 */
function confirmUpdate(param) {
    layer.msg('确定' + param.msg + '？', {
        time: 0
        , btn: ['确定', '取消']
        , yes: function (index) {
            $.ajax({
                url: param.url
                , data: param.data
                , type: 'PUT'
                , success: function (data) {
                    if (data.status == "success") {
                        refreshTable({
                            id: param.tableId
                            , where: ""
                        });
                        layer.msg(param.msg + '成功', {icon: 1});
                        layer.close(index);
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                }
                , error: function (data) {
                    layer.msg(param.msg + "异常", {icon: 5});
                }
            });
        }
    });
}