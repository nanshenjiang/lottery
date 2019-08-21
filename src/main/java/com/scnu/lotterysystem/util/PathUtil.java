package com.scnu.lotterysystem.util;

public class PathUtil {
  private static String seperator =System.getProperty("file.seperator");

  /* *
  * 依据系统环境的不同提供不同的根路径
  * 项目所有图片需要存放的路径
  * */
  public static String getImgBasePath(){
      String os= System.getProperty("os.name");
      String basePath="";
      //根据获取的系统不同设置不同path
      if(os.toLowerCase().startsWith("win")){
          basePath="D:/project/images/";
      }else {
          basePath="/home/project/images/";
      }
      //根据系统的不同“/"要做出改变
      basePath=basePath.replace("/",seperator);
      return basePath;
  }

  /* *
  *获取prize的id所需要的相对子路径
  * */
  public static String getPrizeImagePath(Integer id){
      String imagePath="upload/item/prize/"+id+"/";
      return imagePath.replace("/",seperator);
  }
}
