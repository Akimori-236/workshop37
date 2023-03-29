package nus.iss.tfip.workshop37.repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import nus.iss.tfip.workshop37.model.Post;

@Repository
public class PostRepository {
    private static final String INSERT_POSTS_TBL = "INSERT INTO posts (comments, picture) VALUES(?, ?)";

    private static final String SQL_GET_POST_BY_POST_ID =
       "SELECT post_id, comments, picture FROM posts WHERE post_id=?";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate template;

    public void uploadBlob(MultipartFile file, String comments)
            throws SQLException, IOException  {

        try (Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(INSERT_POSTS_TBL)) {
            InputStream is = file.getInputStream();
            pstmt.setString(1, comments);
            pstmt.setBinaryStream(2, is, file.getSize());

            pstmt.executeUpdate();
        }
    }

    public Optional<Post> getPostById(Integer postId){
        return template.query(
            SQL_GET_POST_BY_POST_ID,
            (ResultSet rs) -> {
                if(!rs.next())
                    return Optional.empty();
                final Post post = Post.populate(rs);
                return Optional.of(post);
            },
            postId
        );
    }

}