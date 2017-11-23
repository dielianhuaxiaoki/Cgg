package com.cgg.pub.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cgg.pub.common.base.action.BaseAction;
import com.cgg.pub.common.listener.TestEmailEvent;
import com.cgg.pub.common.util.SpringBeanUtil;
import com.cgg.pub.entity.Movie;
import com.cgg.pub.service.MovieService;




@Controller("movieAction")
@Scope("prototype")
public class MovieAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3568629061510449267L;
	
	
	@Autowired
	private MovieService movieService;
	
	
	public void movie(){
		
		System.out.println("movie--------");
		List<Movie> movieList = movieService.getMovieList();
		for (Movie movie : movieList) {
			movieService.saveMovieCache(movie);
		}
		writeJSON(getResponse(), movieList);
//		return SUCCESS;
		
	}
	
	public void list(){
		System.out.println("list--------");
		HttpSession session = getRequest().getSession();
		String sessionId = session.getId();
		System.out.println(session.isNew());
		System.out.println(sessionId);
		System.out.println(getRequest().getContextPath());
		System.out.println(getResponse().encodeUrl(getRequest().getContextPath()));
		List<Movie> movieList = movieService.getMovieList();
		writeJSON(getResponse(), movieList);
	}
	
	public void get(){
		String movieid = getRequest().getParameter("id");
		System.out.println("movie--------"+movieid);
		Movie movie = movieService.getMovieFromCache(Integer.valueOf(movieid));
		System.out.println(movie.toString());
		writeJSON(getResponse(), movie);
		
	}
	
	public void clear(){
		System.out.println("list--------");
		movieService.clearMovieCache();
		writeJSON(getResponse(), "ok");
	}
//	public void test(){
//		ApplicationContext aContext = SpringBeanUtil.getApplicationContext();
//		TestEmailEvent testEmailEvent = new TestEmailEvent("hello","chengang@golive-tv.com","this is a email text!");
//		aContext.publishEvent(testEmailEvent);
//		System.out.println("movieAction test-------------");
//		
//	}
}
