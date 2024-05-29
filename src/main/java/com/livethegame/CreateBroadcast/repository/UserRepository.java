package com.livethegame.CreateBroadcast.repository;

import com.livethegame.CreateBroadcast.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
