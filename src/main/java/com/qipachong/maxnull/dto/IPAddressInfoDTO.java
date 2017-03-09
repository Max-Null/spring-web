/**     
 * @Project: adms-service  
 * @Package: com.xiaobai.adms.dto   
 * @Title: AddressInfoDTO.java
 * @Description: TODO(用一句话描述该文件做什么)   
 * @Author: mqc
 * @Date: 2016-6-5 下午5:00:03   
 * @Version: V1.0     
 */  
package com.qipachong.maxnull.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**   
 * @ClassName: AddressInfoDTO   
 * @Description: IP地址信息类
 * @Author: mqc
 * @Date: 2016-6-5 下午5:00:03   
 *      
 */
public class IPAddressInfoDTO {

	/**
	 * IP地址
	 */
	private String ip;
	
	/**
	 * 国家
	 */
	private String country;
	
	/**
	 * 国家编码
	 */
	private String countryCode;
	
	/**
	 * 地区
	 */
	private String area;
	
	/**
	 * 地区编码
	 */
	private String areaCode;
	
	/**
	 * 省
	 */
	private String region;
	
	/**
	 * 省编码
	 */
	private String regionCode;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 城市编码
	 */
	private String cityCode;
	
	/**
	 * 区县
	 */
	private String county;
	
	/**
	 * 区县编码
	 */
	private String countyCode;
	
	/**
	 * 所属运营商
	 */
	private String isp;
	
	/**
	 * 所属运营商编码
	 */
	private String ispCode;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getIspCode() {
		return ispCode;
	}

	public void setIspCode(String ispCode) {
		this.ispCode = ispCode;
	}
	
	/* (非 Javadoc)   
	* <p>Title: toString</p>   
	* <p>Description: </p>   
	* @return   
	* @see java.lang.Object#toString()   
	*/   
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
