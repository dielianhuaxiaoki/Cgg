package com.cgg.pub.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.cgg.pub.common.base.dao.impl.BaseDaoImpl;
import com.cgg.pub.dao.MovieDao;
import com.cgg.pub.entity.Movie;

@Repository
public class MovieDaoImpl extends BaseDaoImpl<Movie, Long> implements MovieDao {

//	@Resource(name="redisTemplate")
//	protected RedisTemplate<Serializable, Serializable> redisTemplate;
	
//	@Resource(name="cacheApi")
//	protected ICacheApi cacheApi;
	
//	private EhcacheManagementHelper cache = EhcacheManagementHelper.getInstance();
//	@Autowired()
//	@Qualifier("redisCacheDataSource")
//	@Resource(name="redisCacheDataSource")
	
	@Override
	public List<Movie> getMovieList() {
		// TODO Auto-generated method stub
		return this.sqlSessionTemplate.selectList("movieListSqlTest");
	}

	@Override
	public void saveMovieCache(final Movie movie) {
//		cache.cache("movie.id." + movie.getMovieid(), movie.getCnname());
//		try {
//			cacheApi.add("movie.id." + movie.getMovieid(), 0, movie.getCnname());
//		} catch (MemcachedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try{
//			redisTemplate.execute(new RedisCallback<Object>() {
//
//				@Override
//				public Object doInRedis(RedisConnection connection) throws DataAccessException {
//					System.out.println("connection----------");
//					connection.set(redisTemplate.getStringSerializer().serialize("movie.id." + movie.getMovieid()), 
//							redisTemplate.getStringSerializer().serialize(movie.getCnname()));
//					System.out.println("connection----------2");
//					return null;
//				}
//			});
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}

	@Override
	public Movie getMovieFromCache(final Integer movieId) {
		return null;
//		String cname = (String) cache.get("movie.id." + String.valueOf(movieId));
//		Movie movie = new Movie();
//		movie.setMovieid(movieId);
//		movie.setCnname(cname);
//		return movie;
//		try {
//			String cname = (String) cacheApi.get("movie.id." + String.valueOf(movieId));
//			Movie movie = new Movie();
//			movie.setMovieid(movieId);
//			movie.setCnname(cname);
//			return movie;
//			
//		} catch (MemcachedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 TODO Auto-generated method stub
//		return redisTemplate.execute(new RedisCallback<Movie>() {
//
//			@Override
//			public Movie doInRedis(RedisConnection connection) throws DataAccessException {
//				byte[] movieKey = redisTemplate.getStringSerializer().serialize("movie.id." + String.valueOf(movieId));
//				if(connection.exists(movieKey)){
//					byte[] movieValue = connection.get(movieKey);
//					String cname = redisTemplate.getStringSerializer().deserialize(movieValue);
//					System.out.println("cname:"+cname);
//					Movie movie = new Movie();
//					movie.setMovieid(movieId);
//					movie.setCnname(cname);
//					return movie;
//					
//				}
//				System.out.println("cname:-------------");
//				return null;
//			}
//		});
	}
}
