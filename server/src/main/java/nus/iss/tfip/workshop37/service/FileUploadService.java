package nus.iss.tfip.workshop37.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import nus.iss.tfip.workshop37.model.Post;
import nus.iss.tfip.workshop37.repository.PostRepository;

@Service
public class FileUploadService {
    @Autowired
    private PostRepository postRepo;

    public void uploadBlob(MultipartFile file, String comments)
            throws SQLException, IOException {
        postRepo.uploadBlob(file, comments);
    }

    public Optional<Post> getPostById(Integer postId) {
        return postRepo.getPostById(postId);
    }
}