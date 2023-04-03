package nus.iss.tfip.workshop37.service;

import java.io.IOException;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import nus.iss.tfip.workshop37.model.Post;
import nus.iss.tfip.workshop37.repository.PostRepository;

@Service
public class FileUploadService {

    @Autowired
    private PostRepository postRepo;

    public String uploadComment(MultipartFile file, String commentText)
            throws SQLException, IOException {
        Post p = Post.create(commentText, file);
        postRepo.uploadPost(p);
        return p.getPost_id();
    }

    public String getPostById(String postId) throws SQLException {
        Post p = postRepo.getPostById(postId);
        return p.toJsonString();
    }
}