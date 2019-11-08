package com.example.firstaid.utils;

import com.example.firstaid.dto.FileEntity;
import com.example.firstaid.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  lijunming
 * on  date 2018-08-08
 * time 13:07
 */
@Component
@RequestMapping("/viewVideoUtil")
public class ViewVideoUtil {

    @Autowired
    private FileService service;
    /**
     * 根据本地图片全路径，响应给浏览器1个图片流
     */
    @RequestMapping("/showImage")
    public void showImage(HttpServletResponse response, @RequestParam("fileName")String fileName) {
        show(response,fileName,"image");
    }

    /**
     * 根据本地视频全路径，响应给浏览器1个视频
     */
    @RequestMapping("/showVideo")
    public void showVideo(HttpServletResponse response, @RequestParam("fileName")String fileName) {
        show(response,fileName,"video");
    }

    /**
     * 响应文件
     * @param response
     * @param fileName  文件全路径
     * @param type  响应流类型
     */
    public void  show(HttpServletResponse response, String fileName,String type){
        try{
            FileInputStream fis = new FileInputStream(fileName); // 以byte流的方式打开文件
            int i=fis.available(); //得到文件大小
            byte data[]=new byte[i];
            fis.read(data);  //读数据
            response.setContentType(type+"/*"); //设置返回的文件类型
            OutputStream toClient=response.getOutputStream(); //得到向客户端输出二进制数据的对象
            toClient.write(data);  //输出数据
            toClient.flush();
            toClient.close();
            fis.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("文件不存在");
        }
    }


    @RequestMapping(value = "/upload")
    public ModelAndView upload(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
                               HttpServletRequest request, ModelMap map) {
        String message = "";
        FileEntity entity = new FileEntity();
        String logoPathDir=request.getParameter("shipin");
        System.out.println("-------"+logoPathDir+"----------------------------------");
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(logoPathDir,multipartFile, request);
            if (entity != null) {
                //service.saveFile(entity);
                message = "上传成功";
                map.put("entity", entity);
                map.put("result", message);
            } else {
                message = "上传失败";
                map.put("result", message);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("/pages/views/result", map);
    }


    @RequestMapping(value = "/{id}/play")
    public ModelAndView playVideo(@PathVariable("id") long id, ModelMap model) {
        // CharterDto charterDto_ = charterFacade.getCharterById(id);
        //FileEntity entity = service.findByid(id);
        FileEntity entity = new FileEntity();
        model.put("entity", entity.getPath());
        ModelAndView view = new ModelAndView("index", model);
        return view;
    }


    @RequestMapping("/show")
    public ModelAndView showList(HttpServletRequest request, ModelMap map) {
        // 获取上传文件目录
        String logoPathDir = "/video/";
        String uploadFilePath =request.getSession().getServletContext().getRealPath(logoPathDir);
        // 存储要下载的文件名
        List<String> fileNameMap = new ArrayList<String>();
        this.listFile(new File(uploadFilePath), fileNameMap);
        map.put("list", fileNameMap);
        return new ModelAndView("listFile", map);


    }

    /**
     * @Description: 递归遍历指定目录下的所有文件
     * @param file：即代表一个文件，也代表一个文件目录
     * @param map：存储文件名的Map集合
     */
    public void listFile(File file, List<String> map) {
            // 如果file代表的不是一个文件，而是一个目录
        if (!file.isFile()) {
            // 列出该目录下的所有文件和目录
            File files[] = file.listFiles();
        // 遍历files[]数组
            for (File f : files) {
        // 递归
                listFile(f, map);
            }
        } else {
            map.add(file.getName());
        }
    }

    @RequestMapping(value = "/download")//下载
    public void download(@RequestParam(value = "filename", required = false) String fileName,
                         HttpServletRequest request, ModelMap map, HttpServletResponse response) {
        try {
            fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e2) {
// TODO Auto-generated catch block
            e2.printStackTrace();
        }
// 获取上传文件目录
        String logoPathDir = "/video/";
        String fileSaveRootPath = request.getSession().getServletContext().getRealPath(logoPathDir);
// 文件路径
        String fileDir = fileSaveRootPath + File.separator + fileName;
        File file = new File(fileDir);
        if (!file.exists()) {
            System.out.println("下载的文件不存在");
            return;
        }
// 设置响应头，控制浏览器下载该文件
        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
// TODO Auto-generated catch block
            e1.printStackTrace();
        }
// 读取要下载的文件，保存到文件输入流
        FileInputStream in = null;
        try {
            in = new FileInputStream(fileDir);
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
// 创建输出流
        OutputStream out;
        try {
            out = response.getOutputStream();
// 创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
// 循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
// 输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
// 关闭文件输入流
            in.close();
// 关闭输出流
            out.close();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

}
