package com.example.demo.model;

public class TODO {
    private Integer Id;
    private String Content;

    public TODO(Integer id, String content) {
        Id = id;
        Content = content;
    }

    public TODO() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
