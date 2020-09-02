package bootStrap;

import com.sasa5680.ProtoMessages.GeneralMSG;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import io.netty.handler.ssl.SslContext;
 
public class ChatClientInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;
 
    public ChatClientInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }
    @Override
    protected void initChannel(SocketChannel arg0) throws Exception {
        ChannelPipeline pipeline = arg0.pipeline();
        
        //pipeline.addLast(sslCtx.newHandler(arg0.alloc(), ChatClient.HOST, ChatClient.PORT));
        //pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        //pipeline.addLast(new StringDecoder());
        //pipeline.addLast(new StringEncoder());
        
        //--------InBound---------
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        pipeline.addLast(new ProtobufDecoder(GeneralMSG.General.getDefaultInstance()));
        
        
        //--------OutBound---------
        pipeline.addLast(new NetWorkCheckerOutBoundHandler());
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new ChatClientHandler());
    }
 
}


