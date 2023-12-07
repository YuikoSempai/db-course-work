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

- GET http://localhost:8080/api/flowers/1/flowers

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