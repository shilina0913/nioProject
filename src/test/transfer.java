package test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class transfer {
	public static void main(String[] args) throws IOException {
		RandomAccessFile fromFile = new RandomAccessFile("C:/Users/Administrator/Desktop/fromFile.txt", "rw");
		FileChannel fromChannel = fromFile.getChannel();
		RandomAccessFile toFile = new RandomAccessFile("C:/Users/Administrator/Desktop/toFile.txt", "rw");
		FileChannel toChannel = toFile.getChannel();
		toChannel.transferFrom(fromChannel, 0, fromChannel.size());
	}
}
