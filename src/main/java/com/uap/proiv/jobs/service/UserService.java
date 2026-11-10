package com.uap.proiv.jobs.service;

import com.uap.proiv.jobs.dto.User;
import com.uap.proiv.jobs.dto.UserApiResponse;
import com.uap.proiv.jobs.dto.UserRequest;
import com.uap.proiv.jobs.dto.CreatedUser;

public interface UserService {
    UserApiResponse search(int page);
    User searchById(int id);
    void update(User user);
    CreatedUser addUser(UserRequest request);
}
