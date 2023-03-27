package nus.iss.tfip.workshop37.model;

import java.sql.Blob;

import lombok.Data;

@Data
public class Post {
    private String post_id;
    private String comments;
    private Blob picture;
}
