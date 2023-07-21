package net.cctv3.BaijiaJiangtan;

import net.cctv3.BaijiaJiangtan.bean.Teacher;
import net.cctv3.BaijiaJiangtan.service.TeacherService;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootTest
class BaijiaJiangtanApplicationTests {
    private LogUtils logUtils = new LogUtils(BaijiaJiangtanApplicationTests.class);
    @Autowired
    TeacherService teacherService;

    @Test
    void contextLoads() {
        // tree(new File("/Users/net.cctv3.i/net.cctv3.BaijiaJiangtan/service"), 0);
        // calcDaikuan();
        // imageScaler();
        gitlabChats();
    }

private int countCommitsInSomeMonth(List<String> datas, String month, String regexp) {
    int counts = 0;
    for (String data : datas) {
        if (data.contains(month) && data.toLowerCase().matches(regexp)) {
            counts++;
        }
    }
    return counts;
}

    private int[] recountDatas(int counts[]) {
        int[] result = new int[counts.length];
        for (int i = 0; i < counts.length; i++) {
            int count = 0;
            for (int j = 0; j <= i; j++) {
                count += counts[j];
            }
            result[i] = count;
        }
        return result;
    }

    private String datas2String(String name, int datas[]) {
        String line = name + "\t";
        for (int i = 0; i < datas.length; i++) {
            line += (datas[i] + "\t");
        }
        line += datas[datas.length - 1];
        return line;
    }

    private String datas2String(String name, List<String> datas) {
        String line = name + "\t";
        for (int i = 0; i < datas.size(); i++) {
            line += (datas.get(i) + "\t");
        }
        line += datas.get(datas.size() - 1);
        return line;
    }

    private void gitlabChats() {
        try {
            // String datas = FileUtils.readFileToString();
            File file = new File("/Users/net.cctv3.i/net.cctv3.BaijiaJiangtan/caches/GitLab.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = "";
            List<String> datas = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                datas.add(line);
            }
            List<String> months = new ArrayList<>();
            System.out.println(datas.size());
            LocalDate startDate = LocalDate.of(2023, 1, 1);
            LocalDate endDate = LocalDate.of(2023, 8, 1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年M月");
            while (startDate.isBefore(endDate)) {
                // System.out.println(startDate.format(formatter));
                months.add(startDate.format(formatter));
                startDate = startDate.plusMonths(1);
            }
            int stationChenqoo[] = new int[months.size()];
            int liuChang[] = new int[months.size()];
            int tongYuhu[] = new int[months.size()];
            int wuZhenwei[] = new int[months.size()];
            int niuhao[] = new int[months.size()];
            int daishuo[] = new int[months.size()];
            int menxing[] = new int[months.size()];
            for (int i = 0; i < months.size(); i++) {
                stationChenqoo[i] = countCommitsInSomeMonth(datas, months.get(i), ".*(station|陈桥驿站|sunyupeng).*");
                liuChang[i] = countCommitsInSomeMonth(datas, months.get(i), ".*(liu|admin).*");
                tongYuhu[i] = countCommitsInSomeMonth(datas, months.get(i), ".*(tongyuhu).*");
                wuZhenwei[i] = countCommitsInSomeMonth(datas, months.get(i), ".*(zhenwei|wzw).*");
                niuhao[i] = countCommitsInSomeMonth(datas, months.get(i), ".*(niuhao).*");
                daishuo[i] = countCommitsInSomeMonth(datas, months.get(i), ".*(dai|shuo).*");
                menxing[i] = countCommitsInSomeMonth(datas, months.get(i), ".*(xmx).*");
            }
            stationChenqoo = recountDatas(stationChenqoo);
            liuChang = recountDatas(liuChang);
            tongYuhu = recountDatas(tongYuhu);
            wuZhenwei = recountDatas(wuZhenwei);
            niuhao = recountDatas(niuhao);
            daishuo = recountDatas(daishuo);
            menxing = recountDatas(menxing);

            logUtils.log("Structured Datas: ", new Object[]{stationChenqoo, liuChang, tongYuhu, wuZhenwei, niuhao
                    , daishuo});
            File excel = new File("/Users/net.cctv3.i/net.cctv3.BaijiaJiangtan/caches/Commit.txt");
            FileWriter writer = new FileWriter(excel);
            String names[] = new String[]{"Station Chenqoo", "Liuchang", "Tongyuhu", "Wuzhenwei", "Niuhao", "Daishuo", "Xumengxing"};
            int values[][] = new int[][]{stationChenqoo, liuChang, tongYuhu, wuZhenwei, niuhao, daishuo, menxing};
            writer.append(datas2String("时间/童鞋", months) + "\r\n");
            for (int i = 0; i < names.length; i++) {
                writer.append(datas2String(names[i], values[i]) + "\r\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void renameCapture2Https(String http, String https) {
        List<Teacher> teachers = teacherService.list();
    }

    private void imageScaler() {
        File outFile = new File("/Users/net.cctv3.i/net.cctv3.BaijiaJiangtan/caches/" + StringUtils.useUUID() + ".jpg");
        try {
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
//            Thumbnails.of (new URL("https://cdn.cctv3.net/net.cctv3.next/article/linuxKillNodeProcess1.jpg"))
            Thumbnails.of(new File("/Users/net.cctv3.i/net.cctv3.BaijiaJiangtan/caches/iPhone6s.jpg"))
                    .width(540)
                    .outputQuality(0.99)
                    .outputFormat("jpg")
                    .toFile(outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calcDaikuan() {
        int count = 0;
        int sum = 0;
        int base = 424;
        int baseBenjin = 1979;
        int sumBenjin = 0;
        do {
            count++;
            System.out.println(count + ": " + baseBenjin + " -> " + base);
            sum += base;
            base -= 16;
            baseBenjin += 15;
            sumBenjin += baseBenjin;
        } while (count < 24);
        System.out.println("Sum: " + sum);
        System.out.println("Sum Benjin: " + sumBenjin);
    }

    public void tree(File root, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        System.out.println(root.getName());
        for (File f : root.listFiles()) {
            for (int i = 0; i < level + 1; i++) {
                System.out.print(" ");
            }
            if (f.isFile()) {
                System.out.println("├─" + f.getName());
            } else {
                tree(f, level + 1);
            }
        }
    }
}