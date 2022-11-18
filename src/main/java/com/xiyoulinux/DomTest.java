package com.xiyoulinux;

import com.xiyoulinux.model.Blog;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DomTest {
  public DomTest() {
  }

  public static void main(String[] args) {
    ArrayList<Blog> blogs = getBlogFromHtml();
    if (null != blogs) {
      Iterator var2 = blogs.iterator();

      while(var2.hasNext()) {
        Blog blog = (Blog)var2.next();
        System.out.println("地址：" + blog.getUrl());
        System.out.println("标题：" + blog.getTitle());
        System.out.println("作者：" + blog.getAuthor());
        System.out.println("时间：" + blog.getDate() + " " + blog.getTime());
        System.out.println("内容：" + blog.getSummary());
        System.out.println("__________________________________________");
      }
    }

  }

  private static ArrayList<Blog> getBlogFromHtml() {
    Document doc;
    try {
      doc = Jsoup.connect("http://blog.xiyoulinux.com").get();
    } catch (IOException var17) {
      System.out.println("\nGet Blog From http://blog.xiyoulinux.com");
      System.out.println("JSON Resolve Error :  " + var17.toString());
      System.out.println("================================================");
      return null;
    }
    File f=new File("/tmp/blog.html");
    if(!f.exists()) {
      try {
        f.createNewFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    try {
      BufferedOutputStream os=new BufferedOutputStream(Files.newOutputStream(f.toPath()));
      os.write(doc.toString().getBytes(StandardCharsets.UTF_8));
      os.flush();
      os.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Element bbox = doc.getElementById("bbox");
    Elements vbox = bbox.getElementsByClass("vbox");
    ArrayList<Blog> blogs = new ArrayList();

    for(int i = 0; i < vbox.size(); ++i) {
      Blog blog = new Blog();
      Elements node = ((Element)vbox.get(i)).getElementsByClass("node");
      Elements link = ((Element)vbox.get(i)).getElementsByClass("more-link");
      Elements fsize = ((Element)vbox.get(i)).getElementsByClass("fsize");
      Elements title = ((Element)fsize.get(0)).getElementsByTag("a");
      Elements afont = ((Element)vbox.get(i)).getElementsByClass("afont");
      Elements time = ((Element)vbox.get(i)).getElementsByClass("time");
      SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

      Date date;
      try {
        date = sdf.parse(((Element)time.get(0)).text());
      } catch (ParseException var16) {
        System.out.println("\nGet Blog From http://blog.xiyoulinux.com");
        System.out.println("JSON Resolve Error :  " + var16.toString());
        System.out.println("================================================");
        return null;
      }

      SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
      blog.setTitle(((Element)title.get(0)).text());
      blog.setAuthor(((Element)afont.get(0)).text());
      blog.setUrl("http://blog.xiyoulinux.com/" + ((Element)link.get(0)).attr("href"));
      blog.setDate(d.format(date));
      blog.setTime(t.format(date));
      blog.setSummary(((Element)node.get(0)).text());
      blogs.add(blog);
    }

    return blogs;
  }
}
