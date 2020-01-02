package com.dygstudio.epsms.service.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.io.File;

/**
 * 常用的 MultipartFile 对象的方法：
 *  byte[] getBytes()
 *  String getContentType()
 *  InputStream getInputStream()
 *  String getName()
 *  String getOriginalFilename()
 *  long getSize()
 *  boolean isEmpty()
 *  void transferto(File dest)
 *
 * @className: CommonController
 * @package: com.dygstudio.epsms.service.controller
 * @author: diyaguang
 * @date: 2020/1/2 21:27
 */
@Controller
public class CommonController {

    /**
     * 可以在参数中使用 @ModelAttribute 来使用实体对象来传入表单对象的方式。表单中元素的名称对应实体的属性。
     * 表单提交的时候会自动对应属性，并赋值上传。
     */
    @ResponseBody
    @PostMapping("/uploadFile")
    public String upload(HttpServletRequest request,
                         @RequestParam("description") String description, @RequestParam("file") MultipartFile file) throws Exception{
        System.out.println("description="+description);
        if(!file.isEmpty()){
            String path = request.getServletContext().getRealPath("/uploadFile/");
            System.out.println("path="+path);
            String fileName = file.getOriginalFilename();
            File filepath = new File(path,fileName);
            if(!filepath.getParentFile().exists()){
                filepath.getParentFile().mkdirs();
            }
            file.transferTo(new File(path+ File.separator+fileName));  //保存目标文件
            return "success";
        }else{
            return "error";
        }
    }

    @RequestMapping("/downloadFile")
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @RequestParam("filename") String fileName,
                                           @RequestHeader("User-Agent") String userAgent,
                                           Model model) throws Exception{
        String path = request.getServletContext().getRealPath("/upload/");
        File file = new File(path+File.separator+fileName);
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        builder.contentLength(file.length());
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        fileName = URLEncoder.encode(fileName,"UTF-8");
        if(userAgent.indexOf("MSIE")>0){
            builder.header("Content-Disposition","attachment;filename="+fileName);
        }else{
            builder.header("Content-Disposition","attachment; filename*=UTF-8 ''"+fileName);
        }
        return builder.body(FileUtils.readFileToByteArray(file));
    }
}
