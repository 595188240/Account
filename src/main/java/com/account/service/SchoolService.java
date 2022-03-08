package com.account.service;

import com.account.entity.School;
import com.account.repository.SchoolRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ffdeng2
 */
@Service
public class SchoolService {

    @Resource
    private SchoolRepository schoolRepository;

    public void save(School school) {
        schoolRepository.save(school);
    }

    public List<School> getAll() {
        return schoolRepository.findAll();
    }

    public Page page(Pageable pageable) {
        return schoolRepository.findAll(pageable);
    }

}
