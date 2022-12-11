package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {
    ParcelEntity findById(long id);
    ParcelEntity save(ParcelEntity parcelEntity);

     ParcelEntity deleteById(long id);

    //

    /* * JPA has the form <T, ID> where T is the domain type that the repository manages
     * and ID is the type of id of the entity, that the repository manages.
     * */


    //ParcelEntity save(ParcelEntity parcel);

}
