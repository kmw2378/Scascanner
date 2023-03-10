package com.scascanner.studycafe.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StudyCafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_cafe_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private String name;
    private Integer minUsingTime;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String address;
    private String comment;

    @Builder
    public StudyCafe(Long id, Owner owner, String name, Integer minUsingTime, LocalTime openTime, LocalTime closeTime, String address, String comment) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.minUsingTime = minUsingTime;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.address = address;
        this.comment = comment;
    }

    public void update(String name, Integer minUsingTime, LocalTime openTime, LocalTime closeTime, String address, String comment) {
        this.name = name;
        this.minUsingTime = minUsingTime;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.address = address;
        this.comment = comment;
    }
}
