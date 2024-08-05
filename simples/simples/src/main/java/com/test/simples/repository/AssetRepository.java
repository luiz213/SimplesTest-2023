package com.test.simples.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.simples.entity.Asset;
@Repository
public interface AssetRepository extends JpaRepository <Asset, Long> {

	Asset getByCode(String code);
}
