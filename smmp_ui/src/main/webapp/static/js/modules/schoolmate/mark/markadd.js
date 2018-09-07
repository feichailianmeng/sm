/**
 * @autor syp
 * @content 角色列表页面js
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
	function initSchoolmateData()
	{
		if(parent.editFormData)
		{
			$("#markid").val(parent.editFormData.id);
			$("#name").val(parent.editFormData.name);
			$("#code").val(parent.editFormData.code);
			$("input:radio[value="+parent.editFormData.useable+"]").val(parent.editFormData.useable);
		}
	}
	initSchoolmateData();	
	
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
      		var selectData=parent.layui.treeGrid.checkStatus('markList').data;
      		if(selectData.length>0)
	      	{
	      		parentId=selectData[0].id;
	      	}
      	}
		$.ajax({
			url: application.SERVE_URL+'/sys/smMark/save', //ajax请求地址
			type: "POST",
			data:{
				 id:$("#markid").val(),
				 parentId:parentId,
				 name: $("#name").val(),
				 code :$("#code").val(),
				 useable:$("input:radio:checked").val()
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
})