package messageHandler;


import com.sasa5680.ProtoMessages.GeneralMSG.General;

public abstract class MessageHandler {
		
	public MessageHandler() {}
	
	public abstract void handle(General MSG);
		
	

}
