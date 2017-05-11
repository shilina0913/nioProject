package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class socketChannel {
	public static void main(String[] args) throws IOException {
		SocketChannel channel = SocketChannel.open();
		channel.connect(new InetSocketAddress("172.16.2.4", 8081));
		ByteBuffer buffer = ByteBuffer.allocate(48);
		int byteRead = channel.read(buffer);
		System.out.println(byteRead);
		while (byteRead > -1) {
			buffer.flip();
			while (buffer.hasRemaining()) {
				System.out.println(buffer.get());
			}
			buffer.clear();
			byteRead = channel.read(buffer);
		}
	}

}
