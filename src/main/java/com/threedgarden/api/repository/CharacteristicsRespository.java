package com.threedgarden.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Collector;

public interface CharacteristicsRespository extends JpaRepository<Collector.Characteristics,Long> {
}
