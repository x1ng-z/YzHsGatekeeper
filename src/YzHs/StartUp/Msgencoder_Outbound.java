package YzHs.StartUp;

import YzHs.Bean.Vehicle_info;
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
        byte[] aa=new byte[20];
        aa[0]=Integer.valueOf(0x88).byteValue();
        aa[1]=Integer.valueOf(0x10).byteValue();
        aa[2]=Integer.valueOf(0x40).byteValue();
        aa[3]=Integer.valueOf(0x10).byteValue();
        aa[4]=Integer.valueOf(vehicle_info.getTermial()).byteValue();
        aa[5]=Integer.valueOf(0xa5).byteValue();
        aa[6]=Integer.valueOf(0x07).byteValue();
        if(vehicle_info.getVehicleno()!=null){
            aa[7]=Integer.valueOf(0x01).byteValue();
        }else {
            aa[7]=Integer.valueOf(0x00).byteValue();
        }
        aa[8]=Integer.valueOf(0x00).byteValue();
        aa[9]=Integer.valueOf(0x00).byteValue();
        aa[10]=Integer.valueOf(0x00).byteValue();
        aa[11]=Integer.valueOf(0x00).byteValue();
        aa[12]=Integer.valueOf(0x00).byteValue();
        aa[13]=Integer.valueOf(0x00).byteValue();
        aa[14]=Integer.valueOf(0x00).byteValue();
        aa[15]=Integer.valueOf(0x00).byteValue();
        aa[16]=Integer.valueOf(0x00).byteValue();
        aa[17]=Integer.valueOf(0x00).byteValue();
        aa[18]=Integer.valueOf(0x00).byteValue();
        aa[19]=Integer.valueOf(0x5a).byteValue();
//        →◇88 18 40 10 01 A5 12 00 0A CE 5C 78 32 00 00 00 00 5A 00 5A
        ByteBuf buf =ctx.alloc().buffer().writeBytes(aa);
        ctx.writeAndFlush(buf).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                StringBuilder sb = new StringBuilder("");

                if (future.isSuccess()) {
                    logger.info(sb.toString() + "回写成功");
                } else {
                    logger.error(sb.toString() + "回写失败");
                }
//                ReferenceCountUtil.release(buf); // (2)
//                buf.release();
            }
        });

    }
}
