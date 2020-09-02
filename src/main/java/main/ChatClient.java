package main;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

import bootStrap.ChatClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import messageHandler.HandlerInit;
import self.Self;
 
public class ChatClient {
 
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = com.sasa5680.CommonIndex.Network.port;
    
 
    
    public static void main(String[] args) throws Exception {
        System.out.println("port : "+ PORT);
    	new ChatClient().run();
    }
    
    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        
        //HandlerInit Init = new HandlerInit();
        //Init.Init();
        
        try {
            
            final SslContext sslCtx = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
            
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
            .channel(NioSocketChannel.class)
            .handler(new ChatClientInitializer(sslCtx));
            
            Channel channel = bootstrap.connect(HOST, PORT).sync().channel();
            
            System.out.println("Connection");
            
            InetAddress local; 
            local = InetAddress.getLocalHost(); 
    		String ip = local.getHostAddress(); 
            
    		Self.getSelf().setChannel(channel);
            Self.getSelf().IP = ip;
            
            Self.getSelf().InitSelf();
            
           /* ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
 
                // Sends the received line to the server.
                lastWriteFuture = channel.writeAndFlush(line + "\r\n");
 
                // If user typed the 'bye' command, wait until the server closes
                // the connection.
                if ("bye".equals(line.toLowerCase())) {
                    channel.closeFuture().sync();
                    break;
                }
            }
 
            // Wait until all messages are flushed before closing the channel.
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
                
            }*/
            
            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
 
}


