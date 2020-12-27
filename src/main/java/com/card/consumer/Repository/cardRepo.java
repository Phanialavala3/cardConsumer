package com.card.consumer.Repository;

import com.card.consumer.Entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cardRepo extends JpaRepository<CardEntity, Long > {
}
