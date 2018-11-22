package com.jmev.VehicleConnector.netty.entity;

import com.jmev.VehicleConnector.common.constant.ResponseSymbolType;
import com.jmev.VehicleConnector.exception.IllegalFrameException;
import com.jmev.VehicleConnector.utils.BCCUtils;
import com.jmev.VehicleConnector.utils.ByteUtils;
import io.netty.util.CharsetUtil;
import lombok.Data;
import lombok.Setter;

/**
 * <h2> 一、基础交互数据帧(上下行通用)，以下为简单的数据帧定义：</h2>
 * <pre>
 * 1、<code>framePrefixByte</code>:数据帧前缀
 * 长度: <b>两个字节</b>
 * 说明：固定为ASCII字符“##”，用“0x23,0x23”表示
 *
 * 2、<code>commandSymbolByte</code>:命令标示
 * 长度：<b>1个字节</b>
 *
 * 3、<code>responseSymbolByte</code>:应答标示
 * 长度：<b>1个字节</b>
 * 说明：0x01 成功；0x02 失败；0x03:VIN重复；oxFE：命令
 *
 * 4、<code>vinByte</code>:车辆VIN
 * 长度：<b>17个字节</b>
 * 说明：车辆VIN，字码符合GB16735中的规定
 *
 * 5、<code>encryptionTypeByte</code>:数据加密方式
 * 长度：<b>1个字节</b>
 * 说明：0x01：数据不加密；0x02：数据经过RSA算法加密； 0x03：数据经过AES128位算法加密；0xFE：表示异常；0xFF：表示无效；其他预留
 *
 * 6、<code>dataUnitLengthByte</code>:数据单元长度
 * 长度：<b>2个字节</b>
 * 说明：数据单元长度是数据单元的总字节数，有效值范围：0～65531
 *
 * 7、<code>dataUnitByte</code>:数据单元
 * 长度：<b>长度由dataUnitLength描述</b>
 * 说明：细节请参考国标定义
 *
 * 8、<code>bccCodeByte</code>:校验码
 * 长度：<b>1个字节</b>
 * 说明：采用BCC（异或校验）法，校验范围从命令单元的第一个
 * 字节开始，同后一字节异或，直到校验码前一字节为止，校验码占用一个字节，当数据单元存在加密时，应先加密后校验，先校验后解密
 * </pre>
 * <hr/>
 * <h2> 二、响应数据帧的简单定义 </h2>
 * 根据国标定义，部分上行数据帧需要响应，定义如下：
 * <blockquote>
 * 命令的主动发起方应答标志位0xFE,标示此包为命令包；当应答标志不是0xFE时，被动接收方应不应答。当命令的被动接收方应答标志不是0xFE,
 * 此表标示为应答包。
 * 当服务器发送应答时，只需改变应答标志、应答报文时间、并重新计算校验位即可，其余报文内容与主动发送报文一致。
 * </blockquote>
 * <hr/>
 *
 * @author Jun
 * @date 2018-11-16 19:22
 */
@Data
public final class BaseFrame {

    /**
     * 默认前缀
     */
    public static final byte[] DEFAULT_PREFIX = {0x23, 0x23};

    /**
     * 原始数据帧
     */
    private byte[] originFrameByte;

    /**
     * 数据帧起始符
     */
    @Setter
    private byte[] framePrefixByte;

    /**
     * 命令标示
     */
    private byte commandSymbolByte;

    /**
     * 应答标示
     */
    private byte responseSymbolByte;

    /**
     * 车辆VIN号
     */
    private byte[] vinByte;

    /**
     * 数据加密方式
     */
    private byte encryptionTypeByte;

    /**
     * 数据单元长度
     */
    private byte[] dataUnitLengthByte;

    /**
     * 数据单元
     */
    private byte[] dataUnitByte;

    /**
     * BCC(异或校验)码
     */
    private byte bccCodeByte;

    public BaseFrame() {
    }

    /**
     * 用于建造者模式
     *
     * @param builder 构造器
     */
    public BaseFrame(BaseFrame.Builder builder) {
        setOriginFrameByte(builder.originFrameByte); // 完整数据帧
        setFramePrefixByte(builder.framePrefixByte); // 前缀
        setCommandSymbolByte(builder.commandSymbolByte); // 命令标示
        setResponseSymbolByte(builder.responseSymbolByte); // 响应标示
        setVinByte(builder.vinByte); //vin
        setEncryptionTypeByte(builder.encryptionTypeByte); //加密方式
        setDataUnitLengthByte(builder.dataUnitLengthByte); //数据长度
        setDataUnitByte(builder.dataUnitByte); //数据单元
        setBccCodeByte(builder.bccCodeByte); //BCC
    }

    /**
     * 数据帧BCC（异或校验）校验,主要校验数据帧的前缀和BCC码
     *
     * @return true 数据校验通过
     */
    public boolean isAvailable() {
        //先判断数据完整性，bccCodeByte无法判断，因为其初始化为 0x00，数据也有可能为 0x00
        if (
                framePrefixByte == null ||  //前缀不能为空
                        originFrameByte == null || //完整数据不能为空
                        commandSymbolByte == 0x00 ||  //命令标示不能为 0x00
                        responseSymbolByte == 0x00 || //响应标示不能为 0x00
                        vinByte == null || //vin号不能为空
                        encryptionTypeByte == 0x00 || //加密方式不能为 0x00
                        dataUnitLengthByte == null //数据长度不能为空
        ) {
            return false;
        }

        //当数据单元长度为不为0时，校验数据单元完整性
        if (getDataUnitLength() > 0 && dataUnitByte == null) {
            return false;
        }

        //前缀 {0x23,0x23}
        if (!(framePrefixByte[0] == 0x23 && framePrefixByte[1] == 0x23)) {
            return false;
        }

        return BCCUtils.bccCalculate(originFrameByte, true) == bccCodeByte;
    }


    public int getCommandSymbol() {
        return 0xff & commandSymbolByte;
    }

    public int getResponseSymbol() {
        return 0xff & responseSymbolByte;
    }

    public String getVin() {
        return new String(vinByte, CharsetUtil.US_ASCII);
    }

    public int getEncryptionType() {
        return 0xff & encryptionTypeByte;
    }

    public int getDataUnitLength() {
        return ByteUtils.getUnsignedInt(dataUnitLengthByte);
    }

    public int getBccCode() {
        return 0xff & bccCodeByte;
    }

    /**
     * 获得构建对象
     *
     * @return 建造者
     */
    public static BaseFrame.Builder builder() {
        return new BaseFrame.Builder();
    }

    /**
     * 建造者，用于构建 {@link BaseFrame}
     */
    public static class Builder {

        private byte[] originFrameByte; //调用build方法时组合
        private byte[] framePrefixByte = DEFAULT_PREFIX;
        private byte commandSymbolByte;
        private byte responseSymbolByte;
        private byte[] vinByte;
        private byte encryptionTypeByte;
        private byte[] dataUnitLengthByte;
        private byte[] dataUnitByte;
        private byte bccCodeByte; //调用build方法时计算

        private Builder() {
        }


        public Builder setCommandSymbolByte(byte commandSymbolByte) {
            this.commandSymbolByte = commandSymbolByte;
            return this;
        }

        public Builder setResponseSymbolByte(byte responseSymbolByte) {
            this.responseSymbolByte = responseSymbolByte;
            return this;
        }

        public Builder setVinByte(byte[] vinByte) {
            this.vinByte = vinByte;
            return this;
        }

        public Builder setEncryptionTypeByte(byte encryptionTypeByte) {
            this.encryptionTypeByte = encryptionTypeByte;
            return this;
        }

        public Builder setDataUnitLengthByte(byte[] dataUnitLengthByte) {
            this.dataUnitLengthByte = dataUnitLengthByte;
            return this;
        }

        public Builder setDataUnitByte(byte[] dataUnitByte) {
            this.dataUnitByte = dataUnitByte;
            return this;
        }

        /**
         * 完成构建
         *
         * @return 构建后的合法数据帧
         */
        public BaseFrame build() {
            //判断数据完整性
            if (
                    commandSymbolByte == 0x00 ||
                            responseSymbolByte == 0x00 ||
                            vinByte == null ||
                            encryptionTypeByte == 0x00 ||
                            dataUnitLengthByte == null
            ) {
                throw new IllegalArgumentException("构建数据不完全，无法完成构建");
            }

            //当数据单元长度不为0，数据单元必定存在
            if (ByteUtils.getUnsignedInt(dataUnitLengthByte) > 0 && dataUnitByte == null) {
                throw new IllegalArgumentException("数据单元长度不为0，数据单元必须存在");
            }

            //类型转换
            byte[] csb = {this.commandSymbolByte};
            byte[] rsb = {this.responseSymbolByte};
            byte[] etb = {this.encryptionTypeByte};

            //数据组合，一定要按照数据帧的顺序进行组合
            if (this.dataUnitByte != null && this.dataUnitByte.length > 0) {
                //当存在数据单元时，同步组合
                byte[][] needMerge = {
                        this.framePrefixByte, //前缀,默认{0x23,0x23}
                        csb, //命令标示
                        rsb, //响应标示
                        this.vinByte, //vin
                        etb, //加密方式
                        this.dataUnitLengthByte, //数据单元长度
                        this.dataUnitByte //数据单元
                };

                byte[] bytes = ByteUtils.byteMerge(needMerge);
                this.bccCodeByte = BCCUtils.bccCalculate(bytes, false);

                //置入originFrameByte
                this.originFrameByte = ByteUtils.byteMerge(bytes, new byte[]{this.bccCodeByte});
            } else {
                byte[][] needMerge = {
                        this.framePrefixByte,
                        csb,
                        rsb,
                        this.vinByte,
                        etb,
                        this.dataUnitLengthByte
                };

                byte[] bytes = ByteUtils.byteMerge(needMerge);
                this.bccCodeByte = BCCUtils.bccCalculate(bytes, false);

                //置入originFrameByte
                this.originFrameByte = ByteUtils.byteMerge(bytes, new byte[]{this.bccCodeByte});
            }

            //检查构建后数据帧的有效性
            BaseFrame baseFrame = new BaseFrame(this);
            if (!baseFrame.isAvailable()) {
                throw new IllegalFrameException("无效数据帧", baseFrame);
            }

            return baseFrame;
        }
    }

    /**
     * 命令数据帧转为响应数据帧
     * <p>
     * 根据国标定义，响应数据只需要修改原数据帧的应答标志和重新计算bcc即可
     * <pre>
     * 转换步骤：
     *      1、改变应答标志
     *      2、重新计算<b>bcc</b>
     * </pre>
     *
     * @param responseSymbolType 响应的类型
     * @return 响应数据帧
     */
    public BaseFrame requestToResponse(ResponseSymbolType responseSymbolType) {
        //转换类型
        byte responseSymbol = (byte) responseSymbolType.getValue();

        //改变应答标志
        setResponseSymbolByte(responseSymbol);
        originFrameByte[3] = responseSymbol;

        //重新计算bcc
        byte bcc = BCCUtils.bccCalculate(originFrameByte, true);
        setBccCodeByte(bcc);
        originFrameByte[originFrameByte.length - 1] = bcc;

        return this;
    }
}
