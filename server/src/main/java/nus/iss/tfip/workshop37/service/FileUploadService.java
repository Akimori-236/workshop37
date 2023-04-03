package nus.iss.tfip.workshop37.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import nus.iss.tfip.workshop37.model.Comment;
import nus.iss.tfip.workshop37.repository.CommentRepository;

@Service
public class FileUploadService {

    @Autowired
    private CommentRepository commentRepo;

    public String uploadComment(MultipartFile file, String commentText)
            throws SQLException, IOException {
        Comment c = Comment.create(commentText, file);
        commentRepo.uploadComment(c);
        return c.getPost_id();
    }

    // public Optional<Comment> getPostById(Integer postId) {
    //     return postRepo.getPostById(postId);
    // }
}