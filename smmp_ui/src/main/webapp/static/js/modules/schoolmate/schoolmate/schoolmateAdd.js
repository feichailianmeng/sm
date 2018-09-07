/**
 * @autor syp
 * @content 角色列表页面js
 * @returns
 * @Time 2018-08-02
 */
layui.config({
	base: '../../../../static/js/' //此处路径请自行处理, 可以使用绝对路径
}).extend({
	formSelects: 'formSelects-v4',
	"application" : "application",
	'publicUtil' : 'publicUtil'
});

layui.use(['jquery','form','layer','laydate', 'formSelects','publicUtil','application','element','laytpl'],function(){
    var form = layui.form,
		$ = layui.jquery,
		formSelects = layui.formSelects,
    publicUtil  = layui.publicUtil,
		application = layui.application,
		layer =layui.layer,
		element = layui.element,
	  laydate = layui.laydate,
		laytpl = layui.laytpl;
		//籍贯
		var nation_place_datas;

		
		//禁止多选
		// formSelects.disabled('native_place');
		var config = {
		open: '{{',
		close: '}}'
		};
		
		initSchoolInfo(0);
		initContact(0);
		initAddress(0,null)
		
		
		//籍贯下拉配置
		formSelects.config('native_place', {
			
		});		

		//基础信息编辑表单回填
		if(parent.editFormData != ''){
			data = parent.editFormData;
			$('#schoolmate_id').val(data.id);
			$('#sysUserId').val(data.sysUserId);
			$(".name").val(data.name);
			$('#username').val(data.username);
			$('.cardNum').val(data.cardNum);
			$('#birthday').val(data.birthday);
			if($("#schoolmate_id").val()){
				$("#username").attr("readonly","true");
			};
			publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'NATION'} ,"nation",data.nation);
			publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'USERTYPE'} ,"type",data.type);
			publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'SEX'} ,"sex",data.sex);
			publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'IDCARD_TYPE'} ,"cardType",data.cardType);
			nation_place_datas = new Array();
			nation_place_datas.push(data.smAddress.province +"/"+data.smAddress.city+"/"+data.smAddress.district);
			initSelect();
			form.render();
			formSelects.render();
		}else{
			//页面初始化
			initSchoolmateData();
			form.render();		
		}



		
		
	// 卡片页签点击触发事件
	element.on('tab(info)', function(elem){
		location.hash = $(this).attr('lay-id');
		//获取卡片对应的数据,回显数据
		var username = $("#username").val();
		var sysUserId = $('#sysUserId').val();
		 //基本信息
		
	  if(elem.index == 0){
			
		}else if(elem.index == 1){ //教育经历
			if(!sysUserId){
				judgeIsSaveSm(sysUserId);
			}else{
				if(edu >= 1){
					$("#eduFrom").nextAll("form").remove();
				}	
				$.ajax({
					url: application.SERVE_URL+"/sys/smEducation/loadAllListBy", //ajax请求地址
					data: {"sysUserId":sysUserId},
					success: function (res) {
						if(res.code==application.REQUEST_SUCCESS){
								//获取长度
								var len = res.data.length;
								//加载DOM
								edu = len -1;
								for(var i = 1; i <= edu ;i++){
									$("#eduFrom").after(appendEduForm(i));
									initSchoolInfo(i);
								}
								//值回填
								for(var j = 0; j < res.data.length ;j++){
									$('#eduId_'+j).val(res.data[j].id);
									$('#orgId_'+j).val(res.data[j].orgId),
									$('#orgName_'+j).val(res.data[j].orgName),
									$('#studentid_'+j).val(res.data[j].studentid),
									$('#startdate_'+j).val(res.data[j].startdate),
									$('#enddate_'+j).val(res.data[j].enddate),								
									//学历
									publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'EDU_DEGREE'} ,"degree_"+j,res.data[j].degree);	
									//学位类型
									publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", 
									{'typeCode' : 'EDU_DEGREETYPE'} ,"degreetype_"+j,res.data[j].degreetype);	
									//学制
									publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", 
									{'typeCode' : 'EDU_SCHOOLEN'} ,"schoollen_"+j,res.data[j].schoollen);	
								}								
						}else{
							layer.msg(res.msg);
						}
					}
				}); 
			}
		
		}else if(elem.index == 3){ //联系方式
			if(!sysUserId){
				judgeIsSaveSm(sysUserId);
			}else{
				if(conta >= 1){
					$("#contactForm").nextAll("form").remove();
				}		
				$.ajax({
					url: application.SERVE_URL+"/sys/smContact/loadAllListBy", //ajax请求地址
					data: {"sysUserId":sysUserId},
					success: function (res) {
						if(res.code==application.REQUEST_SUCCESS){
								//获取长度
								var len = res.data.length;
								//加载DOM
								conta = len -1;
								for(var i = 1; i <= conta ;i++){
									$("#contactForm").after(appendContactForm(i));
									initContact(i);
								}
								//值回填
								for(var j = 0; j < res.data.length ;j++){
									$('#contId_'+j).val(res.data[j].id);
									$('#contact_'+j).val(res.data[j].contact);
									publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'CONTACT_TYPE'} ,"type_"+j,res.data[j].type);	
								}
								
						}else{
							layer.msg(res.msg);
						}
					}
				});
			}
		}else if(elem.index == 2){ //通讯地址
			if(!sysUserId){
				judgeIsSaveSm(sysUserId);
			}else{
				if(addr >= 1){
					$("#addFrom").nextAll("form").remove();
				}		
					$.ajax({
						url: application.SERVE_URL+"/sys/smAddress/loadAllListBy", //ajax请求地址
						data: {"sysUserId":sysUserId},
						success: function (res) {
							if(res.code==application.REQUEST_SUCCESS){
									//获取长度
									var len = res.data.length;
									//加载DOM
									addr = len -1;
									for(var i = 1; i <= addr ;i++){
										$("#addFrom").after(appendAddressForm(i));
										initAddress(i,null);
									}
									//值回填
									for(var j = 0; j < res.data.length ;j++){
										var addresses = new Array();
										addresses.push(res.data[j].country+ "/"+ res.data[j].province +"/"+res.data[j].city);
										initAddress(j,addresses);
										$('#addId_'+j).val(res.data[j].id);
										$('#detail_'+j).val(res.data[j].detail);
										$('#zipcode_'+j).val(res.data[j].zipcode);
										$('#remark_'+j).val(res.data[j].remark);
										publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode",
										{'typeCode' : 'IS_ADDRESS_POST'} ,"ispost_"+j,res.data[j].ispost);
										form.render();
										formSelects.render();
									}
									
							}else{
								layer.msg(res.msg);
							}
						}
					});
			}
		}
	});


		//新增教育经历
		var edu =1;
		$("#addEduinfo").click(function(){
			$("#eduFrom").after(appendEduForm(edu));
			initSchoolInfo(edu);
			edu++;
		}); 
		
		
		//新增地址经历
		var addr =1;
		$("#addAddressinfo").click(function(){
			$("#addFrom").after(appendAddressForm(addr));
			initAddress(addr);
			addr++;

		}); 
		
		
			//新增联系方式
		var conta =1;
		$("#addContactinfo").click(function(){
			$("#contactForm").after(appendContactForm(conta));
			initContact(conta);
			conta++;
		}); 

		//页面打开时，或者基本信息时，点击填充数据
		function initSchoolmateData(){
			
			initSelect();
			publicUtil.selectBase(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'NATION'} ,"nation");
			publicUtil.selectBase(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'USERTYPE'} ,"type");
			publicUtil.selectBase(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'SEX'} ,"sex");
			publicUtil.selectBase(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'IDCARD_TYPE'} ,"cardType");

			//生日日期绑定
			laydate.render({
				elem: '#birthday',
				lang: 'en'
			});
		}

	


   //submit()  绑定提交按钮（基本信息）
			form.on("submit(addSchoolfellow)",function(data){
				var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
				
				var smAdd  = excFormSelectVal(layui.formSelects.value('native_place','val'));
				
				$.ajax({
					url: application.SERVE_URL+"/sys/smSchoolmate/save", //ajax请求地址
					contentType: "application/json",
					data:JSON.stringify({
						id :  $('#schoolmate_id').val() ==null|| $('#schoolmate_id').val() =="" ? null : $('#schoolmate_id').val(),
						sysUserId :$('#sysUserId').val() ==null|| $('#sysUserId').val() =="" ? null : $('#sysUserId').val(),
						name : $('.name').val(),
						sex : $('#sex').val(),
						type: $('#type').val(),
						username : $('#username').val(),
						userType : $('#userType').val(),
						nation : $('#nation').val(),
						cardType : $('#cardType').val(),
						cardNum  : $('.cardNum').val(),
						birthday : $('#birthday').val(),
						smAddress : {
											province: smAdd[0] = undefined ? "" : smAdd[0] ,
											city: smAdd[1] = undefined ? "" : smAdd[1] ,
											district: smAdd[2] = undefined ? "" : smAdd[2]
						}
					}),
					success: function (res) {
						if(res.code==application.REQUEST_SUCCESS){
							top.layer.close(index);
							top.layer.msg(res.msg);	
							layer.closeAll("iframe");
							//刷新父页面
							$("#username").val(res.data.username);
							$("#sysUserId").val(res.data.sysUserId);
							$("#schoolmate_id").val(res.data.id);
							$("#username").attr("readonly","true");
							//此处不刷新
							// location.reload();
						}else{
							layer.msg(res.msg);
						}
					}
				}); 
				return false;
			})
			
		//submit(addUser)  绑定提交按钮（教育经历）
			form.on("submit(addEdu)",function(data){
				var i = getIdBystr($(data.form).find(".mark").attr("id"));
				var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
							$.ajax({
								url: application.SERVE_URL+"/sys/smEducation/save", //ajax请求地址
								contentType: "application/json",
								data:JSON.stringify({
										id : $('#eduId_'+i).val() ==null|| $('#eduId_'+i).val() =="" ? null : $('#eduId_'+i).val(),
										sysUserId :$('#sysUserId').val(),
										sysUserUsername : $('#username').val(),
// 										sysUserId :'67fb5eaf2ee74f2d9ae7d54c7fdaac62',
// 										sysUserUsername : 'wang',
										orgId : $('#orgId_'+i).val(),
										degree : $('#degree_'+i).val(),
										degreetype : $('#degreetype_'+i).val(),
										studentid : $('#studentid_'+i).val(),
										schoollen : $('#schoollen_'+i).val(),
										startdate : $('#startdate_'+i).val(),
										enddate : $('#enddate_'+i).val(),
									}),
								success: function (res) {
									//保存结束后数据回显
									if(res.code==application.REQUEST_SUCCESS){
										var data = res.data;
										$('#eduId_'+i).val(data.id);
										top.layer.close(index);
										top.layer.msg(res.msg);	
										layer.closeAll("iframe");
										//刷新父页面
										//此处不刷新
										// location.reload();
									}else{
										layer.msg(res.msg);
									}
								}
							}); 
							return false;
			})
			//删除教育经历
			form.on("submit(delEdu)",function(data){
			var i = getIdBystr($(data.form).find(".mark").attr("id"));
			
			layer.confirm('确定删除？',{icon:3, title:'提示信息'},function(index){				
				$.ajax({
					url: application.SERVE_URL+"/sys/smEducation/delete", //ajax请求地址
					data:{
						id : $('#eduId_'+i).val()
					},										
					success: function (res) {
						if(res.code==application.REQUEST_SUCCESS){
								layer.close(index);
								$('#eduId_'+i).parents('form').remove();
						}else{
							top.layer.msg(res.msg);
						}
						layer.close(index);	
					}
				});	
			});
			return false;
		})
		
		
		
		
		
			//submit()  绑定提交按钮（联系方式）
			form.on("submit(addContact)",function(data){
				
				var i = getIdBystr($(data.form).find(".mark").attr("id"));
				var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
				$.ajax({
					url: application.SERVE_URL+"/sys/smContact/save", //ajax请求地址
					data:{
							id : $('#contId_'+i).val() ==null|| $('#contId_'+i).val() =="" ? null : $('#contId_'+i).val(),
							sysUserId :$('#sysUserId').val(),
							username : $('#username').val(),
// 							sysUserId :'67fb5eaf2ee74f2d9ae7d54c7fdaac62',
// 							username : 'wang',
							type : $('#type_'+i).val(),
							contact : $('#contact_'+i).val(),
						},
					success: function (res) {
						//保存结束后数据回显
						if(res.code==application.REQUEST_SUCCESS){
							var data = res.data;
							$(".contact").val(data.contact);
							$('#contId_'+i).val(data.id);
							publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'CONTACT_TYPE'} ,"type_"+i,data.type);	
							top.layer.close(index);
							top.layer.msg(res.msg);	
							layer.closeAll("iframe");
							//刷新父页面
							//此处不刷新
							// location.reload();
						}else{
							layer.msg(res.msg);
						}
					}
				}); 
				return false;
	
			});
			
			//删除联系方式
			form.on("submit(delContact)",function(data){
				var i = getIdBystr($(data.form).find(".mark").attr("id"));
				
				layer.confirm('确定删除？',{icon:3, title:'提示信息'},function(index){				
					$.ajax({
						url: application.SERVE_URL+"/sys/smContact/delete", //ajax请求地址
						data:{
							id : $('#contId_'+i).val()
						},										
						success: function (res) {
							if(res.code==application.REQUEST_SUCCESS){
									layer.close(index);
									$('#contId_'+i).parents('form').remove()
									// console.log($("#contact").reload());
									// .eq(index).find("iframe")[0];

									// location.reload();
// 									element.init();
// 									$(".layui-tab-title [lay-id='0']").removeClass("layui-this");
// 									$(".layui-tab-title [lay-id='3']").addClass("layui-this");
// 									element.tabChange('info', 3);
// 									element.tabChange('info', 2);
// // 									element.init();	
// // 									// var layid = location.hash.replace(/^#info=/, '3');
// 									element.render('tab','contact');
							}else{
								top.layer.msg(res.msg);
							}
							layer.close(index);	
						}
					});	
				});
				return false;
	
			})
		
			//submit()  绑定提交按钮（通讯地址）
			form.on("submit(addAddress)",function(data){
				var i = getIdBystr($(data.form).find(".mark").attr("id"));
				var address = excFormSelectVal(layui.formSelects.value('address_'+i,'val'));
				var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
				$.ajax({
					url: application.SERVE_URL+"/sys/smAddress/save", //ajax请求地址
					contentType: "application/json",
					data:JSON.stringify({
							id : $('#addId_'+i).val() ==null|| $('#addId_'+i).val() =="" ? null : $('#addId_'+i).val(),
							sysUserId :$('#sysUserId').val(),
							username : $('#username').val(),
// 							sysUserId :'67fb5eaf2ee74f2d9ae7d54c7fdaac62',
// 							username : 'wang',
							detail : $('#detail_'+i).val(),
							zipcode : $('#zipcode_'+i).val(),
							remark : $('#remark_'+i).val(),
							ispost : $('#ispost_'+i).val(),
							country : address[0] = undefined ? "" : address[0],
							province : address[1] = undefined ? "" : address[1],
							city : address[2] = undefined ? "" : address[2],
							district : address[3] = undefined ? "" : address[3],
						}),
					success: function (res) {
						//保存结束后数据回显
						if(res.code==application.REQUEST_SUCCESS){
							var data = res.data;
							$('#addId_'+i).val(data.id);
							top.layer.close(index);
							top.layer.msg(res.msg);	
							layer.closeAll("iframe");
							//刷新父页面
							//此处不刷新
							// location.reload();
						}else{
							layer.msg(res.msg);
						}
					}
				}); 
				return false;
			})
		
			//删除通讯地址
			form.on("submit(delAddress)",function(data){
				var i = getIdBystr($(data.form).find(".mark").attr("id"));
				
				layer.confirm('确定删除？',{icon:3, title:'提示信息'},function(index){				
					$.ajax({
						url: application.SERVE_URL+"/sys/smAddress/delete", //ajax请求地址
						data:{
							id : $('#addId_'+i).val()
						},										
						success: function (res) {
							if(res.code==application.REQUEST_SUCCESS){
									layer.close(index);
									$('#addId_'+i).parents('form').remove();
							}else{
								top.layer.msg(res.msg);
							}
							layer.close(index);	
						}
					});	
				});
				return false;
	
			})
		
		
		//判断是否填写基本信息
		function judgeIsSaveSm(sysUserId){
			if(null == sysUserId || "" == sysUserId){
					layer.msg("请先填写并保存基础信息", {icon: 1,time: 800},function(){
						location.reload();
					}); 
			}
		}
		

		//教育经历数据填充
		function initSchoolInfo(i){
			//学历
			publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'EDU_DEGREE'} ,"degree_"+i);	
			//学位类型
			publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'EDU_DEGREETYPE'} ,"degreetype_"+i);	
			//学制
			publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'EDU_SCHOOLEN'} ,"schoollen_"+i);	
			//生日日期绑定
			laydate.render({
				elem: '#startdate_'+i,
				lang: 'en'
			});
			//生日日期绑定
			laydate.render({
				elem: '#enddate_'+i,
				lang: 'en'
			});
		}
		
		//籍贯联动下拉框处理
		function initSelect(){
			$.ajax({
				url: application.SERVE_URL+'/sys/sysarea/getFormSelectDatas', //ajax请求地址
				success: function (rs) {
					formSelects.data('native_place', 'local', {
							arr: rs.data.children,
							linkage: true,
							linkageWidth: 180
					})
					if(nation_place_datas != null && nation_place_datas != '' && nation_place_datas != 'undefined'){
						layui.formSelects.value('native_place', nation_place_datas);								
					}
					
				}
			});
		}	
		
		
		//联系方式初始化
		function initContact(i){
			
			publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'CONTACT_TYPE'} ,"type_"+i);	
		}	
	
	
		//通讯地址初始化
		function initAddress(i,addresses){
			$.ajax({
				url: application.SERVE_URL+'/sys/sysarea/getAllFormSelectDatas', //ajax请求地址
				success: function (rs) {
					formSelects.data('address_'+i, 'local', {
							arr: rs.data.children,
							linkage: true,
							linkageWidth: 180
					})
					if(addresses != null && addresses != '' && addresses != 'undefined'){
						layui.formSelects.value('address_'+i, addresses);								
					}
					
				}
			});
			formSelects.config('address_'+ i, {

			});	
			//是否邮寄地址
			publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'IS_ADDRESS_POST'} ,"ispost_"+i);	
		}					
		
		//机构选择
		function selectOrg(idStr){
			var index = layui.layer.open({
				type: 2,
				title: '机构选择',
				shadeClose: true,
				shade: 0.8,
				area: ['280px', '65%'],
				// content: '../views/module/system/menu/menuselect.html',
				content: 'orgselect.html',
				success : function(layero, index){
					//
	// 				// 获取子页面的iframe
					var iframe = window['layui-layer-iframe' + index];
	// 				// 向子页面的全局函数child传参
					iframe.child(idStr);				
					setTimeout(function(){
							layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
									tips: 3
							});
					},500)											
				},
			})
		}
		$(document).on('click','.orgName',function(){
			selectOrg(getIdBystr($(this).attr("id")));
		})
	
	
		//获取id
		function getIdBystr(t){
			var strs = t.split("_");
			return strs[strs.length-1];
		}
	
		//处理三联菜单的内容 如 ： ["102/10201/1020101"]
		function excFormSelectVal(arr){
			var val = arr[0];
			var arrs =  new Array();
			arrs = val.split("/")
			return arrs;
		}
		
		//自定义验证规则
		form.verify({
			user_exist: function(value){
				var message="";
				//判断是否是编辑
				if($("#schoolmate_id").val()){
					return;
				}else{
					$.ajax({
						url: application.SERVE_URL+'/sys/sysuser/checkUserExist',
						async: false,
						method : 'post',
						data:{
							username: value
						},											
						success: function (res) {
							if(res.code==application.DATA_USED){
								message = res.msg;
							}else{
								return;
							}
						}
					})
				}
				return message;
			}
		});

  
		
/*Dom 拼接*/
/* 教育经历表单 */
function appendEduForm(i){
	var addEdu = "<form class='layui-form'  style='width:100%;'> " +
						 "<fieldset class='layui-elem-field site-demo-button' style='padding:15px;'> " +
							"<input type='hidden' class='layui-input mark' id='eduId_"+i+"'   >				 " +		
							"<div class='layui-row'> " +
								"<div class='magb15  layui-col-xs12'> " +
									"<label class='layui-form-label'>学籍信息</label> " +
									"<div class='layui-input-block'> " +
										"<input type='text' class='layui-input orgId' id = 'orgId_"+ i+"' style='display: none;'>"+
										"<input type='text' class='layui-input orgName' id = 'orgName_"+ i+"' lay-verify='required'>	"+
									"</div> " +
								"</div> " +
// 								"<div class='magb15  layui-col-xs3'> " +
// 									"<label class='layui-form-label'>班级</label> " +
// 									"<div class='layui-input-block'> " +
// 										"<input type='text' class='layui-input ' id='class_"+i+"'  placeholder='班级'> " +
// 									"</div> " +
// 								"</div> " +
							"</div> " +
							"<div class='layui-row'> " +
								"<div class='magb15  layui-col-xs3'> " +
									"<label class='layui-form-label'>学历</label> " +
									"<div class='layui-input-block'> " +
										"<select  id='degree_"+i+"'  > " +
										"</select> " +
									"</div> " +
								"</div> " +
								"<div class='magb15  layui-col-xs3'> " +
									"<label class='layui-form-label'>学位类型</label> " +
									"<div class='layui-input-block'> " +
										"<select  id='degreetype_"+i+"' > " +
										"</select> " +
									"</div> " +
								"</div> " +
								"<div class='magb15  layui-col-xs3'> " +
									"<label class='layui-form-label'>学号</label> " +
									"<div class='layui-input-block'> " +
										"<input type='text' class='layui-input ' id='studentid_"+i+"'  " +
											 "  placeholder='请输入学号'> " +
									"</div> " +
								"</div> " +
								"<div class='magb15 layui-col-xs3'> " +
									"<label class='layui-form-label'>学制</label> " +
									"<div class='layui-input-block'> " +
										"<select  id='schoollen_"+i+"'  > " +
										"</select> " +
									"</div> " +
								"</div> " +
							"</div> " +
							"<div class='layui-row'> " +
								"<div class='magb15  layui-col-xs6'> " +
									"<label class='layui-form-label'>入学日期</label> " +
									"<div class='layui-input-block'> " +
										"<input type='text' class='layui-input ' id='startdate_"+i+"'  > " +
									"</div> " +
								"</div> " +
								"<div class='magb15  layui-col-xs6' id = 'addedu'> " +
									"<label class='layui-form-label'>毕业日期</label> " +
									"<div class='layui-input-block'> " +
										"<input type='text' class='layui-input' id='enddate_"+i+"' > " +
									"</div> " +
								"</div> " +
							"</div> " +
						"</fieldset>  " +				
						 "<div class='layui-layer-btn'> " +
							"<button class='layui-btn layui-btn-danger' lay-submit='' lay-filter='delEdu'>删除</button> " +
							"<button class='layui-btn' lay-submit='' lay-filter='addEdu'>保存</button>" +
						"</div> " +
					"</form> " ;
					return addEdu;
}

	/*地址*/
function appendAddressForm(i){

	var Address = "<form  class='layui-form' style='width:100%;'> " +
				"<fieldset  class='layui-elem-field site-demo-button' style='padding:15px;'> " +
				"<input type='hidden' class='layui-input mark' id='addId_"+i+"'   > " +
				"<div class='layui-row'> " +
				"<div class='magb15  layui-col-xs6'> " +
				"<label class='layui-form-label'>地址</label> " +
				"<div class='layui-input-block'> " +
				"<select  xm-select='address_"+i+"' xm-select-radio=''> " +
				"<option value=''>请选择地址</option> " +
				"</select> " +
				"</div> " +
				"</div> " +
				"<div class='magb15  layui-col-xs6'> " +
				"<label class='layui-form-label'>详细地址</label> " +
				"<div class='layui-input-block'> " +
				"<input type='text' class='layui-input '  id='detail_"+i+"'  lay-verify='required' placeholder='请输入详细地址'> " +
				"</div> " +
				"</div> " +
				"</div> " +
				"<div class='layui-row'> " +
				"<div class='magb15  layui-col-xs3'> " +
				"<label class='layui-form-label'>邮编</label> " +
				"<div class='layui-input-block'> " +
				"<input type='text' class='layui-input ' id='zipcode_"+i+"'  lay-verify='required' placeholder='请输入邮编'> " +
				"</div> " +
				"</div> " +
				"<div class='magb15  layui-col-xs3'> " +
				"<label class='layui-form-label'>备注</label> " +
				"<div class='layui-input-block'> " +
				"<input type='text' class='layui-input'  id='remark_"+i+"'  lay-verify='required' placeholder='请输入备注'> " +
				"</div> " +
				"</div> " +
				"<div class='magb15  layui-col-xs6'> " +
				"<label class='layui-form-label'>邮寄地址</label> " +
				"<div class='layui-input-block'> " +
				"<select  id='ispost_"+i+ "' lay-verify='required'> " +
				"</select> " +
				"</div> " +
				"</div> " +
				"</div> " +
				"</fieldset> " +
				"<div class='layui-layer-btn '> " +
				"<button class='layui-btn layui-btn-danger' lay-submit='' lay-filter='delAddress'>删除</button> " +
				"<button class='layui-btn' lay-submit='' lay-filter='addAddress'>保存</button>" +
				"</div> " +
				"</form>";
				
				return Address;
}


/*联系方式*/

function appendContactForm(i){
	var addContact = "<form   class='layui-form' style='width:100%;'> " + 
				"<fieldset class='layui-elem-field site-demo-button' style='padding:15px;'> " + 
				"<input type='hidden' class='layui-input mark' id='contId_"+i+ "'   > " + 
				"<div class='layui-row'> " + 
				"<div class='magb15  layui-col-xs4'> " + 
				"<label class='layui-form-label'>联系类型</label> " + 
				"<div class='layui-input-block'> " + 
				"<select  id='type_"+i+"' lay-verify='required'> " + 
				"</select> " + 
				"</div> " + 
				"</div> " + 
				"<div class='magb15  layui-col-xs8'> " + 
				"<label class='layui-form-label'>联系内容</label> " + 
				"<div class='layui-input-block'> " + 
				"<input type='text' class='layui-input'  lay-verify='required' id='contact_"+i+"' placeholder='请输入内容'> " + 
				"</div> " + 
				"</div> " + 
				"</div> " + 
				"</fieldset> " + 
				"<div class='layui-layer-btn '> " + 
				"<button class='layui-btn layui-btn-danger' lay-submit='' lay-filter='delContact'>删除</button>" + 
				"<button class='layui-btn' lay-submit='' lay-filter='addContact'>保存</button>" + 
				"</div> " + 
				"</form> "	;
				
				return addContact;
}

})

