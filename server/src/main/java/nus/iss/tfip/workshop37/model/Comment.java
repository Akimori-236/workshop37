package nus.iss.tfip.workshop37.model;

import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Comment {
    private String post_id;
    private String comments;
    private Blob picture;
    private String fileType;

    public static Comment create(String commentText, MultipartFile imgFile) throws IOException, SerialException, SQLException {
        Comment comment = new Comment();
        // GENERATE ID
        String id = UUID.randomUUID().toString().substring(0, 8);
        comment.setPost_id(id);
        comment.setComments(commentText);
        Blob b = new SerialBlob(imgFile.getBytes());
        comment.setPicture(b);
        comment.setFileType(imgFile.getContentType());
        return comment;
    }

    // public static Comment populate(ResultSet rs) throws SQLException {
    // final Comment post = new Comment();
    // post.setPost_id(rs.getString("id"));
    // post.setComments(rs.getString("comments"));
    // post.setPicture(rs.getBytes("blobc"));
    // return post;
    // }
}
