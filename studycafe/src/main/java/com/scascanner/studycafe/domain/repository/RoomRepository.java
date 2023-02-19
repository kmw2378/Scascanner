package com.scascanner.studycafe.domain.repository;

import com.scascanner.studycafe.domain.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class RoomRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Room room) {

        if (room.getId() == null) {
            em.persist(room);
        } else {
            em.merge(room);
        }
    }

    public Room findById(Long roomId) {
        return em.find(Room.class, roomId);
    }

    public List<Room> findByStudyCafeId(Long cafeId) {
        return em.createQuery("select r from Room r where r.studyCafe.id = :cafeId", Room.class)
                .setParameter("cafeId", cafeId)
                .getResultList();
    }

    public List<Room> findAll() {
        return em.createQuery("select r from Room r", Room.class).getResultList();
    }
}