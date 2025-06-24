package com.ignite.hairhub.repository;

import com.ignite.hairhub.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, UUID> {
}
