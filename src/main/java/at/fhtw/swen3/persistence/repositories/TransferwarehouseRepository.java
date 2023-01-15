package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TransferwarehouseRepository extends JpaRepository<TransferwarehouseEntity, Long> {


    @Transactional
    @Modifying
    @Query(value = "UPDATE hop_arrival " +
            "SET visited = true " +
            "WHERE (parcel_id = :parcel_id AND code = :code);"
            , nativeQuery = true)
    void updateHopAsVisited(@Param("parcel_id") Long parcel_id, @Param("code") String code);

}
