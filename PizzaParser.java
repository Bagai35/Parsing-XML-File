import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PizzaParser {

    public static void main(String[] args) {
        try {
            // Путь к XML-файлу
            String filePath = "pizza_recipes.xml";

            // Создание экземпляра DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Создание экземпляра DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Парсинг XML-файла
            Document document = builder.parse(new File(filePath));

            // Получение корневого элемента
            Element root = document.getDocumentElement();

            // Создание HTML-файла для записи информации
            FileWriter htmlWriter = new FileWriter("pizzaRecipes.html");

            // Запись начальной части HTML-файла
            htmlWriter.write("<html><body><h1>Результаты парсинга</h1>");

            // Перебор каждого элемента pizza внутри элемента pizzas
            NodeList pizzaList = root.getElementsByTagName("pizza");
            for (int i = 0; i < pizzaList.getLength(); i++) {
                Element pizzaElem = (Element) pizzaList.item(i);

                // Получение данных о пицце
                String name = getElementTextByTagName(pizzaElem, "name").trim();
                String description = getElementTextByTagName(pizzaElem, "description").trim();

                NodeList ingredientList = pizzaElem.getElementsByTagName("ingredient");
                String[][] ingredients = new String[ingredientList.getLength()][2];
                for (int j = 0; j < ingredientList.getLength(); j++) {
                    Element ingredientElem = (Element) ingredientList.item(j);
                    String ingredientName = ingredientElem.getAttribute("name");
                    String ingredientAmount = ingredientElem.getAttribute("amount");
                    ingredients[j][0] = ingredientName;
                    ingredients[j][1] = ingredientAmount;
                }

                NodeList stepList = pizzaElem.getElementsByTagName("step");
                String[][] preparationSteps = new String[stepList.getLength()][2];
                for (int j = 0; j < stepList.getLength(); j++) {
                    Element stepElem = (Element) stepList.item(j);
                    String stepId = stepElem.getAttribute("id");
                    String stepDescription = stepElem.getAttribute("description");
                    preparationSteps[j][0] = stepId;
                    preparationSteps[j][1] = stepDescription;
                }

                // Запись информации о пицце в HTML-файл
                htmlWriter.write("<h2>Name: " + name + "</h2>");
                htmlWriter.write("<p>Description: " + description + "</p>");
                htmlWriter.write("<h3>Ingredients:</h3>");
                htmlWriter.write("<ul>");
                for (String[] ingredient : ingredients) {
                    htmlWriter.write("<li>" + ingredient[0] + " (" + ingredient[1] + ")</li>");
                }
                htmlWriter.write("</ul>");
                htmlWriter.write("<h3>Preparation:</h3>");
                htmlWriter.write("<ol>");
                for (String[] step : preparationSteps) {
                    htmlWriter.write("<li>Step " + step[0] + ":" + step[1] + "</li>");
                }
                htmlWriter.write("</ol>");
            }

            // Запись завершающей части HTML-файла
            htmlWriter.write("</body></html>");

            // Закрытие HTML-файла
            htmlWriter.close();

            System.out.println("Parsing complete. The results have been saved to pizzaRecipes.html.");
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
