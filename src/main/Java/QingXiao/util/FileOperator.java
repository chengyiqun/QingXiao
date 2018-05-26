package QingXiao.util;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by xpb on 2017/10/23.
 */
public class FileOperator {
    public static final String AVATAR = "\\avatar\\";
    public static final String IDENTIFY_CODE="\\identifyCode\\";
    public static final String COURSE_RESOURCE = "\\courseResource\\";
    public static final String TOPIC_PICTURE = "topicPicture";

    public static void download(HttpServletRequest request, HttpServletResponse response, String storeName,
                                String contentType, String realName,String folder) throws Exception {

        //设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + realName);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        //用于记录以完成的下载的数据量，单位是byte
        long downloadedLength = 0l;
        // 下载源路径
        String ctxPath = request.getSession().getServletContext().getRealPath("/")
                +folder;
        String downLoadPath = ctxPath + storeName;

        long fileLength = new File(downLoadPath).length();

        response.setContentType(contentType);
        System.out.println("111realName3 "+realName);
        response.setHeader("Content-disposition",
                "attachment; filename=" + new String(realName.getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
            downloadedLength += buff.length;
        }
        bis.close();
        bos.close();
    }

    public static void download(HttpServletRequest request,HttpServletResponse response, String contentType, String storeName,String folder) throws Exception {

        //设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        //response.setHeader("Content-Disposition", "attachment;fileName=" + realName);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        //用于记录以完成的下载的数据量，单位是byte
        long downloadedLength = 0l;
        // 下载源路径
        String ctxPath = request.getSession().getServletContext().getRealPath("/")+folder;
        String downLoadPath = ctxPath + storeName;

        long fileLength = new File(downLoadPath).length();
        System.out.println("文件长度："+fileLength);
        response.setContentType(contentType);
        //response.setHeader("Content-disposition", "attachment; filename=" + new String(realName.getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
            downloadedLength += buff.length;
        }
        bis.close();
        bos.close();
    }

    public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename)throws Exception {
        //下载文件路径
        String path = request.getServletContext().getRealPath("/images/");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    // 判断文件夹是否存在
    public static void judeDirExists(String path) {
        File file=new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }
    }

    /**
     * 判断文件及目录是否存在，若不存在则创建文件及目录
     * @param filepath
     * @return
     * @throws Exception
     */
    public static void checkExist(String filepath) throws Exception{
        File file=new File(filepath);

        if (file.exists()) {//判断文件目录的存在
            System.out.println("文件夹存在！");
           /* if(file.isDirectory()){//判断文件的存在性
                System.out.println("文件存在！");
            }else{
                file.createNewFile();//创建文件
                System.out.println("文件不存在，创建文件成功！"   );
            }*/
        }else {
            System.out.println("文件夹不存在！");
           // File file2=new File(file.getParent());
            file.mkdirs();
            System.out.println("创建文件夹成功！");
          /*  if(file.isDirectory()){
                System.out.println("文件存在！");
            }else{
                file.createNewFile();//创建文件
                System.out.println("文件不存在，创建文件成功！"   );
            }*/
        }
       // return file;
    }

}
