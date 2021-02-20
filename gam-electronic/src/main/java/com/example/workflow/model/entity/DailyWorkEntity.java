package com.example.workflow.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TST_DAILY_WORK")
public class DailyWorkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WORK_ID")
    private Long id;

    @Column(name = "WORK_TYPE", columnDefinition = "varchar(2)")
    private String workType;

    @Column(name = "WORK_TITLE", columnDefinition = "varchar(100)")
    private String workTitle;

    @JoinColumn(name = "WORK_GROUP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private GroupMemberEntity groupMember;

    @Column(name = "WORK_DUE_DATE")
    private Date workDueDate;

    public DailyWorkEntity() {
    }

    public DailyWorkEntity(String workType, String workTitle, GroupMemberEntity groupMember, Date workDueDate) {
        this.workType = workType;
        this.workTitle = workTitle;
        this.groupMember = groupMember;
        this.workDueDate = workDueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public GroupMemberEntity getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(GroupMemberEntity groupMember) {
        this.groupMember = groupMember;
    }

    public Date getWorkDueDate() {
        return workDueDate;
    }

    public void setWorkDueDate(Date workDueDate) {
        this.workDueDate = workDueDate;
    }
}
