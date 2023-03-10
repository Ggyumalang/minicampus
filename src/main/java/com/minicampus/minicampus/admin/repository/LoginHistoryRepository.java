package com.minicampus.minicampus.admin.repository;

import com.minicampus.minicampus.admin.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
    Optional<LoginHistory> findFirstByUserIdOrderByLoggedDtDesc(String userId);

    List<LoginHistory> findAllByUserIdOrderByLoggedDtDesc(String userId);
}
