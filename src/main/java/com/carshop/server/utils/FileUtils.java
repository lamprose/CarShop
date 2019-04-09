package com.carshop.server.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {

    //根据用户名和日期重命名文件
    public static String formatFileName(String id, String originalFileName){

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 用户Id+当前时间,例如Peter_2019040418183840.png
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = id + '_' + date + "." + type;

        return fileName;
    }

    //查询某一目录下是否存在某个文件，如果存在则删除
    public static boolean deleteFile(String id, String directoryPath){
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!directoryPath.endsWith(File.separator)) {
            directoryPath = directoryPath + File.separator;
        }
        File dirFile = new File(directoryPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }

        String fileName;
        // 若文件夹下已存在某一文件则删除
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            fileName = files[i].getName();
            //文件名下划线之前的即id
            if(id.equals(fileName.substring(0, fileName.indexOf("_")))){
                System.out.println("头像已存在");
                return files[i].delete();
            }
        }
        return true;
    }

}
