package com.example.SnapHive.SnapHive.controller;

import com.example.SnapHive.SnapHive.entities.Story;
import com.example.SnapHive.SnapHive.entities.User;
import com.example.SnapHive.SnapHive.exceptions.StoryException;
import com.example.SnapHive.SnapHive.exceptions.UserException;
import com.example.SnapHive.SnapHive.services.StoryService;
import com.example.SnapHive.SnapHive.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story")
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Story> createStory(@RequestBody Story story, @RequestHeader("Authorization") String token) throws UserException {
        User user = userService.findUserByToken(token);
        Story story1 = storyService.createStory(story,user.getId());
        return new ResponseEntity<Story>(story1, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Story>> findAllStoryByUserId(@PathVariable Long userId) throws StoryException, UserException {
        List<Story> stories = storyService.findStoryByUserId(userId);
        return new ResponseEntity<List<Story>>(stories,HttpStatus.OK);
    }
}