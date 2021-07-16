package com.test.zenithtest;

import com.test.zenithtest.model.Post;
import com.test.zenithtest.model.PostTag;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Singleton to keep post queue for each of tags
 */
public class PostStorage {
	private Map<PostTag, Queue<Post>> postMap;
	private static PostStorage ourInstance = new PostStorage();

	public static PostStorage getInstance() {
		return ourInstance;
	}

	private PostStorage() {
		postMap = new HashMap<>();
		for (PostTag tag: PostTag.values()) {
			postMap.put(tag, new ConcurrentLinkedQueue<>());
		}
	}

	public void addPostToQueue(Post post){
		postMap.get(post.getTag()).add(post);
	}

	public Post getPostByTag(PostTag tag){
		return postMap.get(tag).poll();
	}
}
