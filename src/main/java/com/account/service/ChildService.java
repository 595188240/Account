package com.account.service;

import com.account.entity.Child;
import com.account.repository.ChildRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ffdeng2
 * @date 2022-2-12 10:10
 */
@Service
public class ChildService {

    @Resource
    private ChildRepository childRepository;

    public List<Child> getAll() {
        return childRepository.findAll();
    }

    public Object findAllChild() {
        return childRepository.findAllChild();
    }
}
