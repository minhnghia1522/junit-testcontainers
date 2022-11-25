package com.sysexevn.sunshinecity.entity.listener;

import java.util.Date;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sysexevn.sunshinecity.config.CustomUserDetails;
import com.sysexevn.sunshinecity.entity.Post;

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
		entity.getAuditing().setCreatedDate(new Date());
		entity.getAuditing().setCreatedBy(getCurrentAuditorName());
		EntityListener.super.preInsert(entity, context);
	}

	@Override
	public void preUpdate(Post entity, PreUpdateContext<Post> context) {
		entity.getAuditing().setModifiedDate(new Date());
		entity.getAuditing().setModifiedBy(getCurrentAuditorName());
		EntityListener.super.preUpdate(entity, context);
	}

}
