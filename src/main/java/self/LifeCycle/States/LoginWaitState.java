package self.LifeCycle.States;

import com.sasa5680.ProtoMessages.GeneralMSG.General;

import java.util.concurrent.TimeUnit;

import com.sasa5680.ProtoMessages.C2S.C2SLoginRequest.C2S_LoginRequest;

import ForwardingRetry.ForwardingFailedListener;
import ForwardingRetry.Forwarding_Retry;
import io.netty.util.concurrent.ScheduledFuture;
import self.Self;
import self.LifeCycle.LifeCycleState;

public class LoginWaitState implements LifeCycleState{
	
	ScheduledFuture<?> LoginWatcher;
	
	@Override
	public void Start() {
		// TODO Auto-generated method stub
		
		System.out.println("Client : LoginWaitState");
		
		Self self = Self.getSelf();
		
		//request Login
		C2S_LoginRequest inner = C2S_LoginRequest.newBuilder().setID(self.DeviceID)
															  .setType(self.DeviceType)
															  .setIP(self.IP)
															  .build();
		
		General g = com.sasa5680.ProtoMessages.MessageWrapper.Wrap_NonRouting(inner);
		
		Forwarding_Retry r = new Forwarding_Retry.Builder(g, self.getChannel()).build();
		r.ForwardingFailedListener(new ForwardingFailedListener() {

			@Override
			public void isFailed() {
				// TODO Auto-generated method stub
				
			
				//go to shudown...
			}
			
			
		});
		
		r.Foward_Message();
		
		
		//LoginWathcer
		LoginWatcher = self.getChannel().eventLoop().schedule(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				//check Login flag after 30 s
				
				boolean login = self.lifeCycle.loginflag;
				
				if(!(login)) {
					
					//go to shutdown state...
				}				
			}

		}, 30, TimeUnit.SECONDS);
		
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
		
		//terminate Login Watcher
		LoginWatcher.cancel(true);
	}

}
