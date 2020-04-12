package com.one.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface PostsRepository extends JpaRepository<Posts,Long>{
    //SpringDataJpa 에서 제공하지 않는 메소드는 아래처럼 쿼리로 작성해도 된다. Query가 가독성이 좋기는 하다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
