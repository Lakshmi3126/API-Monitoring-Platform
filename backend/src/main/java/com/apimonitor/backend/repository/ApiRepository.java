
package com.apimonitor.backend.repository;

import com.apimonitor.backend.model.Api;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiRepository extends JpaRepository<Api, Long> {
    List<Api> findByNameContaining(String name);
}
