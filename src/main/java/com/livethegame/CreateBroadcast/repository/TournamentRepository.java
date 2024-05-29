package com.livethegame.CreateBroadcast.repository;

import com.livethegame.CreateBroadcast.entities.Broadcasts;
import com.livethegame.CreateBroadcast.entities.Tournaments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournaments, Long> {
}
