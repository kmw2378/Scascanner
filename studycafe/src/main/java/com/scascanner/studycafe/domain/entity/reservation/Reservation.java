package com.scascanner.studycafe.domain.entity.reservation;

import com.scascanner.studycafe.domain.entity.Room;
import com.scascanner.studycafe.domain.entity.StudyCafe;
import com.scascanner.studycafe.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_cafe_id")
    private StudyCafe studyCafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(value = EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Builder
    public Reservation(StudyCafe studyCafe, User user, Room room, LocalDate date, LocalTime startTime, LocalTime endTime, ReservationStatus reservationStatus) {
        this.studyCafe = studyCafe;
        this.user = user;
        this.room = room;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reservationStatus = reservationStatus;
    }

    public void cancel(){
        this.reservationStatus = ReservationStatus.CANCELED;
    }
}
