package com.busanit501.bootpractice.controller;

import com.busanit501.bootpractice.dto.upload.UploadFileDTO;
import com.busanit501.bootpractice.dto.upload.UploadResultDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/files")
@Log4j2
public class FileController {
    @Value("${com.busanit501.upload.path}")
    private String uploadPath;


    @Tag(name = "파일 업로드", description = "UploadFileDTO 를 받아 저장하고 저장한 파일들의 정보를 result List 로 만들어 반환")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {
        if (uploadFileDTO.getFiles() != null && !uploadFileDTO.getFiles().isEmpty()) {
            List<UploadResultDTO> result = new ArrayList<>();
            uploadFileDTO.getFiles().forEach(file -> {
                String fileName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                Path path = Paths.get(uploadPath,uuid + "_" + fileName);
                boolean img = false;
                try {
                    file.transferTo(path); // 원본을 path 에 저장 이 시점부터 path 엔 파일이 있음
                    if (Files.probeContentType(path).startsWith("image")){
                        img = true;
                        File thumbnail = new File(uploadPath,"thumb_" + uuid + "_" + fileName);
                        // path 를 file 로 변환하고 이것을 thumbnail 파일의 경로에 저장 , 200,200 으로
                        Thumbnailator.createThumbnail(path.toFile(), thumbnail, 200, 200);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // uuid 와 fileName 으로 path 즉 file 을 지정하므로 반환값은 uuid 와 fileName , 그리고 이미지여부
                result.add(UploadResultDTO.builder()
                        .fileName(fileName)
                        .uuid(uuid)
                        .img(img)
                        .build());
            });
            return result;
        }
        return null;
    }

    @Tag(name = "파일 조회", description = "fileName 을 받아서 파일을 ResponseEntity 에 담아 반환")
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> view(@PathVariable String fileName) {
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        log.info(resource);
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
            return ResponseEntity.ok().headers(headers).body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().headers(headers).body(null);
        }
    }

    @Tag(name = "삭제", description = "fileName 을 받아서 삭제하고 삭제되었는지 여부를 반환")
    @DeleteMapping("/delete/{fileName}")
    public Map<String, Boolean> delete(@PathVariable String fileName) {
        Resource resource = new FileSystemResource(Paths.get(uploadPath).resolve(fileName).normalize());
        boolean deleted = false;
        Map<String, Boolean> result = new HashMap<>();
        try{
          if (Files.probeContentType(resource.getFile().toPath()).startsWith("image")){
              File thumbnail = Paths.get(uploadPath).resolve("thumb_"+fileName).normalize().toFile();
              thumbnail.delete();
          }
          deleted = resource.getFile().delete();
        } catch (Exception e){
            e.printStackTrace();
        }
        result.put("deleted", deleted);
        return result;
    }

    @Tag(name = "다운로드", description = "fileName 을 받아서 해당 이름의 경로를 url 로 만들어 반환")
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName) {
        Path path = Paths.get(uploadPath).resolve(fileName).normalize();
        try {
            Resource resource = new UrlResource(path.toUri());
            if (!resource.exists()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().header("content-disposition"," attachment; filename= \" " + resource.getFilename() + " \" ").body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
