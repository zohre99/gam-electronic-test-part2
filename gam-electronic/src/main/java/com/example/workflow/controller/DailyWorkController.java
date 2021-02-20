package com.example.workflow.controller;

import com.example.workflow.model.entity.DailyWorkEntity;
import com.example.workflow.model.request.DailyWorkRequest;
import com.example.workflow.model.request.GroupMemberRequest;
import com.example.workflow.model.response.DailyWorkResponse;
import com.example.workflow.service.DailyWorkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dailyWork")
public class DailyWorkController {

    @Autowired
    DailyWorkService service;

    @PostMapping("/save")
    public ResponseEntity<DailyWorkResponse> save(@RequestBody DailyWorkRequest dailyWorkRequest) {
        DailyWorkResponse response = null;
        try {
            response = service.save(dailyWorkRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (InvocationTargetException | IllegalAccessException | RuntimeException exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DailyWorkResponse>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<DailyWorkResponse> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(service.getById(Long.parseLong(id)), HttpStatus.OK);
    }

    @PostMapping("/random/multipleSave")
    public ResponseEntity<List<DailyWorkEntity>> assignRandomTasks(@RequestBody Object names) {
        return new ResponseEntity<>(service.assignRandomTasks(Arrays.asList(names.toString().replaceAll("\\[|\\]|\\{|\\}|\"", "").split(","))), HttpStatus.OK);
    }

}
