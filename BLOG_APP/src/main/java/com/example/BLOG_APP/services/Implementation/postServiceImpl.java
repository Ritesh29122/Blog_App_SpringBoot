    package com.example.BLOG_APP.services.Implementation;

    import com.example.BLOG_APP.Exceptions.ResourceNotFoundException;
    import com.example.BLOG_APP.models.Category;
    import com.example.BLOG_APP.models.Post;
    import com.example.BLOG_APP.models.User;
    import com.example.BLOG_APP.payloads.PostDto;
    import com.example.BLOG_APP.repositories.UserRepo;
    import com.example.BLOG_APP.repositories.categoryRepo;
    import com.example.BLOG_APP.repositories.postRepo;
    import com.example.BLOG_APP.services.postService;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.Date;
    import java.util.List;

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
        public Post createPost(PostDto postDto, Integer user_id, Integer category_id) {
            Post createpost=postDtoToPost(postDto);
            User user= this.userRepo.findById(user_id).orElseThrow(()->new ResourceNotFoundException("User", "Id", user_id));
            Category category=this.categoryRepo.findById(category_id).orElseThrow(()->new ResourceNotFoundException("Category","Id",category_id));

            createpost.setImageName("default.png");
            createpost.setAddedDate(new Date());
            createpost.setUser(user);
            createpost.setCategory(category);
            return createpost;
        }

        @Override
        public Post updatePost(PostDto postDto, Integer post_id) {
            return null;
        }

        @Override
        public void deletePost(Integer post_id) {

        }

        @Override
        public List<Post> getAllPosts() {
            return List.of();
        }

        @Override
        public Post getAllPostsByCategoryId(Integer category_id) {
            return null;
        }

        @Override
        public Post getAllPostsByUserId(Integer user_id) {
            return null;
        }

        @Override
        public List<Post> searchPost(String keyword) {
            return List.of();
        }
        public PostDto postToPostDto(Post post){
            return this.modelMapper.map(post,PostDto.class);
        }

        public Post postDtoToPost(PostDto postDto){
            return this.modelMapper.map(postDto,Post.class);
        }
    }
