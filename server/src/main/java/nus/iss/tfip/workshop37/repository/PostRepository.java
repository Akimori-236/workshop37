package nus.iss.tfip.workshop37.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nus.iss.tfip.workshop37.model.Post;

@Repository
public class PostRepository {

    @Autowired
    private JdbcTemplate template;

    private String SQL_INSERT_POST = """
            INSERT INTO posts(post_id, comments, picture)
            VALUES(?, ? ,?)
            """;

    public Boolean savePost(Post post) {
        return template.update(SQL_INSERT_POST,
                post.getPost_id(),
                post.getComments(),
                post.getPicture()) > 0;
    }
}
