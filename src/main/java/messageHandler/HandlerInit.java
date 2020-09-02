package messageHandler;

import com.sasa5680.ProtoMessages.S2C.S2CDroneLoadingMSG;
import com.sasa5680.ProtoMessages.S2C.S2CDroneLoadingMSG.S2C_DroneLoadingMSG;
import com.sasa5680.ProtoMessages.S2C.S2CLoginRequestReturn.S2C_LoginRequestReturn;
import com.sasa5680.ProtoMessages.S2C.S2CPing.S2C_Ping;

import messageHandler.S2C.S2C_DroneLoadingHandler;
import messageHandler.S2C.S2C_LoginRequestReturnHandler;
import messageHandler.S2C.S2C_PingHandler;

public class HandlerInit {
	
	public static void Init() {
		
		HandlerMapManager HM = HandlerMapManager.getHandlertMap();
		
		/*--------------------------S2C------------------------*/
		HM.addMessage(S2C_Ping.getDefaultInstance(), new S2C_PingHandler());
		HM.addMessage(S2C_LoginRequestReturn.getDefaultInstance(), new S2C_LoginRequestReturnHandler());
		HM.addMessage(S2C_DroneLoadingMSG.getDefaultInstance(), new S2C_DroneLoadingHandler());
		
		
		/*--------------------------A2R------------------------*/
		
		
	}

}
