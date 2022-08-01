package com.hs.gatekeeper.service.mapper;

import com.hs.gatekeeper.model.entity.VehicleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface VehicleMapper {


    /**
     * 查询车辆信息 by 卡号
     * @param cardno 卡号
     * */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 3, readOnly = true, rollbackFor = {Exception.class})
    VehicleInfo findVehicleInfoByCNo(@Param("cardno") String cardno);


    /**
     * 查询车辆信息 by 车牌
     * @param vechelno 车牌
     * */
    VehicleInfo findVehicleInfoByVNo(@Param("vechelno") String vechelno);
}
