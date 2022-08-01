package com.hs.gatekeeper.components.netty;

import com.hs.gatekeeper.constant.Command;
import com.hs.gatekeeper.model.entity.VehicleInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@ChannelHandler.Sharable
@Component
@Slf4j
public class MsgencoderOutbound extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info(((VehicleInfo) msg).getVehicleno());
        VehicleInfo vehicle_info = (VehicleInfo) msg;
        byte[] tmpSendBuf = Command.SEND_ALLOW.build(vehicle_info);
        ByteBuf buf = ctx.alloc().buffer().writeBytes(tmpSendBuf);
        ctx.writeAndFlush(buf).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    log.info("回写成功");
                } else {
                    log.error("回写失败");
                }
            }
        });
    }
}
