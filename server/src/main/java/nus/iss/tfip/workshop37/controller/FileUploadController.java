package nus.iss.tfip.workshop37.controller;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nus.iss.tfip.workshop37.model.Post;
import nus.iss.tfip.workshop37.service.FileUploadService;
import nus.iss.tfip.workshop37.service.S3Service;

@Controller
@RequestMapping(path = "/api")
public class FileUploadController {

    @Autowired
    private S3Service s3Svc;

    @Autowired
    private FileUploadService flSvc;
    private static final String BASE64_PREFIX_DECODER = "data:image/png;base64,";

    @PostMapping(path = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin("*")
    public ResponseEntity<String> saveUpload(
            @RequestPart MultipartFile imgFile,
            @RequestPart String comments) {
        String key = "";
        System.out.printf("comments: %s", comments);

        try {
            key = s3Svc.upload(imgFile);
            flSvc.uploadBlob(imgFile, comments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObject payload = Json.createObjectBuilder()
                .add("imageKey", key)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload.toString());
    }

    @GetMapping(path = "/get-image/{postId}")
    public String retrieveImage(@PathVariable Integer postId, Model model) {
        Optional<Post> opt = flSvc.getPostById(postId);
        Post p = opt.get();
        String encodedString = Base64.getEncoder().encodeToString(p.getPicture());
        model.addAttribute("postId", p.getPost_id());
        model.addAttribute("comments", p.getComments());
        model.addAttribute("file", BASE64_PREFIX_DECODER + encodedString);
        return "blob";
    }
}
