package com.brylkowski.mahumito.mahumito.models;

import android.graphics.drawable.Drawable;

public class Story {
    private Integer id;
    private String title;
    private String description;
    private String backgroundUrl;
    private Integer distance;

    public Story(
        Integer id,
        String title,
        String description,
        String backgroundUrl,
        Integer distance
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.backgroundUrl = backgroundUrl;
        this.distance = distance;
    }

    public Integer id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public String backgroundUrl() {
        return backgroundUrl;
    }

    public Integer distance() {
        return distance;
    }
}
