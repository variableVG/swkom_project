package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {

    //

    /* * JPA has the form <T, ID> where T is the domain type that the repository manages
     * and ID is the type of id of the entity, that the repository manages.
     * */


    //ParcelEntity save(ParcelEntity parcel); JpaRepository's extensions allow us to use all the functions it contains without having to rewrite them here

}
