package messageHandler.A2R;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sasa5680.ProtoMessages.GeneralMSG.General;
import com.sasa5680.ProtoMessages.A2R.A2RDroneControl;

import messageHandler.MessageHandler;

public class A2R_DroneControlHandler extends MessageHandler{

	@Override
	public void handle(General MSG) {
		// TODO Auto-generated method stub
		
		Any any = MSG.getInnerMSG(0);
		
		
		try {
			
			A2RDroneControl.A2R_DroneControl msg = any.unpack(A2RDroneControl.A2R_DroneControl.class);
			
			//deliver control order to control Process
			
			
			
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
