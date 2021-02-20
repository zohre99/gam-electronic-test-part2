package com.example.workflow.service;

import com.example.workflow.dao.DailyWorkDao;
import com.example.workflow.model.entity.DailyWorkEntity;
import com.example.workflow.model.entity.GroupMemberEntity;
import com.example.workflow.model.request.DailyWorkRequest;
import com.example.workflow.model.response.DailyWorkResponse;
import com.example.workflow.model.response.GroupMemberResponse;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DailyWorkService {

    @Autowired
    DailyWorkDao dao;

    @Autowired
    GroupMemberService groupMemberService;

    public DailyWorkResponse save(DailyWorkRequest dailyWorkRequest) throws RuntimeException, InvocationTargetException, IllegalAccessException {

        // This check validation is define in entity column definition
        if (!checkSaveValidation(dailyWorkRequest))
            throw new RuntimeException("Enter Valid Size for Strings");

        GroupMemberEntity groupMemberEntity = new GroupMemberEntity();
        GroupMemberResponse groupMemberResponse = groupMemberService.getById(dailyWorkRequest.getGroupMemberId());
        if (groupMemberResponse == null) {
            throw new IllegalAccessException();
        }

        BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
        beanUtilsBean.copyProperties(groupMemberEntity, groupMemberResponse);

        DailyWorkEntity dailyWorkEntity = new DailyWorkEntity(
                dailyWorkRequest.getWorkType(),
                dailyWorkRequest.getWorkTitle(),
                groupMemberEntity,
                dailyWorkRequest.getWorkDueDate()
        );

        DailyWorkEntity savedDailyWorkEntity = dao.save(dailyWorkEntity);
        DailyWorkResponse dailyWorkResponse = new DailyWorkResponse();
        beanUtilsBean.copyProperties(dailyWorkResponse, savedDailyWorkEntity);
        dailyWorkResponse.setGroupMemberEntity(savedDailyWorkEntity.getGroupMember());

        return dailyWorkResponse;
    }

    private boolean checkSaveValidation(DailyWorkRequest dailyWorkRequest) {
        if (dailyWorkRequest.getWorkType().length() > 2 || dailyWorkRequest.getWorkTitle().length() > 100)
            return false;
        return true;
    }

    public List<DailyWorkResponse> getAll() {
        return dao.getAll();
    }

    public DailyWorkResponse getById(Long id) {
        return dao.getById(id);
    }

    public List<DailyWorkEntity> assignRandomTasks(List<String> usernames) {
        List<DailyWorkEntity> resultList = new ArrayList<>();
        for (int i = 0 ; i < usernames.size() ; i++) {
            GroupMemberEntity groupMemberEntity = groupMemberService.getByUserName(usernames.get(i));
            if (groupMemberEntity != null) {
                DailyWorkEntity dailyWorkEntity = new DailyWorkEntity("T" + i,
                        "Work Title - " + i,
                        groupMemberEntity,
                        new Date()
                );
                resultList.add(dao.save(dailyWorkEntity));
            }
        }
        return resultList;
    }
}
