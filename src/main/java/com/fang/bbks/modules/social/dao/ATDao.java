package com.fang.bbks.modules.social.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fang.bbks.common.persistence.BaseDao;
import com.fang.bbks.common.persistence.BaseDaoImpl;
import com.fang.bbks.modules.social.entity.AT;
import com.fang.bbks.modules.sys.entity.BookContent;

/**
 * @Intro "@功能辅助类"
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-11-19
 * @since 下午6:01:12
 */
public interface ATDao extends ATDaoCustom, CrudRepository<AT, Integer> {

	@Modifying
	@Query("update AT set delFlag=" + BookContent.DEL_FLAG_DELETE
			+ " where id = ?1")
	public int deleteById(Integer id);

	@Query("from AT where type = ?1 and target = ?2")
	List<AT> findByTypeAndTarget(int type, String target);

	@Query("from AT where uid = ?1 and isRead=" + AT.READ_HIDE)
	List<AT> findByUid(int uid);

	@Query("from AT where uid = ?1")
	List<AT> findAll(int uid);

	@Query("from AT where target = ?1")
	List<AT> findByTarget(String target);
}

interface ATDaoCustom extends BaseDao<AT> {

}

@Repository("aTDao")
class ATDaoImpl extends BaseDaoImpl<AT> implements ATDaoCustom {
	
}
