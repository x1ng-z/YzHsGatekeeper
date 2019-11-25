package YzHs.StartUp;

import YzHs.Bean.Vehicle_info;
import YzHs.Service.Command;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@ChannelHandler.Sharable
@Component
public class Msgencoder_Outbound extends ChannelOutboundHandlerAdapter {
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Msgencoder_Outbound.class);
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println(((Vehicle_info)msg).getVehicleno());
        Vehicle_info vehicle_info=(Vehicle_info) msg;
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
