package nus.iss.tfip.workshop37.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Data;

@Data
public class Post {
    private String post_id;
    private String comments;
    private byte[] picture;

    public static Post populate(ResultSet rs) throws SQLException {
        final Post post = new Post();
        post.setPost_id(rs.getString("id"));
        post.setComments(rs.getString("comments"));
        post.setPicture(rs.getBytes("blobc"));
        return post;
    }
}
