package com.example.workflow.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyWorkRequest {

    private String workType;
    private String workTitle;
    private Long groupMemberId;
    private Date workDueDate;

}
