package com.test.zenithtest.controller;

import com.test.zenithtest.model.Post;
import com.test.zenithtest.service.PostPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Controller for web gui
 */
@Controller
public class WebController {
	private List<Post> feed = null;

	@Autowired
	PostPhotoService service;

	@Autowired
	FeedController feedController;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("feed", feed);
		return "index";
	}

	@GetMapping("update_feed")
	public String unpdateFeed() {
		feed = feedController.getFeed();
		return "redirect:/";
	}

	@PostConstruct
	public void setupDbWithDummyData() {
		for (int i = 0; i < 1000; i++) {
			service.postPhoto();
		}
	}
}
