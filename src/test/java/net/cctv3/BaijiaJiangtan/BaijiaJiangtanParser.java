package net.cctv3.BaijiaJiangtan;

import com.alibaba.fastjson.JSON;
import net.cctv3.BaijiaJiangtan.x.HttpClientUtils;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaijiaJiangtanParser {
    private static Document readHTML(String file) {
        Document document = null;
        try {
            document = Jsoup.parse(
                    /** 源代码的 class=""，手动加一个 class */
                    FileUtils.readFileToString(
                            new File(String.format("/Users/net.cctv3.i/net.cctv3.BaijiaJiangtan/caches/%s", file))
                    ).replace("class=\"\"", "class=\"e\"")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    private static String findVideoGuidByUrl(String url) {
        String result = "";
        String html = HttpClientUtils.doGet(url, "UTF-8");
        String strings[] = html.split("\n");
        for(String s : strings) {
            if(s.trim().startsWith("var guid =")) {
                Pattern pattern = Pattern.compile("\"([a-fA-F0-9]{32})\"");
                Matcher matcher = pattern.matcher(s.trim());
                if (matcher.find()) {
                    result = matcher.group(1);
                }
            }
        }
        // System.out.println("findVideoGuidByUrl strings: " + strings.length);
        // System.out.println(html.matches("var guid = \"[0-9][a-z]{32}\""));
        return result;
    }

    public static void main(String[] args) {
        System.out.println("findVideoGuidByUrl: " + findVideoGuidByUrl("https://tv.cctv.com/2009/12/30/VIDE1372752738505194.shtml"));;
//        Document document = readHTML("2009.html");
//        Element element = document.getElementById("boxList");
//        Elements elements = element.getElementsByClass("e");
//        int index = 0;
//        for (Element e : elements) {
//            // System.out.println(e);
//            Elements images = e.getElementsByClass("image");
//            // System.out.println(images);
//            for (Element image : images) {
//                index++;
//                System.out.println(String.format("第%d组: ", index));
//                // System.out.println(image);
//                Element img = image.getElementsByAttribute("title").first();
//                // System.out.println(img);
//                String title = img.attr("title");
//                String cover = img.attr("data-echo");
//                Element a = image.getElementsByTag("a").first();
//                String href = a.attr("href");
//                Element time = image.getElementsByClass("time").first();
//                Element year = image.getElementsByClass("year").first();
//                String myTime = year.text();
//                System.out.println("Title: " + title);
//                System.out.println("Cover: " + cover);
//                System.out.println("Href: " + href);
//                System.out.println("Time: " + myTime);
//                System.out.println("Guid: " + findVideoGuidByUrl(href));
//            }
//        }

    }
}