import xml.etree.ElementTree as ET

file_path = "pizza_recipes.xml"

# Парсинг XML-файла
tree = ET.parse(file_path)
root = tree.getroot()

# Перебор каждого элемента pizza внутри элемента pizzas
for pizza_elem in root.findall("./pizzas/pizza"):
    # Получение данных о пицце
    name = pizza_elem.find("name").text.strip()
    description = pizza_elem.find("description").text.strip()

    ingredients = []
    ingredients_elem = pizza_elem.find("ingredients")
    for ingredient_elem in ingredients_elem.findall("ingredient"):
        ingredient_name = ingredient_elem.get("name")
        ingredient_amount = ingredient_elem.get("amount")
        ingredients.append((ingredient_name, ingredient_amount))

    preparation_steps = []
    preparation_elem = pizza_elem.find("preparation")
    for step_elem in preparation_elem.findall("step"):
        step_id = step_elem.get("id")
        step_description = step_elem.get("description")
        preparation_steps.append((step_id, step_description))

    # Вывод информации о пицце
    print("Name:", name)
    print("Description:", description)
    print("Ingredients:")
    for ingredient_name, ingredient_amount in ingredients:
        print("- {} ({})".format(ingredient_name, ingredient_amount))
    print("Preparation:")
    for step_id, step_description in preparation_steps:
        print("Step {}: {}".format(step_id, step_description))
    print()
