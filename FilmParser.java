import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class FilmParser {
    public static void main(String[] args) {
        try {
            // Создание парсера
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Парсинг XML-файла
            Document document = builder.parse(new File("movies.xml"));

            // Создание HTML-файла для записи результатов парсинга
            BufferedWriter htmlWriter = new BufferedWriter(new FileWriter("movie.html"));

            // Запись начальной части HTML-файла
            htmlWriter.write("<html><body><h1>Результаты парсинга</h1>");

            // Получение списка всех элементов <movie>
            NodeList movieList = document.getElementsByTagName("movie");

            // Итерация по каждому элементу <movie>
            for (int i = 0; i < movieList.getLength(); i++) {
                Element movie = (Element) movieList.item(i);

                // Получение информации о фильме
                String title = movie.getAttribute("title");
                String year = movie.getAttribute("year");
                String director = movie.getAttribute("director");
                String plot = movie.getAttribute("plot");

                // Запись информации о фильме в HTML-файл
                htmlWriter.write("<h2>Film " + (i + 1) + "</h2>");
                htmlWriter.write("<p>Title: " + title + "</p>");
                htmlWriter.write("<p>Year: " + year + "</p>");
                htmlWriter.write("<p>Director: " + director + "</p>");
                htmlWriter.write("<p>Plot: " + plot + "</p>");
            }

            // Запись завершающей части HTML-файла
            htmlWriter.write("</body></html>");

            // Закрытие HTML-файла
            htmlWriter.close();

            System.out.println("Parsing complete. The results have been saved to movie.html.");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
