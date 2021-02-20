package com.example.workflow.controller;

import com.example.workflow.model.request.GroupMemberRequest;
import com.example.workflow.model.response.GroupMemberResponse;
import com.example.workflow.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/groupMember")
public class GroupMemberController {

    @Autowired
    GroupMemberService service;

    @PostMapping("/save")
    public ResponseEntity<GroupMemberResponse> save(@RequestBody GroupMemberRequest groupMemberRequest) {
        GroupMemberResponse response = null;
        try {
            response = service.save(groupMemberRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InvocationTargetException | IllegalAccessException | RuntimeException exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GroupMemberResponse>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<GroupMemberResponse> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(service.getById(Long.parseLong(id)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
        try {
            service.deleteById(Long.parseLong(id));
            return new ResponseEntity<>("Successfully Deleted!", HttpStatus.OK);
        } catch (EmptyResultDataAccessException exception) {
            return new ResponseEntity<>("No Such Group Member!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getGroupNames")
    public ResponseEntity<List<String>> getGroupNames() {
        return new ResponseEntity<>(service.getGroupNames(), HttpStatus.OK);
    }

    @GetMapping("/getUsernames/{groupName}")
    public ResponseEntity<List<String>> getUsernamesByGroupName(@PathVariable("groupName") String groupName) {
        return new ResponseEntity<>(service.getUsernamesByGroupName(groupName.split(":")[1]), HttpStatus.OK);
    }

}
