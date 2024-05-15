package com.fiap.pos.tech.challenge.repository;

import com.fiap.pos.tech.challenge.repository.entity.ApoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApoliceRepository extends JpaRepository<ApoliceEntity, UUID> {
}
