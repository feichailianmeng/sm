/**
 * @autor syp
 * @content 用户列表页面js
 * @returns
 * @Time 2018-08-03
 */

layui.config({
	base : "../../../../static/js/"
}).extend({
	"application" : "application",
	"publicUtil" : "publicUtil",
	"dateUtils"  : "dateUtils"
})

layui.use(['table','form','element','layer','jquery','application','upload','publicUtil','laydate','dateUtils'],function(){
	var layer = layui.layer,
		form = layui.form,
		laydate = layui.laydate,
		dateUtils = layui.dateUtils,
		_$ = layui.jquery,
		publicUtil = layui.publicUtil,
		table = layui.table,
		application = layui.application,
		upload = layui.upload;
		
		application.init();
		//获取权限并加载按钮
		publicUtil.getPerms(application.PERMS_URL,application.HEADER,parent.cur_menu_id,'get','but_per');
		




		
		//根据Id 加载右侧用户数据
		/**
		 * 校友管理列表
		 */
		tableIns = table.render({
			elem: '#smList',
			url : application.SERVE_URL+'/sys/smSchoolmate/list',
			//生产坏境下请求后台
			cellMinWidth : 95,
			page : true,
			even : true ,
			headers : { 'Authorization' : application.HEADER},
			height : "full-125",
			limit : 10,
			id : "smList",
			cols : [[
				//姓名、性别、证件类型、证件号码、入校日期、专业、班级、校友类型、申请日期、状态、操作
				// {field: 'id', title: 'ID', align:"center"},
				{type:'checkbox'},
				{field: 'name', title: '姓名'},
				{field: 'sex', title: '性别'},
				{field: 'cardNum', title: '证件号码'},
				{field: 'nation', title: '民族'},
				{field: 'username', title: '用户名'},
				{field: 'type', title: '校友类型'},
				{field: 'birthday', title: '生日'}
			]]
			,done: function(res, curr, count){    //res 接口返回的信息,
				publicUtil.tableSetStr(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'SEX'},'sex');
				publicUtil.tableSetStr(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'USERTYPE'},'type');
				publicUtil.tableSetStr(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'NATION'},'nation');
			}	
		});	


		//新增操作
		_$(document).on('click','.PER_ADD',function(){
			addSm();
		});
		
		//设置标签
		_$(document).on('click','.PER_SETMARK',function(){
			var flag = publicUtil.jurgeSelectRows(table.checkStatus('smList').data);
				if(flag){
					_$.ajax({
						url : application.SERVE_URL+'/sys/smSchoolmate/get',
						data : {"id" : table.checkStatus('smList').data[0].id}, //ajax请求地址
						success: function (res) {
							formdatas = res.data;
							var index = layui.layer.open({
								type: 2,
								title: '标签选择',
								shadeClose: true,
								shade: 0.8,
								area: ['280px', '65%'],
								content: '/views/module/schoolmate/mark/markselect.html',
								success : function(layero, index){										
									setTimeout(function(){
										layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
											tips: 3
										});
									},500)
								},
							})																				
						}
					});

				}else{
					return false;
				}
		});
		
		//编辑操作
		_$(document).on('click','.PER_EDIT',function(){		
			var flag = publicUtil.jurgeSelectRows(table.checkStatus('smList').data);
			if(flag){			
				addSm(table.checkStatus('smList').data[0]);
			}else{
				return false;
			}

		})
		
		//删除
		_$(document).on('click','.PER_DEL',function(){		
			var flag = publicUtil.jurgeSelectRows(table.checkStatus('smList').data);
			if(flag){
				layer.confirm('确定删除此此编码？',{icon:3, title:'提示信息'},function(index){				
					_$.ajax({
						url: application.SERVE_URL+"/sys/smSchoolmate/delete", //ajax请求地址
						data:{
							id : table.checkStatus('smList').data[0].id  
						},
						headers : { 'Authorization' : application.HEADER},												
						success: function (res) {
							if(res.code==application.REQUEST_SUCCESS){
								tableIns.reload();
								// location.reload();
								layer.close(index);	
								top.layer.msg(res.msg);							
							}else{
								top.layer.msg(res.msg);
							}

						}
					});	
				});			
			}else{
				return false;
			}
		})	

		
		_$(document).on('click','.PER_EXPORT',function(){
					//创建url
					var url=application.SERVE_URL+"/sys/smSchoolmate/exportsmDatas";
					//创建form
					var form=$("<form></form>");
					//创建要提交的参数
					var nameDom =  $("<input type='hidden' name='name' value ='"+$("#name").val() +"'>");
					var typeDom =  $("<input type='hidden' name='type' value ='"+$("#type").val() +"'>");
					var sexDom  =  $("<input type='hidden' name='sex' value ='"+$("#sex").val() +"'>");
					var nationDom  =  $("<input type='hidden' name='nation' value ='"+$("#nation").val() +"'>");
					var cardTypeDom  =  $("<input type='hidden' name='cardType' value ='"+ $("#cardType").val() +"'>");
					var cardNumDom  =  $("<input type='hidden' name='cardNum' value ='"+$("#cardNum").val() +"'>");
					var orgIdDom  =  $("<input type='hidden' name='orgId' value ='"+$("#orgId"+"Hide").val() +"'>");
					var studentidDom  =  $("<input type='hidden' name='studentid' value ='"+$("#cardNum").val() +"'>");
					var degreeDom  =  $("<input type='hidden' name='degree' value ='"+$("#degree").val() +"'>");
					var degreetypeDom  =  $("<input type='hidden' name='degreetype' value ='"+$("#degreetype").val() +"'>");
					var schoollenDom  =  $("<input type='hidden' name='schoollen' value ='"+$("#schoollen").val() +"'>");
					var queryBeginDateDom  =  $("<input type='hidden' name='queryBeginDate' value ='"+$("#startdate").val() +"'>");
					var queryEndDateDom  =  $("<input type='hidden' name='queryEndDate' value ='"+$("#enddate").val() +"'>");
					var contactTypeDom  =  $("<input type='hidden' name='contactType' value ='"+$("#contact_type").val() +"'>");
					var contactDom  =  $("<input type='hidden' name='contact' value ='"+$("#contact").val() +"'>");
					var areaIdDom  =  $("<input type='hidden' name='areaId' value ='"+$("#address" + "Hide").val() +"'>");
					//设置属性
					form.attr("action",url);
					form.attr("method","post");
					//创建input，即参数

					//注入参数到表单
					form.append(nameDom);form.append(typeDom);form.append(sexDom);form.append(nationDom);
					form.append(cardTypeDom);form.append(cardNumDom);form.append(orgIdDom);form.append(studentidDom);
					form.append(degreeDom);form.append(degreetypeDom);form.append(schoollenDom);form.append(queryBeginDateDom);
					form.append(queryEndDateDom);form.append(contactTypeDom);form.append(contactDom);form.append(areaIdDom);
					form.appendTo("body");  
					form.hide();
					//提交表单
					form.submit();
					form.remove();
					// var url = host+"&"+params;
					// _$('<form method="post" action="' + application.SERVE_URL+"/sys/smSchoolmate/exportsmDatas" + '"></form>').appendTo('body').submit().remove();
					// delete dataParams[exportExcel];		
		});

		
		//搜索【此功能需要后台配合，所以暂时没有动态效果演示】
		form.on("submit(searchForm)",function(data){

				table.reload("smList",{
					page: {
						curr: 1 //重新从第 1 页开始
					},
					where: {
						name: $("#name").val(),
						type: $("#type").val(),
						sex : $("#sex").val(),
						nation : $("#nation").val(),
						cardType : $("#cardType").val(),
						cardNum : $("#cardNum").val(),
						orgId : $("#orgId"+"Hide").val(),
						studentid : $("#cardNum").val(),
						degree : $("#degree").val(),
						degreetype : $("#degreetype").val(),
						schoollen : $("#schoollen").val(),
						queryBeginDate : $("#startdate").val(),
						queryEndDate : $("#enddate").val(),
						contactType : $("#contact_type").val(),
						contact : $("#contact").val(),
						areaId : $("#address" + "Hide").val()
												 
					}
				})
				
		});
		
		
		//添加校友
		function addSm(edit){
			publicUtil.gotoEditPage(application.SERVE_URL +'/sys/smSchoolmate/get',edit ==undefined?null:edit.id,"校友管理","schoolmateAdd.html");
		}
		
		/* ------------------------------------------------------------筛选js--------------------------------------------------------------- */
		//初始化
		publicUtil.selectBase(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'NATION'} ,"nation",true);
		publicUtil.selectBase(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'USERTYPE'} ,"type",true);
		publicUtil.selectBase(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'SEX'} ,"sex",true);
		publicUtil.selectBase(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'IDCARD_TYPE'} ,"cardType",true);
		publicUtil.selectBase(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'CONTACT_TYPE'} ,"contact_type",true,true);
		publicUtil.selectBase(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'EDU_DEGREE'} ,"degree",true);
		publicUtil.selectBase(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'EDU_DEGREETYPE'} ,"degreetype",true);
		publicUtil.selectBase(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'EDU_SCHOOLEN'} ,"schoollen",true);
		//初始化日期组件
		laydate.render({
			elem: '#startdate',
			lang: 'en'
		});
		laydate.render({
			elem: '#enddate',
			lang: 'en'
		});
		
		//机构初始化
		var orgTree;
		//通讯地址初始化
		var areaTree;
		
		$(function(){
			orgTree = initSelectTree("orgId", application.SERVE_URL+'/sys/sysorg/tree');
			areaTree = initSelectTree("address", application.SERVE_URL+'/sys/sysarea/tree');
			$("#orgId").click();
		})
		
					
		//处理三联菜单的内容 如 ： ["102/10201/1020101"]
		function getFormSelectVal(arr){
			var val = arr[0];
			if(arr != null && arr != ""){
				var arrs =  new Array();
				arrs = val.split("/")
				return arrs[arrs.length -1];
			}
			return null;
		}
		
		
		
		
		
		/* 树级下拉 */

		function initSelectTree(id,url) {
			var setting = {
				view: {
					selectedMulti: false,
					showLine: false
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "parentId"			
					}
				},
				check: {
					enable: false,
					chkboxType: {"Y": "ps", "N": "s"}
				},
				callback: {
		            onClick: onClick,
		            onCheck: onCheck			
				}
			};
		    var html = '<div class = "layui-select-title" >' +
		        '<input id="' + id + 'Show"' + 'type = "text" placeholder = "请选择" value = "" class = "layui-input" readonly>' +
		        '<i class= "layui-edge" ></i>' +
		        '</div>';
		    _$("#" + id).append(html);
		    _$("#" + id).parent().append('<div class="tree-content scrollbar">' +
		        '<input hidden id="' + id + 'Hide" ' +
		        'name="' + $(".select-tree").attr("id") + '">' +
		        '<ul id="' + id + 'Tree" class="ztree scrollbar" style="margin-top:0;"></ul>' +
		        '</div>');
		    $("#" + id).bind("click", function () {
		        if (_$(this).parent().find(".tree-content").css("display") !== "none") {
		            hideMenu()
		        } else {
		            _$(this).addClass("layui-form-selected");
		            var Offset = _$(this).offset();
		            var width = _$(this).width() - 2;
		            _$(this).parent().find(".tree-content").css({
		                left: Offset.left + "px",
		                top: Offset.top + _$(this).height() + "px"
		            }).slideDown("fast");
		            _$(this).parent().find(".tree-content").css({
		                width: width
		            });
		            _$("body").bind("mousedown", onBodyDown);
		        }
		    });
			_$.ajax({
				url: url,
				type: "POST", // 默认使用POST方式
				success: function (data) {
					$.fn.zTree.init($("#"+id+ "Tree"), setting, data.data); //加载数据	
				}
			});	
				
		}
		
		function onClick(event, treeId, treeNode) {
		    var zTree = $.fn.zTree.getZTreeObj(treeId);
		    if (zTree.setting.check.enable == true) {
		        zTree.checkNode(treeNode, !treeNode.checked, false)
		        assignment(treeId, zTree.getCheckedNodes());
		    } else {
		        assignment(treeId, zTree.getSelectedNodes());
		        hideMenu();
		    }
		}
		
		function onCheck(event, treeId, treeNode) {
		    var zTree = $.fn.zTree.getZTreeObj(treeId);
		    assignment(treeId, zTree.getCheckedNodes());
		}
		
		function hideMenu() {
		    _$(".select-tree").removeClass("layui-form-selected");
		    _$(".tree-content").fadeOut("fast");
		    _$("body").unbind("mousedown", onBodyDown);
		}
		
		function assignment(treeId, nodes) {
		    var names = "";
		    var ids = "";
		    for (var i = 0, l = nodes.length; i < l; i++) {
		        names += nodes[i].name + ",";
		        ids += nodes[i].id + ",";
		    }
		    if (names.length > 0) {
		        names = names.substring(0, names.length - 1);
		        ids = ids.substring(0, ids.length - 1);
		    }
		    treeId = treeId.substring(0, treeId.length - 4);
		    _$("#" + treeId + "Show").attr("value", names);
		    _$("#" + treeId + "Show").attr("title", names);
		    _$("#" + treeId + "Hide").attr("value", ids);
		}
		
		function onBodyDown(event) {
		    if (_$(event.target).parents(".tree-content").html() == null) {
		        hideMenu();
		    }
		}
		
		function covert(data) {
			for (var i = 0; i < data.length; i++) {
				data[i].name = data[i].name +"("+dateUtils.getYearAndDay(data[i].openDate)+"--"+ judgeNull(data[i].closeDate)+")"
			}
			return data;
		}
		function judgeNull(data){
			if(data == null ||data =='null' ||data ==""){
				return "至今";
			}else{
				return dateUtils.getYearAndDay(data);
			}
		}
})