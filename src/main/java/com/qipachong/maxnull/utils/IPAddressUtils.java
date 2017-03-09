/**
 * @Project: adms-service  
 * @Package: com.xiaobai.adms.utils
 * @Title: IPAddressUtils.java
 * @Description: TODO(用一句话描述该文件做什么)
 * @Author: mqc
 * @Date: 2016-6-3 下午5:56:53   
 * @Version: V1.0
 */
package com.qipachong.maxnull.utils;

/**
 * @ClassName: IPAddressUtils
 * @Description: 根据IP地址获取详细的地域信息
 * @Author: mqc
 * @Date: 2016-6-3 下午5:56:53   
 *
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qipachong.maxnull.dto.IPAddressInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class IPAddressUtils {
    private static String APP_KEY = "1V2En6VSKalD9PkclM38Z9OtT9X5GTDd";

    protected static Logger log = LoggerFactory.getLogger(IPAddressUtils.class);

    /**
     * @param content        IP地址
     * @param encodingString 服务器端请求编码  如GBK,UTF-8等
     * @return
     * @throws UnsupportedEncodingException
     */
    public static IPAddressInfoDTO getAddresses(String content, String encodingString)
            throws UnsupportedEncodingException {
        // 这里调用pconline的接口
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        // 取得IP所在的省市区信息
        String returnStr = getResult(urlStr, content, encodingString);

//		if (log.isDebugEnabled()) {
//			log.debug("getAddresses......");
//			log.debug(returnStr);
//		}

        log.info("getAddresses......");
        log.info(returnStr);

        IPAddressInfoDTO addressInfo = null;

        if (returnStr != null) {
            // 处理返回的省市区信息

            JSONObject jsonResult = JSON.parseObject(returnStr);
            Integer resultCode = jsonResult.getInteger("code");
            if (resultCode == 0) {
                JSONObject data = jsonResult.getJSONObject("data");
                addressInfo = new IPAddressInfoDTO();

                // 国家
                String country = data.getString("country");
                if (null != country) {
                    addressInfo.setCountry(decodeUnicode(country));
                }
                // 国家编码
                String countryCode = data.getString("country_id");
                if (null != countryCode) {
                    addressInfo.setCountryCode(countryCode);
                }
                // 地区
                String area = data.getString("area");
                if (!area.equals("")) {
                    addressInfo.setArea(decodeUnicode(area));
                }
                // 地区编码
                String areaCode = data.getString("area_id");
                if (!areaCode.equals("")) {
                    addressInfo.setAreaCode(areaCode);
                }
                // 省份
                String region = data.getString("region");
                if (!region.equals("")) {
                    addressInfo.setRegion(decodeUnicode(region));
                }
                // 省份编码
                String regionCode = data.getString("region_id");
                if (!regionCode.equals("")) {
                    addressInfo.setRegionCode(regionCode);
                }
                // 城市
                String city = data.getString("city");
                if (!city.equals("")) {
                    addressInfo.setCity(decodeUnicode(city));
                }
                // 城市编码
                String cityCode = data.getString("city_id");
                if (!cityCode.equals("")) {
                    addressInfo.setCityCode(cityCode);
                }
                // 区县
                String county = data.getString("county");
                if (!county.equals("")) {
                    addressInfo.setCounty(decodeUnicode(county));
                }
                // 区县编码
                String countyCode = data.getString("county_id");
                if (!countyCode.equals("")) {
                    addressInfo.setCountyCode(countyCode);
                }
                // 所属运营商
                String isp = data.getString("isp");
                if (!isp.equals("")) {
                    addressInfo.setIsp(decodeUnicode(isp));
                }
                // 运营商编码
                String ispCode = data.getString("isp_id");
                if (!ispCode.equals("")) {
                    addressInfo.setIspCode(ispCode);
                }
                // IP地址
                String ip = data.getString("ip");
                addressInfo.setIp(ip);

//				if (log.isDebugEnabled()) {
//					log.debug(addressInfo.toString());
//				}
                log.info(addressInfo.toString());
            }
        }
        return addressInfo;
    }

    /**
     * @throws
     * @Title: IPAddressUtils
     * @param: @param content
     * @param: @return
     * @return: IPAddressInfoDTO
     * @Creator: wanghao
     * @Description: 
     * @Date: 2016-7-12 上午9:22:28
     * @Modifier:
     * @Date:
     * @Remark:
     * @Version: V1.0
     */
    public IPAddressInfoDTO getAddressFromBaidu(String ip) {
        String urlStr = "http://api.map.baidu.com/location/ip";
        String returnStr = getResult(urlStr, "ip=" + ip + "&ak=" + APP_KEY, "utf-8");
        log.info("getAddressesFromBaidu......" + ip);
        JSONObject infoJSONObject = null;
        JSONObject infoContentJsonObject = null;
        JSONObject addressDetailObject = null;
        if (org.apache.commons.lang3.StringUtils.isEmpty(returnStr)) {
            return null;
        }
        infoJSONObject = JSON.parseObject(returnStr);
        infoContentJsonObject = infoJSONObject.getJSONObject("content");
        if (infoContentJsonObject == null) {
            return null;
        }
        addressDetailObject = infoContentJsonObject.getJSONObject("address_detail");
        IPAddressInfoDTO addressInfo = null;

        int resultCode = infoContentJsonObject.getIntValue("status");
        if (returnStr != null) {
            if (resultCode == 0) {
                addressInfo = new IPAddressInfoDTO();

                // 国家
                String country = "";
                if (null != country) {
                    addressInfo.setCountry(decodeUnicode(country));
                }
                // 国家编码
                String countryCode = infoJSONObject.getString("address").toString().split("\\|")[0];
                if (null != countryCode) {
                    addressInfo.setCountryCode(countryCode);
                }
                // 省份
                String region = addressDetailObject.getString("province");
                if (!region.equals("")) {
                    addressInfo.setRegion(decodeUnicode(region));
                }
                // 城市
                String city = addressDetailObject.getString("city");
                if (!city.equals("")) {
                    addressInfo.setCity(decodeUnicode(city));
                }
                // 城市编码
                String cityCode = addressDetailObject.getString("city_code");
                if (!cityCode.equals("")) {
                    addressInfo.setCityCode(cityCode);
                }
                // 区县
                String county = addressDetailObject.getString("district");
                if (!county.equals("")) {
                    addressInfo.setCounty(decodeUnicode(county));
                }
                // 区县编码
                String countyCode = "";
                if (!countyCode.equals("")) {
                    addressInfo.setCountyCode(countyCode);
                }
                // 所属运营商
                String isp = "";
                if (!isp.equals("")) {
                    addressInfo.setIsp(decodeUnicode(isp));
                }
                // 运营商编码
                String ispCode = infoJSONObject.getString("address").toString().split("\\|")[4];
                if (!ispCode.equals("")) {
                    addressInfo.setIspCode(ispCode);
                }
                // IP地址
                addressInfo.setIp(ip);
            }

        }
        return addressInfo;
    }

    /**
     * @param urlStr   请求的地址
     * @param content  IP地址
     * @param encoding 服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(content);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * unicode 转换成 中文
     *
     * @param theString
     * @return
     * @author fanhui 2007-3-15
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

    // 测试
    public static void main(String[] args) {
        IPAddressUtils addressUtils = new IPAddressUtils();
        // 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信
        String ip = "123.123.109.77";
//		String ip = "219.136.134.157";
        int count = 0;
//		for (int i = 0;i<20;i++)
//		{
//			log.info(i + "");
        IPAddressInfoDTO info = addressUtils.getAddressFromBaidu(ip);
//			log.info(info.toString());
//			if(null != info)
//			{
//				count++;
//			}
//		}
        log.info(count + "");
    }
}