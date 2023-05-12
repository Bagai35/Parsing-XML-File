import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class NewsParser {

    public static void main(String[] args) {
        try {
            // Создание фабрики для создания парсера
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Парсинг XML-файла
            Document doc = builder.parse(new File("news.xml"));

            // Получение списка всех элементов <item>
            NodeList itemList = doc.getElementsByTagName("item");

            // Создание HTML-файла для записи результатов
            FileWriter htmlWriter = new FileWriter("news.html");

            // Запись начальной части HTML-файла
            htmlWriter.write("<html><body><h1>Результаты парсинга:</h1>");

            // Обход элементов <item> и запись информации в HTML-файл
            for (int i = 0; i < itemList.getLength(); i++) {
                Element item = (Element) itemList.item(i);
                String title = getElementTextByTagName(item, "title");
                String description = getElementTextByTagName(item, "description");
                String pubDate = getElementTextByTagName(item, "pubDate");
                String author = getElementTextByTagName(item, "author");
                String category = getElementTextByTagName(item, "category");

                // Запись информации в HTML-файл
                htmlWriter.write("<h2>" + title + "</h2>");
                htmlWriter.write("<p><strong>Description:</strong> " + description + "</p>");
                htmlWriter.write("<p><strong>Publication Date:</strong> " + pubDate + "</p>");
                htmlWriter.write("<p><strong>Author:</strong> " + author + "</p>");
                htmlWriter.write("<p><strong>Category:</strong> " + category + "</p>");
                htmlWriter.write("<hr>");
            }

            // Запись завершающей части HTML-файла
            htmlWriter.write("</body></html>");

            // Закрытие HTML-файла
            htmlWriter.close();

            System.out.println("Parsing complete. The results have been saved to результат.html.");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String getElementTextByTagName(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return "";
    }
}
