package com.xk.tom.domain.model.aggreate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="daily_sequence")
@Entity
public class OrderId implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Comment("流水號") // 描述
    private Long id;


    @Column(name = "seq_date" , nullable = false)
    private String seqDate;

    @Column(name = "sequence" , nullable = false)
    private String sequence;





}