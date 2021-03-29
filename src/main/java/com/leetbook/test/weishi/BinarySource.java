package com.leetbook.test.weishi;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/27 16:06
 * @Description:
 */
public class BinarySource implements Closeable {

    private BufferedReader reader;
    private String cachedLine;
    private String filename;

    public BinarySource(String filename) {
        try {
            this.filename = filename;
            this.reader = IOHelper.getBufferedReader(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasNext() {
        String line;
        try {
            line = this.reader.readLine();
            if (line == null || line.isEmpty()) {
                return false;
            }
            this.cachedLine = line.trim();
            return true;
        } catch (IOException e) {
        }
        return false;
    }

    public String nextString(){
        if (this.cachedLine == null) {
            if (!hasNext()) {
                throw new IllegalStateException("no content");
            }
        }
        String num = this.cachedLine;
        this.cachedLine = null;
        return num;
    }

    public int next() {
        if (this.cachedLine == null) {
            if (!hasNext()) {
                throw new IllegalStateException("no content");
            }
        }
        int num = Integer.parseInt(this.cachedLine);
        this.cachedLine = null;
        return num;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public void close() throws IOException {
        this.reader.close();
    }
}
