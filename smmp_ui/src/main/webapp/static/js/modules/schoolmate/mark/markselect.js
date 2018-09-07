layui.config({
	base: "../../../../static/js/"
}).extend({
	"application": "application",
	"publicUtil": "publicUtil"
})
layui.use(['layer', 'application', "publicUtil", 'application'], function () {
	var form = layui.form;
	layer = layui.layer,
		application = layui.application;
	publicUtil = layui.publicUtil
	layer = layui.layer;


	//ztree设置
	var setting = {
		view: {
			selectedMulti: false
		},
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "parentId"
				// rootPId: 0									
			}
		}
	};

	$(document).ready(function () {
		$.ajax({
			url: application.SERVE_URL + '/sys/smMark/list', //ajax请求地址
			data:{pageNo:1,pageSize:10},
			headers: {
				"Authorization": application.HEADER
			},
			success: function (res) {
				ztreeObj = $.fn.zTree.init($("#tree"), setting, res.data); //加载数据
				var nodeList = ztreeObj.getNodes();　　　　　　 //展开第一个根节点
				ztreeObj.expandNode(nodeList[0], true);
				publicUtil.setTreeSel(parent.formdatas.markList, ztreeObj);
			}
		});

		$("#reset").click(function () {
			ztreeObj.checkAllNodes(false);
		});
	});


	form.on("submit(getSelectData)", function (data) {
		//弹出loading
		var index = top.layer.msg('数据提交中，请稍候', {
			icon: 16,
			time: false,
			shade: 0.8
		});
		$.ajax({
			url: application.SERVE_URL + "/sys/smSchoolmate/saveSchoolmateMark", //ajax请求地址
			type: 'post',
			contentType: "application/json",
			data: JSON.stringify({
				id: parent.formdatas.id,
				markList: getSelectData(ztreeObj.getCheckedNodes())
			}),
			success: function (data) {
				if(data.code==application.REQUEST_SUCCESS){
					top.layer.close(index);
					top.layer.msg(data.msg);
					//刷新父页面
					parent.location.reload();
				}else{
					top.layer.msg(data.msg+"("+data.code+")");
				}
			},
			error: function(data){
				var result=data.responseJSON;
				top.layer.msg(result.msg+"("+result.code+")");
			}
		});
		return false;
	})


});

function getSelectData(treeCheckedDatas) {
	var menuIds = new Array();
	for (var i = 0; i < treeCheckedDatas.length; i++) {
		menuIds.push({
			"id": treeCheckedDatas[i].id
		});
	}
	return menuIds;
}
