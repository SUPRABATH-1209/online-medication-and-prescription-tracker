package com.health.medicare.repository;

import com.health.medicare.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward, Long> {
}
