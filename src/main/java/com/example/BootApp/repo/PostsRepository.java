package com.example.BootApp.repo;


import com.example.BootApp.DTO.PostHeaderDTO;
import com.example.BootApp.models.Post;
import com.example.BootApp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post,Integer> {


    List<Post>findByOwner(Person owner);


    @Query("SELECT new com.example.BootApp.DTO.PostHeaderDTO(p.id, p.post_header) FROM Post p")
    List<PostHeaderDTO> selectHeaders();


}
