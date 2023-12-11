# Ручки

Формат следующий: method + url, request, response, curl

## Получение всех цветков по userId
- GET http://localhost:8080/api/flowers/{userId}/flowers
```json
```

Response:
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

curl:
```shell
curl -X GET --location "http://localhost:8080/api/flowers/1/flowers"
```

## Добавление нового цветка
- POST http://localhost:8080/api/flowers/100/flowers/add
```json
{
  "id": 2,
  "flowerSpecies": "rose",
  "soil": "sandy",
  "fertilizerType": "borehole",
  "waterType": "crane",
  "height": 1
}
```

Response:
```json
{
  "status": 200,
  "message": "Action was performed"
}
```

curl:
```shell
curl -X POST --location "http://localhost:8080/api/flowers/100/flowers/add" \
    -H "Content-Type: application/json" \
    -d "{
          \"id\": 2,
          \"flowerSpecies\": \"rose\",
          \"soil\": \"sandy\",
          \"fertilizerType\": \"borehole\",
          \"waterType\": \"crane\",
          \"height\": 1
        }"
```

## Удаление цветка по userId и flowerId
- DELETE http://localhost:8080/api/flowers/{userId}/flowers?flowerId={flowerId}
```json
{}
```

Response:
```json
{
  "status": 200,
  "message": "Action was performed"
}
```

curl:
```shell
curl -X DELETE --location "http://localhost:8080/api/flowers/{userId}/flowers?flowerId={flowerId}"
```

## Обновление данных по цветку
- PATCH http://localhost:8080/api/flowers/{userId}/flowers
  Content-Type: application/json
```json
{
  "id": 2,
  "flowerSpecies": "rose",
  "soil": "sandy",
  "fertilizerType": "borehole",
  "waterType": "crane",
  "height": 1
}
```

Response:
```json
{
  "status": 200,
  "message": "Action was performed"
}
```

curl:
```shell
curl -X PATCH --location "http://localhost:8080/api/flowers/1/flowers" \
    -H "Content-Type: application/json" \
    -d "{
          \"id\": 2,
          \"flowerSpecies\": \"rose\",
          \"soil\": \"sandy\",
          \"fertilizerType\": \"borehole\",
          \"waterType\": \"crane\",
          \"height\": 1
        }"
```