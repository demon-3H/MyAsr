package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.example.utils.BaiduAsrUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@Slf4j
public class AsrController {
    @PostMapping("/conversion")
    public String conversion(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartRequest.getFile("file");
        if(file==null){return "0";}
        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        String test = BaiduAsrUtil.run(bytes);
        log.info(test);
        return "解析为："+test;
    }
}
