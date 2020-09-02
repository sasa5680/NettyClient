package messageHandler.MultiMessageHandler;

import com.sasa5680.ProtoMessages.GeneralMSG.General;

import messageHandler.MessageHandler;

public abstract class MutiMessageHandler extends MessageHandler
{
	public MutiMessageHandler( ) {
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public final void handle(General MSG) {
		// TODO Auto-generated method stub
		

		try {
			//get Message's time stamp
			TimeStamp stamp = getTimeStamp(MSG);
			
			//if first message
			if(stamp.getCurrent() == 1) {
				
				Session session = new Session(MSG.getMessageType());
				session.startSession(stamp);
				
				//call handler
				dohandle(MSG);
			} else {
				
				//search Session
				Session session = SessionMap.getSessionMap().Map.get(MSG.getMessageType());
				
				if(session.CheckNewMessageOrder(stamp)) {
					
					FailListner();

				} else {
					
					dohandle(MSG);
					
					//check Session is done
					if(session.completeChecker()) {
						session.endSession();
						finallyDoneListner();
					}
					
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//if something went wrong
			
			FailListner();
			e.printStackTrace();
		}
	

	}
	
	public abstract TimeStamp getTimeStamp(General G);
	
	public abstract void FailListner();
	
	public abstract void dohandle(General G);
	
	public abstract void finallyDoneListner();
	

}
