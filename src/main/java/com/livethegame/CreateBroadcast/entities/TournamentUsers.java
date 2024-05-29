package com.livethegame.CreateBroadcast.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class TournamentUsers {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence_tournamentusers", allocationSize = 1)
    private long id;
    private boolean is_active;
    private LocalDateTime date_created;
    private LocalDateTime last_updated;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournaments tournament;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;


    public TournamentUsers() {
        this.setDate_created();
        this.setLast_updated();
        this.setIs_active();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean is_active() {
        return is_active;
    }

    private void setIs_active() {
        this.is_active = true;
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

    public Tournaments getTournament() {
        return tournament;
    }

    public void setTournament(Tournaments tournament) {
        this.tournament = tournament;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
