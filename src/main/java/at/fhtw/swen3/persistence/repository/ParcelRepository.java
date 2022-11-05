package at.fhtw.swen3.persistence.repository;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {
    /* * JPA has the form <T, ID> where T is the domain type that the repository manages
     * and ID is the type of id of the entity, that the repository manages.
     * */


    //ParcelEntity save(ParcelEntity parcel);

}
