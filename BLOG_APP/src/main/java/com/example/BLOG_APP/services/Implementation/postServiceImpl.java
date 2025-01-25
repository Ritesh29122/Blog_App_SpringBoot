    package com.example.BLOG_APP.services.Implementation;

    import com.example.BLOG_APP.Exceptions.ResourceNotFoundException;
    import com.example.BLOG_APP.models.Category;
    import com.example.BLOG_APP.models.Post;
    import com.example.BLOG_APP.models.User;
    import com.example.BLOG_APP.payloads.PostDto;
    import com.example.BLOG_APP.payloads.postResponse;
    import com.example.BLOG_APP.repositories.UserRepo;
    import com.example.BLOG_APP.repositories.categoryRepo;
    import com.example.BLOG_APP.repositories.postRepo;
    import com.example.BLOG_APP.services.postService;
    import jakarta.persistence.criteria.CriteriaBuilder;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Sort;
    import org.springframework.stereotype.Service;

    import java.util.Date;
    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class postServiceImpl implements postService {

        @Autowired
        private postRepo postRepo;

        @Autowired
        private ModelMapper modelMapper;

        @Autowired
        private UserRepo userRepo;

        @Autowired
        private categoryRepo categoryRepo;


        @Override
        public PostDto createPost(PostDto postDto, Integer user_id, Integer category_id) {
            Post createpost=postDtoToPost(postDto);
            User user= this.userRepo.findById(user_id).orElseThrow(()->new ResourceNotFoundException("User", "Id", user_id));
            System.out.println("User found: " + user);
            Category category=this.categoryRepo.findById(category_id).orElseThrow(()->new ResourceNotFoundException("Category","Id",category_id));
            System.out.println("Category found: " + category);
            createpost.setImageName("default.png");
            createpost.setAddedDate(new Date());
            createpost.setUser(user);
            createpost.setCategory(category);

            Post newPost=this.postRepo.save(createpost);
            System.out.println("Post saved: " + newPost);

            return this.postToPostDto(newPost);
        }

        @Override
        public PostDto updatePost(PostDto postDto, Integer post_id) {
            Post newpost=this.postRepo.findById(post_id).orElseThrow(()->new ResourceNotFoundException("Post","Id",post_id));
            newpost.setTitle(postDto.getTitle());
            newpost.setContent(postDto.getContent());
            newpost.setImageName(postDto.getImageName());

            Post updatedPost=this.postRepo.save(newpost);
            return this.postToPostDto(updatedPost);
        }

        @Override
        public void deletePost(Integer post_id) {
            Post newpost=this.postRepo.findById(post_id).orElseThrow(()->new ResourceNotFoundException("Post","Id",post_id));
            this.postRepo.delete(newpost);
        }

        @Override
        public postResponse getAllPosts(Integer page_number, Integer page_size, String sort_by) {
            List<String> validSortFields = List.of("post_id", "title", "content", "addedDate");
            if (!validSortFields.contains(sort_by)) {
                throw new IllegalArgumentException("Invalid sort field: " + sort_by);
            }
            Pageable pageable = PageRequest.of(page_number, page_size, Sort.by(sort_by).ascending());
//            Pageable pageable = PageRequest.of(page_number, page_size, Sort.by(sort_by).ascending());
            Page<Post> pagePost = this.postRepo.findAll(pageable);
            List<Post>allPosts= pagePost.getContent();
            List<PostDto> postDtos=allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
            postResponse postResponse1=new postResponse();
            postResponse1.setPageNumber(pagePost.getNumber());
            postResponse1.setContent(postDtos);
            postResponse1.setPageSize(pagePost.getSize());
            postResponse1.setTotalElements(pagePost.getTotalElements());
            postResponse1.setTotalPages(pagePost.getTotalPages());

            return postResponse1;
        }

        @Override
        public List<PostDto> getAllPostsByCategoryId(Integer category_id) {
            Category category = this.categoryRepo.findById(category_id)
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", category_id));

            List<Post> posts = this.postRepo.findByCategory(category);
            List<PostDto> postDtoList=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
            return postDtoList;
        }

        @Override
        public List<PostDto> getAllPostsByUserId(Integer user_id) {
            User user = this.userRepo.findById(user_id)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "Id", user_id));

            List<Post> posts = this.postRepo.findByUser(user);
            return posts.stream()
                    .map(this::postToPostDto)
                    .toList();
        }

        @Override
        public List<PostDto> searchPost(String keyword) {
            List<Post> posts = this.postRepo.findByTitleContaining(keyword);
            List<PostDto>postDtos=posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
            return postDtos;
        }
        public PostDto postToPostDto(Post post){
            return this.modelMapper.map(post,PostDto.class);
        }

        public Post postDtoToPost(PostDto postDto){
            return this.modelMapper.map(postDto,Post.class);
        }
    }
