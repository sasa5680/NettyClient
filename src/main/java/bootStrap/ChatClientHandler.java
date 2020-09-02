package bootStrap;


import java.util.HashMap;

import com.sasa5680.ProtoMessages.GeneralMSG.General;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.WriteTimeoutException;
import messageHandler.HandlerInit;
import messageHandler.HandlerMapManager;
import messageHandler.MessageHandler;
import self.Self;


public class ChatClientHandler extends ChannelInboundHandlerAdapter {
	
	
	HashMap<String, MessageHandler> MH;
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
	  
		//add Channel
		Self.getSelf().setChannel(ctx.channel());
		  
		  
	}
	  
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	  
	
        
        HandlerInit.Init();
		MH = HandlerMapManager.getHandlertMap().getHandlerMap(ctx.channel());
		
		System.out.println("Channel Active");
		
	  
	}
    
	@Override
    public void channelRead(ChannelHandlerContext arg0, Object arg) throws Exception {
       
		//System.out.println("Channel Read");
    	try {
    		General generalMSG = (General) arg;
		
		//System.out.println(MSG.MessageType);
    	//System.out.println(MSG.Routing);
    	
    		String MSGType = generalMSG.getMessageType();
    		System.out.println("Client : Message Type - "+MSGType);
    		
    		
    		MessageHandler h = MH.get(MSGType);
    		if(h == null) {
    			System.out.println("Map Empty : "+MH.isEmpty());
    			System.out.println(""+MH.containsKey(MSGType));
    			System.out.println("Handler null");
    		}
    		
    		h.handle(generalMSG);
    	
    	} catch(NullPointerException e){
    		
    		System.out.println("UnHandled Message");
    		e.printStackTrace();
    		
    	} catch (ClassCastException e) {
    		
    		
    	}
    	
    	
    	
    	
        
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("Error !!");
        
        if (cause instanceof WriteTimeoutException) {
        	
        	
        	//if write action failed in certain time
        }
        
        if (cause instanceof java.io.IOException) {
        	
        	System.out.println("fatal Error : Client exited without order");
        }
        
        if (cause instanceof java.net.ConnectException) {
        	
        	//if there is no Connection with the server
        	System.out.println("there is no Connection with Server");
        }
        
        //if( cause instanceof  io.netty.channel.AbstractChannel$AnnotatedConnectException)
        
        
        
    }
 
}


