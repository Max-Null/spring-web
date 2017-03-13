<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>奇葩虫</title>

    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap-switch/4.0.0-alpha.1/css/bootstrap-switch.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap-slider/9.7.2/css/bootstrap-slider.css" rel="stylesheet">
    <!-- 引入下面两个库让 IE8 支持 HTML5 元素 -->
    <!-- 警告: Respond.js 通过 file:// 浏览的时候不能正常工作！-->
    <!--[if lt IE 9]>
    <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        .num {
            width: 40px
        }
        .platform {
            width: 100px
        }
        th,td{text-align:center;vertical-align:middle}
    </style>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    LOGO <small>奇葩虫</small>
                </h1>
            </div>
        </div>
        <div class="col-md-4 column">
            <form role="form" class="form-inline">
                <div class="form-group">
                    <label for="key"></label><input type="text" class="form-control" id="key" data-toggle="popover"/>
                    <button id ="submit" type="button" class="btn btn-default">搜索</button>
                    <button id ="reset"  type="button" class="btn btn-default">重置</button>
                </div>
                <div class="form-group">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="platform">
                                <div class="switch bootstrap-switch-large">
                                    <input id = "allSwitch" type="checkbox" data-on-text="是" data-off-text="否" data-off-color="danger" data-label-text="全选" data-label-width="25">
                                </div>
                            </th><th style="width: 200px">范围——数量</th>
                        </tr>
                        </thead>
                        <tbody id="range" data-toggle="popover">
                        <tr>
                            <td>
                                <div class="switch bootstrap-switch-large">
                                    <input id = "baiduSwitch" for = "baidu" name="platform" type="checkbox" data-on-text="开" data-off-text="关" data-off-color="danger" data-label-text="百度" data-label-width="25">
                                </div>
                            </td>
                            <td>
                                <input class="my-slider" id ="baidu" type="number" data-slider-min="10" data-slider-max="100" data-slider-step="10" data-slider-value="20"/>
                                <br />
                                <span id ="baiduCurrentSliderValLabel">条数：<span id ="baiduSliderVal">20</span></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="switch bootstrap-switch-large">
                                    <input id = "weixinSwitch" for = "weixin" name="platform" type="checkbox" data-on-text="开" data-off-text="关" data-off-color="danger" data-label-text="微信" data-label-width="25">
                                </div>
                            </td>
                            <td>
                                <input class="my-slider" id ="weixin" type="number" data-slider-min="10" data-slider-max="100" data-slider-step="10" data-slider-value="20"/>
                                <br />
                                <span id ="weixinCurrentSliderValLabel">条数：<span id ="weixinSliderVal">20</span></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="switch bootstrap-switch-large">
                                    <input id = "weiboSwitch" for = "weibo" name="platform" type="checkbox" data-on-text="开" data-off-text="关" data-off-color="danger" data-label-text="微博" data-label-width="25">
                                </div>
                            </td>
                            <td>
                                <input class="my-slider" id ="weibo" type="number" data-slider-min="10" data-slider-max="100" data-slider-step="10" data-slider-value="20"/>
                                <br />
                                <span id ="weiboCurrentSliderValLabel">条数：<span id ="weiboSliderVal">20</span></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="switch bootstrap-switch-large">
                                    <input id = "zhidaoSwitch" for = "zhidao" name="platform" type="checkbox" data-on-text="开" data-off-text="关" data-off-color="danger" data-label-text="知道" data-label-width="25">
                                </div>
                            </td>
                            <td>
                                <input class="my-slider" id ="zhidao" type="number" data-slider-min="10" data-slider-max="100" data-slider-step="10" data-slider-value="20"/>
                                <br />
                                <span id ="zhidaoCurrentSliderValLabel">条数：<span id ="zhidaoSliderVal">20</span></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="switch bootstrap-switch-large">
                                    <input id = "tiebaSwitch" for = "tieba" name="platform" type="checkbox" data-on-text="开" data-off-text="关" data-off-color="danger" data-label-text="贴吧" data-label-width="25">
                                </div>
                            </td>
                            <td>
                                <input class="my-slider" id ="tieba" type="number" data-slider-min="10" data-slider-max="100" data-slider-step="10" data-slider-value="20"/>
                                <br />
                                <span id ="tiebaCurrentSliderValLabel">条数：<span id ="tiebaSliderVal">20</span></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="switch bootstrap-switch-large">
                                    <input id = "qitaSwitch" for = "qita" name="platform" type="checkbox" data-on-text="开" data-off-text="关" data-off-color="danger" data-label-text="其他" data-label-width="25">
                                </div>
                            </td>
                            <td>
                                <input class="my-slider" id ="qita" type="number" data-slider-min="10" data-slider-max="100" data-slider-step="10" data-slider-value="20"/>
                                <br />
                                <span id ="qitaCurrentSliderValLabel">条数：<span id ="qitaSliderVal">20</span></span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="btn-group-vertical" data-toggle="buttons" role="group" aria-label="Vertical button group">
                    <br />
                </div>
            </form>
        </div>
        <div class="col-md-8 column">
            <div class="progress progress-striped active">
                <div class="progress-bar progress-bar-success" role="progressbar"
                     aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"
                     style="width: 80%;">
                    <span class="sr-only">40% 完成</span>
                </div>
            </div>
            <div class="tabbable" id="tabs-223781">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#panel-baidu" data-toggle="tab">百度</a>
                    </li>
                    <li>
                        <a href="#panel-weixin" data-toggle="tab">微信</a>
                    </li>
                    <li>
                        <a href="#panel-weibo" data-toggle="tab">微博</a>
                    </li>
                    <li>
                        <a href="#panel-zhidao" data-toggle="tab">知道</a>
                    </li>
                    <li>
                        <a href="#panel-tieba" data-toggle="tab">贴吧</a>
                    </li>
                    <li>
                        <a href="#panel-qita" data-toggle="tab">其他</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="panel-baidu">
                        <div class="btn-group">
                            <button class="btn btn-default">操作</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">操作</a>
                                </li>
                                <li class="disabled">
                                    <a href="#">另一操作</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">其它</a>
                                </li>
                            </ul>
                        </div>
                        <table class="table table-hover table-condensed table-bordered">
                            <thead>
                                <tr>
                                    <th class="num"> 编号</th> <th class="platform"> 平台</th> <th> 标题</th> <th> 网址</th> <th> 时间</th> <th> 状态</th>
                                </tr>
                            </thead>
                            <tbody id="tbody-baidu">
                                <%--<tr>
                                    <td> 1</td> <td> 土豆</td><td> 测试标题</td> <td> http://www.baidu.com</td> <td> 01/04/2012</td> <td> Default</td>
                                </tr>
                                <tr class="success">
                                    <td> 1</td> <td> 土豆</td><td> 测试标题</td> <td> http://www.baidu.com</td> <td> 01/04/2012</td> <td> Approved</td>
                                </tr>
                                <tr class="error">
                                    <td> 2</td> <td> 优酷</td><td> 测试标题</td> <td> http://www.baidu.com</td> <td> 02/04/2012</td> <td> Declined</td>
                                </tr>
                                <tr class="warning">
                                    <td> 3</td> <td> 优酷</td><td> 测试标题</td> <td> http://www.baidu.com</td> <td> 03/04/2012</td> <td> Pending</td>
                                </tr>
                                <tr class="info">
                                    <td> 4</td> <td> 爱奇艺</td><td> 测试标题</td> <td> http://www.baidu.com</td> <td> 04/04/2012</td> <td> Call in to confirm</td>
                                </tr>--%>
                            </tbody>
                        </table>
                        <div id="baiduPage"></div>
                        <%--<ul class="pagination">
                            <li>
                                <a href="#">上一页</a>
                            </li>
                            <li><a href="#">1</a></li> <li><a href="#">2</a></li> <li><a href="#">3</a></li> <li><a href="#">4</a> </li> <li> <a href="#">5</a> </li>
                            <li>
                                <a href="#">下一页</a>
                            </li>
                        </ul>--%>
                    </div>
                    <div class="tab-pane" id="panel-weixin">
                        <div class="btn-group">
                            <button class="btn btn-default">操作</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">操作</a>
                                </li>
                                <li class="disabled">
                                    <a href="#">另一操作</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">其它</a>
                                </li>
                            </ul>
                        </div>
                        <table class="table table-hover table-condensed table-bordered">
                            <thead>
                            <tr>
                                <th class="num"> 编号</th> <th class="platform"> 平台</th> <th> 标题</th> <th> 网址</th> <th> 状态</th>
                            </tr>
                            </thead>
                            <tbody id="tbody-weixin">
                            </tbody>
                        </table>
                        <ul class="pagination">
                            <li>
                                <a href="#">上一页</a>
                            </li>
                            <li><a href="#">1</a></li> <li><a href="#">2</a></li> <li><a href="#">3</a></li> <li><a href="#">4</a> </li> <li> <a href="#">5</a> </li>
                            <li>
                                <a href="#">下一页</a>
                            </li>
                        </ul>
                    </div>
                    <div class="tab-pane" id="panel-weibo">
                        <div class="btn-group">
                            <button class="btn btn-default">操作</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">操作</a>
                                </li>
                                <li class="disabled">
                                    <a href="#">另一操作</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">其它</a>
                                </li>
                            </ul>
                        </div>
                        <table class="table table-hover table-condensed table-bordered">
                            <thead>
                            <tr>
                                <th class="num"> 编号</th> <th class="platform"> 平台</th> <th> 标题</th> <th> 网址</th> <th> 状态</th>
                            </tr>
                            </thead>
                            <tbody id="tbody-weibo">
                            </tbody>
                        </table>
                        <ul class="pagination">
                            <li>
                                <a href="#">上一页</a>
                            </li>
                            <li><a href="#">1</a></li> <li><a href="#">2</a></li> <li><a href="#">3</a></li> <li><a href="#">4</a> </li> <li> <a href="#">5</a> </li>
                            <li>
                                <a href="#">下一页</a>
                            </li>
                        </ul>
                    </div>
                    <div class="tab-pane" id="panel-zhidao">
                        <div class="btn-group">
                            <button class="btn btn-default">操作</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">操作</a>
                                </li>
                                <li class="disabled">
                                    <a href="#">另一操作</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">其它</a>
                                </li>
                            </ul>
                        </div>
                        <table class="table table-hover table-condensed table-bordered">
                            <thead>
                            <tr>
                                <th class="num"> 编号</th> <th class="platform"> 平台</th> <th> 标题</th> <th> 网址</th> <th> 状态</th>
                            </tr>
                            </thead>
                            <tbody id="tbody-zhidao">
                            </tbody>
                        </table>
                        <ul class="pagination">
                            <li>
                                <a href="#">上一页</a>
                            </li>
                            <li><a href="#">1</a></li> <li><a href="#">2</a></li> <li><a href="#">3</a></li> <li><a href="#">4</a> </li> <li> <a href="#">5</a> </li>
                            <li>
                                <a href="#">下一页</a>
                            </li>
                        </ul>
                    </div>
                    <div class="tab-pane" id="panel-tieba">
                        <div class="btn-group">
                            <button class="btn btn-default">操作</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">操作</a>
                                </li>
                                <li class="disabled">
                                    <a href="#">另一操作</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">其它</a>
                                </li>
                            </ul>
                        </div>
                        <table class="table table-hover table-condensed table-bordered">
                            <thead>
                            <tr>
                                <th class="num"> 编号</th> <th class="platform"> 平台</th> <th> 标题</th> <th> 网址</th> <th> 状态</th>
                            </tr>
                            </thead>
                            <tbody id="tbody-tieba">
                            </tbody>
                        </table>
                        <ul class="pagination">
                            <li>
                                <a href="#">上一页</a>
                            </li>
                            <li><a href="#">1</a></li> <li><a href="#">2</a></li> <li><a href="#">3</a></li> <li><a href="#">4</a> </li> <li> <a href="#">5</a> </li>
                            <li>
                                <a href="#">下一页</a>
                            </li>
                        </ul>
                    </div>
                    <div class="tab-pane" id="panel-qita">
                        <div class="btn-group">
                            <button class="btn btn-default">操作</button> <button data-toggle="dropdown" class="btn btn-default dropdown-toggle"><span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">操作</a>
                                </li>
                                <li class="disabled">
                                    <a href="#">另一操作</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">其它</a>
                                </li>
                            </ul>
                        </div>
                        <table class="table table-hover table-condensed table-bordered">
                            <thead>
                            <tr>
                                <th class="num"> 编号</th> <th class="platform"> 平台</th> <th> 标题</th> <th> 网址</th> <th> 状态</th>
                            </tr>
                            </thead>
                            <tbody id="tbody-qita">
                            </tbody>
                        </table>
                        <ul class="pagination">
                            <li>
                                <a href="#">上一页</a>
                            </li>
                            <li><a href="#">1</a></li> <li><a href="#">2</a></li> <li><a href="#">3</a></li> <li><a href="#">4</a> </li> <li> <a href="#">5</a> </li>
                            <li>
                                <a href="#">下一页</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="http://apps.bdimg.com/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-switch/4.0.0-alpha.1/js/bootstrap-switch.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-slider/9.7.2/bootstrap-slider.js"></script>
<script src="${ctx}resource/js/pageHelper.js?ver=${nowDate}"></script>
<script>
    var baiduPage;
    $(function () {
        $("input[type=\"checkbox\"], input[type=\"radio\"]").not("[data-switch-no-init]").bootstrapSwitch();
        //$("[name='my-checkbox']").bootstrapSwitch();
        $("[data-switch-get]").on("click", function () {
            var type;
            type = $(this).data("switch-get");
            alert($("#switch-state").bootstrapSwitch("state"));
            return alert($("#switch-" + type).bootstrapSwitch(type));
        });

        $(".my-slider").bootstrapSlider();
        $(".my-slider").on("slide", function(slideEvt) {
            $("#"+this.id+"SliderVal").text(slideEvt.value);
        });
        var $checkAll = $('#allSwitch'),
                $checkboxes = $('#range input[name="platform"]');

        /**
         * 全选/反选事件
         */
        $checkAll.on('switchChange.bootstrapSwitch', function(e, state){
            //alert(state);
            $checkboxes.bootstrapSwitch('state',state,true);
        });
        $checkboxes.on('switchChange.bootstrapSwitch', function(e, state){
            var allLength = $checkboxes.length,
                    checkedLength = 0;
            $.each($checkboxes, function(){
                if ($(this).bootstrapSwitch("state")){
                    checkedLength++;
                }
            });
            if(checkedLength === 0){
                $checkAll.bootstrapSwitch('state',false,true);
            }
            if(checkedLength < allLength){
                $checkAll.bootstrapSwitch('state',false,true);
            }else{
                $checkAll.bootstrapSwitch('state',true,true);
            }
        });

        //输入框提示工具
        $('#key').popover({
            container:"body",
            toggle:"popover",
            placement:"top",
            trigger:"manual",
            content: '请输入关键词'}
        );

        //输入框提示工具
        $('#range').popover({
            container:"body",
            toggle:"popover",
            placement:"top",
            trigger:"manual",
            content: '请至少选择一个范围'}
        );
        //定时隐藏提示工具
        $('[data-toggle="popover"]').on('shown.bs.popover', function () {
            var id = this.id;
            window.setTimeout(function(){ hidePopover(id);},3000);
        });

        //点击搜索
        $("#submit").on("click", function () {
            var key = $("#key").val().trim();
            if (key==""){
                $('#key').popover('show');
                return;
            }
            var range = "";
            var platforms = new Array()
            var ii = 0;
            $.each($checkboxes, function(){
                if ($(this).bootstrapSwitch("state")){
                    range += ($(this).attr("for") + "-"+ $('#' + $(this).attr("for")).bootstrapSlider('getValue') + ";")
                    platforms[ii]=$(this).attr("for");
                    ii++;
                }
            });
            if (range==""){
                $('#range').popover('show');
                return;
            }
            $.getJSON('${ctx}/getInfo', $.param({
                'key' : key,
                'range' : range
            }, true), function(resp) {
                if (resp['success']) {
                    for (var j = 0; j < platforms.length; j++) {
                        var platform = platforms[j];
                        var list = resp['data'][platform+'List'];
                        baiduPage = new PageHelper("baiduPage","baidu",list,{count:15,navigatePages:3});
//                        $("#tbody-"+platform).empty();
//                        for(var i=0;i<list.length;i++){
//                            $("#tbody-"+platform).append(
//                                    "<tr id=\""+platform+(i+1)+"\">"+
//                                    "<td>"+(i+1)+"</td>"+
//                                    "<td>"+list[i].platform+"</td>"+
//                                    "<td><div style=\"width:100px; white-space:nowrap; text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow:hidden;\">"+list[i].title+"</div></td>"+
//                                    "<td><a href=\""+list[i].url+"\" target=\"_blank\"><div style=\"width:150px; white-space:nowrap; text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow:hidden;\">"+list[i].url+"</div></a></td>"+
//                                    "<td>"+list[i].date+"</td>"+
//                                    "<td>"+list[i].state+"</td>"+
//                                    "</tr>"
//                            );
//                        }
                    }
                } else {
                    alert(resp['errormsg']);
                }
            });
        });
        //点击重置
        $("#reset").on("click", function () {
            $("#key").val("");
            $checkboxes.bootstrapSwitch('state',false);
            $(".my-slider").bootstrapSlider('setValue', 20);
        });
    })
    //隐藏提示栏
    function hidePopover(id) {
        $('#'+id).popover('hide');
    }
</script>
</body>
</html>