package messageHandler;

import java.util.HashMap;

import io.netty.channel.Channel;

public class HandlerMapManager {

	private  HashMap<String, MessageHandler> HandlerMap = new HashMap<String, MessageHandler>();
	
	//get copy of the MessageMap
	public HashMap<String, MessageHandler> getHandlerMap(Channel channel){
		
		HashMap<String, MessageHandler> HandlerMap = new HashMap<String, MessageHandler>();
		
		HandlerMap.putAll(this.HandlerMap);	
		
		return HandlerMap;
	}
	
	
	public void addMessage(com.google.protobuf.Message M, MessageHandler MH) {
		
		String Type = M.getClass().getSimpleName();
		
		
		if(HandlerMap.containsKey(Type)) {
			
			System.out.println("Message Type already used");
		} else {
			
			System.out.println("Message add + Type : "+Type);
			HandlerMap.put(Type, MH);
		}
		
		
		//check
	}
	
	
	
	//apply Singleton pattern
	final private static class HandlerMapHolder {
		
		public static final HandlerMapManager handlerListMap = new HandlerMapManager();
	}	
	
	final public static HandlerMapManager getHandlertMap() {
		
		return HandlerMapHolder.handlerListMap;
	}
	
}
