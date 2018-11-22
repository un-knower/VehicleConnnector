package com.jmev.VehicleConnector.netty.entity.upstream;

import com.jmev.VehicleConnector.common.constant.RealTimeInfoType;
import com.jmev.VehicleConnector.netty.entity.BaseFrame;
import com.jmev.VehicleConnector.netty.entity.upstream.realtime.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <h2>实时信息数据帧定义</h2>
 *
 * <h3>以下为国标定义</h3>
 * <table>
 *     <tr>
 *         <th><b>类型编码</b></th>
 *         <th><b>说明</b></th>
 *     </tr>
 *     <tr>
 *         <td>0x01</td>
 *         <td>整车数据</td>
 *     </tr>
 *     <tr>
 *         <td>0x02</td>
 *         <td>驱动电机数据</td>
 *     </tr>
 *     <tr>
 *         <td>0x03</td>
 *         <td>燃料电池数据</td>
 *     </tr>
 *     <tr>
 *         <td>0x04</td>
 *         <td>发动机数据</td>
 *     </tr>
 *     <tr>
 *         <td>0x05</td>
 *         <td>车辆位置数据</td>
 *     </tr>
 *     <tr>
 *         <td>0x06</td>
 *         <td>极值数据</td>
 *     </tr>
 *     <tr>
 *         <td>0x07</td>
 *         <td>报警数据</td>
 *     </tr>
 *     <tr>
 *         <td>0x08~0x09</td>
 *         <td>终端数据预留</td>
 *     </tr>
 *     <tr>
 *         <td>0x0A~0x2F</td>
 *         <td>平台交换协议自定义数据</td>
 *     </tr>
 *     <tr>
 *         <td>0x30~0x7F</td>
 *         <td>预留</td>
 *     </tr>
 *     <tr>
 *         <td>0x80~0xFE</td>
 *         <td>用户自定义</td>
 *     </tr>
 * </table>
 *
 *
 * @author Jun
 * @date 2018-11-17 15:51
 */
@Data
public final class RealtimeInfoUpFrame {


    private BaseFrame baseFrame;

     /*--------------------------------------------
    |                 数据时间                     |
    ============================================*/
    /**
     * 数据采集时间
     */
    private byte[] timestampByte;
    private LocalDateTime timestamp;


    /*--------------------------------------------
    |                 整车数据                     |
    ============================================*/

    /**
     * 整车数据标志
     */
    private static final byte VEHICLE_DATA = (byte) RealTimeInfoType.VEHICLE_DATA.getValue();

    /**
     * 整车数据
     */
    private VehicleData vehicleData;

    /*--------------------------------------------
    |                 驱动电机数据                 |
    ============================================*/

    /**
     * 驱动电机数据标志位
     */
    private static final byte MOTOR_DATA = (byte) RealTimeInfoType.MOTOR_INFO.getValue();

    /**
     * 驱动电机数量
     *
     * <pre>
     *  有效值范围：<b>1~253</b>
     * </pre>
     */
    private byte motorNum;

    /**
     * 驱动电机数据
     */
    private MotorData[] motorData;

    /*--------------------------------------------
    |                 燃料电池数据                 |
    ============================================*/

    /**
     * 燃料电池数据标志位
     */
    private static final byte FUEL_CELL_DATA = (byte) RealTimeInfoType.FUEL_CELL_DATA.getValue();

    /**
     * 燃料电池数据
     */
    private FuelCellData fuelCellData;

    /*--------------------------------------------
    |                 发动机数据                   |
    ============================================*/

    /**
     * 发动机数据标志位
     */
    private static final byte ENGINE_DATA = (byte) RealTimeInfoType.ENGINE_INFO.getValue();

    /**
     * 发动机数据
     */
    private EngineData engineData;

    /*--------------------------------------------
    |                 车辆定位数据                 |
    ============================================*/

    /**
     * 车辆定位数据标志位
     */
    private static final byte VEHICLE_POSITION_DATA = (byte) RealTimeInfoType.VEHICLE_POSITION_DATA.getValue();

    /**
     * 车辆定位数据
     */
    private VehiclePositionData vehiclePositionData;

    /*--------------------------------------------
    |                 极值数据                    |
    ============================================*/

    /**
     * 极值数据标志位
     */
    private static final byte EXTREME_DATA = (byte) RealTimeInfoType.EXTREME_DATA.getValue();

    /**
     * 极值数据
     */
    private ExtremeData extremeData;

    /*--------------------------------------------
    |                 报警数据                     |
    ============================================*/

    /**
     * 报警数据标志位
     */
    private static final byte ALARM_DATA = (byte) RealTimeInfoType.ALARM_DATA.getValue();

    /**
     * 报警数据
     */
    private AlarmData alarmData;
}