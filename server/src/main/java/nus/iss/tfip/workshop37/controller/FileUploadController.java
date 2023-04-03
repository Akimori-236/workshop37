package nus.iss.tfip.workshop37.controller;

import java.io.IOException;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import nus.iss.tfip.workshop37.service.FileUploadService;

@Controller
@CrossOrigin("*")
@RequestMapping(path = "/api")
public class FileUploadController {

    @Autowired
    private FileUploadService fileSvc;

    @PostMapping(path = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin("*")
    public ResponseEntity<String> saveUpload(
            @RequestPart String comment,
            @RequestPart MultipartFile imgFile) throws SQLException, IOException {

        // get back generated id of comment
        String id = fileSvc.uploadComment(imgFile, comment);

        String responseBody = Json.createObjectBuilder()
                .add("commentId", id)
                .build()
                .toString();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBody);
    }

    @GetMapping(path = "/post/{postId}")
    public ResponseEntity<String> getPostById(@PathVariable String postId) throws SQLException {
        String response = fileSvc.getPostById(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
