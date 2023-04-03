package nus.iss.tfip.workshop37.model;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.UUID;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import lombok.Data;

@Data
public class Post {
    private String post_id;
    private String comments;
    private Blob picture;
    private String fileType;

    public static Post create(String commentText, MultipartFile imgFile) throws IOException, SerialException, SQLException {
        Post comment = new Post();
        // GENERATE ID
        String id = UUID.randomUUID().toString().substring(0, 8);
        comment.setPost_id(id);
        comment.setComments(commentText);
        Blob b = new SerialBlob(imgFile.getBytes());
        comment.setPicture(b);
        comment.setFileType(imgFile.getContentType());
        return comment;
    }

    public String toJsonString() throws SQLException {
        byte[] pictureBytes = this.getPicture().getBytes(1, (int) this.getPicture().length());
        String pictureBase64 = Base64.getEncoder().encodeToString(pictureBytes);
        return Json.createObjectBuilder()
                .add("post_id", this.getPost_id())
                .add("comments", this.getComments())
                .add("picture", pictureBase64)
                .build()
                .toString();
    }

    // public static Comment populate(ResultSet rs) throws SQLException {
    // final Comment post = new Comment();
    // post.setPost_id(rs.getString("id"));
    // post.setComments(rs.getString("comments"));
    // post.setPicture(rs.getBytes("blobc"));
    // return post;
    // }
}
