package com.account.repository;

import com.account.entity.Child;
import com.account.entity.CustomDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ffdeng2
 * @date 2022-2-12 10:09
 */
@Repository
public interface ChildRepository extends JpaRepository<Child, Long>, JpaSpecificationExecutor<Child> {

    /**
     * 基于实体自定义返回值
     */
    @Query(value = "select new com.account.entity.CustomDTO(c.id, c.name) from Child c")
    List<CustomDTO> findAllChild();

}
