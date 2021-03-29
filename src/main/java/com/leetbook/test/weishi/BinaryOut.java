package com.leetbook.test.weishi;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/27 16:06
 * @Description:
 */
public class BinaryOut implements Closeable {

    private BufferedWriter writer;

    private String filename;

    private AtomicLong counts = new AtomicLong(0L);

    public BinaryOut(String filename) {
        try {
            this.filename = filename;
            writer = IOHelper.getBufferedWriter(filename);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void write(Integer num) throws IOException {

        writer.write(num+"\r\n");
        counts.incrementAndGet();

    }

    public void writeString(String num) throws IOException {

        writer.write(num+"\r\n");
        counts.incrementAndGet();
    }

    public String getFilename() {
        return filename;
    }

    public Long getCounts() {
        return counts.get();
    }

    @Override
    public void close() throws IOException {
        writer.flush();
        writer.close();
    }
}
