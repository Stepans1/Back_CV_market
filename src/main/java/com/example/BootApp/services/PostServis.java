package com.example.BootApp.services;


import com.example.BootApp.DTO.PostHeaderDTO;
import com.example.BootApp.models.Post;
import com.example.BootApp.models.Person;
import com.example.BootApp.repo.PeopleRepositorry;
import com.example.BootApp.repo.PostsRepository;
import com.example.BootApp.util.PostNotDeleted;
import com.example.BootApp.util.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostServis {


    private final PostsRepository postsRepository;



    public PostServis(PostsRepository postsRepository) {


        this.postsRepository = postsRepository;

    }


    public Post findOne(int id) {
        Optional<Post> foundPerson=postsRepository.findById(id);

        return foundPerson.orElseThrow(PostNotFoundException::new);

    }
    @Transactional
    public void save(Post post,Person person) {

        post.setOwner(person);


         postsRepository.save(post);
    }





    public List<PostHeaderDTO> headers() {
        return postsRepository.selectHeaders();
    }
    @Transactional
    public void delete(int id) {

            Post post=postsRepository.findById(id).orElseThrow(PostNotDeleted::new);

            postsRepository.delete(post);


    }
    @Transactional
    public void update(int id, Post post) {
        post.setId(id);
        postsRepository.save(post);
    }


}
