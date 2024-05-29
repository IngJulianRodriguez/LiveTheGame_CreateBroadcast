package com.livethegame.CreateBroadcast.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence_roles", allocationSize = 1)
    private long id;
    private String name;
    private boolean is_admin;
    private LocalDateTime date_created;
    private LocalDateTime last_updated;


    public Roles() {
        this.setDate_created();
        this.setLast_updated();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean is_admin() {
        return is_admin;
    }

    public void setIs_admin() {
        this.is_admin = true;
    }

    public LocalDateTime getDate_created() {
        return date_created;
    }

    private void setDate_created() {
        ZoneId easternTime = ZoneId.of("America/Bogota");
        this.date_created = LocalDateTime.now(easternTime);;
    }

    public LocalDateTime getLast_updated() {
        return last_updated;
    }

    private void setLast_updated() {
        this.last_updated = this.getDate_created();
    }


}
