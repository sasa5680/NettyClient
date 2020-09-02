package self.StoredMessages;

import java.util.ArrayList;

import com.sasa5680.ProtoMessages.GeneralMSG.General;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import self.Self;

public class StoredMessages {
	
	Self self = Self.getSelf();
	
	public StoredMessages(){
		

	}
	
	private ArrayList<General> savedMessage = new ArrayList<General>();
	
	public void sendSavedMessages() {
		
		for(General msg : savedMessage) {
			
			ChannelFuture f = self.getChannel().writeAndFlush(msg);
			f.addListener(new ChannelFutureListener() {

				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					// TODO Auto-generated method stub
					
					if(f.isSuccess()) {
						
						savedMessage.remove(msg);
					}
					
				}
			});
		}
	}
	
	public void addMessage(General msg) {
		
		savedMessage.add(msg);
		
		
	}

}
