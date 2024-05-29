package com.livethegame.CreateBroadcast.repository;

import com.livethegame.CreateBroadcast.entities.BroadcastTypes;
import com.livethegame.CreateBroadcast.entities.TournamentTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BroadcastTypeRepository extends JpaRepository<BroadcastTypes, Long> {
}

