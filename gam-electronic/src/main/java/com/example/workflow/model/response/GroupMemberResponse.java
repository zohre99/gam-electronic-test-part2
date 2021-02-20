package com.example.workflow.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMemberResponse {

    private Long id;
    private String groupName;
    private String username;

}
