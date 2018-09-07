package com.mpri.aio.donation.model;
import com.mpri.aio.base.model.DataEntity;

 /**   
 *  
 * @Description:  捐赠项目附件
 * @Author:       LZQ
 * @project       AIO   
 * @CreateDate:   Wed Aug 29 15:34:58 CST 2018
 * @Version:      v_1.0
 *    
 */
public class DonDonationFile extends DataEntity<DonDonationFile> {

	private static final long serialVersionUID = 1535528098833L;
	
	private String donDonationId;
	private String filepath;
	private Integer sequence;
	private Integer count;
	private String state;
	private String remark;
	
	public String getDonDonationId() {
		return this.donDonationId;
	}
	public void setDonDonationId(String donDonationId) {
		this.donDonationId = donDonationId;
	}	
	public String getFilepath() {
		return this.filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}	
	public Integer getSequence() {
		return this.sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}	
	public Integer getCount() {
		return this.count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}	
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}	
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}	

}
