package com.example.workflow.model.response;

import com.example.workflow.model.entity.GroupMemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyWorkResponse {

    private Long id;
    private String workType;
    private String workTitle;
    private GroupMemberEntity groupMemberEntity;
    private Date workDueDate;

}
