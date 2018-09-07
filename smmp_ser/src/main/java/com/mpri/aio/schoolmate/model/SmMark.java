package com.mpri.aio.schoolmate.model;
import com.mpri.aio.base.model.DataEntity; 

 /**   
 *  
 * @Description:  校友标签管理
 * @Author:       LZQ
 * @project       AIO   
 * @CreateDate:   Fri Aug 24 11:05:42 CST 2018
 * @Version:      v_1.0
 *    
 */
public class SmMark extends DataEntity<SmMark> {

	private static final long serialVersionUID = 1535079943000L;
	
	private String parentId;
	private String parentIds;
	private String name;
	private String code;
	private String type;
	private String useable;

	
	public String getParentId() {
		return this.parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}	
	public String getParentIds() {
		return this.parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}	
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public String getUseable() {
		return this.useable;
	}
	public void setUseable(String useable) {
		this.useable = useable;
	}	

}
