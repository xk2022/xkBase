package com.xk.tom.domain.model.aggreate;


import com.xk.common.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Order_Record")
public class OrderRecordAggreate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = BaseEntity.Update.class)
    @Comment("00_流水號")
    private Long id;


    @Column(name="isExport")
    @Comment("是否出口")
    private Boolean isExport;

    @Column(name="isImport")
    @Comment("是否進口")
    private Boolean isImport;

}
