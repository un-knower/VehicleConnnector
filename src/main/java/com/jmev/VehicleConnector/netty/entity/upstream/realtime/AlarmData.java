package com.jmev.VehicleConnector.netty.entity.upstream.realtime;

import lombok.Data;

/**
 * 告警数据
 *
 * @author Jun
 * @date 2018-11-19 19:53
 */
@Data
public final class AlarmData {

    /**
     * 当前故障中最高报警等级
     *
     * <pre>
     *  有效值范围：<b>0~3</b>
     *
     *  <b>0</b>：无故障
     *  <b>0</b>：一级故障，不影响正常行驶
     *  <b>0</b>：二级故障，影响车辆性能，需驾驶员限制行驶
     *  <b>0</b>：三级故障，驾驶员应立即停车处理货求救
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte maxAlarmLevel;

    /**
     * 通用报警标志
     *
     * <pre>
     *  字段长度：<b>4</b>
     *
     *  <b>[bit31,bit30,...,bit3,bit2,bit1,bit0]</b>
     *
     *  <table align="center">
     *      <tr>
     *          <th>bit</th>
     *          <th>0</th>
     *          <th>1</th>
     *          <th>bit</th>
     *          <th>0</th>
     *          <th>1</th>
     *      </tr>
     *      <tr>
     *          <td>0</td>
     *          <td>温度差异报警</td>
     *          <td>正常</td>
     *          <td>11</td>
     *          <td>绝缘报警</td>
     *          <td>正常</td>
     *      </tr>
     *      <tr>
     *          <td>1</td>
     *          <td>电池高温报警</td>
     *          <td>正常</td>
     *          <td>12</td>
     *          <td>DC-DC温度报警</td>
     *          <td>正常</td>
     *      </tr>
     *      <tr>
     *          <td>2</td>
     *          <td>车载储能装置类型过压报警</td>
     *          <td>正常</td>
     *          <td>13</td>
     *          <td>制动系统报警</td>
     *          <td>正常</td>
     *      </tr>
     *      <tr>
     *          <td>3</td>
     *          <td>车载储能装置类型欠压报警</td>
     *          <td>正常</td>
     *          <td>14</td>
     *          <td>DC-DC状态报警</td>
     *          <td>正常</td>
     *      </tr>
     *      <tr>
     *          <td>4</td>
     *          <td>SOC低报警</td>
     *          <td>正常</td>
     *          <td>15</td>
     *          <td>驱动电机温度控制器报警</td>
     *          <td>正常</td>
     *      </tr>
     *      <tr>
     *          <td>5</td>
     *          <td>单体电池过压报警</td>
     *          <td>正常</td>
     *          <td>16</td>
     *          <td>高压互锁状态报警</td>
     *          <td>正常</td>
     *      </tr>
     *      <tr>
     *          <td>6</td>
     *          <td>单体电池欠压报警</td>
     *          <td>正常</td>
     *          <td>17</td>
     *          <td>驱动电机温度报警</td>
     *          <td>正常</td>
     *      </tr>
     *      <tr>
     *          <td>7</td>
     *          <td>SOC过高报警</td>
     *          <td>正常</td>
     *          <td>18</td>
     *          <td>车载储能装置类型过充</td>
     *          <td>正常</td>
     *      </tr>
     *      <tr>
     *          <td>8</td>
     *          <td>SOC跳变报警</td>
     *          <td>正常</td>
     *          <td>19</td>
     *          <td>预留</td>
     *          <td>预留</td>
     *      </tr>
     *      <tr>
     *          <td>9</td>
     *          <td>可充电储能系统不匹配报警</td>
     *          <td>正常</td>
     *          <td>...</td>
     *          <td>预留</td>
     *          <td>预留</td>
     *      </tr>
     *      <tr>
     *          <td>10</td>
     *          <td>电池单体一致性差报警</td>
     *          <td>正常</td>
     *          <td>31</td>
     *          <td>预留</td>
     *          <td>预留</td>
     *      </tr>
     *  </table>
     * </pre>
     */
    private byte[] alarmSymbol;

    /**
     * 电池故障数量
     *
     * <pre>
     * 有效值范围：<b>0~252</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte batteryErrorNum;

    /**
     * 电池故障代码列表
     *
     * <pre>
     *  字段长度：<b>4 * batteryErrorNum</b>
     * </pre>
     */
    private byte[] batteryErrorCode;

    /**
     * 驱动电机故障数量
     *
     * <pre>
     * 有效值范围：<b>0~252</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte motorErrorNum;

    /**
     * 驱动电机故障代码列表
     *
     * <pre>
     *  字段长度：<b>4 * motorErrorNum</b>
     * </pre>
     */
    private byte[] motorErrorCode;

    /**
     * 发动机故障数量
     *
     * <pre>
     * 有效值范围：<b>0~252</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte engineErrorNum;

    /**
     * 发动机故障列表
     * <pre>
     *  字段长度：<b>4 * engineErrorNum</b>
     * </pre>
     */
    private byte[] engineErrorCode;

    /**
     * 其它故障数量
     *
     * <pre>
     * 有效值范围：<b>0~252</b>
     *
     * <b>0xFF,0XFE</b>：异常
     * <b>0xFF,0XFF</b>：无效
     * </pre>
     */
    private byte otherErrorNum;

    /**
     * 其他故障代码列表
     *
     * <pre>
     *  字段长度：<b>4 * otherErrorNum</b>
     * </pre>
     */
    private byte[] otherErrorCode;
}
