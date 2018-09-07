/**
 * @autor syp
 * @content 捐赠项目列表页面js
 * @returns
 * @Time 2018-08-02
 */
layui.config({
	base: "../../../../static/js/"
}).extend({
	"application": "application",
	"publicUtil": "publicUtil"
})
var donationTableIns;
var projectObj;
layui.use(['form', 'layer', 'laydate', 'table', 'laytpl', 'application', 'publicUtil'], function () {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		publicUtil = layui.publicUtil,
		application = layui.application,
		laydate = layui.laydate,
		laytpl = layui.laytpl,
		table = layui.table;

	application.init();

	var tableIns = table.render({
		elem: '#projectList',
		url: application.SERVE_URL + '/sys/donProject/list',
		even : true,
		cellMinWidth: 95,
		page: true,
		height: "full-160",
		limit: 10,
		headers: {
			'Authorization': application.HEADER
		},
		id: "projectList",
		cols: [
			[{
				field: 'name',
				title: '项目名称',
				event: 'click'
			}, {
				field: 'type',
				title: '项目类型',
				event: 'click'
			}, {
				field: 'targetMoney',
				title: '目标金额',
				event: 'click'
			}, {
				field: 'gotMoney',
				title: '当前金额',
				event: 'click'
			}, {
				field: 'donatingNum',
				title: '捐款人数',
				event: 'click'
			}]
		],
		done: function(res, curr, count){    //res 接口返回的信息,
			publicUtil.tableSetStr(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'PROJECT_TYPE'},'type');
		}
	});
	
	function rendRecordList(id)
	{
		donationTableIns = table.render({
			elem: '#recordList',
			url: application.SERVE_URL + '/sys/donRecord/list?donProjectId=' + id,
			even : true,
			cellMinWidth: 95,
			page: true,
			height: "full-160",
			limit: 10,
			headers: {
				'Authorization': application.HEADER
			},
			id: "recordList",
			cols: [
				[{
					type: 'checkbox'
				}, {
					field: 'donType',
					title: '捐赠类型'
				}, {
					field: 'name',
					title: '捐赠人'
				}, {
					field: 'money',
					title: '捐赠金额'
				}, {
					field: 'moneyType',
					title: '币种'
				}, {
					field: 'time',
					title: '捐赠时间'
				}, {
					field: 'money',
					title: '捐赠金额'
				}]
			],
			done: function(res, curr, count){    //res 接口返回的信息,
				publicUtil.tableSetStr(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'DONATION_TYPE'},'donType');
			}
		});
	}
	rendRecordList();

	table.on('tool(projectList)', function (obj) {
		projectObj=obj;
		$(".layui-select-tr").removeClass("layui-select-tr");
		obj.tr.addClass("layui-select-tr");
		rendRecordList(obj.data.id);
	});

	//新增操作
	$(document).on('click', '.PER_ADD', function () {
		if(projectObj)
		{
			addCard();
		}
		else
		{
			top.layer.msg("请先选择项目！");
		}
	});

	//编辑操作
	$(document).on('click', '.PER_EDIT', function () {
		var flag = publicUtil.jurgeSelectRows(table.checkStatus('recordList').data);
		if (flag) {
			addCard(table.checkStatus('recordList').data[0]);
		} else {
			return false;
		}

	})

	//删除
	$(document).on('click', '.PER_DEL', function () {
		var flag = publicUtil.jurgeSelectRows(table.checkStatus('recordList').data);
		if (flag) {
			layer.confirm('确定删除此捐赠信息吗？', {
				icon: 3,
				title: '提示信息'
			}, function (index) {
				$.ajax({
					url: application.SERVE_URL + "/sys/donRecord/delete", //ajax请求地址
					type: "POST",
					data: {
						id: table.checkStatus('recordList').data[0].id
					},
					headers: {
						'Authorization': application.HEADER
					},
					success: function (data) {
						if (data = "success") {
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
	$(".search_btn").on("click", function () {
		table.reload("recordList", {
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
	function addCard(edit) {
		var restUrl = application.SERVE_URL + '/sys/donRecord/get';
		var id = edit ? (edit.id ? edit.id : null) : null;
		publicUtil.gotoEditPage(restUrl, id, "添加捐赠信息", "recordadd.html")
	}
})
