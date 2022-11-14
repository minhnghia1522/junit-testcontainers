package com.sysexevn.sunshinecity.dao;

import java.util.List;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.domain.Post;

@Dao
@ConfigAutowireable
@Transactional
public interface IPostDAO {

	@Insert
	int insert(Post post);
	
	@Update
	int update(Post post);
	
	@BatchInsert
	int[] insertAll(List<Post> posts);
	
	@Delete
	int delete(Post post);
	

    @Select
    Post selectById(Integer id);
	
	@Select
	List<Post> findAllPost();
	
	@Select
	long count(Integer id);
}
