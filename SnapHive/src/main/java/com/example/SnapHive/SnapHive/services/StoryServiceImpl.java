package com.example.SnapHive.SnapHive.services;

import com.example.SnapHive.SnapHive.entities.Story;
import com.example.SnapHive.SnapHive.entities.User;
import com.example.SnapHive.SnapHive.exceptions.StoryException;
import com.example.SnapHive.SnapHive.exceptions.UserException;
import com.example.SnapHive.SnapHive.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService{

    private final StoryRepository storyRepository;
    private final UserService userService;

    @Override
    public Story createStory(Story story, Long userId) throws UserException {
        User user = userService.findUserById(userId);
        story.setUser(user);
        story.setTimestamp(LocalDateTime.now());
        user.getStories().add(story);
        return storyRepository.save(story);
    }

    @Override
    public List<Story> findStoryByUserId(Long userId) throws UserException, StoryException {
        User user = userService.findUserById(userId);
        List<Story> stories = storyRepository.findAllStoryByUserId(userId);
        return stories;
    }
}
