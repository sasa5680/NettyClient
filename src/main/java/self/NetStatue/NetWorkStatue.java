package self.NetStatue;

public class NetWorkStatue {
		
	private NetState Currentnet_State;
	
	public NetWorkStatue( ) {}
	
    final public void moveState(NetState New_state) {
    	
    	if(Currentnet_State == null) {
    		
    		Currentnet_State = New_state;
    		Currentnet_State.start();
    		
    	} else {
    		
    		if(Currentnet_State.getClass().getSimpleName().equals(New_state.getClass().getSimpleName())) {
        		
        		//do nothing 
        	} else {
        		
        		Currentnet_State.end();
            	
            	this.Currentnet_State = New_state;
            	
            	Currentnet_State.start();
        	}
    	}
    }
    
    public void FatalNetworkError() {
    	
    	//when fatal NetworkError Happened...
    	
    }
    

	private int MessageFailCount   = 0;
	private int MessageSucessCount = 0;
	
	private int MessageFailCountForRatio = 0;
	private int MessageSucessCountForRatio  = 0;
	
	public void ResetCount() {
		
		MessageFailCountForRatio    = 0;
		MessageSucessCountForRatio  = 0;
	}

	public void BadNetwork() {
		MessageFailCountForRatio++;
		MessageFailCount++;
	}
	
	public void GoodNetwork() {
		MessageSucessCountForRatio++;
		
		//reset fail counter
		MessageFailCount = 0;
		
		MessageSucessCount++;
		
	} 
	
	public int getMessageFailCount() {return MessageFailCount;}
	public int getMessageSucessCountForRatio() {return MessageSucessCountForRatio;}
	public int getMessageFailCountForRatio() {return MessageFailCountForRatio;}
}
