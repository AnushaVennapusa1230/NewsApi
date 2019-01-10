package com.example.anusha.newsapi;

public class NewsModel {
    String title,BookImage,Description;

    public NewsModel(String title, String bookImage, String description) {
        this.title = title;
        BookImage = bookImage;
        Description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookImage() {
        return BookImage;
    }

    public void setBookImage(String bookImage) {
        BookImage = bookImage;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
