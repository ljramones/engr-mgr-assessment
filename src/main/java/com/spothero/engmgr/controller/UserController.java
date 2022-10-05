package com.spothero.engmgr.controller;

import com.spothero.engmgr.controller.model.WorkedHoursDTO;
import com.spothero.engmgr.db.model.WorkedHours;
import com.spothero.engmgr.controller.model.UserDto;
import com.spothero.engmgr.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        log.info("Number of users returned:" + users.size());
        return users;
    }

    @GetMapping(path = "/{UserId}/worked_hours")
    public List<WorkedHoursDTO> getWorkedHours(@PathVariable("UserId") Integer userId) {

        List<WorkedHoursDTO> workedHours = userService.getWorkedHours(userId);
        log.info("Number of worked hours=" + workedHours.size());

        return workedHours;
    }

    @PostMapping(path = "/{UserId}/worked_hours")
    public WorkedHours createTimeCard(@PathVariable("UserId") String userId,
                                      @RequestBody WorkedHours workedHours) {

        WorkedHours workedHoursCreated = userService.createTimeCard(workedHours);
        log.info("timecard=" + workedHoursCreated);
        return workedHoursCreated;
    }

}
