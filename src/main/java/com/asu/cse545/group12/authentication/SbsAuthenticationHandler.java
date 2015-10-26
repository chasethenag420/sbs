package com.asu.cse545.group12.authentication;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.asu.cse545.group12.domain.Users;


public class SbsAuthenticationHandler implements AuthenticationSuccessHandler {
	private static final Logger logger = Logger.getLogger(SbsAuthenticationHandler.class);

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication) throws IOException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/** Builds the target URL according to the logic defined in the main class Javadoc. */
	protected String determineTargetUrl(Authentication authentication) {
		boolean isIndividual = false;
		boolean isMerchant = false;
		boolean isRegular = false;
		boolean isManager = false;
		boolean isAdmin = false;
		boolean isGovernment = false;

		String prefix="/"; 
		String suffix=".html";
		String individualTargetUrl = prefix+"individual"+suffix;
		String merchantTargetUrl = prefix+"merchant"+suffix;
		String regularTargetUrl = prefix+"regular"+suffix;
		String managerTargetUrl = prefix+"manager"+suffix;
		String adminTargetUrl = prefix+"admin"+suffix;
		String governmentTargetUrl = prefix+"government"+suffix;
		String defaultTargetUrl = prefix+"home"+suffix;

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_MANAGER")) {
				isManager = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
				break;
			}else if (grantedAuthority.getAuthority().equals("ROLE_REGULAR")) {
				isRegular = true;
				break;
			}else if (grantedAuthority.getAuthority().equals("ROLE_MERCHANT")) {
				isMerchant = true;
				break;
			}else if (grantedAuthority.getAuthority().equals("ROLE_INDIVIDUAL")) {
				isIndividual = true;
				break;
			}else if (grantedAuthority.getAuthority().equals("ROLE_GOVERNMENT")) {
				isGovernment = true;
				break;
			}
		}

		if (isIndividual) {
			return individualTargetUrl;
		} else if (isMerchant) {
			return merchantTargetUrl;
		}else if (isRegular) {
			return regularTargetUrl;
		}else if (isManager) {
			return managerTargetUrl;
		}else if (isAdmin) {
			return adminTargetUrl;
		} else if (isGovernment) {
			return governmentTargetUrl;
		}else {
			throw new IllegalStateException();
		}
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); //get logged in username
		if(logger.isDebugEnabled()){
			logger.debug("Logged in as: " + name);
		}
		session.setAttribute("username", name);
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
}