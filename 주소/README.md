
BigTechPlus API Service Guide
===========


## 주소


### API 목록

* ##### [주소 조회](#T010000-주소-검색-목록)

  ​


---

<br/>

### T010000. 주소 검색 [[목록]](#API-목록)

주소, 대장번호, 등기번호로 해당 주소의 상세정보를 확인

> **POST** https://api.homeq.kr/rest/v1/real-estate/price/t010000

</br>

* #### HTTP Request Body
| 이름   | 타입   | 필수   | 설명   | 비고                             |
| :--- | :--- | :--- | :--- | ------------------------------ |
| addr        | Text | 32   | △    | 주소   | search_type = 1 일 경우 필수<br />예)  경기도 과천시 별양로 164, 706동 403호 |
| ledg_prp_no | Text | 33   | △    | 대장번호 | search_type = 2 일 경우 필수<br />예)  41290100195629 |
| reg_prp_no  | Text | 14   | △    | 등기번호 | search_type = 3 일 경우 필수<br />예)  13412021011745 |



* #### HTTP Response Body Data

| 이름                        | 타입      | 설명            | 비고                                       |
| ------------------------- | ------- | :------------ | ---------------------------------------- |
| item_size                 | Numeric | 결과 수          | 예)                                       |
| article_list              | Array   | 주소조회결과 목록     | [ ]                                      |
| article_list.pnu_cd       | Text    | PNU코드         | 4129011000-1-00960000                    |
| article_list.bldg_typ     | Text    | 건물구분          | 0: 일반건물(비공동주택) <br/>1: 집합건물(공동주택)        |
| article_list.ledg_prp_no  | Text    | 대장 관리번호       | 41290-100195115                          |
| article_list.ledg_tit_no  | Text    | 표제부 대장 관리번호   | 41290-100195104                          |
| article_list.ledg_mst_no  | Text    | 총괄표제부 대장 관리번호 | 41290-100194754                          |
| article_list.reg_prp_no   | Text    | 부동산 고유번호      | 1341-2021-012018                         |
| article_list.lgldiv_cd    | Text    | 법정동코드         | 4129011000                               |
| article_list.addr_l       | Text    | 지번 주소         | 경기도 과천시 부림동 96 과천센트럴파크푸르지오써밋             |
| article_list.addr_r       | Text    | 도로명 주소        | 경기도 과천시 별양로 164 (부림동, 과천센트럴파크푸르지오써밋)     |
| article_list.sgg_nm       | Text    | 시군구명          | 과천시                                      |
| article_list.sido_nm      | Text    | 시도명           | 경기도                                      |
| article_list.umd_nm       | Text    | 읍면동명          | 부림동                                      |
| article_list.ri_nm        | Text    | 리명            |                                          |
| article_list.san_cls      | Text    | 산구분           | 0: 일반 <br/>1: 산                          |
| article_list.jibun_bon    | Text    | 지번 본번         | 96                                       |
| article_list.jibun_bu     | Text    | 지번 부번         | 0                                        |
| article_list.zip_num      | Text    | 우편번호          | 13832                                    |
| article_list.rdnm         | Text    | 도로명           | 별양로                                      |
| article_list.rdnm_bon     | Text    | 건물 본번         | 164                                      |
| article_list.rdnm_bu      | Text    | 건물 부번         | 0                                        |
| article_list.bldg_nm      | Text    | 건물 명          | 과천 센트럴파크 푸르지오 써밋                         |
| article_list.dong_nm      | Text    | 동 명           | 706동                                     |
| article_list.floor_nm     | Text    | 층 명           | 4                                        |
| article_list.ho_nm        | Text    | 호 명           | 403호                                     |
| article_list.layer_cls    | Text    | 층 구분          | 0: 지상 1: 지하                              |
| article_list.is_house     | Text    | 주거 여부         | 0: 주거용아님 <br/>1: 주거용 <br/>2: 오피스텔, 주상복합, 업무시설 <br/>3. 단독주택 |
| article_list.house_typ    | Text    | 주택유형          | 아파트, 오피스텔, 연립/다세대, 단독/다가구, 상업용           |
| article_list.is_kb_price  | Text    | KB시세 제공여부     | 1: KB시세 제공 <br/>0: 제공안함                  |
| article_list.kb_complx_no | Numeric | KB단지일련번호      | 40395                                    |

* _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{"search_type":"0",addr":"서울특별시 관악구 봉천로 545"}'
  'https://api.homeq.kr/rest/v1/real-estate/price/t010000'

```

* _응답예제 (JSON)_


```json
{
    "trace_id": "1697181557843uhtNVpg",
    "request_time": 1697181557843,
    "response_time": 1697181558584,
    "elapsed_time": 741,
    "error_code": "S000",
    "error_message": "성공",
    "data": {
        "item_size": 19290841,
        "item_list": [
            {
                "pnu_cd": "4129011000-1-00960000",
                "bldg_typ": "1",
                "ledg_prp_no": "41290-100195115",
                "ledg_tit_no": "41290-100195104",
                "ledg_mst_no": "41290-100194754",
                "reg_prp_no": "1341-2021-012018",
                "lgldiv_cd": "4129011000",
                "addr_l": "경기도 과천시 부림동 96 과천센트럴파크푸르지오써밋",
                "addr_r": "경기도 과천시 별양로 164 (부림동, 과천센트럴파크푸르지오써밋)",
                "sgg_nm": "과천시",
                "sido_nm": "경기도",
                "umd_nm": "부림동",
                "ri_nm": "",
                "san_cls": "0",
                "jibun_bon": "96",
                "jibun_bu": "0",
                "zip_num": "13832",
                "rdnm": "별양로",
                "rdnm_bon": "164",
                "rdnm_bu": "0",
                "bldg_nm": "과천 센트럴파크 푸르지오 써밋",
                "dong_nm": "706동",
                "floor_nm": "4",
                "ho_nm": "403호",
                "layer_cls": "0",
                "is_house": "1",
                "house_typ": "아파트",
                "is_kb_price": "1",
                "kb_complx_no": 40395
            }, ...
        ]
    }
}
```

</br>
