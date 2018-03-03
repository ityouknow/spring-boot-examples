package com.tools.schedulejob.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepository extends JpaRepository<Logs, Long> {
    Logs findByMethod(String method);
}