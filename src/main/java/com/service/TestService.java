package com.service;

import com.dao.testDao;
import com.entity.TestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class TestService {

    private static final Log log = LogFactory.getLog(TestService.class);
    @Autowired
    private testDao testDao;

    public boolean saveTestEntity(TestEntity testEntity) {
        log.info("SERVICE***" + this.getClass().getName()+"."+Thread.currentThread() .getStackTrace()[1].getMethodName());

        try {
            testDao.savetestEntity(testEntity);
        } catch (Exception e) {
            log.error(e);
            // roolback data
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 删除User对象
     *
     * @param id
     * @return boolean
     */
    public boolean delTestEntityById(Integer id) {
        log.info("SERVICE***" + this.getClass().getName());
        try {
            TestEntity testEntity = testDao.getTestEntityById(id);
            testDao.deltestEntity(testEntity);
        } catch (Exception e) {
            log.error(e);
            // roolback data
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 更新User进数据库
     *
     * @param testEntity
     * @return boolean
     */
    public boolean updateTestEntity(TestEntity testEntity) {
        log.info("SERVICE***" + this.getClass().getName());
        try {
            if (testEntity != null) {
                testDao.updatetestEntity(testEntity);
            }
        } catch (Exception e) {
            log.error(e);
            // roolback data
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

    /**
     * 根据ID查询User对象
     */
    public TestEntity getTestEntityById(Integer Id) {
        log.info("SERVICE***" + this.getClass().getName());
        TestEntity testEntity = testDao.getTestEntityById(Id);
        if (testEntity == null) {
            return null;
        }
        return testEntity;
    }


    /**
     * 根据user查user
     *
     * @param currentPage
     * @param pageSize
     * @param testEntity
     * @return list<User>
     */
    public List<TestEntity> getTestEntities(int currentPage, int pageSize, TestEntity testEntity) {
        log.info("SERVICE***" + this.getClass().getName());
        List<TestEntity> TestEntityList = testDao.gettestEntities(currentPage, pageSize, testEntity);
        if (TestEntityList == null || TestEntityList.isEmpty()) {
            return null;
        }
        return TestEntityList;
    }
}
