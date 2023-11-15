package com.example.BootApp.services;

import com.example.BootApp.DTO.AddPostDTO;
import com.example.BootApp.DTO.GetPostDTO;
import com.example.BootApp.DTO.PostHeaderDTO;
import com.example.BootApp.models.Person;
import com.example.BootApp.models.Post;
import com.example.BootApp.util.PostNotDeleted;
import com.example.BootApp.util.PostNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public GetPostDTO findOne(int id) throws JsonProcessingException;

    public void save(AddPostDTO post) ;






    public List<PostHeaderDTO> headers();

    public void delete(int id);

    public void update(int id, Post post);
}
