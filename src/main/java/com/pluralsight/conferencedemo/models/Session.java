package com.pluralsight.conferencedemo.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sessions")
public class Session {
    /* Bringing in the database table columns
    Names are not typical java naming conventions, they are the exact same as in the db
    This is so they can be bound to automatically and no annotations are required
    */
    @Id // Specifies the primary key field (session_id in this case)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies how the primary id is populated
    private long session_id;

    private String session_name;
    private String session_description;
    private Integer session_length;

    // Bringing in the speakers, these are connected in the db
    @ManyToMany // Setting up manytomany relationship, this is for the join table in the db
    @JoinTable( // Defining that JoinTable, and the foreign key columns
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private List<Speaker> speakers;

    // Constructor
    public Session() {
    }

    // Getters and setters for the attributes

    public long getSession_id() {
        return session_id;
    }

    public void setSession_id(long session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }

    // Getters and setters for the speakers
    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }
}
