package com.xiyoulinux.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlUtil {
  public HtmlUtil() {
  }

  public static String getTextFromTHML(String htmlStr) {
    Document doc = Jsoup.parse(htmlStr);
    String text = doc.text();
    StringBuilder builder = new StringBuilder(text);

    for(int index = 0; builder.length() > index; ++index) {
      char tmp = builder.charAt(index);
      if (Character.isSpaceChar(tmp) || Character.isWhitespace(tmp)) {
        builder.setCharAt(index, ' ');
      }
    }

    text = builder.toString().replaceAll(" +", " ").trim();
    String text1 = text.replace("<", "&lt;");
    String text2 = text1.replace(">", "&gt;");
    String text3 = text2.replace("'", "&amp;");
    return text3.replace("\"", "&quot;");
  }

  public static void main(String[] args) {
    String text = "\"/><script>alert(\"haha\");</script><!--ssssssssssssssss";
    System.out.println(getTextFromTHML("fdsfdsfsvf>><script></script>&lt;&gt;"));
  }
}
