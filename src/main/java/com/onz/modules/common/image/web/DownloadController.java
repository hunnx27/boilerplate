package com.onz.modules.common.image.web;

import com.onz.common.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Controller
public class DownloadController {
    private final FileUtil fileUtil;
    private final ResourceLoader resourceLoader;



    @GetMapping("/download/image")
    @ResponseBody
    public ResponseEntity<Resource>  getImage(@RequestParam String path) throws IOException {
        File file = fileUtil.getDownloadFile(path);
        try {
//            Resource resource = resourceLoader.getResource(file.getPath());
            byte[] fileByte = FileUtils.readFileToByteArray(file);




//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION,file.getName())	//다운 받아지는 파일 명 설정
//                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))	//파일 사이즈 설정
//                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString())	//바이너리 데이터로 받아오기 설정
//                    .body(fileByte);	//파일 넘기기

            Path pathObj = Paths.get(file.getPath());
            String contentType = Files.probeContentType(pathObj);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(
                    ContentDisposition.builder("attachment")
                            .filename(file.getName(), StandardCharsets.UTF_8)
                            .build());
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);

            Resource resource = new InputStreamResource(Files.newInputStream(pathObj));
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);

        } catch (Exception e ) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }


    @GetMapping("/download/image2")
    @ResponseBody
    public Resource  getImage2(@RequestParam String path) throws IOException {
        File file = fileUtil.getDownloadFile(path);

        Resource resource = resourceLoader.getResource("file:"+file.getPath());
        return resource;
    }
}
