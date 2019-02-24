// $(function(){
//
//     //初始化菜单
//     initMenu();
//
//     //自动调整宽高
//     var heightObj = $(".nav, .workspace, #mainIframe");
//     heightObj.height($("html").height() - $(".header").height());
//     $(".workspace").width($(".main").width() - $(".nav").width());
//
//     //鼠标经过主菜单显示当前菜单的下级菜单（注意要用动态加载on，否则append菜单后无法触发函数）
//     $('.main-menu').on('mouseenter','>li',function(){
//         $(this).find(".sub-menu").show();
//         $(this).css("background-color","#222222");
//         $(this).css("border","1px solid #2E3C3A");
//     })
//
//     $('.main-menu').on('mouseleave','>li',function(){
//         $(this).css("background-color","#222222");
//         $(this).css("border","0px solid");
//         $(this).find(".sub-menu").hide();
//     });
//
//     $("#exitButton").click(function(){
//         var url ='${ctx }/LoginController/logout';
//         window.location.href = url;
//     });
//
//
// });
//
// //加载菜单
// function initMenu(){
//     $.ajax({
//         url:"${ctx}/MenuController/list",
//         type:"GET",
//         success:function(result){
//             console.log(result);
//             addMenu(result.result.menuList);
//         }
//     });
// }
//
// function addMenu(data) {
//     console.log(data);
//     $.each(data,function(index,item){
//         //console.log(item);
//         //alert("item.url="+item.url);
//
// //			debugger
//         //加载一级菜单
//         if (item.type == "1") {
//             var main_li = $("<li></li>").attr("id",item.id);
//             var main_div = $("<div></div>");
//             var main_img = $("<img></img>").addClass("icon").attr("src","${ctx}" + item.icon);
//             var main_span = $("<span></span>").addClass("main-name").append(item.name);
//             var main_arrow_right = $("<img></img>").addClass("arrow-right").attr("src","${ctx}/static/img/icon/arrow-right.png");
//             var sub_ul = $("<ul></ul>").addClass("sub-menu");
//             main_div.append(main_img).append(main_span).append(main_arrow_right);
//             main_li.append(main_div).append(sub_ul);
//             main_li.appendTo(".main-menu");
//         }
//
//         //加载二级菜单
//         if (item.type == "2") {
//             var sub_li = $("<li></li>").addClass("sub-name").attr("id",item.id);
//             var sub_div = $("<div></div>").append(item.name);
//             var third_ul = $("<ul></ul>").addClass("third-menu");
//             sub_li.append(sub_div).append(third_ul);
//             sub_li.appendTo("#" + item.pid + " .sub-menu");
//         }
//
//         //加载三级菜单 <a href="#" onclick="openMenu('${ctx }/list.jsp');">组织架构</a>
//         if (item.type == "3") {
//             var third_li = $("<li></li>").attr("id",item.id);
//             var third_div = $("<div></div>");
//             var third_a = $("<a></a>").attr("href","#").attr("onclick","openMenu('"+"${ctx}" + item.url+"')").append(item.name);
//             third_div.append(third_a);
//             third_li.append(third_div);
//             third_li.appendTo("#" + item.pid + " .third-menu");
//         }
//
//     });
//
// }
//
// function openMenu(url) {
//     $("iframe[name='mainIframe']").attr("src",url);
// }