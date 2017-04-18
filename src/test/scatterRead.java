package test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class scatterRead {
	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile("C:/Users/Administrator/Desktop/cs.txt", "rw");
		ByteBuffer first = ByteBuffer.allocate(10);
		ByteBuffer second = ByteBuffer.allocate(10);
		FileChannel channel = file.getChannel();
		ByteBuffer[] bufferArray = { first, second };
		channel.read(bufferArray);
		System.out.println(first.position());
		System.out.println(second.position());
		first.flip();
		while (first.hasRemaining()) {
			System.out.println(first.get());
		}
		while (second.hasRemaining()) {
			System.out.println(second.get());
		}
	}
}
