/**
 * @autor syp
 * @content 字典列表页面js
 * @returns
 * @Time 2018-08-02
 */
layui.config({
	base: "../../../../static/js/"
}).extend({
	"application": "application",
	"publicUtil": "publicUtil"
})
layui.use(['form', 'layer', 'laydate', 'treeGrid', 'laytpl', 'application', 'publicUtil'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		publicUtil = layui.publicUtil,
		application = layui.application,
		laydate = layui.laydate,
		laytpl = layui.laytpl,
		treeGrid = layui.treeGrid;

	application.init();

	var tableIns = treeGrid.render({
		elem: '#associationList'
        ,url: application.SERVE_URL+'/sys/smAssociation/list'
        ,id: "associationList"
		,headers : { 'Authorization' : application.HEADER}
        ,treeId:'id'//树形id字段名称
        ,treeUpId:'parentId'//树形父id字段名称
        ,treeShowName:'name'//以树形式显示的字段
        ,cols: [
			[{
				type: 'checkbox'
			}, {
				field: 'name',
				title: '校友会名称'
			}, {
				field: 'type',
				title: '校友会类型'
			}, {
				field: 'createdate',
				title: '成立时间'
			}]
		]        
		,done: function(res, curr, count){    //res 接口返回的信息
		}
	});
	
	//新增操作
	$(document).on('click', '#ADD', function() {
		addAssociation(treeGrid.checkStatus('markList').data[0],"add");
	});

	//编辑操作
	$(document).on('click', '#EDIT', function() {
		var flag = publicUtil.jurgeSelectRows(treeGrid.checkStatus('associationList').data);
		if(flag) {
			addAssociation(treeGrid.checkStatus('associationList').data[0],"edit");
		} else {
			return false;
		}

	})

	//删除
	$(document).on('click', '#DEL', function() {
		var flag = publicUtil.jurgeSelectRows(treeGrid.checkStatus('associationList').data);
		if(flag) {
			layer.confirm('确定删除此校友会吗？', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.ajax({
					url: application.SERVE_URL + "/sys/smAssociation/delete", //ajax请求地址
					type: "POST",
					data: {
						id: treeGrid.checkStatus('associationList').data[0].id
					},
					headers: {
						'Authorization': application.HEADER
					},
					success: function(data) {
						if(data = "success") {
							tableIns.reload();
							layer.close(index);
						}
					}
				});
			});
		} else {
			return false;
		}
	})
	
	//获取权限并加载按钮
	publicUtil.getPerms(application.PERMS_URL, application.HEADER, parent.cur_menu_id, 'get', 'but_per');
	//搜索【此功能需要后台配合，所以暂时没有动态效果演示】
	$(".search_btn").on("click", function() {
		treeGrid.reload("associationList", {
			page: {
				curr: 1 //重新从第 1 页开始
			},
			where: {
				typeCode: $(".searchTypeCode").val(),
				label: $(".searchLabel").val(),
			}
		})
	});

	//添加编码
	function addAssociation(edit,action) {
		var restUrl=application.SERVE_URL + '/sys/smAssociation/get';
		var id="edit"==action?(edit.id?edit.id:null):null;
		publicUtil.gotoEditPage(restUrl,id,"添加校友会","associationadd.html")
	}
})