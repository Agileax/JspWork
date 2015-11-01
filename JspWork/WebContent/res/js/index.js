$(function(){
	initMainFrameSize();
	loadMenu("com.app.bean.Client");
});
var index = 0;
var saveIsUpdate = false;
function initMainFrameSize(){
	$(".main").width($(document).width()-240);
}
function loadMenu(activeModel){
	var html = "";
	for (var item = 0; item < menuData.length; item++){
		if (activeModel == menuData[item].model){
			index = item;
			html += "<div class='item active' onclick=loadMenu('"+menuData[item].model+"')>"+menuData[item].menu+"</div>";
		}else {
			html += "<div class='item normal' onclick=loadMenu('"+menuData[item].model+"')>"+menuData[item].menu+"</div>";
		}
		
	}
	$("#menu").html(html);
	$("#title").html(menuData[index].menu);
	ajaxload();
}

function ajaxload(){
	var data = menuData[index];
	var cols = data.cols;
	var colId = new Array(cols.length);
	var ths = "<tr>";
	for (var i = 0; i < cols.length; i++){
		ths += "<th id='"+cols[i][0]+"'>"+(cols[i][1] == "id" ? "<input type='checkbox'>" : cols[i][1])+"</th>";
		colId[i] = cols[i][0];
	}
	ths += "</th>";
	$("#dataTable").html(ths);
	$.post(
		contextPath + "/main?method=getInfoList&model=" + data.model,
		function(res){
			var list = res.data;
			for (var i = 0; i < list.length; i++){
				ths += "<tr>";
				for (var j = 0; j < colId.length; j++){
					if (colId[j] == "id"){
						ths += "<td><input type='checkbox' value='"+list[i][colId[j]]+"'></td>";
					}else{
						ths += "<td>"+list[i][colId[j]]+"</td>";
					}
				}
				ths += "</tr>";
			}
			$("#dataTable").html(ths);
		}
	);
	
}
function newData(){
	saveIsUpdate = false;
	createForm(null);
}
function editData(){
	saveIsUpdate = true;
	var boxes = $("#dataTable input:checked");
	if (boxes.length != 1){
		alert("请选择一条数据");
		return;
	}
	var id = boxes[0].value;
	var model = menuData[index].model;
	$.post(
		contextPath + "/main?method=getObject&model=" + model+"&id="+id,
		function(res){
			createForm(res.obj);
		}
	);
}
function createForm(data){
	if (data == null || !"id" in data){
		data = new Object();
		data.id = new Date().getMilliseconds()+Math.random()*1000;
	}
	var cols = menuData[index].cols;
	var html = "";
	for (var i = 0; i < cols.length; i++){
		html += "<tr "+(cols[i][1] == "id" ? "style='display:none'" : "")
				+"><td align='right' width='35%'>"+cols[i][1]
				+"：</td><td><input id='"+cols[i][0]
				+"' value='"+(cols[i][0] in data ? data[cols[i][0]] : "")+ "'></td></tr>";
	}
	$("#dataForm").html(html);
	$("#dataLayer").show();
}
function saveData(){
	var cols = menuData[index].cols;
	var model = menuData[index].model;
	var input = $("#dataForm input");
	var data = new Object();
	for (var i = 0; i < input.length; i++){
		var id = input[i].id;
		var value = input[i].value;
		data[id] = value;
	}
	$.post(
		contextPath + "/main?&model=" + model+"&method=" +(saveIsUpdate != false ? "updateData" : "createData"),
		data,
		function(res){
			ajaxload();
			closeLayer();
		}
	);
}
function closeLayer(){
	$("#dataLayer").hide();
	$("#dataForm").html("");
}
function deleteData(){
	var boxes = $("#dataTable input:checked");
	if (boxes.length != 1){
		alert("请选择一条数据");
		return;
	}
	var model = menuData[index].model;
	var id = boxes[0].value;
	$(boxes[0]).parent().parent().remove();
	$.post(
		contextPath + "/main?method=deleteData&model=" + model + "&id=" + id
	);
}
function logout(){
	$.get(
		contextPath + "/main?method=logout"
	);
	window.location.href=contextPath+"/main?method=login";
}