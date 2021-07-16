package com.test.zenithtest.service;

import com.test.zenithtest.PostStorage;
import com.test.zenithtest.model.Post;
import com.test.zenithtest.model.PostTag;
import com.test.zenithtest.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


/**
 * Service to auto-generate dummpy post
 */
@Service
public class PostPhotoService {
	private final static String URL_BASIC = "https://picsum.photos/id/<id>/200/300";
	private Random random = new Random();
	private final int TAG_LIST_SIZE = PostTag.values().length;

	@Autowired
	PostRepository postRepository;

	public void postPhoto() {
		Post post = postRepository.save(new Post(randomUrl(), randomTag()));
		post.setDescription("Post " + post.getId());
		postRepository.save(post);
		PostStorage.getInstance().addPostToQueue(post);
	}

	private PostTag randomTag() {
		return PostTag.values()[random.nextInt(TAG_LIST_SIZE)];
	}

	private String randomUrl() {
		return URL_BASIC.replace("<id>", String.valueOf(random.nextInt(1000) + 1));
	}
}
