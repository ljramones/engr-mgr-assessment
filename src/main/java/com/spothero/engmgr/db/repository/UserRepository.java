package com.spothero.engmgr.db.repository;

import com.spothero.engmgr.db.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository to handle all database operations for the User table
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
}
