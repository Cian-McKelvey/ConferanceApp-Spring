package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

/* Setting up a JpaRepo
It is an interface, which extends JpaRepository
Session is the data type, long is the primary key
This adds stuff like find, update, save, delete, etc... (CRUD Operations)
*/
public interface SessionRepository extends JpaRepository<Session, Long> {

}
