package com.xk.tom.domain.model.aggreate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderId implements Serializable {

    @Column(name = "seq_date" , nullable = false)
    private String seqDate;

    @Column(name = "sequence" , nullable = false)
    private String sequence;


    @Transient
    public String getFormattedOrderId() {
        String datePart = new SimpleDateFormat("yyyyMMdd").format(seqDate);
        String sequencePart = String.format("%08d", sequence); // 補0至8位數
        return datePart + "-" + sequencePart;
    }

}