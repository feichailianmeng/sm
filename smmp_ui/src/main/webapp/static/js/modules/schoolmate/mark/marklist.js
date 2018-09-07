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
		elem: '#markList'
        ,url: application.SERVE_URL+'/sys/smMark/list'
        ,id: "markList"
		,headers : { 'Authorization' : application.HEADER}
        ,treeId:'id'//树形id字段名称
        ,treeUpId:'parentId'//树形父id字段名称
        ,treeShowName:'name'//以树形式显示的字段
        ,cols: [[
			{type:'checkbox'},			
            {field:'name', title: '标签名称'}
            ,{field:'code', title: '标签编码'}
            ,{field:'useable', title: '是否启用'}
            ,{field:'createDate', title: '创建时间'}
        ]]        
		,done: function(res, curr, count){    //res 接口返回的信息
			$("[data-field = 'useable']").children().each(function(){
				if($(this).text() == 1){
					$(this).text("启用");
				}else if($(this).text() == 0){
					$(this).text("禁用");
				}
			})
		}
	});

	//新增操作
	$(document).on('click', '.PER_ADD', function() {
		addMark(treeGrid.checkStatus('markList').data[0],"add");
	});

	//编辑操作
	$(document).on('click', '.PER_EDIT', function() {
		var flag = publicUtil.jurgeSelectRows(treeGrid.checkStatus('markList').data);
		if(flag) {
			addMark(treeGrid.checkStatus('markList').data[0],"edit");
		} else {
			return false;
		}

	})

	//删除
	$(document).on('click', '.PER_DEL', function() {
		var flag = publicUtil.jurgeSelectRows(treeGrid.checkStatus('markList').data);
		if(flag) {
			layer.confirm('确定删除此标签吗？', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.ajax({
					url: application.SERVE_URL + "/sys/smMark/delete", //ajax请求地址
					type: "POST",
					data: {
						id: treeGrid.checkStatus('markList').data[0].id
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
		treeGrid.reload("markList", {
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
	function addMark(edit,action) {
		var restUrl=application.SERVE_URL + '/sys/smMark/get';
		var id="edit"==action?(edit.id?edit.id:null):null;
		publicUtil.gotoEditPage(restUrl,id,"添加标签","markadd.html")
	}
})