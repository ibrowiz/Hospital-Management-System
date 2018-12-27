package org.calminfotech.error.controllers;

/*import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.calminfotech.user.utils.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private UserIdentity userIdentity;
	
	//error redirect view page
	public static final String DEFAULT_ERROR_VIEW = "error/errorPage";
	
	@ExceptionHandler(value = Exception.class)
	public String resolverExceptionHandler1(HttpServletRequest request, 
		Exception exception)throws Exception{
		// If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
		if(AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null)throw exception;
		// Otherwise setup and send the user to a default error-view.
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("errormessage", exception.getMessage());
		mav.addObject("url", request.getRequestURL());
		mav.addObject("timestamp", new Date(System.currentTimeMillis()));
		mav.setViewName(DEFAULT_ERROR_VIEW);
		
		this.userIdentity.setException(exception);
		this.userIdentity.setErrormessage(exception.getMessage());
		this.userIdentity.setUrl(request.getRequestURL());
		this.userIdentity.setTimestamp(new Date(System.currentTimeMillis()));
		System.out.println("Kunle check Error type :"+exception.getMessage());
		return "redirect:/error/errorPage";
	}
}
*/