package com.qipachong.maxnull.utils;

import java.io.*;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class UploadTools {
	
	
	/**
	 * 将上传的文件进行重命名
	 * @return
	 */
	public static String reName(String name) {
		String fileName = (UUID.randomUUID()+"").split("-")[0]+(System.currentTimeMillis()+Math.round(Math.random()*10000));
		if (name.indexOf(".") != -1) {
			fileName += name.substring(name.lastIndexOf("."));
		}
		return fileName;
	}
   /**
    * 上传多个人文件重新命名 
    * @return
    */
	
	public static String[] reName(String[] name) {
		String[] fileName = new String[name.length];
        for(int i=0;i<name.length;i++){
			fileName[i] = (UUID.randomUUID()+"").split("-")[0]+(System.currentTimeMillis()+Math.round(Math.random()*10000));
			if (name[i].indexOf(".") != -1) {
				fileName[i] += name[i].substring(name[i].lastIndexOf("."));
			}
        }
		return fileName;
	}
	/**
	 * 压缩后的文件名
	 * @return
	 */
	public static String zipName(String name) {
		String prefix = "";
		if (name.indexOf(".") != -1) {
			prefix = name.substring(0, name.lastIndexOf("."));
		} else {
			prefix = name;
		}
		return prefix + ".zip";
	}
	/**
	 * 文件上传
	 * @param is 文件流
	 * @param root  文件存放目录
	 * @param fileName   文件名
	 * @return
	 */
	public static boolean upload(InputStream is,String root,String fileName){
		try {
	        File targetFile = new File(root,fileName);
	        if (!targetFile.getParentFile().exists()) {
	        	targetFile.getParentFile().mkdirs();
			}
	        OutputStream os = new FileOutputStream(targetFile);
	        byte[] buffer = new byte[2048];
	        int length  = 0 ;
	        while((length = is.read(buffer))>0){
	            os.write(buffer, 0, length);
	        }
	        is.close();
	        os.close();
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	/**
	 * mutifile上传文件方法
	 * @param file
	 * @param request
	 * @param relroot
	 * @return
	 */
	public static String uploadSpringMVCfile(MultipartFile file, HttpServletRequest request, String relroot){
		if (file.getSize()>0) {
			String newName = reName(file.getOriginalFilename());
			String path = request.getSession().getServletContext().getRealPath(relroot);
			File targetFile = new File(path, newName);
			String imagePath = relroot +File.separator+ newName;
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				file.transferTo(targetFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return imagePath;
		}
		else{
			return null;
		}	
			
		
	}
	
}
