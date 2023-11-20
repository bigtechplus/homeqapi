
BigTechPlus API Service Guide
===========


## 부동산 매물


### T010000. 네이버 매물순위 조회

네이버 단지번호와 매물번호로 단지내 매물조회 순위를 확인

> **POST** https://api.homeq.kr/rest/v1/real-estate/sale/t010000

</br>

* #### HTTP Request Body

| 이름         | 타입      | 필수   | 설명                                       |
| :--------- | :------ | :--- | :--------------------------------------- |
| complex_no | Text    | O    | 네이버 단지번호                                 |
| article_no | Text    | O    | 네이버 매물번호                                 |
| rank_max   | Numeric | X    | - Default: 100<br />- 최소: 20<br />- 최대: 100 |



* #### HTTP Response Body Data

| 이름           | 타입      | 필수   | 설명                    |
| ------------ | ------- | ---- | :-------------------- |
| rank_no      | Numeric | O    | 100위내 확인 안됨<br />: -1 |
| article_list | Array   | O    | 상위 단지 목록              |



* #### Http Response Body Data.article_list 상위단지정보

| 이름                  | 타입      | 필수   | 설명                                       |
| ------------------- | ------- | ---- | :--------------------------------------- |
| article_order       | Numeric | 0    | 매물순위                                     |
| article_no          | Text    | O    | 매물번호                                     |
| pc_article_url      | Text    | X    | 매물링크                                     |
| article_name        | Text    | O    | 매물이름                                     |
| trade_type_name     | Text    | O    | 거래방식<br />- 매매<br />- 전세<br />- 월세<br />- 단기임대<br />- 분양 |
| deal_or_warrant_prc | Text    | O    | 가격                                       |
| rent_prc            | Text    | O    | 월세                                       |
| floor_info          | Text    | X    | 층정보                                      |
| area_name           | Text    | X    | 평형                                       |
| area1               | Text    | X    | 공급면적(단위: m²)                             |
| area2               | Text    | X    | 전용면적(단위: m²)                             |
| direction           | Text    | X    | 방향                                       |
| realtor_name        | Text    | X    | 중개사                                      |
| cp_name             | Text    | X    | 플랫폼                                      |
| cp_pc_article_url   | Text    | X    | 플랫폼매물링크                                  |
| article_confirm_ymd | Text    | X    | 확인일자                                     |



* _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: 1689232893787" \
  --header "x-btp-access-key: eaea5e41-8d5e-4bbc-9746-332e6cac9352" \
  --header "x-btp-secret-key: 9993d7ce494ebd05d696cd1d6ed32d20c975798adbd9c02d5a72827a9c4401c7" \
  --data '{"complex_no":"1","article_no":"1"}'
  'https://api.homeq.kr/rest/v1/real-estate/sale/t010000'

```

* _응답예제 (JSON)_


```json
{
  "trace_id": "c6ef37a5-2b71-421f-95e2-b5d690aa216b",
  "request_time": 1689235431594,
  "response_time": 1689235431595,
  "elapsed_time": 1,
  "error_code": "S000",
  "error_message": "Success",
  "data": {
    "rank_no": 8,
    "article_list": [
      {
        "article_order": 1,
        "article_no": "2329713779",
        "pc_article_url": "https://new.land.naver.com/complexes/236?articleNo=2329713779",
        "article_name": "은마",
        "trade_type_name": "전세",
        "deal_or_warrant_prc": "6억 5,000",
        "rent_prc": "",
        "floor_info": "13/14",
        "area_name": "115",
        "area1": "115",
        "area2": "84",
        "direction": "남향",
        "realtor_name": "주식회사우리동네오케이부동산중개법인",
        "cp_name": "매경부동산",
        "cp_pc_article_url": "http://land.mk.co.kr/rd/rd.php?UID=2329713779",
        "article_confirm_ymd": "20230712",
      },
      {
        "article_order": 2,
        "article_no": "2329989408",
        "pc_article_url": "https://new.land.naver.com/complexes/22853?articleNo=2329989408",
        "article_name": "반포자이",
        "trade_type_name": "월세",
        "deal_or_warrant_prc": "20억",
        "rent_prc": "180",
        "floor_info": "14/29",
        "area_name": "200A",
        "area1": "200",
        "area2": "165",
        "direction": "남동향",
        "realtor_name": "반포자이공인중개사사무소",
        "cp_name": "매경부동산",
        "cp_pc_article_url": "http://land.mk.co.kr/rd/rd.php?UID=2329989408",
        "article_confirm_ymd": "20230713",
      },
      ...
    ]
  }
}
```



