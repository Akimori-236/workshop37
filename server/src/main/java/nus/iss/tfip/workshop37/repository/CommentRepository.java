package nus.iss.tfip.workshop37.repository;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import nus.iss.tfip.workshop37.model.Comment;

@Repository
public class CommentRepository {
    private static final String SQL_INSERT_COMMENT = "INSERT INTO posts (post_id, comments, picture, picture_filetype) VALUES(?, ?, ?, ?)";

    // private static final String SQL_GET_POST_BY_POST_ID = "SELECT post_id, comments, picture FROM posts WHERE post_id=?";

    // @Autowired
    // private DataSource dataSource;

    @Autowired
    private JdbcTemplate template;

    public void uploadComment(Comment comment)
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

    // public Optional<Comment> getPostById(Integer postId) {
    // return template.query(
    // SQL_GET_POST_BY_POST_ID,
    // (ResultSet rs) -> {
    // if (!rs.next())
    // return Optional.empty();
    // final Comment post = Comment.populate(rs);
    // return Optional.of(post);
    // },
    // postId);
    // }

}