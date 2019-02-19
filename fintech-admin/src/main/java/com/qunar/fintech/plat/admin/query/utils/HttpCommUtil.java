package com.qunar.fintech.plat.admin.query.utils;

import com.qunar.fintech.util.http.TrustAnyHostnameVerifier;
import com.qunar.fintech.util.http.TrustAnyTrustManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpCommUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpCommUtil.class);
	public static final String METHOD_POST = "POST";

	// 如果需要使用这个connectionManager  一定要在使用前调用初始化方法initHttpConnectionPool
	private static MultiThreadedHttpConnectionManager connectionManager;

	public static String encode (String content, final String encoding) {
		try {
			return URLEncoder.encode(content,
					encoding != null ? encoding : HTTP.DEFAULT_CONTENT_CHARSET);
		} catch (UnsupportedEncodingException problem) {
			//throw new IllegalArgumentException(problem);
			return content;
		}
	}

	public static String printMap(Map<String,String> map,String splitString)
	{
		String ret = "";
		if(map == null)
			return ret;

		StringBuffer buffer = new StringBuffer();
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext())
		{
			String key = iterator.next();
			String value = map.get(key);
			if(ret.length() > 0)
				buffer = buffer.append(splitString) ;
			buffer = buffer.append(key).append("=").append(value);
		}

		return ret;
	}

	/**
	 *
	 * @param map
	 * @param safety 为TRUE，不打印敏感数据
	 * @return
	 */
/*	public static String showParam(Map<String,String> map,boolean safety){
		StringBuilder sbuf = new StringBuilder(128);
		Set<String> set = map.keySet();
		boolean flag = false;
		sbuf.append("[");
		for(Iterator<String> it = set.iterator();it.hasNext();flag=true){
			String key = it.next();
			String value = map.get(key);
			if(safety){
				if("passwd".equals(key) || "pass_wd".equals(key) || "cvv2".equals(key) || "cvv".equals(key)){
					if(value == null || "".equals(value)){
						value = "";
					}else{
						value="******";
					}
				}
				if(value == null)value="";
				if(("card_id".equals(key) || "cardid".equals(key)) && value.length() > 6){
					value = value.substring(0,6)+"******";
				}
			}
			if(!flag){
				sbuf.append(key).append("=").append(value);
			}else{
				sbuf.append(",").append(key).append("=").append(value);
			}
		}
		sbuf.append("]");
		return sbuf.toString();
	}*/

	/**
	 * 方法名称：发送POST请求
	 * 			如果是HTTP则发送HTTP请求，如果是HTTPS则发送HTTPS请求
	 *
	 * @param requestUrl
	 * @param paramMap
	 * @return
	 */
	public static String doPost(String requestUrl, Map<String, String> paramMap)throws Exception {
		String responseContent = null;

		if (requestUrl.startsWith("https://")) {
			responseContent = doHttpsClientPost(requestUrl, paramMap);
		} else {
			responseContent = httpClientPost2Str(requestUrl, paramMap);
		}

		return responseContent;
	}

	/**
	 * 方法名称：发送HTTPS请求
	 *
	 * @param requestUrl
	 * @param paramMap
	 * @return
	 */
	public static String doHttpsClientPost(String requestUrl, Map<String, String> paramMap) {
		String responseContent = null;
		HttpsURLConnection httpsURLConnection = null;
		InputStream is = null;
		BufferedReader br = null;
		try {
			SSLContext context = SSLContext.getInstance("SSL");
			context.init(null,new TrustManager[] {new TrustAnyTrustManager()}, new SecureRandom());

			URL url = new URL(requestUrl);

			httpsURLConnection = (HttpsURLConnection) url.openConnection();
			httpsURLConnection.setRequestMethod(METHOD_POST);
			httpsURLConnection.setSSLSocketFactory(context.getSocketFactory());
			httpsURLConnection.setHostnameVerifier(new TrustAnyHostnameVerifier());
			httpsURLConnection.setDoInput(true);
			httpsURLConnection.setDoOutput(true);
			httpsURLConnection.connect();

			String params = printMap(paramMap,"&");
			byte[] b = params.getBytes();

			httpsURLConnection.getOutputStream().write(b, 0, b.length);
			httpsURLConnection.getOutputStream().flush();
			httpsURLConnection.getOutputStream().close();


			is = httpsURLConnection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is, HTTP.UTF_8));

			String tempLine = null;
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");

			while ((tempLine = br.readLine()) != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
			}
			responseContent = tempStr.toString();
		} catch (Exception e) {
			logger.error("doHttpsClientPost exception",e);
		} finally {
			if (httpsURLConnection != null){
				httpsURLConnection.disconnect();
				httpsURLConnection = null;
			}

			try {
				if (is != null) {
					is.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}

		return responseContent;

	}
	public static String httpClientPost2Str(String url,Map<String,String> paramMap)throws Exception{
		if(paramMap == null)
			return "";

		String responseContent = "";
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(url);

			// Execute HTTP request
			logger.info("executing request " + httppost.getURI());
			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
			Iterator<String> iterator = paramMap.keySet().iterator();
			while(iterator.hasNext())
			{
				String key = iterator.next();
				String value = paramMap.get(key);
				nvps.add(new BasicNameValuePair(key, value));
			}

			httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));


			HttpResponse response = httpclient.execute(httppost);

			logger.info("----------------------------------------");
			logger.info(response.getStatusLine().toString());
			logger.info("----------------------------------------");

			// Get hold of the response entity
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			// to bother about connection release
			if (entity != null) {
				Header header = entity.getContentType();
				String headerString = "";
				if(header != null)
					headerString = header.getValue();
				String charset = getEncoding(headerString);
				logger.info("charset=" + charset);
				InputStream instream = entity.getContent();
				try {
					//instream.read();
					// do something useful with the response
					StringBuffer sb = new StringBuffer();
					byte[] btemp = new byte[1024];
					int count = instream.read(btemp);
					while(count >= 0)
					{
						sb.append(new String(btemp,0,count,charset));
						count = instream.read(btemp);
					}

					responseContent = sb.toString();
					logger.debug("responseContent=" + responseContent);
				} catch (IOException ex) {
					// In case of an IOException the connection will be released
					// back to the connection manager automatically
					throw ex;
				} catch (RuntimeException ex) {
					// In case of an unexpected exception you may want to abort
					// the HTTP request in order to shut down the underlying
					// connection immediately.
					httppost.abort();
					throw ex;
				} finally {
					// Closing the input stream will trigger connection release
					try { instream.close(); } catch (Exception ignore) {
						logger.error("ignore error",ignore);
					}
				}
			}

		}
		catch(Exception e){
			logger.error(" errMsg: " +e.getMessage(),e);

		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			//logger.info(""+);
			httpclient.getConnectionManager().shutdown();
		}

		return responseContent;
	}

	/**
	 * 从http头里获取编码格式
	 * @param header
	 * @return
	 */
	public static String getEncoding(String header)
	{
		String charset = "UTF-8";
		if(header == null || header.trim().equals(""))
			return charset;

		if (matcher(header, "(charset)\\s?=\\s?(utf-?8)")) {
			charset = "UTF-8";
		} else if (matcher(header, "(charset)\\s?=\\s?(gbk)")) {
			charset = "GBK";
		} else if (matcher(header, "(charset)\\s?=\\s?(gb2312)")) {
			charset = "GB2312";
		}
		return charset;
	}

	public static boolean matcher(String s, String pattern)
	{
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
		Matcher matcher = p.matcher(s);
		if (matcher.find())
		{
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断字符串是否为NULL和空串。
	 *
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return !notEmpty(str);
	}

	/**
	 * 判断字符串是否不为NULL和空串。
	 *
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean notEmpty(String str) {
		return  str != null &&!str.equals("null")&& str.trim().length() > 0;
	}

	/***
	 * double转字符串
	 * @param d
	 * @param fNumber 小数点的位数
	 * @return
	 */
	public static String double2String(double d, int fNumber) {
		if (fNumber < 0)
			fNumber = 0;

		String pattern = null;
		switch (fNumber) {
			case 0:
				pattern = "#0"; //$NON-NLS-1$
				break;
			default:
				pattern = "#0."; //$NON-NLS-1$
				StringBuffer b = new StringBuffer(pattern);
				for (int i = 0; i < fNumber; i++) {
					b.append('0');
				}
				pattern = b.toString();
				break;

		}
		DecimalFormat formatter = new DecimalFormat();
		formatter.applyPattern(pattern);
		String value = formatter.format(d);
		return value;
	}

	/**
	 * 判断无效yyMM
	 * @param validate
	 * @return
	 */
	public static boolean notValidate(String validate){
		if(isEmpty(validate))
			return true;
		else if (!isNumeric(validate)) {
			return true;
		}
		else if (String_length(validate) !=4) {
			return true;
		}else {
			String monthString = validate.substring(2);
			if(Integer.parseInt(monthString)>12 || Integer.parseInt(monthString)<=0)
			{
				return true;
			}
			String yearString = validate.substring(0,2);
			String  nowyearString = getYear();
			if(Integer.parseInt(yearString)<Integer.parseInt(nowyearString)){
				return true;
			}
			if((nowyearString.equals(yearString) && Integer.parseInt(monthString)<getMonth())){
				return true;
			}
		}
		return false;
	}
	/**
	 * 取系统年后两位
	 * @return
	 */
	public static String getYear(){
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		String year = ""+ca.get(Calendar.YEAR);
		return year.substring(2);
	}

	/**
	 * 取系统月
	 * @return
	 */
	public static int getMonth(){
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		return ca.get(Calendar.MONTH)+1;
	}

	public static void main(String[] args)throws Exception{
		//System.out.println("sss===="+getMonth());
	}

	/**
	 * 判断身份证是否正确
	 * @param IDStr
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public static Boolean IDCardValidate(String IDStr){
		String errorInfo = "";// 记录错误信息
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		String Ai = "";
		// ================ 号码的长度 15位或18位 ================
		if (IDStr.length() != 15 && IDStr.length() != 18) {
			errorInfo = "身份证号码长度应该为15位或18位。";
			return false;
		}
		// =======================(end)========================

		// ================ 数字 除最后以为都为数字 ================
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (isNumeric(Ai) == false) {
			errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			return false;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
			errorInfo = "身份证生日无效。";
			return false;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
					|| (gc.getTime().getTime() - s.parse(
					strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
				errorInfo = "身份证生日不在有效范围。";
				return false;
			}
		} catch (NumberFormatException e) {
			logger.error(" errMsg: " +e.getMessage(), e);
		} catch (java.text.ParseException e) {
			logger.error(" errMsg: " +e.getMessage(), e);
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			errorInfo = "身份证月份无效";
			return false;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			errorInfo = "身份证日期无效";
			return false;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		Hashtable h = GetAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			errorInfo = "身份证地区编码错误。";
			return false;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (IDStr.length() == 18) {
			if (Ai.toUpperCase().equals(IDStr.toUpperCase()) == false) {
				errorInfo = "身份证无效，不是合法的身份证号码";
				return false;
			}
		}
		// =====================(end)=====================
		return true;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Hashtable GetAreaCode() {
		Hashtable hashtable = new Hashtable();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}

	/**
	 * 判断字符串
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 功能：判断字符串是否为日期格式
	 *
	 * @return
	 */
	public static boolean isDate(String strDate) {
		Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static int String_length(String value) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}

	@SuppressWarnings("unused")
	public static String httpClientStr(String url,Map<String,String> paramMap)throws Exception{
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		Iterator<String> iterator = paramMap.keySet().iterator();
		StringBuffer sb = new StringBuffer();
		while(iterator.hasNext())
		{
			String key = iterator.next();
			String value = paramMap.get(key);
			try {
				sb.append(key + "=" + URLEncoder.encode(value, "GBK") + "&");
			} catch (UnsupportedEncodingException e) {
				logger.error(" errMsg: " +e.getMessage(),e);
			}
		}
		//去掉最后一个&
		String reqPars = sb.substring(0, sb.lastIndexOf("&"));
		return url + "?" + reqPars;
	}
	/**
	 * 过滤特殊字符
	 * @param input
	 * @return
	 */
	public static boolean judgeInputParam(String input) {
		if (input == null || "".equals(input.trim())) {
			return true;
		}

		Matcher scriptMatcher;
		Matcher styleMatcher;
		Matcher htmlMatcher;
		Matcher charMatcher;
		try {
			// 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String scriptReg = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";

			// 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String styleReg = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

			// 定义HTML标签的正则表达式
			String htmlReg = "[<>].*";

			charMatcher = Pattern.compile(".*[<>\\?\\*\'\"].*", Pattern.CASE_INSENSITIVE).matcher(input);
			if (charMatcher.matches()) {
				return false;
			}

			// 过滤script标签
			scriptMatcher = Pattern.compile(scriptReg, Pattern.CASE_INSENSITIVE).matcher(input);
			if (scriptMatcher.matches()) {
				return false;
			}

			// 过滤style标签
			styleMatcher = Pattern.compile(styleReg, Pattern.CASE_INSENSITIVE).matcher(input);
			if (styleMatcher.matches()) {
				return false;
			}

			// 过滤html标签
			htmlMatcher = Pattern.compile(htmlReg, Pattern.CASE_INSENSITIVE).matcher(input);
			if (htmlMatcher.matches()) {
				return false;
			}

			return true;
		} catch (Exception e) {
			logger.error(" errMsg: " +e.getMessage(), e);
			return false;
		}
	}

	public static String doPostPlatform(String requestUrl, Map<String, String> paramMap)throws Exception {
		String responseContent = null;

		if (requestUrl.startsWith("https://")) {
			responseContent = doHttpsClientPostPlatform(requestUrl, paramMap);
		} else {
			responseContent = httpClientPost2Str(requestUrl, paramMap);
		}

		return responseContent;
	}

	public static String doHttpsClientPostPlatform(String requestUrl, Map<String, String> paramMap) {
		String responseContent = null;
		HttpsURLConnection httpsURLConnection = null;
		InputStream is = null;
		BufferedReader br = null;
		try {
			SSLContext context = SSLContext.getInstance("SSL");
			context.init(null,new TrustManager[] {new TrustAnyTrustManager()}, new SecureRandom());

			URL url = new URL(requestUrl);

			httpsURLConnection = (HttpsURLConnection) url.openConnection();
			httpsURLConnection.setRequestMethod(METHOD_POST);
			httpsURLConnection.setSSLSocketFactory(context.getSocketFactory());
			httpsURLConnection.setHostnameVerifier(new TrustAnyHostnameVerifier());
			httpsURLConnection.setDoInput(true);
			httpsURLConnection.setDoOutput(true);
			httpsURLConnection.connect();

			StringBuffer params = new StringBuffer("");
			for(Entry<String, String> entry : paramMap.entrySet()){
				params.append(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8")+"&");
			}

			byte[] b = params.substring(0, params.length()-1).getBytes();

			httpsURLConnection.getOutputStream().write(b, 0, b.length);
			httpsURLConnection.getOutputStream().flush();
			httpsURLConnection.getOutputStream().close();


			is = httpsURLConnection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is, HTTP.UTF_8));

			String tempLine = null;
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");

			while ((tempLine = br.readLine()) != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
			}
			responseContent = tempStr.toString();
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(),e);
		} catch (KeyManagementException e) {
			logger.error(e.getMessage(),e);
		} finally {
			if (httpsURLConnection != null){
				httpsURLConnection.disconnect();
				httpsURLConnection = null;
			}

			try {
				if (is != null) {
					is.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}

		return responseContent;

	}

	private static void initHttpConnectionPool(){
		if(connectionManager == null){
			Protocol protocol = new Protocol("https", new MySecureProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", protocol);

			connectionManager = new MultiThreadedHttpConnectionManager();

			int connectionTimeOut = 2000;
			int socketTimeOut = 5000;
			int maxConnectionPerHost = 500;
			int maxTotalConnections = 5000;

			connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
			connectionManager.getParams().setSoTimeout(socketTimeOut);
			connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
			connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
		}
	}

	public static String httpClientPost(String url, Map<String, String> paraMap) throws Exception{
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

			initHttpConnectionPool();

			org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient(connectionManager);
			if(paraMap == null){
				paraMap = new HashMap<String, String>();
			}
			// 添加参数
			Set<Entry<String, String>> entrySet = paraMap.entrySet();
			for(Entry<String, String> entry : entrySet){
				postMethod.addParameter(entry.getKey(), entry.getValue());
			}

			int statusCode = client.executeMethod(postMethod);
			if(statusCode == HttpStatus.SC_OK) {
				return postMethod.getResponseBodyAsString();
			}else{
				logger.error("响应状态码 = " + postMethod.getStatusCode());
				throw new Exception("响应状态码 = " + postMethod.getStatusCode());
			}
		} catch (Exception e) {
			logger.error("请求url出现异常[" + url + "]", e);
			throw new Exception(e);
		} finally{
			if(postMethod != null){
				postMethod.releaseConnection();
				connectionManager.closeIdleConnections(0);
			}
		}
	}

}
