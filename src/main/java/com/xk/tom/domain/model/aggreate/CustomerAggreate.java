package com.xk.tom.domain.model.aggreate;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="Customer")
@Entity
public class CustomerAggreate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", updatable = false, nullable = false)
    @Comment("流水號") // 描述
    private Long customerId;


    @Column(name = "customer_name")
    @NotBlank
    @Comment("客戶名稱（公司名")
    private String customerName;


    @Column(name = "contact_person")
    @NotBlank
    @Comment("聯絡人姓名")
    private String contactPerson;


    @Column(name ="contact_phone")
    @NotBlank
    @Comment("聯絡電話")
    private String contactPhone;


    @Column(name = "contact_email")
    @Comment("聯絡信箱")
    private String contactEmail;


    @Column(name = "tax_id")
    @NotBlank
    @Comment("統一編號")
    private String taxId;

    @Column(name = "address")
    @Comment("公司地址")
    private String address;


    @Schema(description = "創建時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("97_創建時間")
    private ZonedDateTime createdTime;


    @Schema(description = "編輯時間", example = "2024-12-06T10:15:30+08:00[Asia/Taipei]")
    @UpdateTimestamp
    @Column(name = "update_time", nullable = true) // Make the column nullable
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("99_編輯時間")
    private ZonedDateTime updatedTime;




}
