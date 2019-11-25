package YzHs.Service;

import YzHs.Bean.Vehicle_info;
import YzHs.StartUp.DealServer;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public enum  Command {


    SEND_ALLOW("07","01"){
        @Override
        String analye(String context) {
            logger.error(name()+" command no spport");
            throw new UnsupportedOperationException(name()+" command no spport");
        }
        @Override
        byte[] build(Object object) {
            if(object instanceof Vehicle_info){
                Vehicle_info vehicle_info=(Vehicle_info) object;
                byte[] tmp_buf=new byte[20];
                tmp_buf[0]=Integer.valueOf(0x88).byteValue();
                tmp_buf[1]=Integer.valueOf(0x10).byteValue();
                tmp_buf[2]=Integer.valueOf(0x40).byteValue();
                tmp_buf[3]=Integer.valueOf(0x10).byteValue();
                tmp_buf[4]=Integer.valueOf(vehicle_info.getTermial()).byteValue();
                tmp_buf[5]=Integer.valueOf(0xa5).byteValue();
                tmp_buf[6]=Integer.valueOf(0x07).byteValue();
                if(vehicle_info.getVehicleno()!=null){
                    tmp_buf[7]=Integer.valueOf(0x01).byteValue();
                }else {
                    tmp_buf[7]=Integer.valueOf(0x00).byteValue();
                }
                tmp_buf[8]=Integer.valueOf(0x00).byteValue();
                tmp_buf[9]=Integer.valueOf(0x00).byteValue();
                tmp_buf[10]=Integer.valueOf(0x00).byteValue();
                tmp_buf[11]=Integer.valueOf(0x00).byteValue();
                tmp_buf[12]=Integer.valueOf(0x00).byteValue();
                tmp_buf[13]=Integer.valueOf(0x00).byteValue();
                tmp_buf[14]=Integer.valueOf(0x00).byteValue();
                tmp_buf[15]=Integer.valueOf(0x00).byteValue();
                tmp_buf[16]=Integer.valueOf(0x00).byteValue();
                tmp_buf[17]=Integer.valueOf(0x00).byteValue();
                tmp_buf[18]=Integer.valueOf(0x00).byteValue();
                tmp_buf[19]=Integer.valueOf(0x5a).byteValue();
                return tmp_buf;
            }
            return null;
        }
    },
    SEND_FORBID("07","00"){
        @Override
        String analye(String context) {
            logger.error(name()+" command no spport");
            throw new UnsupportedOperationException(name()+" command no spport");
        }
        @Override
        byte[] build(Object object) {
            if(object instanceof Vehicle_info){
                Vehicle_info vehicle_info=(Vehicle_info) object;
                byte[] tmp_buf=new byte[20];
                tmp_buf[0]=Integer.valueOf(0x88).byteValue();
                tmp_buf[1]=Integer.valueOf(0x10).byteValue();
                tmp_buf[2]=Integer.valueOf(0x40).byteValue();
                tmp_buf[3]=Integer.valueOf(0x10).byteValue();
                tmp_buf[4]=Integer.valueOf(vehicle_info.getTermial()).byteValue();
                tmp_buf[5]=Integer.valueOf(0xa5).byteValue();
                tmp_buf[6]=Integer.valueOf(0x07).byteValue();
                if(vehicle_info.getVehicleno()!=null){
                    tmp_buf[7]=Integer.valueOf(0x01).byteValue();
                }else {
                    tmp_buf[7]=Integer.valueOf(0x00).byteValue();
                }
                tmp_buf[8]=Integer.valueOf(0x00).byteValue();
                tmp_buf[9]=Integer.valueOf(0x00).byteValue();
                tmp_buf[10]=Integer.valueOf(0x00).byteValue();
                tmp_buf[11]=Integer.valueOf(0x00).byteValue();
                tmp_buf[12]=Integer.valueOf(0x00).byteValue();
                tmp_buf[13]=Integer.valueOf(0x00).byteValue();
                tmp_buf[14]=Integer.valueOf(0x00).byteValue();
                tmp_buf[15]=Integer.valueOf(0x00).byteValue();
                tmp_buf[16]=Integer.valueOf(0x00).byteValue();
                tmp_buf[17]=Integer.valueOf(0x00).byteValue();
                tmp_buf[18]=Integer.valueOf(0x00).byteValue();
                tmp_buf[19]=Integer.valueOf(0x5a).byteValue();
                return tmp_buf;
            }
            return null;
        }
    },
    RECEIVE_CARDNO("12","00"){
        @Override
        public  String analye(String context){
            System.out.println(context.substring(8,10));
            String findcard=context.substring(10);
            System.out.println(findcard);
            Matcher matcher1 = Pattern.compile("(^a5(.*)5a$)").matcher(findcard);
            if(matcher1.find()){
                String primercard=(matcher1.group().substring(8,16));
                StringBuilder cardbuild=new StringBuilder();
                for(int loop=0;loop<4;++loop){
                    cardbuild.append(primercard.substring(6-2*loop,8-2*loop));
                }
                return Long.valueOf(cardbuild.toString(),16).toString();
            }
            return null;
        }

        @Override
        byte[] build(Object object){
            logger.error(name()+" can't support build");
            throw new UnsupportedOperationException(name()+" can't support build");
        }
    };

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Command.class);
    private String main_command;
    private String sun_command;
    private Command(String main_command, String sun_command) {
        this.main_command = main_command;
        this.sun_command = sun_command;
    }
    /**Function description: analyze receive msg
     * @param context msg
     * @return cardno
     * */
    abstract  String analye(String context);

    /**
     * Function description: build send byte msg
     * @param object msg bean
     * @return byte[] msg byte array
     * */
    abstract byte[] build(Object object);
}
