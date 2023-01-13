package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {

    //

    /* * JPA has the form <T, ID> where T is the domain type that the repository manages
     * and ID is the type of id of the entity, that the repository manages.
     * */


    //ParcelEntity save(ParcelEntity parcel); JpaRepository's extensions allow us to use all the functions it contains without having to rewrite them here

    public ParcelEntity findDistinctFirstByTrackingId(String trackingId);

/*    @Query(value = "SELECT * " +
            "FROM parcel " +
            "WHERE (tracking_id = :id);"
            , nativeQuery = true)
    ParcelEntity findByTrackingId(@Param("tracking_id") String tracking_id);*/
    @Transactional
    @Modifying
    @Query(value = "UPDATE parcel " +
            "SET state = :state " +
            "WHERE (id = :id);"
            , nativeQuery = true)
    void updateParcelStatus(@Param("id") Long id, @Param("state") String state);





}
