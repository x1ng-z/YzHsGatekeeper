package com.hs.gatekeeper.components.netty;


import com.hs.gatekeeper.constant.Command;
import com.hs.gatekeeper.model.entity.VehicleInfo;
import com.hs.gatekeeper.service.mapper.VehicleMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@ChannelHandler.Sharable
@Component
@Slf4j
public class MsgDecoderInbound extends ChannelInboundHandlerAdapter {
    public MsgDecoderInbound() {
        super();
    }

    @Autowired
    public VehicleMapper vehicleMapper;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = ipSocket.getAddress().getHostAddress();
        Integer port = ipSocket.getPort();
        log.info("come in" + clientIp + ":" + port);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = ipSocket.getAddress().getHostAddress();
        Integer port = ipSocket.getPort();
        log.info("come out" + clientIp + ":" + port);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf wait_for_read = (ByteBuf) msg;
        String getinfo = null;
        getinfo = ByteBufUtil.hexDump(wait_for_read.readBytes(wait_for_read.readableBytes()));
        log.info(getinfo);
        //ctx.fireChannelRead(msg); test
        String cardNo = Command.RECEIVE_CARDNO.analye(getinfo);
        if (cardNo != null) {

            VehicleInfo vehicle_info = vehicleMapper.findVehicleInfoByCNo(cardNo);

            if (vehicle_info == null) {
                //找不到直接 新建一个
                log.warn("find null vehicle_info");
                vehicle_info = new VehicleInfo();
            } else {
                //找的到，验证是否为原材料车辆
                if ((vehicle_info = vehicleMapper.findVehicleInfoByVNo(vehicle_info.getVehicleno())) == null) {
                    log.warn("find no yuancailiao vehicle_info");
                    vehicle_info = new VehicleInfo();
                }
            }
            vehicle_info.setTermial(getinfo.substring(8, 10));
            ctx.writeAndFlush(vehicle_info);
        }
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error(cause.getMessage(), cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
            IdleStateEvent event = (IdleStateEvent) evt;

            InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
            String clientIp = ipSocket.getAddress().getHostAddress();
            IdleStateEvent stateEvent = (IdleStateEvent) evt;

            switch (stateEvent.state()) {
                case READER_IDLE:
                    log.info(clientIp + "Read Idle");
                    break;
                case WRITER_IDLE:
                    log.info(clientIp + "Read Idle");
                    break;
                case ALL_IDLE:
                    log.info(clientIp + "Read Idle");
                    break;
                default:
                    break;
            }
        }
    }

}
