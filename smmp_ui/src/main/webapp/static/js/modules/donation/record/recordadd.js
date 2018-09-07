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
		upload = layui.upload;
	
	//页面打开时，或者基本信息时，点击填充数据
	function initAssociationData()
	{
		if(parent.editFormData)
		{
			$("#recordid").val(parent.editFormData.id);
			$("#name").val(parent.editFormData.name);
			$("#phone").val(parent.editFormData.phone);
			$("#email").val(parent.editFormData.email);
			$("#money").val(parent.editFormData.money);
			$("#donationName").val(parent.editFormData.donationName);
			$("#time").val(parent.editFormData.time);
			$("#address").val(parent.editFormData.address);
		}
		
		publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'DONATION_TYPE'} ,"donType",parent.editFormData?parent.editFormData.donType:null);
		publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'BOOLEAN_TYPE'} ,"isCertificate",parent.editFormData?parent.editFormData.isCertificate:null);
		publicUtil.selectBaseAndSetVal(application.SERVE_URL+"/sys/sysdict/getByTypeCode", {'typeCode' : 'BOOLEAN_TYPE'} ,"isInvoice",parent.editFormData?parent.editFormData.isInvoice:null);
		
		laydate.render({
			elem: '#time',
			lang: 'en'
		});
	}
	initAssociationData();	
	
   //submit(addUser)  绑定提交按钮（基本信息）
    form.on("submit(addRecord)",function(data){
        //弹出loading
      	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
		$.ajax({
			url: application.SERVE_URL+'/sys/donRecord/save', //ajax请求地址
			type: "POST",
			data:{
				 id:$("#recordid").val(),
				 donProjectId:parent.projectObj.data.id,
				 name: $("#name").val(),
				 donType :$("#donType").val(),
				 phone :$("#phone").val(),
				 email :$("#email").val(),
				 money :$("#money").val(),
				 moneyType :$("#moneyType").val(),
				 donationName :$("#donationName").val(),
				 time :$("#time").val(),
				 isCertificate :$("#isCertificate").val(),
				 isInvoice :$("#isInvoice").val(),
				 address :$("#address").val()
			},			
			success: function (data) {
				if(data.code==application.REQUEST_SUCCESS){
					top.layer.close(index);
					top.layer.msg(data.msg);
					closeSelf();
					//刷新父页面
					parent.donationTableIns.reload();
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