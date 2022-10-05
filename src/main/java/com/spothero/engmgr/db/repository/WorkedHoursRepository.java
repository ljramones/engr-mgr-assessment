package com.spothero.engmgr.db.repository;

import com.spothero.engmgr.db.model.WorkedHours;
import com.spothero.engmgr.db.model.WorkedHoursId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The repository to handle all database operations for the Worked_Hours table
 */
@Repository
public interface WorkedHoursRepository extends JpaRepository<WorkedHours, WorkedHoursId> {

    /**
     * this method handles the query for a user id and returns all worked hours for that user
     *
     * @param userId the user id we need
     * @return the list of worked hours
     */
    List<WorkedHours> findByUserId(Integer userId);
}
