/**
 * 
 */
package com.protocol.safemon.sys;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class LED_Realtime {

	private Integer fenqu;
	private Integer EA;
	private String LED_STCD;
	private String Site_Name;
	private String bianhao;
	private String Screen_state;
	private String Card_state;
	private String Light_state;
	private Date TM;

	public LED_Realtime() {
	};

	public LED_Realtime(String LED_STCD, String Site_Name, String Screen_state, String Card_state, String Light_state,
			Date TM, Integer fenqu,String bianhao,Integer ea) {

		super();
		this.LED_STCD = LED_STCD;
		this.Card_state = Card_state;
		this.Light_state = Light_state;
		this.Screen_state = Screen_state;
		this.TM = TM;
		this.Site_Name = Site_Name;
		this.fenqu = fenqu;
        this.bianhao = bianhao;
        this.EA = ea;
	}
	
	/**
	 * @return the fenqu
	 */
	public Integer getFenqu() {
		return this.fenqu;
	}

	/**
	 * @param fenqu the fenqu to set
	 */
	public void setFenqu(Integer fenqu) {
		this.fenqu = fenqu;
	}

	/**
	 * @return the lED_STCD
	 */
	public String getLED_STCD() {
		return LED_STCD;
	}

	/**
	 * @param lED_STCD
	 *            the lED_STCD to set
	 */
	public void setLED_STCD(String lED_STCD) {
		LED_STCD = lED_STCD;
	}

	/**
	 * @return the screen_state
	 */
	public String getScreen_state() {
		return Screen_state;
	}

	/**
	 * @param screen_state
	 *            the screen_state to set
	 */
	public void setScreen_state(String screen_state) {

		Screen_state = screen_state;

	}

	/**
	 * @return the card_state
	 */
	public String getCard_state() {
		return Card_state;
	}

	/**
	 * @param card_state
	 *            the card_state to set
	 */
	public void setCard_state(String card_state) {

		Card_state = card_state;

	}

	/**
	 * @return the light_state
	 */
	public String getLight_state() {
		return Light_state;
	}

	/**
	 * @param light_state
	 *            the light_state to set
	 */
	public void setLight_state(String light_state) {

		Light_state = light_state;

	}

	/**
	 * @return the tM
	 */
	public Date getTM() {
		return TM;
	}

	/**
	 * @param tM
	 *            the tM to set
	 */
	public void setTM(Date tM) {
		TM = tM;
	}

	/**
	 * @return the site_Name
	 */
	public String getSite_Name() {
		return Site_Name;
	}

	/**
	 * @param site_Name the site_Name to set
	 */
	public void setSite_Name(String site_Name) {
		Site_Name = site_Name;
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
		 * @return the eA
		 */
		public Integer getEA() {
			return EA;
		}

		/**
		 * @param eA the eA to set
		 */
		public void setEA(Integer eA) {
			EA = eA;
		}
		
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "LED_Realtime [fenqu=" + fenqu + ", EA=" + EA + ", LED_STCD=" + LED_STCD + ", Site_Name=" + Site_Name
					+ ", bianhao=" + bianhao + ", Screen_state=" + Screen_state + ", Card_state=" + Card_state
					+ ", Light_state=" + Light_state + ", TM=" + TM + "]";
		}







	 


	
	
 
	
 

}
