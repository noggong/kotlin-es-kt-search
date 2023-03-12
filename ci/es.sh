curl -XPUT 127.0.0.1:9200/prices  \
-d '
{
  "mappings": {
    "properties": {
      "category": {"type": "keyword"},
      "brand": {"type": "keyword"},
      "price": {"type": "integer"}
    }
  }
}'

curl -XPUT "127.0.0.1:9200/_bulk?pretty" --data-binary @prices.json