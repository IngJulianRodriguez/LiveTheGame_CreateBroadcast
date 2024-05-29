package com.livethegame.CreateBroadcast.repository;

import com.livethegame.CreateBroadcast.entities.Categories;
import com.livethegame.CreateBroadcast.entities.Platforms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platforms, Long> {
}
