package com.spothero.engmgr.service;

import com.spothero.engmgr.controller.model.UserDto;
import com.spothero.engmgr.controller.model.WorkedHoursDTO;
import com.spothero.engmgr.db.model.Users;
import com.spothero.engmgr.db.model.WorkedHours;
import com.spothero.engmgr.db.repository.UserRepository;
import com.spothero.engmgr.db.repository.WorkedHoursRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * we abstract all user actions in its own service to separate web tier form data tier
 */
@Slf4j
@Service
public class UserService {

    /**
     * reference to the data tier for the user entities
     */
    private final UserRepository userRepository;

    /**
     * reference to the data tier for the worked hours entities
     */
    private final WorkedHoursRepository workedHoursRepository;

    /**
     * used to format worked hours reporting
     */
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Constructor to inject our required dependencies
     *
     * @param userRepository        the user entities repo
     * @param workedHoursRepository the worked hours repo
     */
    public UserService(UserRepository userRepository,
                       WorkedHoursRepository workedHoursRepository) {
        this.userRepository = userRepository;
        this.workedHoursRepository = workedHoursRepository;
    }

    /**
     * query the data tier and get all entities,
     * but return them via a data transfer object, so we give the client only what they asked for
     *
     * @return all users in the requested format
     */
    public List<UserDto> getAllUsers() {
        List<UserDto> allUsers = new ArrayList<>();
        Iterable<Users> iterableUser = userRepository.findAll();
        for (Users users : iterableUser) {
            allUsers.add(users.toUserDto());
        }
        return allUsers;
    }

    /**
     * query the data tier for the worked hours for a user
     *
     * @param userId the user id we need
     * @return the list of worked hours
     */
    public List<WorkedHoursDTO> getWorkedHours(Integer userId) {
        List<WorkedHoursDTO> workedHoursDTOList = new ArrayList<>();
        List<WorkedHours> workedHoursList = workedHoursRepository.findByUserId(userId);

        // copy into the transfer object list
        workedHoursList.forEach(workedHours -> {
            WorkedHoursDTO workedHoursDTO = new WorkedHoursDTO();
            workedHoursDTO.setUserId(workedHours.getUserId());
            workedHoursDTO.setHours(workedHours.getHours());
            // pretty print the output of the dates
            workedHoursDTO.setDate(dateFormat.format(workedHours.getDate()));
            workedHoursDTOList.add(workedHoursDTO);
        });
        return workedHoursDTOList;
    }

    /**
     * create a new timeCard (i.e. a worked hour record) for a given user
     *
     * @param workedHours the worked hours we need to save
     * @return return it to the client
     */
    public WorkedHours createTimeCard(WorkedHours workedHours) {
        WorkedHours workedHoursCreate = workedHoursRepository.save(workedHours);
        log.info("Created timeCard =" + workedHoursCreate);
        return workedHoursCreate;
    }

}
