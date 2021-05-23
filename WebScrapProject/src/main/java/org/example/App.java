package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
//import java.sql.SQLOutput;
//import java.util.Locale;
//import java.util.Scanner;

public class App
{
    public static String getData(String c) throws IOException {
        StringBuffer br=new StringBuffer();
        br.append("<html><body style='text-align:centre; border:dotted; border-width:10px 4px;" +
                "border-radius:10px 10px;'>");
        br.append(c.toUpperCase()+"<br>");
        String url="https://www.worldometers.info/coronavirus/country/"+c+"/";
        Document doc=Jsoup.connect(url).get();
        //Id:-#maincounter-wrap
        Elements elements= doc.select("#maincounter-wrap");
        //System.out.println(elements.html());
        //Lambda expressions
        elements.forEach((e)->{
            String text=e.select("h1").text();
            String count=e.select(".maincounter-number>span").text();
            br.append(text).append(count).append("<br>");
        });
        br.append("</body></html>");
        return br.toString();
    }
    public static void main( String[] args ) throws IOException {
        /*System.out.println( "Hello World!" );
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Country: ");
        String country=sc.nextLine();
        System.out.println(getData(country));*/
        JFrame root=new JFrame("Details of country");
        root.setSize(500,500);
        Font font=new Font("algerian",Font.BOLD,30);
        //Text Field
        JTextField field=new JTextField();
        //Label
        JLabel label=new JLabel();
        field.setFont(font);
        label.setFont(font);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        //button
        JButton button=new JButton("Get");
        button.addActionListener((event)->{
            //Click
            try {
                String mainData=field.getText();
                String result=getData(mainData);
                label.setText(result);


            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        button.setFont(font);
        //Adding field
        //North-->top
        //south-->bottom
        root.setLayout(new BorderLayout());
        root.add(field,BorderLayout.NORTH);
        root.add(label,BorderLayout.CENTER);
        root.add(button,BorderLayout.SOUTH);
        root.setVisible(true);
    }
}
