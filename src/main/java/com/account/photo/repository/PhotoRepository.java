package com.account.photo.repository;

import com.account.photo.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    @Query("FROM Photo WHERE id >= :start AND id <= :stop")
    List<Photo> findByIdRange(long start, long stop);
}
