/**
 * @autor syp
 * @content 校友数据页面js
 * @returns
 * @Time 2018-9-4
 */

layui.config({
	base : "../../../../static/js/"
}).extend({
	"application" : "application",
	"publicUtil" : "publicUtil"
})

layui.use(['table','form','element','layer','jquery','application','upload','publicUtil'],function(){
	var layer = layui.layer,
		form = layui.form,
		laydate = layui.laydate,
		$ = layui.jquery,
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
			elem: '#smtempdatalist',
			url : application.SERVE_URL+'/sys/smSchoolmateTemp/list',
			//生产坏境下请求后台
			cellMinWidth : 95,
			page : true,
			even : true ,
			headers : { 'Authorization' : application.HEADER},
			height : "full-160",
			limit : 10,
			id : "smtempdatalist",
			cols : [[
				{type:'checkbox'},
				{field: 'name', title: '姓名'},
				{field: 'sex', title: '性别'},
				{field: 'nativePlace', title: '籍贯'},
				{field: 'idcard', title: '证件号码'},
				{field: 'birthday', title: '生日'},
				{field: 'school', title: '学校'},
				{field: 'academy', title: '书院'},
				{field: 'college', title: '学院'},
				{field: 'series', title: '系'},
				{field: 'studentid', title: '学号'},
				{field: 'remark', title: '备注'}
			]]
		});	


		//新增操作
		$(document).on('click','.PER_ADD',function(){
			addSm();
		});
		
		//编辑操作
		$(document).on('click','.PER_EDIT',function(){		
			var flag = publicUtil.jurgeSelectRows(table.checkStatus('smtempdatalist').data);
			if(flag){			
				addSm(table.checkStatus('smtempdatalist').data[0]);
			}else{
				return false;
			}

		})
		
		//删除
		$(document).on('click','.PER_DEL',function(){		
			var flag = publicUtil.jurgeSelectRows(table.checkStatus('smtempdatalist').data);
			if(flag){
				layer.confirm('确定删除此此编码？',{icon:3, title:'提示信息'},function(index){				
					$.ajax({
						url: application.SERVE_URL+"/sys/smSchoolmateTemp/delete", //ajax请求地址
						data:{
							id : table.checkStatus('smtempdatalist').data[0].id  
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

		
		//搜索【此功能需要后台配合，所以暂时没有动态效果演示】
		$(".search_btn").click(function(){
			if($(".searchVal").val() != ''){
				table.reload("smtempdatalist",{
					page: {
						curr: 1 //重新从第 1 页开始
					},
					where: {
						name: $(".name").val()  //搜索的关键字
					}
				})
				
			}else{
				layer.msg("请输入搜索的内容");
			}
		});

		
		$(document).on('click','#import',function(){	
				var formdata = new FormData;          //创建一个FormData对象  
				formdata.append('file',$("#upfile").get(0).files[0]);
			 $.ajax({
				 url: application.SERVE_URL+"/sys/smSchoolmateTemp/importsmtempdata",
				 type: "POST",
				 headers : { "Authorization" : application.HEADER},
				 dataType: "json",
				 data: formdata,
				 processData: false,
				 contentType: false,
				 success: function(d) {
						console.log(d);
				 }
			 });
			
		})
			
			
			
			
		//导入
		$(document).on('click','.PER_IMPORT',function(){	
				layer.open({
					content: "导入需下下载模板 <br/>"+  
					"<p style='color:red;'>提示：仅允许导入'xls'或'xlsx'格式文件！</p> <br/>"+
					"<input type='file' id='upfile'/>"
					,btn: ['下载模板','导入', '退出']
					,yes: function(index, layero){
						//下载模板
						window.location.href = application.SERVE_URL+"/sm_tmplate.xlsx";
						return false;
					}
					,btn2: function(index, layero){
						var formdata = new FormData;          //创建一个FormData对象  
						formdata.append('file',$("#upfile").get(0).files[0]);
						var filePath = $("#upfile").val().toLowerCase().split(".");
						var fileType =  filePath[filePath.length - 1];
						console.log(fileType);
						if('xls' != fileType && 'xlsx' != fileType){
							layer.msg("请选择正确的格式进行导入");
							return false;
						}else{
							$.ajax({
								url: application.SERVE_URL+"/sys/smSchoolmateTemp/importsmtempdata",
								type: "POST",
								headers : { "Authorization" : application.HEADER},
								dataType: "json",
								data: formdata,
								processData: false,
								contentType: false,
								success: function(res) {
									layer.msg(res.msg);
								}
							});	
						}
					}
					,cancel: function(){ 
						//关闭
					}
				});			
			
		});  
		
		
		//添加校友
		function addSm(edit){
			publicUtil.gotoEditPage(application.SERVE_URL +'/sys/smSchoolmateTemp/get',edit ==undefined?null:edit.id,"校友管理","smtempdataAdd.html");
		}
})