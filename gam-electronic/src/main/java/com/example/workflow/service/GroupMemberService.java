package com.example.workflow.service;

import com.example.workflow.dao.GroupMemberDao;
import com.example.workflow.model.entity.GroupMemberEntity;
import com.example.workflow.model.request.GroupMemberRequest;
import com.example.workflow.model.response.GroupMemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class GroupMemberService {

    @Autowired
    GroupMemberDao dao;

    public GroupMemberResponse save(GroupMemberRequest groupMemberRequest) throws RuntimeException, InvocationTargetException, IllegalAccessException {
        // This check validation is define in entity column definition
        if (!checkSaveValidation(groupMemberRequest))
            throw new RuntimeException("Enter Valid Size for Strings");
        GroupMemberResponse groupMemberResponse = new GroupMemberResponse();
        BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
        GroupMemberEntity groupMemberEntity = dao.save(new GroupMemberEntity(
                groupMemberRequest.getGroupName(),
                groupMemberRequest.getUsername())
        );
        beanUtilsBean.copyProperties(groupMemberResponse, groupMemberEntity);
        return groupMemberResponse;
    }

    private boolean checkSaveValidation(GroupMemberRequest groupMemberRequest) {
        if (groupMemberRequest.getGroupName().length() > 50 || groupMemberRequest.getUsername().length() > 30 )
            return false;
        return true;
    }

    public List<GroupMemberResponse> getAll() {
        return dao.getAll();
    }

    public GroupMemberResponse getById(Long id) {
        return dao.getById(id);
    }

    public void deleteById(Long id) throws EmptyResultDataAccessException {
        dao.deleteById(id);
    }

    public List<String> getGroupNames() {
        return dao.getGroupNames();
    }

    public List<String> getUsernamesByGroupName(String groupName) {
        return dao.getUsernamesByGroup(groupName);
    }

    public GroupMemberEntity getByUserName(String username) {
        return dao.getByUsername(username);
    }

}
