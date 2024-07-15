package org.example.finaltask.Controller;

import org.example.finaltask.response.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping("/v1")
public class UploadController {

    private String dirPath = "D:/Desktop/Idea project/FinalTask/file";

    @PostMapping("/upload")
    public JsonResult upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffix;

        File dirFile = new File(dirPath);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        file.transferTo(new File(dirPath+"/"+fileName));
        return JsonResult.ok("/"+fileName);
    }

    @PostMapping("/remove")
    public JsonResult remove(String url){
        File file = new File(dirPath+"/"+url);
        if(file.exists()){
            file.delete();
        }
        return JsonResult.ok();
    }
}
