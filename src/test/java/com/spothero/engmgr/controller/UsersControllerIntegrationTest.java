package com.spothero.engmgr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spothero.engmgr.controller.model.UserDto;
import com.spothero.engmgr.controller.model.WorkedHoursDTO;
import com.spothero.engmgr.db.model.WorkedHours;
import com.spothero.engmgr.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@WebMvcTest(UserController.class)
public class UsersControllerIntegrationTest {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final List<UserDto> readAllUsersDtoList = new ArrayList<>();

    private static final List<WorkedHoursDTO> readWorkedHoursForUser = new ArrayList<>();


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    /**
     * pre-create our test data
     */
    @BeforeAll
    public static void setup() {
        // 1. create the get All users data structure
        for (int i = 0; i < 5; i++) {
            UserDto userDto = new UserDto(i, "fname" + i, "lname" + i, "i" + i + "@gmail.com");
            readAllUsersDtoList.add(userDto);
        }

        try {
            // 2. create the worked hours data structure
            var date1 = new SimpleDateFormat("yyyyMMdd").parse("20220520");
            var workedHours1 = new WorkedHoursDTO(2, dateFormat.format(date1).toString(), 3);
            readWorkedHoursForUser.add(workedHours1);

            var date2 = new SimpleDateFormat("yyyyMMdd").parse("20220521");
            var workedHours2 = new WorkedHoursDTO(2, dateFormat.format(date2), 2);
            readWorkedHoursForUser.add(workedHours2);

            var date3 = new SimpleDateFormat("yyyyMMdd").parse("20220522");
            var workedHours3 = new WorkedHoursDTO(2, dateFormat.format(date3), 4);
            readWorkedHoursForUser.add(workedHours3);

        } catch (Exception e) {
            fail();
        }

    }

    /**
     * test the get(/v1/users) which gets all users
     *
     * @throws Exception if the test fails
     */
    @Test
    public void shouldReturnAllUsers() throws Exception {
        when(userService.getAllUsers()).then(answerGetAllUsers());
        this.mockMvc.perform(get("/v1/users"))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().json(createJSONRepresentationCall1(readAllUsersDtoList)));
    }

    @Test
    public void getWorkedHoursForUser() throws Exception {
        when(userService.getWorkedHours(2)).then(answerGetAllWorkedHours());
        this.mockMvc.perform(get("/v1/users/2/worked_hours"))
                .andDo(print())
                .andExpect((status().isOk()))
                .andExpect(content().json(createJSONRepresentationCall2(readWorkedHoursForUser)));
    }

    ///////////////////// S U P P O R T   C o d e  ////////////////////////////////////////

    private Answer<List<WorkedHoursDTO>> answerGetAllWorkedHours() {
        return invocationOnMock -> readWorkedHoursForUser;
    }

    /**
     * we use this lambda as a way of supplying the pre-created users for the user service to test the web layer
     *
     * @return a list of answers for the userService.getAllUsers()
     */
    private Answer<List<UserDto>> answerGetAllUsers() {
        return invocationOnMock -> readAllUsersDtoList;
    }

    /**
     * we get an answer back as a JSON string, so we need to marshal the userDTO list into JSON
     *
     * @param userDtoList the user DTO list
     * @return the JSON representation
     */
    private String createJSONRepresentationCall1(List<UserDto> userDtoList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(userDtoList);
        } catch (JsonProcessingException e) {
            // this will generate a failure if we get an exception on marshalling
            return "";
        }
    }

    private String createJSONRepresentationCall2(List<WorkedHoursDTO> workedHoursDTOList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(workedHoursDTOList);
        } catch (JsonProcessingException e) {
            // this will generate a failure if we get an exception on marshalling
            return "";
        }
    }

}
