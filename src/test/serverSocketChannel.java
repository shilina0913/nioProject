package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class serverSocketChannel {
	private Selector selector;

	public void initClient(String add, int port) {
		try {
			ServerSocketChannel channel = ServerSocketChannel.open();
			channel.configureBlocking(false);
			channel.socket().bind(new InetSocketAddress(add, port));
			Selector.open();
			channel.register(selector, SelectionKey.OP_CONNECT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void listen() {

		while (true) {
			try {
				selector.select();
				Set<SelectionKey> set = selector.selectedKeys();
				Iterator<SelectionKey> it = set.iterator();
				while (it.hasNext()) {
					SelectionKey selectionKey = it.next();
					SocketChannel selectableChannel = (SocketChannel) selectionKey.channel();
					if (selectionKey.isConnectable()) {
						if (selectableChannel.isConnectionPending()) {
							selectableChannel.finishConnect();
						}
						selectableChannel.write(ByteBuffer.wrap(new String("向服务器端发送消息").getBytes()));
						selectableChannel.configureBlocking(false);
						selectableChannel.register(selector, SelectionKey.OP_READ);
					} else if (selectionKey.isReadable()) {
						read(selectableChannel);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void read(SocketChannel channel) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int byteRead;
		try {
			byteRead = channel.read(buffer);
			while (byteRead > -1) {
				System.out.println(buffer.get());
				buffer.clear();
				try {
					byteRead = channel.read(buffer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		serverSocketChannel channel = new serverSocketChannel();
		channel.initClient("172.16.2.4", 8081);
		channel.listen();

	}

}
