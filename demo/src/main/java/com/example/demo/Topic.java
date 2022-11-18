package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String name;
    private List<String> comments;
    public Topic(){
        this.name = "New topic";
        this.comments = new ArrayList<>();
    }
    public Topic(String name){
        this.name = name;
        this.comments = new ArrayList<>();
    }
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder(this.name);
        for (String comment : comments){
            s.append("\n");
            s.append(comment);
        }
        return s.toString();
    }
    public String getName(){
        return this.name;
    }
    public void rename(String newName){
        this.name = newName;
    }
    public void addComment(String comment){
        this.comments.add(comment);
    }
    public void deleteComment(Integer index){
        this.comments.remove((int) index);
    }
    public void editComment(Integer index, String newComment){
        this.comments.remove((int) index);
        this.comments.add(index, newComment);
    }

}
