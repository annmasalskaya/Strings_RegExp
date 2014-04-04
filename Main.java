package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.regex.*;

public class Main
{
    public static void main( String args[] )
    {
        String originalText=new String();
        String encodeText=new String();
        String regex = "[^a-zA-Z|\\s|\\.|\\-]";
        try
        {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                originalText+=scanner.next().replaceAll(regex,"") + " ";
            }
            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        // шифруем текст по заданному правилу
        for(int i=0; i<3; i++ )
        {
           for(int j=i; j<originalText.length(); j=j+3)
               encodeText+=Character.toString(originalText.charAt(j));
        }

        FileWriter newfile = null;    // запись в файл
        try
        {   newfile = new FileWriter("output.txt");
            newfile.append(encodeText);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(newfile != null)
            {
                try
                {
                    newfile.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        Pattern p = Pattern.compile(".+\\.\\s"); // смотрим, чтобы после точки в тексте шёл пробел
        Matcher m = p.matcher(originalText);
        boolean b = m.matches();
        System.out.println(b);

        Pattern p1 = Pattern.compile("device"); // ищем наличие подстроки
        Matcher m1 = p1.matcher(originalText);
        if (m1.find())
           System.out.println( "Подстрока : " + m1.group() +"\nначальная позиция : "+  m1.start()+ "\nконечная позиция : "+ m1.end());

        else
        {
            System.out.println("Подстрока не найдена.");
        }

   }

}
