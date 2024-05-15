package com.fiap.pos.tech.challenge.repository;

import com.fiap.pos.tech.challenge.enums.StatusEnum;
import com.fiap.pos.tech.challenge.repository.entity.ApoliceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ApoliceRepository extends JpaRepository<ApoliceEntity, UUID> {
    List<ApoliceEntity> findByStatus(StatusEnum status);
    List<ApoliceEntity> findByStatusAndDataHoraExpiracaoBefore(StatusEnum status, LocalDateTime expiracao);
}
