package com.adil.anoteapi.repository;

import com.adil.anoteapi.dto.tag.TagDetailDto;
import com.adil.anoteapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByNameAndUserId(String name, Long userId);

    List<Tag> findAllByUserId(@Param("user_id") Long userId);

    boolean existsByNameAndUserId(String name, Long userId);
}