import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by RATHANA on 20-Jul-17.
 */
public class ScrapeDemo {

    public static void main(String ...srgs){

        ScrapeDemo demo=new ScrapeDemo();
        /*String s="<li class=\"title font-strong\">\n" +
                "            <a href=\"#\" style=\"background:#04304C\">\n" +
                "              កីឡា \n" +
                "              <i class=\"fa fa-chevron-right\"></i>\n" +
                "              <div class=\"corner\" style=\"border-color: transparent transparent transparent #04304C\"></div>\n" +
                "            </a>\n" +
                "          </li>";
        demo.parseString(s);*/
    demo.extractData("http://news.sabay.com.kh/topics/sport");


    }


    public void parseString(String s){
        try{
            Document document= Jsoup.parse(s);
            //System.out.println(document);

            Elements lis=document.getElementsByTag("li");
            Elements a=lis.get(0).getElementsByTag("a");

            System.out.println(a.get(0).text());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(String url){
        Connection conn=null;
        try{
            conn=Jsoup.connect(url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public void extractData(String url){

        try {
            Document document = this.getConnection(url).get();
            //System.out.println(document);
            Element box= document.getElementById("posts_list");

            Elements subBoxs= box.select("a[href]");
            for(Element subBox : subBoxs){
                System.out.println("------------------------");
                System.out.println(subBox.attr("href"));
                System.out.println(subBox.select("div.title").text());
            }

           //System.out.println(subBoxs);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}