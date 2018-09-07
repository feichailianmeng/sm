/**
 * @autor syp
 * @content 校友会你页面js
 * @returns
 * @Time 2018-08-02
 */
layui.config({
	base: '../../../../static/js/' //此处路径请自行处理, 可以使用绝对路径
}).extend({
/* 	formSelects: 'formSelects-v4', */
	"application" : "application"
});

layui.use(['jquery','form','layer','laydate',/* 'formSelects', */'publicUtil','upload','application','element'],function(){
    var form = layui.form,
		$ = layui.jquery,
/* 		formSelects = layui.formSelects, */
    publicUtil  = layui.publicUtil,
		application = layui.application,
		layer =layui.layer,
		element = layui.element,
	  laydate = layui.laydate,
		username = parent.parent.username,
		upload = layui.upload;
	
	form.on('select(province)', function(data){
		publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysarea/loadChildrenByParent", {'parentId' : data.value} ,"city",null,"id","remark");
	})
		
	//页面打开时，或者基本信息时，点击填充数据
	function initAssociationData()
	{
		if(parent.editFormData)
		{
			$("#associationid").val(parent.editFormData.id);
			$("#name").val(parent.editFormData.name);
			$("#createdate").val(parent.editFormData.createdate);
			$("#master").val(parent.editFormData.master);
			$("#phone").val(parent.editFormData.phone);
			$("#emaiil").val(parent.editFormData.emaiil);
			$("#wcaccount").val(parent.editFormData.wcaccount);
			$("#wcigroup").val(parent.editFormData.wcigroup);
			$("#address").val(parent.editFormData.address);
			$("#summary").val(parent.editFormData.summary);
			$("#constitution").val(parent.editFormData.constitution);
			$("#photo").attr('src', application.SERVE_URL+"/"+parent.editFormData.logo);
			$("#photoPath").val(parent.editFormData.logo);
			//$("#type").val(parent.editFormData.province);
			//$("#province").val(parent.editFormData.province);
			//$("#city").val(parent.editFormData.city);
		}
		publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysarea/loadChildrenByParent", {'parentId' : 1} ,"province",parent.editFormData?parent.editFormData.province:null,"id","remark",function(){
			publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysarea/loadChildrenByParent", {'parentId' : parent.editFormData?parent.editFormData.province:-1} ,"city",parent.editFormData?parent.editFormData.city:null,"id","remark");
		});
		
		laydate.render({
			elem: '#createdate',
			lang: 'en'
		});
	}
	initAssociationData();	
	
	var uploadInst = upload.render({
		elem: '#selectphoto'
		,url: application.SERVE_URL+'/sys/sysuser/uploadimg'
		,choose: function(obj){
			//预读本地文件示例，不支持ie8
			obj.preview(function(index, file, result){
				$('#photo').attr('src', result); //图片链接（base64）
			});
		}
		,bindAction: '#addUser'
		,done: function(res,index, upload){
			$('#photoPath').text(res.data);
			//如果上传失败
			if(res.code > 0){
				return layer.msg('上传成功');							
			}
			//上传成功
		}
		,error: function(){
			//演示失败状态，并实现重传
			var photoPath = $('#photoPath');
			photoPath.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
			photoPath.find('.demo-reload').on('click', function(){
				uploadInst.upload();
			});
		}
	});
	
   //submit(addUser)  绑定提交按钮（基本信息）
    form.on("submit(addSchoolfellow)",function(data){
        //弹出loading
      	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
      	var parentId="root";
      	if(parent.editFormData)
      	{
      		parentId=parent.editFormData.parentId;
      	}
      	else
      	{
      		var selectData=parent.layui.treeGrid.checkStatus('associationList').data;
      		if(selectData.length>0)
	      	{
	      		parentId=selectData[0].id;
	      	}
      	}
		$.ajax({
			url: application.SERVE_URL+'/sys/smAssociation/save', //ajax请求地址
			type: "POST",
			data:{
				 id:$("#associationid").val(),
				 parentId:parentId,
				 name: $("#name").val(),
				 type :$("#type").val(),
				 logo :$('#photoPath').text(),
				 createdate :$("#createdate").val(),
				 master :$("#master").val(),
				 phone :$("#phone").val(),
				 emaiil :$("#emaiil").val(),
				 wcaccount :$("#wcaccount").val(),
				 wcigroup :$("#wcigroup").val(),
				 province :$("#province").val(),
				 city :$("#city").val(),
				 address :$("#address").val()
			},			
			success: function (data) {
				var res = $("#associationid").val() ==null|| $("#associationid").val() =="" ? "新增":"修改" ;
				if(data == "success"){
					top.layer.close(index);
								top.layer.msg("校友会信息" + res + "成功");
								parent.location.reload();	
				}else{
					top.layer.msg("校友会信息" + res + "失败！");
				}
			},
			error: function(data){
				top.layer.msg("校友会信息" + res + "失败！");
			}
		}); 
        return false;
    })
})