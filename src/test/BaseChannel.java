package test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BaseChannel {
	public static void main(String[] args) throws IOException {
		RandomAccessFile afile = new RandomAccessFile("C:/Users/Administrator/Desktop/cs.txt", "rw");
		FileChannel fileChannel = afile.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(48);
		int byteRead = fileChannel.read(buffer);
		while (byteRead != -1) {
			System.out.println("read:" + byteRead);
			buffer.flip();
			while (buffer.hasRemaining()) {
				System.out.println(buffer.get());
			}
			buffer.clear();
			byteRead = fileChannel.read(buffer);
			System.out.println(byteRead);
		}

	}

}
