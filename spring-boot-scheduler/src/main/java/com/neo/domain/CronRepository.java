package com.neo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CronRepository extends JpaRepository<Cron, Long> {
}
