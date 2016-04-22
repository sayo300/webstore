package com.packt.webstore.interceptor;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PerformanceMonitorInterceptor implements HandlerInterceptor{
	ThreadLocal<StopWatch> stopWatchLocal = new ThreadLocal<>();
	Logger logger = Logger.getLogger(this.getClass());
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		StopWatch stopWatch = new StopWatch(handler.toString());
		stopWatch.start(handler.toString());
		stopWatchLocal.set(stopWatch);
		logger.info("Przetwarzanie ¿¹dania do œcie¿ki: " + getURLPath(request));
		logger.info("Przetwarzanie ¿¹dania rozpoczete o:" + getCurrentTime());
		return true;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	
		logger.info("Przetwarzanie zadania zakonczono o: "+ getCurrentTime());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		StopWatch stopWatch = stopWatchLocal.get();
		stopWatch.stop();
		logger.info("Laczny czas przetwarzania zadania: "+stopWatch.getTotalTimeMillis()+" ms");
		stopWatchLocal.set(null);
		logger.info("======================");
		
	}
	private String getURLPath(HttpServletRequest request) {
		String curentPath = request.getRequestURI();
		String queryString = request.getQueryString();
		queryString = queryString == null ?"" : "?" +queryString;
		return curentPath+queryString;
	}
	
	private String getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'o' hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}
}
