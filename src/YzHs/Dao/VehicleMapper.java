package YzHs.Dao;

import YzHs.Bean.Vehicle_info;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface VehicleMapper {
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,timeout = 3,readOnly = true)
    Vehicle_info find_vehicle_info(@Param("cardno") String cardno);
}
