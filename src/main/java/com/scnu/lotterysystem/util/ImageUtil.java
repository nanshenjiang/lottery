package com.scnu.lotterysystem.util;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r=new Random();

    /* *
    *  将File转换为CommonsMultipartFile
    * */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cfile){
        File newFile=new File(cfile.getOriginalFilename());
        try {
            cfile.transferTo(newFile);
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return newFile;
    }

    public static String generateThumbnail(File thumbnail,String targetAddr){
        String realFileName= getRandomFileName();
        String extension= getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr=targetAddr+realFileName+extension;
        File dest=new File(PathUtil.getImgBasePath());
        return relativeAddr;
    }

    /* *
    * 生成随机文件名，当前时间（年月日时间秒+五位随机数）
    * */
    private static String getRandomFileName(){
        int rannum=r.nextInt(89999)+10000;
        String nowTimeStr =sDateFormat.format(new Date());
        return nowTimeStr+rannum;
    }

    /* *
    * 获取输入文件流的扩展名
    * */
    private static String getFileExtension(File cfile){
        String originalFileName =cfile.getName();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /* *
    * 创建目录所涉及到的文件目录
    * */
    private static void makeDirPath(String targetAddr){
        String realFileParentPath= PathUtil.getImgBasePath() +targetAddr;
        File dirPath=new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }
}
