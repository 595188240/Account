package com.account.repository;

import com.account.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author ffdeng2
 * @date 2022-2-12 10:09
 */
@Repository
public interface ClassRepository extends JpaRepository<SchoolClass, Long>, JpaSpecificationExecutor<SchoolClass> {
}
