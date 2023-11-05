package com.locationbasedfoodieservice.sigungu.repository;

import com.locationbasedfoodieservice.sigungu.entity.Sigungu;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SigunguRepository extends JpaRepository<Sigungu, Long> {
    Optional<Sigungu> findByDoSiAndSgg(String doSi, String sgg);
}
