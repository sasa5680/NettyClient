package self;

import io.netty.channel.Channel;
import self.LifeCycle.ClientLifeCycle;
import self.LifeCycle.States.LoginWaitState;
import self.NetStatue.NetWorkStatue;
import self.NetStatue.States.NormalState;
import self.StoredMessages.StoredMessages;

public class Self {
	
	Self(){
		
		//Init Self
		//this.lifeCycle.moveLifeCycle(new LoginWaitState());
		//this.networkStatue.moveState(new NormalState());
	}
	
	
	public final String DeviceID = "2001";
	public final String DeviceType = com.sasa5680.CommonIndex.DeivceTypes.DRONE.toString();
	public String IP;

	private Channel channel;
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		
		//get IP
		
		this.channel = channel;
	}
	
	public final ClientLifeCycle lifeCycle     = new ClientLifeCycle();
	public final NetWorkStatue networkStatue   = new NetWorkStatue();
	public final StoredMessages stroedMessages = new StoredMessages();
	
	public final void InitSelf() {
		
		//Init Self
		this.lifeCycle.moveLifeCycle(new LoginWaitState());
		this.networkStatue.moveState(new NormalState());
		
	}
	 

	final private static class SelfClassHolder {
		
		public static final Self self = new Self();
	}	
	
	final public static Self getSelf() {
		
		return SelfClassHolder.self;
	}
	

}
