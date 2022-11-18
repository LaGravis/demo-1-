package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class ApiController {
    final List<Topic> topics = new ArrayList<>();

    @PostMapping("topics")
    public ResponseEntity<Void> addTopic(@RequestBody String name){
        topics.add(new Topic(name));
        return ResponseEntity.accepted().build();
    }

    @GetMapping("topics")
    public ResponseEntity<List<String>> getTopics(){
        List<String> names = new ArrayList<>();
        for (Topic i : topics){
            names.add(i.getName());
        }
        return ResponseEntity.ok(names);
    }

    @DeleteMapping("topics")
    public ResponseEntity<Void> deleteAllTopics(){
        topics.clear();
        return ResponseEntity.accepted().build();
    }

    @GetMapping("topics/{index}")
    public ResponseEntity<String> getTopic(@PathVariable("index") Integer index){
        return ResponseEntity.ok(topics.get(index).getName());
    }

    @DeleteMapping("topics/{index}")
    public ResponseEntity<Void> deleteTopic(
            @PathVariable("index") Integer index){
        topics.remove((int) index);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("topics/{index}")
    public ResponseEntity<Void> renameTopic(
            @PathVariable("index") Integer index, @RequestBody String newName){
        topics.get(index).rename(newName);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("topics/count")
    public ResponseEntity<Integer> countTopics(){
        return ResponseEntity.ok(topics.size());
    }

    @PostMapping("topics/{topicIndex}/comments")
    public ResponseEntity<Void> addComment(@PathVariable("topicIndex") Integer index, @RequestBody String comment){
        topics.get(index).addComment(comment);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("topics/{index}/comments")
    public ResponseEntity<String> getComments(@PathVariable("index") Integer index){
        return ResponseEntity.ok(topics.get(index).toString());
    }

    @DeleteMapping("topics/{topicIndex}/comments/{commentIndex}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable("topicIndex") Integer index1, @PathVariable("commentIndex") Integer index2){
        topics.get(index1).deleteComment(index2);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("topics/{topicIndex}/comments/{commentIndex}")
    public ResponseEntity<Void> editComment(
            @PathVariable("topicIndex") Integer index1,
            @PathVariable("commentIndex") Integer index2,
            @RequestBody String newComment){
        topics.get(index1).editComment(index2, newComment);
        return ResponseEntity.accepted().build();
    }











    final List<String> messages = new ArrayList<>();
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("messages/search/{text}")
    public ResponseEntity<Integer> searchText(
            @PathVariable("text") String text) {
        for (int i = 0; i < messages.size(); ++i){
            if (messages.get(i).contains(text))
                return ResponseEntity.ok(i);
        }
        return ResponseEntity.ok(-1);
    }
    @GetMapping("messages/count")
    public ResponseEntity<Integer> countMessages(){
        return ResponseEntity.ok(messages.size());
    }


}

