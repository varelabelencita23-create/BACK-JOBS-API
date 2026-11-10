package com.uap.proiv.jobs.controller;


import com.uap.proiv.jobs.dto.*;
import com.uap.proiv.jobs.service.JobService;
import com.uap.proiv.jobs.service.UserJobAssignedService;
import com.uap.proiv.jobs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import com.uap.proiv.jobs.dto.CreatedUser;
import com.uap.proiv.jobs.dto.UserRequest;

@Controller
public class GraphqlController {
    private final UserService userService;
    private final JobService jobService;
    private final UserJobAssignedService userJobAssignedService;

    @Autowired
    public GraphqlController(UserService userService,
                             JobService jobService,
                             UserJobAssignedService userJobAssignedService) {
        this.userService = userService;
        this.jobService = jobService;
        this.userJobAssignedService = userJobAssignedService;
    }

    @QueryMapping
    public List<UserJobAssigned> assigneds(@Argument AssignRequest request) {
        return userJobAssignedService.assign();
    }

    @QueryMapping
    public User userById(@Argument int id) {
        return userService.searchById(id);
    }

    @QueryMapping
    public Job jobById(@Argument String id) {
        try {
            return jobService.getJobById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El id del trabajo debe ser numérico", e);
        }
    }

    @SchemaMapping(typeName = "User", field = "job")
    public Job job(User user) {
        return jobService.getJobById(user.getJobId());
    }

    @MutationMapping
    public Job addJob(@Argument JobRequest request) {
        return jobService.add(request);
    }

    @MutationMapping
    public CreatedUser addUser(@Argument UserRequest request) {
        return userService.addUser(request);
    }

}
