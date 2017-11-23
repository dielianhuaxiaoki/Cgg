package com.cgg.pub.dao;

import java.util.List;

import com.cgg.pub.common.base.dao.BaseDao;
import com.cgg.pub.entity.Movie;

public interface MovieDao extends BaseDao<Movie, Long> {

	List<Movie> getMovieList();
	void saveMovieCache(Movie movie);
	Movie getMovieFromCache(Integer movieId);
}
