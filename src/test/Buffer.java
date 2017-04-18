package test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Buffer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		RandomAccessFile file = new RandomAccessFile("C:/Users/Administrator/Desktop/cs.txt", "rw");
		FileChannel channle = file.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(10);
		int readByte = channle.read(buffer);
		while (readByte > -1) {
			System.out.println("readLength:" + readByte);
			while (buffer.hasRemaining()) {
				System.out.println(buffer.get());
			}
			buffer.clear();
			readByte = channle.read(buffer);
		}
	}

}
