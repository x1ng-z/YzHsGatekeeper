package gateway.netty;

import gateway.bean.VehicleInfo;
import gateway.constant.Command;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import org.springframework.stereotype.Component;

@ChannelHandler.Sharable
@Component
public class MsgencoderOutbound extends ChannelOutboundHandlerAdapter {
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MsgencoderOutbound.class);
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        logger.info(((VehicleInfo)msg).getVehicleno());
        VehicleInfo vehicle_info=(VehicleInfo) msg;
        byte[] tmpSendBuf=Command.SEND_ALLOW.build(vehicle_info);
        ByteBuf buf =ctx.alloc().buffer().writeBytes(tmpSendBuf);
        ctx.writeAndFlush(buf).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.info("回写成功");
                } else {
                    logger.error("回写失败");
                }
            }
        });
    }
}
