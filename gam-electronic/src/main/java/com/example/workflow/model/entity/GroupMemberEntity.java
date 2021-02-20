package com.example.workflow.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "TST_GROUP_MEMBER")
public class GroupMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_MEMBER_ID")
    private Long id;

    @Column(name = "GROUP_NAME", columnDefinition = "varchar(50)")
    private String groupName;

    @Column(name = "USERNAME",  columnDefinition = "varchar(30)")
    private String username;

    public GroupMemberEntity() {
    }

    public GroupMemberEntity(String groupName, String username) {
        this.groupName = groupName;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
