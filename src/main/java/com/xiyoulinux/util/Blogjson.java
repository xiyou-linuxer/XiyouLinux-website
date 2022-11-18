package com.xiyoulinux.util;

import com.xiyoulinux.dao.BlogDAO;
import com.xiyoulinux.model.Blog;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Blogjson {
  public Blogjson() {
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

    System.out.println("\nGet Blog From http://blog.xiyoulinux.com");
    System.out.println("Blog Count:\t" + blogs.size());
    System.out.println("================================================");
    return blogs;
  }

  private static ArrayList<Blog> getBlogFromJson() {
    Document doc;
    try {
      doc = Jsoup.connect("http://blog.xiyoulinux.com/blogjson").get();
    } catch (IOException var19) {
      System.out.println("\nGet Blog From http://blog.xiyoulinux.com/blogjson");
      System.out.println("JSON Resolve Error :  " + var19.toString());
      return getBlogFromHtml();
    }

    Elements js = doc.getElementsByTag("body");
    String jsonString = js.html();
    ArrayList<Blog> blogs = new ArrayList();

    JSONObject jsonObjects;
    try {
      jsonObjects = new JSONObject(jsonString);
    } catch (JSONException var18) {
      System.out.println("\nGet Blog From http://blog.xiyoulinux.com/blogjson");
      System.out.println("JSON Resolve Error :  " + var18.toString());
      return getBlogFromHtml();
    }

    for(int i = 0; i < 10; ++i) {
      String key = "blog-" + i;
      JSONObject jsonObject = jsonObjects.getJSONObject(key);
      String datetime = jsonObject.getString("PubDate");
      SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
      SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");

      Date date;
      try {
        date = sdf.parse(datetime);
      } catch (ParseException var17) {
        date = new Date();
      }

      Blog blog = new Blog();
      String title = jsonObject.getString("Title");
      String author = jsonObject.getString("Author");
      String link = jsonObject.getString("BlogArticleLink");
      if (title.length() > 50) {
        title = title.substring(0, 49);
      }

      if (author.length() > 20) {
        author = author.substring(0, 19);
      }

      if (link.length() > 256) {
        link = link.substring(0, 255);
      }

      blog.setTitle(title);
      blog.setAuthor(author);
      blog.setUrl(link);
      blog.setDate(d.format(date));
      blog.setTime(t.format(date));
      blog.setSummary(jsonObject.getString("ArticleDetail"));
      blogs.add(blog);
    }

    System.out.println("\nGet Blog From http://blog.xiyoulinux.com/blogjson");
    System.out.println("Blog Count:\t" + blogs.size());
    System.out.println("================================================");
    return blogs;
  }

  public static void saveBlog() throws IOException, ParseException {
    BlogDAO blogDAO = new BlogDAO();
    ArrayList<Blog> blogs = getBlogFromJson();
    if (null != blogs) {
      Iterator var2 = blogs.iterator();

      while(var2.hasNext()) {
        Blog blog = (Blog)var2.next();
        ArrayList<Blog> b = blogDAO.getBlogByTitle(blog.getTitle());
        if (b.size() == 0) {
          blogDAO.insert(blog);
        }
      }

    }
  }
}
