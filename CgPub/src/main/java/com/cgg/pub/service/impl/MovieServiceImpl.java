package com.cgg.pub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgg.pub.common.base.service.impl.BaseServiceImpl;
import com.cgg.pub.common.cache.redis.RedisDataSource;
import com.cgg.pub.dao.MovieDao;
import com.cgg.pub.entity.Movie;
import com.cgg.pub.service.MovieService;

@Service
@Transactional
public class MovieServiceImpl extends BaseServiceImpl<Movie, Long, MovieDao> implements MovieService {

	@Autowired
	protected RedisDataSource redisCacheDataSource;
	
	@Override
	public List<Movie> getMovieList() {
//		String cache_key=RedisCache.CAHCENAME+"|getUserList|"+offset+"|"+limit;
		String cache_key=RedisDataSource.CAHCENAME+"|getUserList|";
		//先去缓存中取
		List<Movie> result_cache=redisCacheDataSource.getListCache(cache_key, Movie.class);
		if(result_cache==null){
			//缓存中没有再去数据库取，并插入缓存（缓存时间为60秒）
			result_cache=getDao().getMovieList();
			redisCacheDataSource.putListCacheWithExpireTime(cache_key, result_cache, RedisDataSource.CAHCETIME);
			System.out.println("put cache with key:"+cache_key);
		}else{
			System.out.println("get cache with key:"+cache_key);
		}
		return result_cache;
//		return getDao().getMovieList();
	}

	@Override
	public void saveMovieCache(Movie movie) {
		getDao().saveMovieCache(movie);
	}

	@Override
	public Movie getMovieFromCache(Integer movieId) {
		return getDao().getMovieFromCache(movieId);
	}

	@Override
	public void clearMovieCache() {
		redisCacheDataSource.clearCache();
	}

}
