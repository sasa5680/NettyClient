package messageHandler.MultiMessageHandler;

public final class Session {
	
	public int CurrentMessageCount;
	public int TotalMessageCount;
	
	private final String MessageType;
	
	
	public Session(String MessageType) {
		
		this.MessageType = MessageType;	
		
	}
	
	public String getMessageType() {
		
		return this.MessageType;
	}

	public void startSession(TimeStamp timeStamp) {
		
		//add Session to Map
		SessionMap.getSessionMap().Map.put(MessageType, this);
		this.TotalMessageCount = timeStamp.getTotal();
		CurrentMessageCount = 1;
	}
	

	public boolean CheckNewMessageOrder(TimeStamp timestamp) {
		
		CurrentMessageCount++;
		
		if(timestamp.getCurrent() != CurrentMessageCount) {
			
			//error
			return true;
		} else {
			
			return false;
		}

	}
	
	public boolean completeChecker() {
		
		if(TotalMessageCount == CurrentMessageCount) {
			
			return true;
		} else {
			
			return false;

		}
	}
	
	public void endSession() {
		
		SessionMap.getSessionMap().Map.remove(MessageType);
		
	}
	
}
