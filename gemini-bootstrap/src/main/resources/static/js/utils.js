// 打开模态框
function openModal(dom) {
    $("#" + dom).modal({
        backdrop: 'static',
        keyboard: true
    });
}

// 清空表单数据
function clearFrom() {
    $(".modal form input").each(function () {
        // 缺点，不能初始化原来input的value值，待解决
        $(this).val('');
    });
    $(".modal form select").each(function () {
        // 默认选中第一个
        $(this).prop('selectedIndex', 0);
    });
}