package com.xk.tom.domain.repository;

import com.xk.tom.domain.model.aggreate.OrderId;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface OrderIdRepository extends JpaRepository<OrderId , Long> {


    @Query(value = "SELECT MAX(CAST(sequence AS SIGNED)) FROM daily_sequence e WHERE e.seq_date = :seqDate FOR UPDATE", nativeQuery = true)
    Long findMaxSequenceBySeqDate(@Param("seqDate") Date seqDate);
}
