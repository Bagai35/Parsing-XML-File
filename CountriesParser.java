import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class CountriesParser {
    public static void main(String[] args) {
        try {
            // Создание парсера
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Чтение XML-файла
            Document document = builder.parse(new File("countries.xml"));

            // Создание HTML-файла для записи результатов парсинга
            BufferedWriter htmlWriter = new BufferedWriter(new FileWriter("countries.html"));

            // Запись начальной части HTML-файла
            htmlWriter.write("<html><head><h1>Результаты парсинга</h1></head><body>");

            // Получение списка элементов <country>
            NodeList countryList = document.getElementsByTagName("country");

            // Обход каждого элемента <country>
            for (int i = 0; i < countryList.getLength(); i++) {
                Node countryNode = countryList.item(i);
                if (countryNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element countryElement = (Element) countryNode;

                    // Получение значений name, lang и currency
                    String name = getElementTextByTagName(countryElement, "name");
                    String lang = getElementTextByTagName(countryElement, "language");
                    String currency = countryElement.getElementsByTagName("currency").item(0).getTextContent();

                    // Запись информации в HTML-файл
                    htmlWriter.write("<h3>Страна: " + name + "</h3>");
                    htmlWriter.write("<p>Язык: " + lang + "</p>");
                    htmlWriter.write("<p>Валюта: " + currency + "</p>");
                }
            }

            // Запись завершающей части HTML-файла
            htmlWriter.write("</body></html>");

            // Закрытие HTML-файла
            htmlWriter.close();

            System.out.println("Parsing complete. The results have been saved to countries.html.");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String getElementTextByTagName(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent().trim();
        }
        return "";
    }
}
