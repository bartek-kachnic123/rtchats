package com.kachnic.rtchats.modules.userprofile.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserProfileJpaRepository extends JpaRepository<UserProfileJpa, UUID> {}
