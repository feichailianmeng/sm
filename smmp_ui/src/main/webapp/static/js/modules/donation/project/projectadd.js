/**
 * @autor syp
 * @content 捐赠项目页面js
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
	
	//页面打开时，或者基本信息时，点击填充数据
	function initAssociationData()
	{
		if(parent.editFormData)
		{
			$("#projectid").val(parent.editFormData.id);
			$("#name").val(parent.editFormData.name);
			$("#startdate").val(parent.editFormData.startdate);
			$("#enddate").val(parent.editFormData.enddate);
			$("#targetMoney").val(parent.editFormData.targetMoney);
			$("#master").val(parent.editFormData.master);
			$("#phone").val(parent.editFormData.phone);
			$("#email").val(parent.editFormData.email);
			$("#summary").val(parent.editFormData.summary);
			$("#content").val(parent.editFormData.content);
			$("#photo").attr('src', application.SERVE_URL+"/"+parent.editFormData.pic);
			$("#photoPath").text(parent.editFormData.pic);
			
			$.ajax({
				url: application.SERVE_URL + '/sys/donDonationFile/list', //ajax请求地址
				type: "POST",
				success: function (data) {
					var files=data.data;
					for(var i=0;i<files.length;i++)
					{
						$('#filePath').append("<div class=\"layui-field-box\"><span class=\"uploaded-filename\" data-filename=\""+files[i].filepath+"\">"+files[i].filepath+"<span><button type=\"button\" class=\"layui-btn layui-btn-danger layui-btn-sm delete-uploaded-file\"><i class=\"layui-icon\">&#xe640;</i></button></div>");
					}
				},
				error: function(data){
					var result=data.responseJSON;
					top.layer.msg(result.msg+"("+result.code+")");
				}
			}); 
		}
		
		publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'PROJECT_TYPE'} ,"type",parent.editFormData?parent.editFormData.type:null);
		
		laydate.render({
			elem: '#startdate',
			lang: 'en'
		});
		laydate.render({
			elem: '#enddate',
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
	
	var uploadFile = upload.render({
		elem: '#selectfile'
		,url: application.SERVE_URL+'/sys/sysuser/uploadimg'
		,choose: function(obj){
			//预读本地文件示例，不支持ie8
			obj.preview(function(index, file, result){
				//$('#file').attr('src', result); //图片链接（base64）
			});
		}
		,bindAction: '#addUser'
		,done: function(res,index, upload){
			//如果上传失败
			if(res.code > 0){
				$('#filePath').append("<div class=\"layui-field-box\"><span class=\"uploaded-filename\" data-filename=\""+res.data+"\">"+res.data+"<span><button type=\"button\" class=\"layui-btn layui-btn-danger layui-btn-sm delete-uploaded-file\"><i class=\"layui-icon\">&#xe640;</i></button></div>");
				return layer.msg('上传成功');
			}
			//上传成功
		}
		,error: function(){
			//演示失败状态，并实现重传
			var filePath = $('#filePath');
			filePath.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
			filePath.find('.demo-reload').on('click', function(){
				uploadInst.upload();
			});
		}
	});
	
	$(document).on('click', '.delete-uploaded-file', function () {
		$(this).parent().parent().parent().remove();
	});
	
   //submit(addUser)  绑定提交按钮（基本信息）
    form.on("submit(addProject)",function(data){
			$(".uploaded-filename").each(function(){
				$("#filesname").val($("#filesname").val()+";"+$(this).data("filename"))
			})
        //弹出loading
      	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
			$.ajax({
				url: application.SERVE_URL+'/sys/donProject/save', //ajax请求地址
				type: "POST",
				data:{
					 id:$("#projectid").val(),
					 name: $("#name").val(),
					 type :$("#type").val(),
					 startdate :$("#startdate").val(),
					 enddate :$("#enddate").val(),
					 targetMoney:$("#targetMoney").val(),
					 master :$("#master").val(),
					 phone :$("#phone").val(),
					 email :$("#email").val(),
					 summary :$("#summary").val(),
					 content :$("#content").val(),
					 pic :$('#photoPath').text(),
					 files:$("#filesname").val().length>0?$("#filesname").val().substring(1):""
				},			
				success: function (data) {
					if(data.code==application.REQUEST_SUCCESS){
						top.layer.close(index);
						top.layer.msg(data.msg);
						layer.closeAll("iframe");
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
	
	function closeSelf()
	{
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	}
	$("#close").click(closeSelf)
})