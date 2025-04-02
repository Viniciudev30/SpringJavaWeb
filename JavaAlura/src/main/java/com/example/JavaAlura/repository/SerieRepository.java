package com.example.JavaAlura.repository;

import com.example.JavaAlura.Model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
}
