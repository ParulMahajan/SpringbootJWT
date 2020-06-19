package com.allianz.ins.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.allianz.ins.model.Employee;
import com.allianz.ins.service.EmployeeService;
import com.allianz.ins.util.JWTUtil;

import io.jsonwebtoken.ExpiredJwtException;


@Component
public class JWTRequestFilter extends OncePerRequestFilter{

	private static final Logger LOGGER = LogManager.getLogger(JWTRequestFilter.class);
	
	@Autowired
	private JWTUtil JWTUtil;
	
	@Autowired
	EmployeeService employeeService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		LOGGER.debug("requestTokenHeader: "+requestTokenHeader);
		String username = null;
		String jwtToken = null;

		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);

			try {
				username = JWTUtil.getUsernameFromToken(jwtToken);
				LOGGER.debug("username in token: "+username);
			} catch (IllegalArgumentException e) {
				LOGGER.debug("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				LOGGER.debug("JWT Token has expired");
			}
		} else {
			LOGGER.debug("JWT Token does not begin with Bearer String");
		}
		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		
			Employee employee = employeeService.getEmployee(username);
			// if token is valid configure Spring Security to manually set
			// authentication
			if (JWTUtil.validateToken(jwtToken, employee)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						employee, null, null);
				usernamePasswordAuthenticationToken
				.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);

	}

}
