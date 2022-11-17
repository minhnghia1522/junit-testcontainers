package com.sysexevn.sunshinecity.dao;

import java.util.List;
import java.util.Optional;

import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.Result;
import org.seasar.doma.jdbc.criteria.Entityql;
import org.springframework.transaction.annotation.Transactional;

import com.sysexevn.sunshinecity.domain.Post;
import com.sysexevn.sunshinecity.domain.Post_;

@Dao
@ConfigAutowireable
@Transactional
public interface IPostDAO {

	@Insert
	Result<Post> insert(Post post);

	// insert
	default Result<Post> insertUseDSL(Post post) {
		Post_ p = new Post_();
		Entityql entityql = new Entityql(Config.get(this));
		Result<Post> result = entityql.insert(p, post).execute();
		return result;
	}

	@Update
	Result<Post> update(Post post);

	// update
	default Result<Post> updateUseDSL(Post post) {
		Post_ p = new Post_();
		Entityql entityql = new Entityql(Config.get(this));
		Result<Post> result = entityql.update(p, post).execute();
		return result;
	}

	@BatchInsert
	BatchResult<Post> insertAll(List<Post> posts);

	// insert all
	default BatchResult<Post> insertAllUseDSL(List<Post> posts) {
		Post_ p = new Post_();
		Entityql entityql = new Entityql(Config.get(this));
		BatchResult<Post> result = entityql.insert(p, posts).execute();
		return result;
	}

	@Delete
	Result<Post> delete(Post post);

	// delete
	default Result<Post> deleteUseDSL(Post post) {
		Post_ p = new Post_();
		Entityql entityql = new Entityql(Config.get(this));
		Result<Post> result = entityql.delete(p, post).execute();
		return result;
	}

	@Select
	Optional<Post> selectById(Integer id);

	// find by id
	default Optional<Post> selectByIdUseDSL(Integer id) {
		Post_ p = new Post_();
		Entityql entityql = new Entityql(Config.get(this));
		Optional<Post> post = entityql.from(p).where(c -> c.eq(p.id, id)).fetchOptional();
		return post;
	}

	@Select
	List<Post> findAllPost();

	// find all
	default List<Post> findAllPostUseDSL() {
		Post_ p = new Post_();
		Entityql entityql = new Entityql(Config.get(this));
		List<Post> list = entityql.from(p).fetch();
		return list;
	}

	@Select
	long count(Integer id);

}
