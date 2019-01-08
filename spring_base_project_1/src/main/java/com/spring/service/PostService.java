package com.spring.service;

import java.util.List;
import java.util.Map;

import com.spring.domain.Post;

public interface PostService {

	List<Post> selectPostList(Map<String, Object> map);

	int insertPost(Post post);

	Post selectPost(int idx);

	int updatePost(Post post);

	int deletePost(int idx);

	int selectPostLike(int idx);
}
