#!/bin/bash
curl -XPUT localhost:9200/prices  \
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

curl -XPUT localhost:9200/_bulk --data-binary @./docker/prices.json