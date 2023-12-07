# Курсовая работа ИСБД

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