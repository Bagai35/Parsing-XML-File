from bs4 import BeautifulSoup as Soup

if __name__ == '__main__':
    with open('countries.xml', 'r', encoding='utf-8') as xml:
        soup = Soup(xml.read(), 'lxml-xml')  # Используем XML-парсер (lxml-xml) вместо HTML-парсера (lxml)

    countries = soup.find_all('country')  # Находим все элементы 'country'

    for country in countries:
        name = country.find('name').text.strip()  # Извлекаем текст элемента 'name' и удаляем пробельные символы
        language = country.find('language').text.strip()  # Извлекаем текст элемента 'language' и удаляем пробельные символы

        print(f"Name: {name}")
        print(f"Language: {language}")
        print()

