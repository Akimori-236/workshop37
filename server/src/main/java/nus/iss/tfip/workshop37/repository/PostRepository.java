package nus.iss.tfip.workshop37.repository;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import nus.iss.tfip.workshop37.model.Post;

@Repository
public class PostRepository {
    private static final String SQL_INSERT_COMMENT = "INSERT INTO posts (post_id, comments, picture, picture_filetype) VALUES(?, ?, ?, ?)";

    private static final String SQL_GET_POST_BY_POST_ID = "SELECT * FROM posts WHERE post_id=?";

    // @Autowired
    // private DataSource dataSource;

    @Autowired
    private JdbcTemplate template;

    public void uploadPost(Post comment)
            throws SQLException, IOException {
        template.update((PreparedStatementCreator) con -> {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_COMMENT);
            ps.setString(1, comment.getPost_id());
            ps.setString(2, comment.getComments());
            ps.setBlob(3, comment.getPicture());
            ps.setString(4, comment.getFileType());
            return ps;
        });
    }

    public Post getPostById(String postId) {
        return template.queryForObject(SQL_GET_POST_BY_POST_ID, BeanPropertyRowMapper.newInstance(Post.class), postId);
    }
}