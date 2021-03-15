package com.security.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.search.model.Security;

@Repository
public interface SecurityRepository extends JpaRepository<Security, Long> {}
