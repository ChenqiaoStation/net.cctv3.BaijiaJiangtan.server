package net.cctv3.BaijiaJiangtan;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class BaijieDealer {
    public static void main(String[] args) throws IOException {
        File file = new File("Baijie.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        StringBuilder builder = new StringBuilder();
        while((line = reader.readLine()) != null) {
            String s = line.toUpperCase().trim()
                    .replace(",", "，").replace(".", "。")
                    .replace("「", "“").replace("」", "”")
                    .replace("『", "").replace("』", "");
            if(s.contains("章") || s.contains("节")) {

            }
            else {
                s = s.replace(" ", "");
            }
            if(s.equals("")
                    || s.contains("八八书城")
                    || s.contains("小说下载")
                    || s.contains("电子书下载")
                    || s.contains("()") || s.contains("(）") || s.contains("（)")
                    || s.contains("TXT电子书")) {

            }
            else {
                if(!builder.toString().contains(s)) {
                    builder.append(s + "\r\n");
                }
            }
        }
        FileWriter writer = new FileWriter("少妇白洁.txt");
        writer.append(builder.toString());
        writer.flush();
        writer.close();
    }
}
