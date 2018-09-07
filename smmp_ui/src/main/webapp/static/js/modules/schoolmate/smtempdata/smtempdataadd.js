/**
 * @autor syp
 * @content 字典增加页面js
 * @returns
 * @Time 2018-08-02
 */
layui.config({
	base : "../../../../static/js/"
}).extend({
	"validparam"  : "validparam",
	"publicUtil" : "publicUtil",
	"application" : "application"
}) 
layui.use(['form','layer','application','laydate','validparam','publicUtil'],function(){
    var form = layui.form,
    	application = layui.application,
    	validparam = layui.validparam,
		laydate = layui.laydate,
    	publicUtil = layui.publicUtil,
        layer = parent.layer,
        $ = layui.jquery;
    
	laydate.render({
		elem: '#startdate',
		lang: 'en'
	});
	laydate.render({
		elem: '#enddate',
		lang: 'en'
	});
	laydate.render({
		elem: '#birthday',
		lang: 'en'
	});
	
	function formEdit(FormDatas){
		if(FormDatas != ''){
			var data = FormDatas;
			$(".id").val(data.id);
			$(".name").val(data.name);
			$(".sex").val(data.sex);
			$(".birthday").val(data.birthday);
			$(".cardNum").val(data.cardNum);
			$(".cardType").val(data.cardType);
			$(".type").val(data.type);
			$(".nativePlace").val(data.nativePlace);
			$(".nation").val(data.nation);
			$(".address").val(data.address);
			$(".school").val(data.school);
			$(".college").val(data.college);
			$(".academy").val(data.academy);
			$(".series").val(data.series);
			$(".specialty").val(data.specialty);
			$(".smclass").val(data.smclass);
			$(".degree").val(data.degree);
			$(".degreetype").val(data.degreetype);
			$(".studentid").val(data.studentid);
			$(".schoollen").val(data.schoollen);
			$(".startdate").val(data.startdate);
			$(".enddate").val(data.enddate);
			$(".email").val(data.email);
			$(".phone").val(data.phone);
			$(".remark").val(data.remark);
		}else{
			return false;
		}
	}

	
	/**
	 * 表单回显
	 */
	formEdit(parent.editFormData);
	
    form.on("submit(addSmTemp)",function(){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        
		$.ajax({
			url: application.SERVE_URL+"/sys/smSchoolmateTemp/save", //ajax请求地址
			type : 'post',
			contentType: "application/json",
			data:JSON.stringify({
				id : $(".id").val() ==null|| $(".id").val() =="" ? null : $(".id").val(),
				name : $(".name").val(),
				sex : $(".sex").val(),
				birthday : $(".birthday").val(),
				cardNum : $(".cardNum").val(),
				cardType : $(".cardType").val(),
				type : $(".type").val(),
				nativePlace : $(".nativePlace").val(),
				nation : $(".nation").val(),
				address : $(".address").val(),
				school : $(".school").val(),
				college : $(".college").val(),
				academy : $(".academy").val(),
				series : $(".series").val(),
				specialty : $(".specialty").val(),
				smclass : $(".smclass").val(),
				degree : $(".degree").val(),
				degreetype : $(".degreetype").val(),
				studentid : $(".studentid").val(),
				schoollen : $(".schoollen").val(),
				startdate : $(".startdate").val(),
				enddate : $(".enddate").val(),
				email : $(".email").val(),
				phone : $(".phone").val(),
				remark : $(".remark").val(),
			}),
			success: function (res) {
				if(res.code==application.REQUEST_SUCCESS){
				 	top.layer.close(index);
		            top.layer.msg(res.msg);	
		            layer.closeAll("iframe");
		            //刷新父页面
		            parent.location.reload();
				}else{
					layer.msg(res.msg);
				}
			}
		}); 
        return false;
    })
	
	$("#close").click(function(){
		layer.closeAll("iframe");
		//刷新父页面
		parent.location.reload();	
	})
})