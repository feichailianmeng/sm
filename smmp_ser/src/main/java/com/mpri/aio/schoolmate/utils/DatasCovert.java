package com.mpri.aio.schoolmate.utils;

import java.util.ArrayList;
import java.util.List;

import com.mpri.aio.schoolmate.vo.FormSelectDatas;

/**
 * 数据转换
 * @author Administrator
 *
 */
public class DatasCovert {

	/**
	 * 转换成formSelect 所需数据(籍贯)
	* <p>Title: setRootFormSelectData</p>  
	* <p>Description: </p>  
	* @param formSelectDatas
	* @param root
	* @return
	 */
	public static FormSelectDatas setRootFormSelectData(List<FormSelectDatas> formSelectDatas,String root) {
		FormSelectDatas formSelectData = new FormSelectDatas();
		for(FormSelectDatas fd : formSelectDatas) {
			if(fd.getParentId().equalsIgnoreCase(root)) {
				formSelectData.setChildren(covertFormSelectDatas(formSelectDatas,fd.getValue()));
				break;
			}
		}
		return formSelectData;
	}
	
	/**
	 * 数据处理
	* <p>Title: covertFormSelectDatas</p>  
	* <p>Description: </p>  
	* @param formSelectDatas
	* @param parentId
	* @return
	 */
	private static List<FormSelectDatas> covertFormSelectDatas(List<FormSelectDatas> formSelectDatas,String parentId){
		List<FormSelectDatas> fds =  new ArrayList<FormSelectDatas>();
		for(FormSelectDatas fd : formSelectDatas) {
			if(fd.getParentId().equals(parentId)) {
				fd.setChildren(covertFormSelectDatas(formSelectDatas,fd.getValue()));
				fds.add(fd);
			}
		}
		return fds;
	}
	
	/**
	 * 转换成formSelect 所需数据（住址）
	* <p>Title: setRootFormSelectData</p>  
	* <p>Description: </p>  
	* @param formSelectDatas
	* @param root
	* @return
	 */
	public static FormSelectDatas setRootFormSelectDatas(List<FormSelectDatas> formSelectDatas,String root) {
		FormSelectDatas all = new FormSelectDatas();
		List<FormSelectDatas> datas = new ArrayList<FormSelectDatas>();
		for(FormSelectDatas fd : formSelectDatas) {
			FormSelectDatas formSelectData = new FormSelectDatas();
			if(fd.getParentId().equalsIgnoreCase(root)) {
				formSelectData.setValue(fd.getValue());
				formSelectData.setName(fd.getName());
				formSelectData.setChildren(covertFormSelectDatas(formSelectDatas,fd.getValue()));
				datas.add(formSelectData);
			}
		}
		all.setChildren(datas);
		return all;
	}
	
	
}
