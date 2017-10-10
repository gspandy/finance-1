package com.easyrong.wireless.finance.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class FileController {

    public static final Logger logger = LogManager.getLogger(FileController.class.getName());

    /**
     * 获取file.html页面
     *
     * @return
     */
    @RequestMapping("file")
    public String file() {
        return "/file";
    }

    /**
     * 实现文件上传
     */
    @RequestMapping("fileUpload")
    public String fileUpload(@RequestParam("fileName") MultipartFile file, Model model, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        logger.info(fileName + "-->" + size);

        String path = request.getSession().getServletContext().getRealPath("/uploadFile");

        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();


        }

        model.addAttribute("messages", "文件上传失败!");

        try {
            file.transferTo(dest); //保存文件
            model.addAttribute("messages", "文件：" + fileName + " 上传到: " + path + " 成功!");
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "succeed";
    }

    /**
     * 获取multifile.html页面
     *
     * @return
     */
    @RequestMapping("multifile")
    public String multifile() {
        return "/multifile";
    }

    /**
     * 实现多文件上传
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "multifileUpload", method = RequestMethod.POST)
    public String multifileUpload(Model model, HttpServletRequest request) {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");

        model.addAttribute("messages", "文件上传失败!");

        if (files.isEmpty()) {
            return "succeed";
        }

        String path = request.getSession().getServletContext().getRealPath("/uploadFile");
        StringBuilder sb = new StringBuilder();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            logger.info(fileName + "-->" + size);

            if (file.isEmpty()) {
                return "succeed";
            } else {
                File dest = new File(path + "/" + fileName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);

                    sb.append("文件：" + fileName + " 上传到: " + path + " 成功!" + "\r\n");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        model.addAttribute("messages", sb.toString());
        return "succeed";
    }

    @RequestMapping("download")
    public String downLoad(HttpServletResponse response, HttpServletRequest request) {
        String filename = "Special_01.png";
        String filePath = request.getSession().getServletContext().getRealPath("/uploadFile");
        File file = new File(filePath + "/" + filename);
        if (file.exists()) { //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            logger.info("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
