package com.qipachong.maxnull.utils;

import java.io.*;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	
	/**向页面渲染内容*/
	public static void  render(HttpServletResponse response, String contentType, Object value, String name, String charset){
		try{
			response.reset();
			if(value instanceof File){
				if(!StringUtils.isNotEmpty(charset)){
					charset="iso-8859-1";
				}
				String filename=null;
				if(StringUtils.isNotEmpty(name)){
					filename=name;
				}else{
					filename=((File)value).getName();
				}
				Long length=((File)value).length();
				response.addHeader("Content-Disposition",String.format("attachment;filename=%s",new String(filename.getBytes(),charset)));
				if(StringUtils.isNotEmpty(contentType)){
					response.setContentType(contentType);
				}else{
					response.setContentType(String.format("application/octet-stream;charset=%s",charset));
				}
				response.addHeader("Content-Length",String.format("%d",length));
				response.setBufferSize(4096);
				OutputStream  out=new BufferedOutputStream(response.getOutputStream());
				InputStream is=new BufferedInputStream(new FileInputStream(((File)value)));
				byte[] buffer=new byte[length.intValue()];
				is.read(buffer);
				is.close();
				out.write(buffer);
				out.flush();
				out.close();
			}else if(value instanceof InputStream){
				if(!StringUtils.isNotEmpty(charset)){
					charset="iso-8859-1";
				}
				int length=((InputStream)value).available();
				response.addHeader("Content-Disposition",String.format("attachment;filename=%s",new String(name.getBytes(),charset)));
				response.setContentType(String.format("application/octet-stream;charset=%s",charset));
				response.addHeader("Content-Length",String.format("%d",length));
				response.setBufferSize(4096);
				OutputStream  out=new BufferedOutputStream(response.getOutputStream());
				InputStream is=new BufferedInputStream(((InputStream)value));
				byte[] buffer=new byte[length];
				is.read(buffer);
				is.close();
				out.write(buffer);
				out.flush();
				out.close();
			}else if((value instanceof String)||(value instanceof Number||(value instanceof Boolean))){
				response.reset();
				if(StringUtils.isNotEmpty(charset)){
					response.setCharacterEncoding(charset);
				}else{
					response.setCharacterEncoding("UTF-8");
				}
				PrintWriter writer=response.getWriter();
				if(StringUtils.isNotEmpty(contentType)){
					response.setContentType(contentType);
				}else{
					response.setContentType("text/plain;charset=UTF-8");
				}
				writer.write(String.valueOf(value));
			}else{
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void  addCookie(HttpServletResponse response, Cookie cookie){
		response.addCookie(cookie);
	}
	public static void  addCookie(HttpServletResponse response, String name, String value){
		Cookie cookie=new Cookie(name,value);
		addCookie(response,cookie);
	}
	/**打印字符串*/
	public static void  rendertext(HttpServletResponse response, String text, String charset){
		render(response,"text/plain;charset=UTF-8",text,null, charset);
	}
	/**打印json字符串*/
	public static void  renderjson(HttpServletResponse response, String text, String charset){
		render(response,"application/json;charset=UTF-8",text,null, charset);
	}
	/**打印xml*/
	public static void  renderxml(HttpServletResponse response, String text, String charset){
		render(response,"text/xml;charset=UTF-8",text,null, charset);
	}
	/**打印html*/
	public static void  renderhtml(HttpServletResponse response, String text, String charset){
		render(response,"text/html;UTF-8",text,null, charset);
	}
	/**打印脚本script*/
	public static void  renderscript(HttpServletResponse response, Map<String,String> value, String charset) throws Throwable {
		if(value!=null){
			String message=value.get("message");
			String url=value.get("url");
			if(StringUtils.isNotEmpty(charset)){
				response.setCharacterEncoding(charset);
			}else{
				response.setCharacterEncoding("UTF-8");
			}
			response.setContentType("text/html;UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML>");
			out.println("<html>");
			out.println("\t<head>");
			out.println("\t\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=EmulateIE8\">");
			out.println("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>");
			out.println("\t\t<title>消息</title>");
			out.println("\t</head>");
			out.println("\t<body>");
			out.println("\t\t<script type=\"text/javascript\">");
			if(StringUtils.isNotEmpty(message)){
				out.println(String.format("\t\t\t alert('%s');",message));
			}
			if(StringUtils.isNotEmpty(url)){
				out.println(String.format("\t\t\t window.top.location.href='%s';", url));
			}
			out.println("\t\t</script>");
			out.println("\t</body>");
			out.println("</html>");
		}
	}
}
