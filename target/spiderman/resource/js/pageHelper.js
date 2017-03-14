//分页
/**
 * data list，
 */
var PageHelper = function(id,tableId,data,param){
	this.param = {
		count:param.count == undefined ? 10 :param.count,	//每页条数
		sum:param.sum == undefined ? 1 :param.sum,	//记录总数
		total:param.total == undefined ? 1 :param.total,	//页数
		current:param.current == undefined ? 1 :param.current,	//当前页
		previous:param.previous == undefined ? 0 :param.previous,	//上一页
		next:param.next == undefined ? 2 :param.next,	//下一页
		first:param.first == undefined ? 0 :param.first,	//第一页
		last:param.last == undefined ? 0 :param.last,	//最后一页
		jump:param.jump == undefined ? 1 :param.jump,	//跳到X页
		navigatePages:param.navigatePages == undefined ? 5 :param.navigatePages,	//导航页码数
		navigatePageNums:param.navigatePageNums	//所有导航页号
	};

	var p = this.param;
	//记算总数
	p.sum = data.length;
	//计算总页数
	p.total = parseInt(p.sum/p.count);
	if(p.sum%p.count != 0){
		p.total = p.total + 1;
	}
	p.last=p.total;
	//计算导航页
	this.calcNavigatePageNums = function(){
		//当总页数小于或等于导航页码数时
		if (p.total <= p.navigatePages) {
			p.navigatePageNums = new Array(p.total);
			for (var i = 0; i < p.total; i++) {
				p.navigatePageNums[i] = i + 1;
			}
		} else { //当总页数大于导航页码数时
			p.navigatePageNums = new Array(p.navigatePages);
			var startNum = parseInt(p.current) - parseInt(p.navigatePages / 2);
			var endNum = parseInt(p.current) + parseInt(p.navigatePages / 2);

			if (startNum < 1) {
				startNum = 1;
				//(最前navigatePages页
				for (var i = 0; i < p.navigatePages; i++) {
					p.navigatePageNums[i] = startNum++;
				}
			} else if (endNum > p.total) {
				endNum = p.total;
				//最后navigatePages页
				for (var i = p.navigatePages - 1; i >= 0; i--) {
					p.navigatePageNums[i] = endNum--;
				}
			} else {
				//所有中间页
				for (var i = 0; i < p.navigatePages; i++) {
					p.navigatePageNums[i] = startNum++;
				}
			}
		}
	}
	//上一页
	this.previousPage = function(){
		if(p.current > 1){
			p.previous--;
			p.current--;
			p.next--;
		}
		this.currentData();
	};
	//下一页
	this.nextPage = function(){
		if(p.current < p.total){
			p.previous++;
			p.current++;
			p.next++;
		}
		this.currentData();
	};
	//第一页
	this.firstPage = function(){
		p.previous = 0;
		p.current = 1;
		p.next = 2;
		this.currentData();
	};
	//最末页
	this.lastPage = function(){
		p.previous = p.total - 1;
		p.current = p.total;
		p.next = p.total + 1;
		this.currentData();
	};
	//跳到
	this.jumpTo = function(jumpTo){
		var value = jumpTo == undefined ? $("#"+id+"PageNum").val() : jumpTo;
		//正则验证正整数
		var reg = /^[0-9]{1,5}$/;
		if (reg.test(value)) p.jump = value;
		else p.jump = p.current;

		if(p.jump < 1 && p.jump < (p.total + 1)){
			this.firstPage;
		}else if(p.jump > p.total){
			this.lastPage;
		}else{
			p.previous = p.jump - 1;
			p.current = p.jump;
			p.next = p.jump + 1;
		}
		this.currentData();
	};

	this.pageInfo = function() {
		//初始化
		$("#" + id).empty();
		var pagination =
			"<div class=\"scroll navbar-right\">"
			+ "<ul class=\"pagination navbar-right\">";
		//首页
		//上一页
		if (p.previous == 0) {
			pagination += "<li class=\"disabled\"><a href=\"#\" rel=\"First\">首页</a></li>";
			pagination += "<li class=\"disabled\"><a href=\"#\" rel=\"Previous\">上一页</a></li>";
		} else {
			pagination += "<li><a href=\"javascript:" + id + ".firstPage()\" rel=\"First\">首页</a></li>";
			pagination += "<li><a href=\"javascript:" + id + ".previousPage()\" rel=\"Previous\">上一页</a></li>";
		}
		//中间页
		for (var i = 0; i < p.navigatePageNums.length; i++) {
			if (p.navigatePageNums[i] == p.current) {
				pagination += "<li id=\"current\" class=\"active\" value=\"" + p.navigatePageNums[i] + "\"><span>" + p.navigatePageNums[i] + "</span></li>";
			} else {
				pagination += "<li><a href=\"javascript:" + id + ".jumpTo(\'" + p.navigatePageNums[i] +"\')\" value=\"" + p.navigatePageNums[i] + "\">" + p.navigatePageNums[i] + "</a></li>";
			}
		}
		//下一页
		//尾页
		if (p.last == p.current) {
			pagination += "<li class=\"disabled\"><a href=\"#\" rel=\"Next\">下一页</a></li>";
			pagination += "<li class=\"disabled\"><a href=\"#\" rel=\"Last\">末页</a></li>";
		} else {
			pagination += "<li><a href=\"javascript:" + id + ".nextPage()\" rel=\"Next\">下一页</a></li>";
			pagination += "<li><a href=\"javascript:" + id + ".lastPage()\" rel=\"Last\">末页</a></li>";
		}
		pagination +=
			"<li>共" + p.total + "页，</li>"
			+ "<li>共" + p.sum + "条，</li>"
				//+"<li>第"+p.current+"页</li>"
			+ "<li> 到第"
			+ "<input id=\"" + id + "PageNum\" type=\"number\" style=\"width:50px;height:28px;vertical-align:middle;font-size:18px;line-height:22px;\">页"
			+ "<button id=\"" + id + "Confirm\" type=\"button\" class=\"btn navbar-btn\" onclick=\"" + id + ".jumpTo()\">跳转</button>"
			+ "</ul>"
			+ "</div>";
		$("#" + id).append(pagination);
	}

	//currentData
	this.currentData = function(){
		//当前页数显示
		//$("#"+id+" :text").val(p.current);
		var rs = new Array();
		for(var i=0;i<p.sum;i++){
			var start,end;
			start = p.count*(p.current-1)-1;
			end = p.count*(p.current);
			if(i > start && i < end){
				rs.push(data[i]);
			}
		}
		//##############这里可以插入其他方法##########
		this.extend(rs);
		this.calcNavigatePageNums();
		this.pageInfo();
		//############################################
		return rs;
	}

	//数据展示策略
	this.extend = function(rs){
		$("#tbody-"+ tableId).empty();
		for(var i=0;i<rs.length;i++){
			$("#tbody-"+tableId).append(
				"<tr id=\""+tableId+(i+1)+"\">"+
				"<td>"+(i+1)+"</td>"+
				"<td>"+rs[i].platform+"</td>"+
				"<td><div style=\"width:100px; white-space:nowrap; text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow:hidden;\">"+rs[i].title+"</div></td>"+
				"<td><a href=\""+rs[i].url+"\" target=\"_blank\"><div style=\"width:150px; white-space:nowrap; text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow:hidden;\">"+rs[i].url+"</div></a></td>"+
				"<td>"+rs[i].date+"</td>"+
				"<td>"+rs[i].state+"</td>"+
				"</tr>"
			);
		}
	}
	this.currentData();

}