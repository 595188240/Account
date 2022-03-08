package com.account.controller;

import com.account.entity.School;
import com.account.entity.SchoolClass;
import com.account.service.ClassService;
import com.account.service.SchoolService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author ffdeng2
 * @date 2022-3-1 14:08
 */
@RestController
@RequestMapping("/school")
public class SchoolController {

    @Resource
    private SchoolService schoolService;

    @Resource
    private ClassService classService;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @RequestMapping("/test")
    public Object test(){

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassName("初一");
        schoolClass.setClassCode("cy");
        schoolClass.setSchoolCode("gysz");
        classService.save(schoolClass);

        // 手动提交事务
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = platformTransactionManager.getTransaction(def);
        try {
            School school = new School();
            school.setSchoolCode("gys2z");
            school.setSchoolName("涡阳四中2");
            schoolService.save(school);
            platformTransactionManager.commit(status);
        } catch (Exception e) {
            platformTransactionManager.rollback(status);
        }

        int i = 1 / 0 ;
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
