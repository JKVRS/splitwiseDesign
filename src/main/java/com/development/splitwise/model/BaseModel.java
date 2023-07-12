package com.development.splitwise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifyDate;

}
