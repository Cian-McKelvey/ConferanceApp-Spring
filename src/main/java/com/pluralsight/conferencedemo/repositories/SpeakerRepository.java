package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

// Check comments on SessionRepository for in depth comments
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
