package bootStrap;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import self.Self;

public class NetWorkCheckerOutBoundHandler extends ChannelOutboundHandlerAdapter {
	
	Self self = Self.getSelf();
	
	public NetWorkCheckerOutBoundHandler() {
	
	}
	
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
	    ChannelFuture f = ctx.writeAndFlush(msg);
	    f.addListener(new ChannelFutureListener() {
	    	@Override
			public void operationComplete(ChannelFuture future) throws Exception{
	    			
	    		if(future.isSuccess()) {
	    			
	    			self.networkStatue.GoodNetwork();
	    			System.out.println("CLient : Good Net");
					
				} else {
					
					self.networkStatue.BadNetwork();
					System.out.println("CLient : Bad Net");
				}
	    	}
	    	
	    });
	}
}
