package com.personal.ing.test.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer id;
	private String title;
	private String url;
	private String author;
	private String points;
	@Lob
	@Column(length = 100000)
	private String story_text;
	@Lob
	@Column(length = 100000)
	private String comment_text;
	private String num_comments;
	private String story_title;
	private String story_url;
	private Date created_at;
	private String tags;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getStory_text() {
		return story_text;
	}

	public void setStory_text(String story_text) {
		this.story_text = story_text;
	}

	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	public String getNum_comments() {
		return num_comments;
	}

	public void setNum_comments(String num_comments) {
		this.num_comments = num_comments;
	}

	public String getStory_title() {
		return story_title;
	}

	public void setStory_title(String story_title) {
		this.story_title = story_title;
	}

	public String getStory_url() {
		return story_url;
	}

	public void setStory_url(String story_url) {
		this.story_url = story_url;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", url=" + url + ", author=" + author + ", points=" + points
				+ ", story_text=" + story_text + ", comment_text=" + comment_text + ", num_comments=" + num_comments
				+ ", story_title=" + story_title + ", story_url=" + story_url + ", created_at=" + created_at + ", tags="
				+ tags + "]";
	}

}
