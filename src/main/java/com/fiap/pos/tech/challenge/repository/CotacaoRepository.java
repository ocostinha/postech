package com.fiap.pos.tech.challenge.repository;

import com.fiap.pos.tech.challenge.enums.StatusEnum;
import com.fiap.pos.tech.challenge.repository.entity.CotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CotacaoRepository extends JpaRepository<CotacaoEntity, UUID> {
    CotacaoEntity findByCpfAndPlaca(String cpf, String placa);
    List<CotacaoEntity> findByStatus(StatusEnum status);
}
