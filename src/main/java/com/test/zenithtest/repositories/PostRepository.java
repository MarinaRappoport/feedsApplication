package com.test.zenithtest.repositories;

import com.test.zenithtest.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	List<Post> findByTag(String tag);
}
