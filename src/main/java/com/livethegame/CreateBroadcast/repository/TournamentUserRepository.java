package com.livethegame.CreateBroadcast.repository;

import com.livethegame.CreateBroadcast.entities.TournamentUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TournamentUserRepository extends JpaRepository<TournamentUsers, Long> {
    Optional<TournamentUsers> findByTournamentIdAndRoleId(Long id, Long roleId);
}
