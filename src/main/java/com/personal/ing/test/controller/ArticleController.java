package com.personal.ing.test.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.personal.ing.test.exception.ResourceNotFoundException;
import com.personal.ing.test.model.Article;
import com.personal.ing.test.repository.ArticleRepository;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	Environment env;

	@Scheduled(initialDelay = 10000, fixedDelay = 3600000)
	public void task() throws Exception {
		
		articleRepository.deleteAll();
		
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> response = rest.exchange(env.getProperty("URL"),
				HttpMethod.GET, entity, String.class);

		JSONObject jsonRsp = new JSONObject(response.getBody());

		JSONArray array = new JSONArray(jsonRsp.get("hits").toString());

		// System.out.println(array.get(0));

		for (int i = 0; i < array.length(); i++) {

			JSONObject jsonArt = array.getJSONObject(i);

			Article art = new Article();
			art.setAuthor(jsonArt.get("author").toString());
			art.setComment_text(jsonArt.get("comment_text").toString());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
			Date created = formatter.parse(jsonArt.get("created_at").toString());
			art.setCreated_at(created);
			art.setNum_comments(jsonArt.get("num_comments").toString());
			art.setPoints(jsonArt.get("points").toString());
			art.setStory_text(jsonArt.get("story_text").toString());
			art.setStory_title(jsonArt.get("title").toString());
			art.setStory_url(jsonArt.get("url").toString());
			art.setTags(jsonArt.get("_tags").toString());
			art.setTitle(jsonArt.get("title").toString());
			art.setUrl(jsonArt.get("url").toString());
			articleRepository.save(art);
		}

	}

	@GetMapping("/article")
	@CrossOrigin
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	@GetMapping("/article/{id}")
	@CrossOrigin
	public ResponseEntity<Article> getArticleById(@PathVariable(value = "id") Integer articleId)
			throws ResourceNotFoundException {
		Article article = articleRepository.findById(articleId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for id: " + articleId));
		return ResponseEntity.ok().body(article);
	}

	@PostMapping("/article")
	@CrossOrigin
	public Article createArticle(@Valid @RequestBody Article article) {
		return articleRepository.save(article);
	}

	@DeleteMapping("/article/{id}")
	@CrossOrigin
	public Map<String, Boolean> deleteArticle(@PathVariable(value = "id") Integer articleId)
			throws ResourceNotFoundException {
		Article article = articleRepository.findById(articleId)
				.orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + articleId));

		articleRepository.delete(article);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
