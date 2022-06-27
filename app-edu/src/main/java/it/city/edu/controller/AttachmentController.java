package it.city.edu.controller;

import it.city.edu.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.UUID;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public HttpEntity<?> upload(MultipartHttpServletRequest request){
        UUID upload = attachmentService.upload(request);
        return ResponseEntity.ok(upload);
    }

    @GetMapping("/download")
    public HttpEntity<?> getFile(@RequestParam(name = "id", required = false) UUID id){
        return attachmentService.getFile(id);
    }

}
