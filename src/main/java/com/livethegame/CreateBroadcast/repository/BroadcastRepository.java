package com.livethegame.CreateBroadcast.repository;

import com.livethegame.CreateBroadcast.entities.BroadcastTypes;
import com.livethegame.CreateBroadcast.entities.Broadcasts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BroadcastRepository extends JpaRepository<Broadcasts, Long> {
}

