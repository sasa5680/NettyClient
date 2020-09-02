package messageHandler.S2C;

import com.sasa5680.ProtoMessages.GeneralMSG.General;
import com.sasa5680.ProtoMessages.C2S.C2SPong.C2S_Pong;

import messageHandler.MessageHandler;
import self.Self;

public class S2C_PingHandler extends MessageHandler{

	@Override
	public void handle(General MSG) {
		// TODO Auto-generated method stub
		
		//send return Message
		C2S_Pong pong = C2S_Pong.newBuilder().build();
		General G = com.sasa5680.ProtoMessages.MessageWrapper.Wrap_NonRouting(pong);
		
		Self.getSelf().getChannel().writeAndFlush(G);
		
	}

}
