package messageHandler.S2C;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sasa5680.ProtoMessages.GeneralMSG.General;
import com.sasa5680.ProtoMessages.S2C.S2CLoginRequestReturn.S2C_LoginRequestReturn;

import messageHandler.MessageHandler;
import self.Self;
import self.LifeCycle.States.ShutdownState;


public class S2C_LoginRequestReturnHandler extends MessageHandler{

	@Override
	public void handle(General MSG) {
		// TODO Auto-generated method stub
		
		//unpack MSG
		try {
			Any any = MSG.getInnerMSG(0);
			S2C_LoginRequestReturn msg = any.unpack(S2C_LoginRequestReturn.class);
			
			boolean result = msg.getResult();
			boolean AndroidFlag = msg.getAndroidFlag();
			
			Self.getSelf().lifeCycle.isLogin(result, AndroidFlag);

		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			
			
		}
		
	}

}
