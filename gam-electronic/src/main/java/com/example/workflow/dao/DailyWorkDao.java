package com.example.workflow.dao;

import com.example.workflow.model.entity.DailyWorkEntity;
import com.example.workflow.model.response.DailyWorkResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyWorkDao extends JpaRepository<DailyWorkEntity, Long> {

    @Query("select new com.example.workflow.model.response.DailyWorkResponse(" +
            "d.id, d.workType, d.workTitle, d.groupMember, d.workDueDate) from DailyWorkEntity d")
    List<DailyWorkResponse> getAll();

    @Query("select new com.example.workflow.model.response.DailyWorkResponse(" +
            "d.id, d.workType, d.workTitle, d.groupMember, d.workDueDate) from DailyWorkEntity d where d.id=:id")
    DailyWorkResponse getById(@Param("id") Long id);

}
