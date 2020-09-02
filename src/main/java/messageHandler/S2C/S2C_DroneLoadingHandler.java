package messageHandler.S2C;

import com.sasa5680.ProtoMessages.C2S.C2SLoadingResult;
import com.sasa5680.ProtoMessages.C2S.C2SLoadingResult.C2S_LoadingResult;

import ForwardingRetry.ForwardingFailedListener;
import ForwardingRetry.Forwarding_Retry;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sasa5680.ProtoMessages.GeneralMSG.General;

import messageHandler.MessageHandler;
import self.Self;
import self.LifeCycle.States.ActivateState;
import self.LifeCycle.States.HoldState;

public class S2C_DroneLoadingHandler extends MessageHandler{
	
	Self self = Self.getSelf();
	
	@Override
	public void handle(General MSG) {
		// TODO Auto-generated method stub
		System.out.println("Client : DroneLoading");

		Any any = MSG.getInnerMSG(0);
		
		try {
			com.sasa5680.ProtoMessages.S2C.S2CDroneLoadingMSG.S2C_DroneLoadingMSG msg 
			= any.unpack(com.sasa5680.ProtoMessages.S2C.S2CDroneLoadingMSG.S2C_DroneLoadingMSG.class);
			
			if(msg.getAndroidFlag()) {
				
				//go to Activate State
				Self.getSelf().lifeCycle.moveLifeCycle(new ActivateState());
				
			} else {
				
				Self.getSelf().lifeCycle.moveLifeCycle(new HoldState());
				
			}
			
			//go to hold state
			//send Loading Result to server
			
			System.out.println("send LoadingResult");
			C2S_LoadingResult LR = C2S_LoadingResult.newBuilder().setResult(true).build();
			General G = com.sasa5680.ProtoMessages.MessageWrapper.Wrap_NonRouting(LR);
			
			Forwarding_Retry R = new Forwarding_Retry.Builder(G, Self.getSelf().getChannel())
																				.Maximum_Count(10)
																				.InitDelay(1)
																				.Interval(1)
																				.build();
			R.ForwardingFailedListener(new ForwardingFailedListener() {

				@Override
				public void isFailed() {
					// TODO Auto-generated method stub
					
					self.stroedMessages.addMessage(G);
				}
				
			});
			
			R.Foward_Message();

		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
