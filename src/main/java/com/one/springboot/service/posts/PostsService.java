package com.one.springboot.service.posts;

import com.one.springboot.domain.posts.Posts;
import com.one.springboot.domain.posts.PostsRepository;
import com.one.springboot.web.dto.PostsListResponseDto;
import com.one.springboot.web.dto.PostsResponseDto;
import com.one.springboot.web.dto.PostsSaveRequestDto;
import com.one.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;

    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)//람다식. .map(posts -> new PostsListResponseDto(posts)) 과 같다
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);
    }
    //JpaRepository에서 이미 delete 메소드를 지원하고 있다. 엔티티를 파라미터로 삭제할 수 있고, deleteById 메소드를
    //이용하면 id로 삭제할 수도 있다. 존재하는 Posts 인지 확인을 위해 엔티티 조회 후 그대로 삭제한다.

}
