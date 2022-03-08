package com.account.service;

import com.account.entity.SchoolClass;
import com.account.repository.ClassRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ffdeng2
 * @date 2022-2-12 10:08
 */
@Service
public class ClassService {

    @Resource
    private ClassRepository classRepository;

    public void save(SchoolClass schoolClass) {
        classRepository.save(schoolClass);
    }

    public List<SchoolClass> getAll() {
        return classRepository.findAll();
    }
}
