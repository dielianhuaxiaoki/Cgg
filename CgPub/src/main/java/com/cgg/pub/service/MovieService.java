package com.cgg.pub.service;

import java.util.List;

import com.cgg.pub.common.base.service.BaseService;
import com.cgg.pub.entity.Movie;

public interface MovieService extends BaseService<Movie, Long> {

	List<Movie> getMovieList();
	
	void saveMovieCache(Movie movie);
	
	Movie getMovieFromCache(Integer movieId);

	void clearMovieCache();
}
