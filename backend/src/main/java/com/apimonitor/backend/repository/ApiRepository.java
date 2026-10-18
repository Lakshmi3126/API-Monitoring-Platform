
package com.apimonitor.backend.repository;

import com.apimonitor.backend.model.Api;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Api, Long> {
}
