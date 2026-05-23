package com.saksham.ShortIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.saksham.ShortIt.Entity.UrlMapping;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlMapping, Integer>{
    Optional<UrlMapping> findByShortCode(String shortCode);
    boolean existsByShortCode(String shortCode);
}
