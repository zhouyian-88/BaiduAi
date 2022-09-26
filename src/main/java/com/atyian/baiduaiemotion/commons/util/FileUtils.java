package com.atyian.baiduaiemotion.commons.util;


import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-22-14:12
 */
public class FileUtils {
    /**
     * 该方法用于通过从HttpServletRequest对象中获取文件数据并返回
     *
     * @param req 请求对象
     * @return
     */
    private static  final int BUFFER_SIZE = 2048;
    public static byte[] getFileDataByRequest(HttpServletRequest req) {
        //上下文
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            //DiskFileItemFactory是FileItemFactory的实现类
            DiskFileItemFactory disk = new DiskFileItemFactory();
            //创建一个ServletFileUpload
            ServletFileUpload fileUpload = new ServletFileUpload(disk);
            fileUpload.setHeaderEncoding("utf-8");
            ServletRequestContext context = new ServletRequestContext(req);
            List<FileItem> fileItems = fileUpload.parseRequest(context);
            //遍历fileItems
            for(int i = 0;i < fileItems.size();i++){
                FileItem fileItem = fileItems.get(i);

//                System.out.println(" fileIten"+fileItem);
                //看item是不是文件内容
                //true：正常的表单字段
                //false:文件内容
                if(!fileItem.isFormField()){//如果fileItem不是正常表单，就按文件读
                    inputStream = fileItem.getInputStream();
                    //创建一个缓冲流
                    byte[] buff = new byte[BUFFER_SIZE];
                    int length = 0;
                    //当length等于-1，就说明读完了
                    while((length=inputStream.read(buff))!=-1){
                        //使用输出流把它写出去
                        outputStream.write(buff,0,length);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return outputStream.toByteArray();
    }

}
