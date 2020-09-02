package messageHandler.S2C;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sasa5680.ProtoMessages.GeneralMSG.General;

import messageHandler.MessageHandler;
import self.Self;

public class S2C_NewDeviceHandler extends MessageHandler{

	@Override
	public void handle(General MSG) {
		// TODO Auto-generated method stub
	
		Any any = MSG.getInnerMSG(0);
		try {
			com.sasa5680.ProtoMessages.S2C.S2CLoginNotify.S2C_NewDeviceNotify NDN
			= any.unpack(com.sasa5680.ProtoMessages.S2C.S2CLoginNotify.S2C_NewDeviceNotify.class);
			
			Self.getSelf().lifeCycle.Androidflag = true;
			
			//if(Self.getSelf().c)
			
			
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
