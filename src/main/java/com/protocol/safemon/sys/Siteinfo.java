/**
 * 
 */
package com.protocol.safemon.sys;

/**
 * @author Administrator
 *
 */
public class Siteinfo {

	private Integer id;
	private Integer fenqu;
	private String bianhao;
	private String Site_Name;
	private String kahao;
	private String danwei;
	private String zerenren;
	private String dianhua;
	private String zuobiao;
	private float revise;
	private String beizhu;

	/**
	 * 
	 */
	public Siteinfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param fenqu
	 * @param bianhao
	 * @param site_Name
	 * @param kahao
	 * @param danwei
	 * @param zerenren
	 * @param dianhua
	 * @param zuobiao
	 * @param revise
	 * @param beizhu
	 */
	public Siteinfo(Integer id, Integer fenqu, String bianhao, String site_Name, String kahao, String danwei,
			String zerenren, String dianhua, String zuobiao, float revise, String beizhu) {
		super();
		this.id = id;
		this.fenqu = fenqu;
		this.bianhao = bianhao;
		Site_Name = site_Name;
		this.kahao = kahao;
		this.danwei = danwei;
		this.zerenren = zerenren;
		this.dianhua = dianhua;
		this.zuobiao = zuobiao;
		this.revise = revise;
		this.beizhu = beizhu;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the fenqu
	 */
	public Integer getFenqu() {
		return fenqu;
	}

	/**
	 * @param fenqu
	 *            the fenqu to set
	 */
	public void setFenqu(Integer fenqu) {
		this.fenqu = fenqu;
	}

	/**
	 * @return the bianhao
	 */
	public String getBianhao() {
		return bianhao;
	}

	/**
	 * @param bianhao
	 *            the bianhao to set
	 */
	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

	/**
	 * @return the site_Name
	 */
	public String getSite_Name() {
		return Site_Name;
	}

	/**
	 * @param site_Name
	 *            the site_Name to set
	 */
	public void setSite_Name(String site_Name) {
		Site_Name = site_Name;
	}

	/**
	 * @return the kahao
	 */
	public String getKahao() {
		return kahao;
	}

	/**
	 * @param kahao
	 *            the kahao to set
	 */
	public void setKahao(String kahao) {
		this.kahao = kahao;
	}

	/**
	 * @return the danwei
	 */
	public String getDanwei() {
		return danwei;
	}

	/**
	 * @param danwei
	 *            the danwei to set
	 */
	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	/**
	 * @return the zerenren
	 */
	public String getZerenren() {
		return zerenren;
	}

	/**
	 * @param zerenren
	 *            the zerenren to set
	 */
	public void setZerenren(String zerenren) {
		this.zerenren = zerenren;
	}

	/**
	 * @return the dianhua
	 */
	public String getDianhua() {
		return dianhua;
	}

	/**
	 * @param dianhua
	 *            the dianhua to set
	 */
	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

	/**
	 * @return the zuobiao
	 */
	public String getZuobiao() {
		return zuobiao;
	}

	/**
	 * @param zuobiao
	 *            the zuobiao to set
	 */
	public void setZuobiao(String zuobiao) {
		this.zuobiao = zuobiao;
	}

	/**
	 * @return the revise
	 */
	public float getRevise() {
		return revise;
	}

	/**
	 * @param revise
	 *            the revise to set
	 */
	public void setRevise(float revise) {
		this.revise = revise;
	}

	/**
	 * @return the beizhu
	 */
	public String getBeizhu() {
		return beizhu;
	}

	/**
	 * @param beizhu
	 *            the beizhu to set
	 */
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Siteinfo [id=" + id + ", fenqu=" + fenqu + ", bianhao=" + bianhao + ", Site_Name=" + Site_Name
				+ ", kahao=" + kahao + ", danwei=" + danwei + ", zerenren=" + zerenren + ", dianhua=" + dianhua
				+ ", zuobiao=" + zuobiao + ", revise=" + revise + ", beizhu=" + beizhu + "]";
	}

}
