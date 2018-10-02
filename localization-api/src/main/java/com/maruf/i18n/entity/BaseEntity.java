package com.maruf.i18n.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author maruf
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(updatable = false)
    private Long tenant;
}