/**
 * 
 */
package com.protocol.safemon.sys;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class FindPar {
	
	

	private Integer fenqu=0;
	private String stcd;
	private String bianhao;  //积水站编号(备用字段)
	private String Name;
	private Integer  Online;
	private Integer  selectType=0;
	private Date  tm;
	private Date  begintm;
	private Date  endtm;	
	private Integer page;
	private Integer Count=0;
	private Integer value_th =0;
	
	/**
	 * 
	 */
	public FindPar() {
	 
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param fenqu
	 * @param stcd
	 * @param bianhao
	 * @param name
	 * @param online
	 * @param selectType
	 * @param tm
	 * @param begintm
	 * @param endtm
	 * @param page
	 * @param count
	 * @param value_th
	 */
	public FindPar(Integer fenqu, String stcd, String bianhao, String name, Integer online, Integer selectType, Date tm,
			Date begintm, Date endtm, Integer page, Integer count, Integer value_th) {
		super();
		this.fenqu = fenqu;
		this.stcd = stcd;
		this.bianhao = bianhao;
		Name = name;
		Online = online;
		this.selectType = selectType;
		this.tm = tm;
		this.begintm = begintm;
		this.endtm = endtm;
		this.page = page;
		Count = count;
		this.value_th = value_th;
	}

	/**
	 * @return the fenqu
	 */
	public Integer getFenqu() {
		return fenqu;
	}

	/**
	 * @param fenqu the fenqu to set
	 */
	public void setFenqu(Integer fenqu) {
		this.fenqu = fenqu;
	}

	/**
	 * @return the stcd
	 */
	public String getStcd() {
		return stcd;
	}

	/**
	 * @param stcd the stcd to set
	 */
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	/**
	 * @return the bianhao
	 */
	public String getBianhao() {
		return bianhao;
	}

	/**
	 * @param bianhao the bianhao to set
	 */
	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the online
	 */
	public Integer getOnline() {
		return Online;
	}

	/**
	 * @param online the online to set
	 */
	public void setOnline(Integer online) {
		Online = online;
	}

	/**
	 * @return the selectType
	 */
	public Integer getSelectType() {
		return selectType;
	}

	/**
	 * @param selectType the selectType to set
	 */
	public void setSelectType(Integer selectType) {
		this.selectType = selectType;
	}

	/**
	 * @return the tm
	 */
	public Date getTm() {
		return tm;
	}

	/**
	 * @param tm the tm to set
	 */
	public void setTm(Date tm) {
		this.tm = tm;
	}

	/**
	 * @return the begintm
	 */
	public Date getBegintm() {
		return begintm;
	}

	/**
	 * @param begintm the begintm to set
	 */
	public void setBegintm(Date begintm) {
		this.begintm = begintm;
	}

	/**
	 * @return the endtm
	 */
	public Date getEndtm() {
		return endtm;
	}

	/**
	 * @param endtm the endtm to set
	 */
	public void setEndtm(Date endtm) {
		this.endtm = endtm;
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return Count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		Count = count;
	}

	/**
	 * @return the value_th
	 */
	public Integer getValue_th() {
		return value_th;
	}

	/**
	 * @param value_th the value_th to set
	 */
	public void setValue_th(Integer value_th) {
		this.value_th = value_th;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FindPar [fenqu=" + fenqu + ", stcd=" + stcd + ", bianhao=" + bianhao + ", Name=" + Name + ", Online="
				+ Online + ", selectType=" + selectType + ", tm=" + tm + ", begintm=" + begintm + ", endtm=" + endtm
				+ ", page=" + page + ", Count=" + Count + ", value_th=" + value_th + "]";
	}

 
	 

 
 





	
	 

 
	
	
	

}
