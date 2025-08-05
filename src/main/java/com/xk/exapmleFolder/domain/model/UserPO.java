package com.xk.exapmleFolder.domain.model;

import com.xk.common.base.BaseEntity;
import com.xk.exapmleFolder.application.model.UserCreateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Table(name = "sample_user")
public class UserPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "example_user_id")
    @NotNull(groups = BaseEntity.Update.class)
    @Comment("00_流水號")
    private Long id;
    private String username;
    private String email;
    private String password;

}