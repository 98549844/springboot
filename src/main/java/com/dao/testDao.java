package com.dao;

import com.dao.common.HibernateBaseDao;
import com.entity.TestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class testDao {

    private Log log = LogFactory.getLog(this.getClass());
    @Autowired
    private HibernateBaseDao<TestEntity> hibernateBaseDao;

    /**
     * 保存testEntity进数据库 * @param testEntity
     */
    public void savetestEntity(TestEntity testEntity) {
        log.info("REPOSITORY***" + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());
        hibernateBaseDao.save(testEntity);
    }

    /**
     * 删除testEntity对象 * @param testEntity
     */
    public void deltestEntity(TestEntity testEntity) {
        log.info("REPOSITORY***" + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());

        hibernateBaseDao.delete(testEntity);
    }

    /**
     * 更新testEntity进数据库 * @param testEntity
     */
    public void updatetestEntity(TestEntity testEntity) {
        log.info("REPOSITORY***" + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());

        hibernateBaseDao.update(testEntity);
    }

    /**
     * 根据Id 查询单个testEntity对象
     */
    public TestEntity getTestEntityById(int id) {
        log.info("REPOSITORY***" + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());

        return hibernateBaseDao.get(TestEntity.class, id);
    }

    /**
     * 根据条件查询List<testEntity>对象列表
     *
     * @param currentPage
     * @param pageSize
     * @param testEntity
     * @return List<testEntity>
     */
    public List<TestEntity> gettestEntities(int currentPage, int pageSize, TestEntity testEntity) {
        log.info("REPOSITORY***" + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());

        StringBuilder hql = new StringBuilder("select l from testEntity as l where 1 = 1");
        Map<String, Object> map = generateHql(testEntity, hql);
        hql.append(" order by l.id asc");
        List<TestEntity> testEntitys = hibernateBaseDao.findListByPage(hql.toString(), map, currentPage, pageSize);
        if (testEntitys == null || testEntitys.isEmpty()) {
            return null;
        }
        return testEntitys;
    }

    /**
     * 生成HQL语句
     */
    private Map<String, Object> generateHql(TestEntity testEntity, StringBuilder hql) {
        log.info("REPOSITORY***" + this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName());

        Map<String, Object> parameter = new HashMap<String, Object>();
        if (testEntity == null) {
            return parameter;
        }
        if (testEntity != null) {
            if (testEntity.getId() != null && !"".equals(testEntity.getId())) {
                hql.append(" and l.id = :id");
                parameter.put("pw", testEntity.getId());
            }
            if (testEntity.getEmail() != null && !"".equals(testEntity.getEmail())) {
                hql.append(" and l.email = :email");
                parameter.put("email", testEntity.getEmail());
            }
            if (testEntity.getUserId() != null && !"".equals(testEntity.getUserId())) {
                hql.append(" and l.userid = :userid");
                parameter.put("email", testEntity.getUserId());
            }
            if (testEntity.getUserName() != null && !"".equals(testEntity.getUserName())) {
                hql.append(" and l.username = :username");
                parameter.put("email", testEntity.getUserName());
            }
        }
        return parameter;

    }
}
