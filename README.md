# Курсовая работа ИСБД

## Важно

1) Если не установлен maven - `brew install maven`
2) После запуска проекта `mvn clean install` (на всякий случай)

## Локальный запуск

1) Устанавливаем docker desktop https://www.docker.com/products/docker-desktop/
2) Запускаем
3) Выполняем команду `docker-compose -f docker-compose.yml up -d`
4) Теперь мы увидели в приложении докера запущенный контейнер
5) Далее необходимо подключиться к бд (данные указаны чуть ниже)
6) Выполняем скрипт resources/changelog/base.sql
7) Ура, можно делать

## Подключение к бд из Idea

1) Открываем вкладку database (в правом верхнем углу под maven)
2) Добавляем новый datasource postgresql
3) Данные для подключения:
    - user: ISDB
    - password: 123456
    - database: ISDB_DEV

Бекенд:
Думаю сюда буду дополнительно писать контракты для ручек и формат ответов,
чтобы можно было как-то взаимодействовать

Ручки:

- GET http://localhost:8080/api/flowers/{userId}/flowers

```json
{
  "flowerList": [
    {
      "id": 1,
      "userId": 1,
      "flowerSpecies": "rose",
      "soil": "podzolic",
      "fertilizerType": "organic",
      "waterType": "crane",
      "height": 10.0
    },
    {
      "id": 2,
      "userId": 2,
      "flowerSpecies": "rose",
      "soil": "podzolic",
      "fertilizerType": "organic",
      "waterType": "crane",
      "height": 10.0
    }
  ]
}
```

```shell
curl -X GET --location "http://localhost:8080/api/flowers/1/flowers"
```

- DELETE http://localhost:8080/api/flowers/{userId}/flowers

```json
{}
```
```shell
curl -X DELETE --location "http://localhost:8080/api/flowers/{userId}/flowers"
```

- PATCH http://localhost:8080/api/flowers/{userId}/flowers
  Content-Type: application/json

```json
//body
{
  "id": 1,
  "flowerSpecies": "someString",
  "soil": "someString",
  "fertilizerType": "someString",
  "waterType": "someString",
  "height": 1
}
```
```shell
curl -X PATCH --location "http://localhost:8080/api/flowers/{userId}/flowers" \
    -H "Content-Type: application/json" \
    -d "{
          \"id\": 1,
          \"flowerSpecies\": \"someString\",
          \"soil\": \"someString\",
          \"fertilizerType\": \"someString\",
          \"waterType\": \"someString\",
          \"height\": 1
        }"
```

## План разработки:

Сюда по мере обновления буду добавлять пункты которые в ближайшее время хотим реализовать. Не думаю что тут важно в
каком порядке делать, стоит просто держать друг дурга в курсе что уже сделано.

### Основной план

Фронт:

- ~~Табличка для отображения всех цветов~~
- Добавить все поля в табличку
- Редактирование отдельного цветка (изменение всех полей)
- Удаление цветка
- Добавление кнопки "Создание тестовых данных". Там нужно просто запрос отправить, позже опишу ручку, чтобы я мог
  нагенерить новые данные для таблицы чтобы легче тестить было.

Бек:

- ~~Создать базовый проект~~
- ~~Настроить подключение к бд и получение от туда данных~~
- Описать уже имеющиеся ручки
- Добавить ручку по удалению моделей
- Добавить ручку по редактированию моделей
- Придумать еще фичей