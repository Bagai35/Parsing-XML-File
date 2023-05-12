from bs4 import BeautifulSoup as Soup

if __name__ == '__main__':
    with open('movies.xml', 'r', encoding='utf-8') as xml_file:
        soup = Soup(xml_file.read(), 'xml')  # Используем XML-парсер (xml)

    movies = soup.find_all('movie')  # Находим все элементы 'movie'

    for movie in movies:
        title = movie['title']
        year = movie['year']
        director = movie['director']

        print(f"Title: {title}")
        print(f"Year: {year}")
        print(f"Director: {director}")
        print()  # Пустая строка для отделения вывода информации о каждом фильме
