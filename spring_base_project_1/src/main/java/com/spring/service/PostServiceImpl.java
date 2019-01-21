package com.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.PostMapper;
import com.spring.model.Post;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostMapper postMapper;

	@Override
	public List<Post> selectPostList(Map<String, Object> map) {

		return postMapper.selectPostList(map);
	}

	@Override
	public int insertPost(Post post) {

		return postMapper.insertPost(post);
	}

	@Override
	public Post selectPost(int idx) {

		return postMapper.selectPost(idx);
	}

	@Override
	public int updatePost(Post post) {

		return postMapper.updatePost(post);
	}

	@Override
	public int deletePost(int idx) {

		return postMapper.deletePost(idx);
	}

	@Override
	public int selectPostLike(int idx) {

		return postMapper.selectPostLike(idx);
	}
}
