package com.sysexevn.sunshinecity.entity;

import java.util.Date;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sysexevn.sunshinecity.config.CustomUserDetails;

public class PostEntityListener implements EntityListener<Post> {

	private String getCurrentAuditorName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		return userDetails.getUsername();
	}

	@Override
	public void preInsert(Post entity, PreInsertContext<Post> context) {
		entity.setCreatedDate(new Date());
		entity.setCreatedBy(getCurrentAuditorName());
		EntityListener.super.preInsert(entity, context);
	}

	@Override
	public void preUpdate(Post entity, PreUpdateContext<Post> context) {
		entity.setModifiedDate(new Date());
		entity.setModifiedBy(getCurrentAuditorName());
		EntityListener.super.preUpdate(entity, context);
	}

}
