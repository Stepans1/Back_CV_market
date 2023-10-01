package com.example.BootApp.DTO;

public class PostHeaderDTO {

    private int id;
    private String post_header;

    public PostHeaderDTO(int id, String post_header) {
        this.id = id;
        this.post_header = post_header;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostHeader() {
        return post_header;
    }

    public void setPostHeader(String postHeader) {
        this.post_header = postHeader;
    }
}
