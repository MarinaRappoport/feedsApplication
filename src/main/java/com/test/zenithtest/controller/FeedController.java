package com.test.zenithtest.controller;

import com.test.zenithtest.PostStorage;
import com.test.zenithtest.model.Post;
import com.test.zenithtest.model.PostTag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.test.zenithtest.model.PostTag.*;

/**
 * Controller for getting feed
 */
@RestController
public class FeedController {
	private final static PostTag[] FEED_CONFIGURATION = new PostTag[]{SPORTS, SPORTS, FASHION, ANIMALS, NATURE, NATURE, LIFESTYLE, FOOD, KIDS, KIDS};
	private final static int FEED_LENGTH = 50;

	@RequestMapping("get_feed")
	public List<Post> getFeed() {
		List<Post> posts = new ArrayList();
		int counter = 0;
		while (counter < FEED_LENGTH) {
			Post post = PostStorage.getInstance().getPostByTag(FEED_CONFIGURATION[counter++ % FEED_CONFIGURATION.length]);
//			if no more posts for needed category, will skip
			if (post == null) continue;
			posts.add(post);
		}
		return posts;
	}
}
