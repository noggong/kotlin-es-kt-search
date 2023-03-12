# Kotlin - ElasticSearch (이하: es)
- Spring boot 3.0.4
- jdk: openjdk17
- ([kt-search](https://github.com/jillesvangurp/kt-search/)) 바로가기

## Getting started
### docker
- es container
- kibana container
- spring boot container
- prices index mapping
- prices bulk indexing
```bash
docker-compose up -d
```

## 요구 사항

### 모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할때 최저가 조회 API
```bash
curl --location 'localhost:8080/lowest-price/by/category'
```

### 한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회 API
```bash
curl --location 'localhost:8080/lowest-price/by/brand'
```

### 각 카테고리 이름으로 최소, 최대 가격 조회 API
```bash
curl --location 'localhost:8080/price/by/상의'
```

### 브랜드 상품 가격 추가
```bash
curl --location 'localhost:8080/prices' \
--header 'Content-Type: application/json' \
--data '{
    "category": "상의",
    "brand": "D",
    "price": 1000
}'
```

### 브랜드 상품 가격 업데이트
```bash
curl --location --request PATCH 'localhost:8080/prices/aJMq0oYBmSvLIkfI021R22' \
--header 'Content-Type: application/json' \
--data '{
    "price": 8000
}'
```

### 브랜드 상품 가격 삭제
```bash
curl --location --request DELETE 'localhost:8080/prices/q5PG1IYBmSvLIkfI-W02'
```

## 과정
### 새롭게 만난 언어 Kotlin 

- Java 와 kotlin 을 고민을 했고, Java 로 spring boot 를 사용할때 보다 kotlin 이 간결하게 사용할 수 있는 것으로 알고 있어 koltin 선택 했습니다.
- Kotlin의 문법은 함수형과, 객체지향을 잘 섞어 놓은 듯한 느낌입니다.

### 데이터 저장소
1. mysql / es 두 곳에 저장 하려고 했습니다.
   - mysql 에 넣어 두고 Stream 혹은 Queue 를 이용하여 logstash 를 통해 새로 입력 된 정보는 ES 에 indexing 되도록 할까 했습니다.
2. 데이터 특성상 두 곳 모두 넣어두는것은 의미가 없을듯 하여 mysql 관련 코드, 의존성 모두 제거 했습니다.
3. kt-search 를 이용하여 CRUD 및 집계 쿼리를 이용하여 요구 사항을 처리 하였습니다.
4. build 패턴 혹은 팩토리 패턴을 이용하여 mysql 또한 구현체를 구현하고 싶었으나 시간 부족으로 거기까진 하지 못했습니다. `PriceRepository` interface 만 작성하였습니다.

### 고난
#### es client
1. es client 를 초반에 spring data 구현체로 사용하였으나 aggregation 결과에 접근 하지 못하였습니다.
   - 접근 구현체를 구현할 수 있다는 글을 보았지만, 너무 복잡 해 보였습니다.
2. [es-kotlin-client](https://github.com/jillesvangurp/es-kotlin-client) 를 이용하려고 했지만, deprecated 되었습니다.
3. 최종적으로 kt-search 를 선택 하였습니다.
4. 하지만 spring boot 에서 코르틴 dipatcher 메소드를 찾을 수 없었고, spring boot 를 3.x openjdk 를 17 버전으로 업그레이드 했습니다.

#### es bucket-path
- search api 를 이용하면 bucket 들의 통계가 key 를 포함하여 받을 수 있으나 kt-search 에서는 bucketStats 에 key 없이 min, max, sum 을 받기에 데이터를 받아서 코드로서 최좃값을 도출하였습니다.

### Issue
- 이후에 하면 좋겠다고 생각한것은 [Issue](https://github.com/noggong/kotlin-es-kt-search/issues) 등록 되어있습니다.

> Kotlin 과 es 의 배움이 부족하여, 제가 올바른 선택을 했는지는 아직 확신이 없으나, 정해진 시간내에서 찾아 보았습니다.
