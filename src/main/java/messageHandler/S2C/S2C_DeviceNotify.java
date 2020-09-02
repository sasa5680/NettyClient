package messageHandler.S2C;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sasa5680.ProtoMessages.GeneralMSG.General;

import messageHandler.MessageHandler;
import self.LifeCycle.States.ActivateState;

public class S2C_DeviceNotify extends MessageHandler{

	@Override
	public void handle(General MSG) {
		// TODO Auto-generated method stub
		
		Any any = MSG.getInnerMSG(0);
		try {
			com.sasa5680.ProtoMessages.S2C.S2CLoginNotify.S2C_NewDeviceNotify inner
			= any.unpack(com.sasa5680.ProtoMessages.S2C.S2CLoginNotify.S2C_NewDeviceNotify.class);
			
			String Type = inner.getType();
			
			//set state to activate state
			
			if(Type.equals(com.sasa5680.CommonIndex.DeivceTypes.ANDROID.toString())) {
				
				self.Self.getSelf().lifeCycle.moveLifeCycle(new ActivateState());
			}
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
