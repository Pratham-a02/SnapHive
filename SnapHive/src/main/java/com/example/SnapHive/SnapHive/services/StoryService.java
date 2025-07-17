package com.example.SnapHive.SnapHive.services;

import com.example.SnapHive.SnapHive.entities.Story;
import com.example.SnapHive.SnapHive.exceptions.StoryException;
import com.example.SnapHive.SnapHive.exceptions.UserException;

import java.util.List;

public interface StoryService {

    public Story createStory(Story story,Long userId) throws UserException;

    public List<Story> findStoryByUserId(Long userId) throws UserException,StoryException;


}
