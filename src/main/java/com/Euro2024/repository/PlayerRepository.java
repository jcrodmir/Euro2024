package com.Euro2024.repository;

import com.Euro2024.models.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository  extends JpaRepository<PlayerEntity,Integer> {

}
