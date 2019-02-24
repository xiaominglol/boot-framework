/**
 * 增删查改工具类
 * @author 小明不读书
 * @date 2018-10-31
 */
// 初始化表格
function initTable(param) {
    layui.use(['table'], function () {
        var table = layui.table;
        table.render({
            elem: '#' + param.dom
            , height: 'full-220'
            , url: param.url
            , data: param.data
            , page: param.page == null ? true : param.page      //默认开启分页
            , cols: [param.cols]
            , where: param.where
            , done: function (res, curr, count) {
                // 是否多选
                if (param.isMultiSelect) {
                    checkboxMultiSelect($, param.dom);
                } else {
                    checkboxRadio($, param.dom);
                }
            }
            , request: {
                pageName: 'pageNum'             //页码的参数名称，默认：page
                , limitName: 'pageSize'         //每页数据量的参数名，默认：limit
            }
            , parseData: function (res) {       //res 即为原始返回的数据
                return {
                    "code": 0,                  //解析接口状态
                    "msg": res.message,         //解析提示文本
                    "count": res.data.total,    //解析数据长度
                    "data": res.data.records       //解析数据列表
                };
            }
        });
    });
}

// 刷新表格
function refreshTable(param) {
    layui.use(['table'], function () {
        var table = layui.table;
        table.reload(param.id, {
            where: param.where
            , data: param.data
            , page: param.page == null ? {curr: 1} : ""     //默认从第一页开始
            , done: param.done == null ? "" : param.done
        });
    });
}

// 打开添加/修改 弹出框
function openAddOrUpdate(param) {
    layui.use(['layer'], function () {
        var layer = layui.layer;
        layer.open({
            type: 1
            , title: param.title
            , content: $('#' + param.id)
            //,skin: 'layui-layer-molv'
            , maxmin: true
            , area: param.area == null ? ['900px', '550px'] : param.area
            , btn: ['保存', '重置']
            , yes: function (index, layero) {
                layero.find('.save').click();
            }
            , btn2: function (index, layero) {
                layero.find('.reset').click();
                return false;
            }
            , success: function (layero) {
                //终于完美解决遮罩层这个问题了
                //https://blog.csdn.net/q646926099/article/details/78797091?%3E
                $(".layui-layer-shade").appendTo(layero.parent());
            }
            , cancel: function (index, layero) {
                /*layer.confirm('确定要关闭么？', {
                    btn: ['确定', '取消']
                }, function (index, layero) {*/
                layer.closeAll();
                $('.reset').click();
                /*}, function (index) {
                });*/

                refreshTable({
                    id: param.tableId
                    , data: []
                    , page: false
                });
                return false;
            }
        })

    });
}

// 初始化树
function initTree(param) {
    $('#' + param.dom).html("");
    var nodes = buildTree(param.data, param.id, param.pid, param.name);
    for (var i = 0; i < nodes.length; i++) {
        //主目录是否默认打开
        nodes[i].spread = true;
        if (nodes[i].children && nodes[i].children.length > 0) {
            for (var j = 0; j < nodes[i].children.length; j++) {
                //子目录是否默认打开
                nodes[i].children[j].spread = false;
            }
        }
    }
    layui.use(['tree'], function () {
        var tree = layui.tree;
        tree({
            elem: '#' + param.dom //传入元素选择器
            , nodes: nodes
            , click: param.click
        });
    });
}

//下拉树
function downpanel() {
    $(".downpanel").on("click", ".layui-select-title", function (e) {
        $(".layui-form-select").not($(this).parents(".layui-form-select")).removeClass("layui-form-selected");
        $(this).parents(".downpanel").toggleClass("layui-form-selected");
        layui.stope(e);
    }).on("click", "dl i", function (e) {
        layui.stope(e);
    });
    $(document).on("click", function (e) {
        $(".downpanel").removeClass("layui-form-selected");
    });
}


function buildTree(nodes, id, pid, name) {
    var tree = new Array();
    if (null != nodes) {
        for (var i = 0; i < nodes.length; i++) {
            var nodeItem = nodes[i];
            var json = nodeItem;
            if (nodeItem[pid] == 0) {
                var child = buildChildTree(nodeItem[id], nodes, id, pid, name);
                // 递归增加子节点
                if (child.length > 0) {
                    json.children = child;
                }
                json.id = nodeItem[id];
                json.name = nodeItem[name];
                tree.push(json);
            }
        }
    }
    return tree;
}

function buildChildTree(key, nodes, id, pid, name) {
    var child = new Array();

    for (var i = 0; i < nodes.length; i++) {
        var nodeItem = nodes[i];
        var json = nodeItem;
        if (key == nodeItem[pid]) {
            var cchild = buildChildTree(nodeItem[id], nodes, id, pid, name);
            if (cchild.length > 0) {
                json.children = cchild;
            }
            json.id = nodeItem[id];
            json.name = nodeItem[name];
            child.push(json);
        }
    }
    return child;
}


// 添加一行
function addRowButton(param) {
    layui.use(['table'], function () {
        var table = layui.table;
        var tableData = table.cache[param.table];
        if (tableData && tableData.length > 0) {
            for (var i = 0; i < tableData.length; i++) {
                if (!tableData[i][param.id]) {
                    layer.msg("已添加！", {
                        icon: 5
                    });
                    return false;
                }
            }
        }
        tableData.unshift(param.data);
        refreshTable({
            id: param.table,
            data: tableData,
            page: false,
            done: param.done == null ? "" : param.done
        });
    });
}


// 批量删除
function batchDelButton(param) {
    layui.use(['table'], function () {
        var table = layui.table;
        var tableData = table.cache[param.table];
        var selectedData = table.checkStatus([param.table]);
        if (!tableData || tableData.length < 1) {
            layer.msg("表格为空！", {
                icon: 5
            });
            return false;
        }
        if (!selectedData.data || selectedData.data.length < 1) {
            layer.msg("请至少选择一条数据！", {
                icon: 5
            });
            return false;
        }
        for (var i = 0; i < selectedData.data.length; i++) {
            for (var j = 0; j < tableData.length; j++) {
                if (selectedData.data[i][param.id] == tableData[j][param.id]) {
                    tableData.splice(j, 1);
                }
            }
        }
        refreshTable({
            id: param.table,
            data: tableData,
            page: false,
            done: param.done == null ? "" : param.done
        });
    });
}
