package com.example.BootApp.services.impl;


import com.example.BootApp.DTO.AddPostDTO;
import com.example.BootApp.DTO.FilterDataDTO;
import com.example.BootApp.DTO.GetPostDTO;
import com.example.BootApp.DTO.PostHeaderDTO;
import com.example.BootApp.models.Post;
import com.example.BootApp.models.Person;
import com.example.BootApp.models.Post_atribute;
import com.example.BootApp.repo.AtributeRepo;
import com.example.BootApp.repo.PeopleRepositorry;
import com.example.BootApp.repo.PostsRepository;
import com.example.BootApp.services.PostService;
import com.example.BootApp.specifications.PostSpecifications;
import com.example.BootApp.util.PostMapperImpl;
import com.example.BootApp.util.PostNotDeleted;
import com.example.BootApp.util.PostNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostServisImpl implements PostService {


    private final PostsRepository postsRepository;
    private final AtributeRepo atributeRepo;

    private final PeopleRepositorry peopleRepositorry;
    public PostServisImpl(PostsRepository postsRepository, AtributeRepo atributeRepo, PeopleRepositorry peopleRepositorry) {


        this.postsRepository = postsRepository;

        this.atributeRepo = atributeRepo;
        this.peopleRepositorry = peopleRepositorry;
    }

    public List<Post> getPostsWithFilter(FilterDataDTO dataDTO) {
        Specification<Post> spec = PostSpecifications.filterPosts(dataDTO);

       return postsRepository.findAll(spec);
    }

    private String buildConditionsString(String postHeader, String postCity, String postType, String company) {
        StringBuilder conditions = new StringBuilder();

        if (postHeader != null && !postHeader.isEmpty()) {
            conditions.append(" AND p.postHeader = '").append(postHeader).append("'");
        }

        if (postCity != null && !postCity.isEmpty()) {
            conditions.append(" AND p.postCity = '").append(postCity).append("'");
        }

        if (postType != null && !postType.isEmpty()) {
            conditions.append(" AND p.postType = '").append(postType).append("'");
        }

        if (company != null && !company.isEmpty()) {
            conditions.append(" AND p.company = '").append(company).append("'");
        }

        return conditions.toString();
    }

    @Transactional
    @Override
    public GetPostDTO findOne(int id)   {


        Post foundPost= postsRepository.findById(id).orElseThrow(PostNotFoundException::new);
        ModelMapper modelMapper=new ModelMapper();
        GetPostDTO getPostDTO=modelMapper.map(foundPost,GetPostDTO.class);

        System.out.println(getPostDTO);
        return getPostDTO;

    }
    @Transactional
    public void save(AddPostDTO post) {
        PostMapperImpl mapper=new PostMapperImpl();
        Post postForSave=  mapper.map(post.getPosts());
        Person person =peopleRepositorry.findById(post.getPosts().getOwner().getId()).orElse(null);
        postForSave.setOwner(person);
        Post savedPost = postsRepository.save(postForSave);

        for (Post_atribute obj : post.getData()) {
            obj.setPost(savedPost);
            atributeRepo.save(obj);
        }




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
