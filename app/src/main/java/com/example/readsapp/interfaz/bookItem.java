package com.example.readsapp.interfaz;

import android.widget.ImageView;

public class bookItem {
    private String text;
    private ImageView image;

    public bookItem(){}

    public bookItem(String text, ImageView image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}