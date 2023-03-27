package nus.iss.tfip.workshop37.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nus.iss.tfip.workshop37.model.Post;
import nus.iss.tfip.workshop37.repository.PostRepository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@ResponseBody
@RequestMapping(path = "/api")
public class PostRestController {

    @Autowired
    private PostRepository postRepo;

    @PostMapping(path = { "/post",
            "/post/" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveBlob(@RequestBody Post post) {
        post.setPost_id(UUID.randomUUID().toString().substring(0, 8));
        Boolean isSaved = postRepo.savePost(post);
        if (isSaved) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("saved");
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("NOT saved");
        }
    }
}
