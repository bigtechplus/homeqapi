
BigTechPlus API Service Guide
===========


## KB 시세


### API 목록

* ##### [주소 조회](#T010000-주소-검색-목록)
* ##### [단지 검색](#T010001-단지-검색-목록)
* ##### [단지 상세조회](#T010002-단지-상세조회-목록)
* ##### [단지내 동조회](#T010003-단지내-동조회-목록)
* ##### [단지내 호조회](#T010004-단지내-호조회-목록)
* ##### [시세 조회](#T010005-시세-조회-목록)
* ##### [시세 이력 조회](#T010006-시세-이력-조회-목록)
* ##### [시세 통계 조회](#T010007-시세-통계-조회-목록)
* ##### [KB단지 대장 매핑 조회](#T010008-KB단지-대장-매핑-조회-목록)


---

<br/>

### T010000. 주소 검색 [[목록]](#API-목록)

주소로 해당 주소의 상세정보를 확인

> **POST** https://api.homeq.kr/rest/v1/real-estate/price/t010000

</br>

* #### HTTP Request Body
| 이름   | 타입   | 필수   | 설명   | 비고                             |
| :--- | :--- | :--- | :--- | ------------------------------ |
| addr | Text | O    | 주소   | 예)  경기도 과천시 별양로 164, 706동 403호 |



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
  --data '{"addr":"서울특별시 관악구 봉천로 545"}'
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

### T010001. 단지 검색 [[목록]](#API-목록)

주소와 법정동코드로 해당 법정동의 단지 기본정보를 확인

> **POST** https://api.homeq.kr/rest/v1/real-estate/price/t010001

<br/>

- #### HTTP Request Body

| 이름          | 타입   | 필수   | 설명    | 비고                             |
| :---------- | :--- | :--- | :---- | ------------------------------ |
| addr        | Text | O    | 주소    | 예)  경기도 과천시 별양로 164, 706동 403호 |
| lcdong_code | Text | O    | 법정동코드 | 예)   4129011000                |



- #### HTTP Response Body Data

| 이름                                  | 타입      | 설명        | 비고                        |
| ----------------------------------- | ------- | :-------- | ------------------------- |
| item_size                           | Numeric | 결과 수      | 예)                        |
| item_list                           | Array   | 단지조회결과 목록 | [ ]                       |
| item_list.kb_complx_no              | Numeric | KB단지일련번호  | 11432                     |
| item_list.kb_object_identifier      | Text    | KB물건식별자   | KBA000002                 |
| item_list.sido_name                 | Text    | 시도명       | 충청남도                      |
| item_list.sgg_name                  | Text    | 시군구명      | 천안시                       |
| item_list.gu_name                   | Text    | 구명        | 서북구                       |
| item_list.umd_name                  | Text    | 읍면동명      | 신당동                       |
| item_list.ri_name                   | Text    | 리명        |                           |
| item_list.bunji                     | Text    | 번지        | 377-10 외 3필지              |
| item_list.etc_bunji                 | Text    | 기타번지      | 377-2,377-3,377-6         |
| item_list.complex_name              | Text    | 단지명       | 조승                        |
| item_list.total_household_count     | Numeric | 총세대수      | 164                       |
| item_list.total_dong_count          | Numeric | 총동수       | 1                         |
| item_list.completion_year_month     | Text    | 준공년월      | 199403                    |
| item_list.construction_company_name | Text    | 건설업체명     | 동남레져건설㈜                   |
| item_list.road_address              | Text    | 도로기본주소    | 충청남도 천안시 서북구 신당새터2길 29-12 |
| item_list.road_address_detail       | Text    | 도로상세주소    | (신당동, 조승아파트)              |



- _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{"addr":"경기도 과천시 별양로 164, 706동 403호","lcdong_cd":"4129011000"}'
  'https://api.homeq.kr/rest/v1/real-estate/price/t010002'

```



- 응답예제 (JSON)_

```json
{
    "trace_id": "1697185157909adeXEN1",
    "request_time": 1697185157909,
    "response_time": 1697185157983,
    "elapsed_time": 74,
    "error_code": "S000",
    "error_message": "성공",
    "data": {
        "item_size": 291012,
        "item_list": [
            {
                "kb_complx_no": 40395,
                "kb_object_identifier": "KBA031841",
                "sido_name": "경기도",
                "sgg_name": "과천시",
                "gu_name": "",
                "umd_name": "부림동",
                "ri_name": "",
                "bunji": "96",
                "etc_bunji": "",
                "complex_name": "과천센트럴파크푸르지오써밋",
                "total_household_count": 1317,
                "total_dong_count": 15,
                "completion_year_month": "202012",
                "construction_company_name": "(주)대우건설",
                "road_address": "경기도 과천시 별양로 164",
                "road_address_detail": "(부림동, 과천센트럴파크푸르지오써밋)"
            },
     		...
        ]
    }
}
```

> 입력한 내용과 결과가 얼마나 정확하게 일치하냐에 따라 결과가 출력됩니다.
>
> 검색결과는 기본적으로 10개 출력됩니다.



</br>

### T010002. 단지 상세조회 [[목록]](#API-목록)

KB단지일련번호 또는 KB물건식별자로 해당 법정동의 단지 상세정보를 확인

> **POST** https://api.homeq.kr/rest/v1/real-estate/price/t010002

</br>

- #### HTTP Request Body

| 이름                   | 타입      | 필수   | 설명       | 비고                              |
| :------------------- | :------ | :--- | :------- | ------------------------------- |
| kb_complx_no         | Numeric | △    | KB단지일련번호 | 둘중 한 개는 필수입력<br/>예) 40395       |
| kb_object_identifier | Text    | △    | KB물건식별자  | 둘중 한 개는 필수입력<br/>예)   KBA031841 |

- #### HTTP Response Body Data

| 이름                                  | 타입      | 설명                         | 비고                                       |
| ----------------------------------- | ------- | :------------------------- | ---------------------------------------- |
| item_size                           | Numeric | 결과 수                       | 예)                                       |
| item_list                           | Array   | 단지조회결과 목록                  | [ ]                                      |
| item_list.kb_complx_no              | Numeric | KB단지일련번호                   | 11432                                    |
| item_list.kb_object_identifier      | Text    | KB물건식별자                    | KBA000002                                |
| item_list.sido_name                 | Text    | 시도명                        | 충청남도                                     |
| item_list.sgg_name                  | Text    | 시군구명                       | 천안시                                      |
| item_list.gu_name                   | Text    | 구명                         | 서북구                                      |
| item_list.umd_name                  | Text    | 읍면동명                       | 신당동                                      |
| item_list.ri_name                   | Text    | 리명                         |                                          |
| item_list.bunji                     | Text    | 번지                         | 377-10 외 3필지                             |
| item_list.etc_bunji                 | Text    | 기타번지                       | 377-2,377-3,377-6                        |
| item_list.complex_name              | Text    | 단지명                        | 조승                                       |
| item_list.total_household_count     | Text    | 총세대수                       | 164                                      |
| item_list.total_dong_count          | Text    | 총동수                        | 1                                        |
| item_list.high_floor                | Text    | 최고층수                       | 15                                       |
| item_list.low_floor                 | Text    | 최저층수                       | 13                                       |
| item_list.completion_year_month     | Text    | 준공년월                       | 199403                                   |
| item_list.construction_company_name | Text    | 건설업체명                      | 동남레져건설㈜                                  |
| item_list.heat_method_type          | Text    | 난방방식구분명                    | 개별                                       |
| item_list.heat_fuel_type            | Text    | 난방연료구분명                    | 도시가스                                     |
| item_list.parking_count             | Text    | 주차대수                       | 80                                       |
| item_list.office_phone_no           | Text    | 관리사무소전화번호                  | 041-567-9874                             |
| item_list.is_sale_ticket            | Text    | 분양권여부                      | F                                        |
| item_list.occupancy_year_month      | Text    | 입주년월                       | 199403                                   |
| item_list.complx_feature            | Text    | 단지특징                       | 부평초 .부평여중. 안남중. 계산고. 계산여고.인천교대.경인여전홈플러스. 월마트.그랜드마트 까르푸 인천지하철 3분내 |
| item_list.zip_code                  | Text    | 우편번호                       | 31083                                    |
| item_list.is_commercial_complex     | Text    | 주상복합여부                     | F                                        |
| item_list.is_villa                  | Text    | 빌라연립여부                     | F                                        |
| item_list.cortar_no                 | Text    | 법정동코드                      | 4413311000                               |
| item_list.is_remodeling             | Text    | 리모델링여부                     | F                                        |
| item_list.road_zip_code             | Text    | 도로명우편번호                    | 441334550430                             |
| item_list.road_zip_um_no            | Text    | 도로명우편읍면번호                  | 01                                       |
| item_list.road_zip_sub_no           | Text    | 도로명우편보조번호                  | 00001                                    |
| item_list.road_zone_no              | Text    | 도로명구역번호                    |                                          |
| item_list.underground_type          | Text    | 지하구분                       | 0                                        |
| item_list.road_bon_no               | Text    | 도로명건물본번                    | 29                                       |
| item_list.road_sub_no               | Text    | 도로명건물부번                    | 12                                       |
| item_list.road_building_no          | Text    | 도로명건물관리번호                  | 4413311000103770002041298                |
| item_list.road_address              | Text    | 도로기본주소                     | 충청남도 천안시 서북구 신당새터2길 29-12                |
| item_list.road_address_detail       | Text    | 도로상세주소                     | (신당동, 조승아파트)                             |
| item_list.longitude                 | Text    | wgs84경도                    | 127.1579                                 |
| item_list.latitude                  | Text    | wgs84위도                    | 36.85267                                 |
| item_list.is_price_unexposed        | Text    | 시세미노출여부                    | 0                                        |
| item_list.reg_date_time             | Text    | 등록일시 (YYYY-MM-DD hh:mm:ss) | 2023-08-18 09:03:36                      |
| item_list.pnu_code                  | Text    | PNU코드                      | 1168011500107460000                      |




- _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{"kb_complx_no":40395,"kb_object_identifier":"KBA031841"}'
  'https://api.homeq.kr/rest/v1/real-estate/price/t010002'

```

- _응답예제 (JSON)_

```json
{
    "trace_id": "1697189753002bjTb0Ku",
    "request_time": 1697189753004,
    "response_time": 1697189753336,
    "elapsed_time": 332,
    "error_code": "S000",
    "error_message": "성공",
    "data": {
        "item_size": 2,
        "item_list": [
            {
                "kb_complx_no": 40395,
                "kb_object_identifier": "KBA031841",
                "sido_name": "경기도",
                "sgg_name": "과천시",
                "gu_name": "",
                "umd_name": "부림동",
                "ri_name": "",
                "bunji": "96",
                "etc_bunji": "",
                "complex_name": "과천센트럴파크푸르지오써밋",
                "total_household_count": 1317,
                "total_dong_count": 15,
                "high_floor": 32,
                "low_floor": 15,
                "completion_year_month": "202012",
                "construction_company_name": "(주)대우건설",
                "heat_method_type": "지역",
                "heat_fuel_type": "열병합",
                "parking_count": "0",
                "office_phone_no": "",
                "is_sale_ticket": "F",
                "occupancy_year_month": "202012",
                "complx_feature": " 과천주공7-1단지 재건축 ,일반분양 575세대, 조합원분양 742세대",
                "zip_code": "13832",
                "is_commercial_complex": "F",
                "is_villa": "F",
                "cortar_no": "4129011000",
                "is_remodeling": "F",
                "road_zip_code": "412903195020",
                "road_zip_um_no": "03",
                "road_zip_sub_no": "00001",
                "road_zone_no": "",
                "underground_type": "0",
                "road_bon_no": "164",
                "road_sub_no": "0",
                "road_building_no": "4129011000100490000000668",
                "road_address": "경기도 과천시 별양로 164",
                "road_address_detail": "(부림동, 과천센트럴파크푸르지오써밋)",
                "longitude": "127.0011311",
                "latitude": "37.4316497",
                "is_price_unexposed": "0",
                "reg_date_time": "2023-09-19T15:36:52.067Z",
                "pnu_code": "4129011000-1-00960000"
            },
     		...
        ]
    }
}
```

</br>

### T010003. 단지내 동조회 [[목록]](#API-목록)

KB단지일련번호 또는 KB물건식별자로 해당 단지의 동 정보를 확인

> **POST** https://api.homeq.kr/rest/v1/real-estate/price/t010003

<br/>

- #### HTTP Request Body

| 이름                   | 타입      | 필수   | 설명       | 비고                             |
| :------------------- | :------ | :--- | :------- | ------------------------------ |
| kb_complx_no         | Numeric | △    | KB단지일련번호 | 둘중 한 개는 필수입력<br/>예) 40395      |
| kb_object_identifier | Text    | △    | KB물건식별자  | 둘중 한 개는 필수입력<br/>예)  KBA031841 |



- #### HTTP Response Body Data

| 이름                             | 타입      | 설명        | 비고         |
| ------------------------------ | ------- | :-------- | ---------- |
| item_size                      | Numeric | 결과 수      | 예)         |
| item_list                      | Array   | 단지조회결과 목록 | [ ]        |
| item_list.kb_complx_no         | Numeric | KB단지일련번호  | 40395      |
| item_list.kb_object_identifier | Text    | KB물건식별자   | KBA031841  |
| item_list. complex_name        | Text    | 시도명       | 조승         |
| item_list. cortar_no           | Text    | 시군구명      | 4413311000 |
| item_list. dong_seq            | Numeric | 구명        | 1          |
| item_list. dong_name           | Text    | 읍면동명      | 101동       |



- _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{"kb_complx_no":40395,"kb_object_identifier":"KBA031841"}'
  'https://api.homeq.kr/rest/v1/real-estate/price/t010003'

```

- _응답예제 (JSON)_

```json
{
    "trace_id": "16971903600222TKc5Zq",
    "request_time": 1697190360024,
    "response_time": 1697190360279,
    "elapsed_time": 255,
    "error_code": "S000",
    "error_message": "성공",
    "data": {
        "item_list": [
            {
                "kb_complx_no": 40395,
                "kb_object_identifier": "KBA031841",
                "complex_name": "과천 센트럴파크 푸르지오 써밋",
                "cortar_no": "4129011000",
                "dong_seq": 1,
                "dong_name": "701동"
            },
     		...
        ]
    }
}
```

</br>

### T010004. 단지내 호조회 [[목록]](#API-목록)

KB단지일련번호 또는 KB물건식별자로 해당 단지의 특정동의 호 정보를 확인

> **POST** https://api.homeq.kr/rest/v1/real-estate/price/t010004

<br/>

- #### HTTP Request Body

| 이름                   | 타입      | 필수   | 설명       | 비고                             |
| :------------------- | :------ | :--- | :------- | ------------------------------ |
| kb_complx_no         | Numeric | △    | KB단지일련번호 | 둘중 한 개는 필수입력<br/>예) 40395      |
| kb_object_identifier | Text    | △    | KB물건식별자  | 둘중 한 개는 필수입력<br/>예)  KBA031841 |
| dong_seq             | Numeric | O    | 동일련번호    | 예)  6                          |



- #### HTTP Response Body Data

| 이름                             | 타입      | 설명        | 비고               |
| ------------------------------ | ------- | :-------- | ---------------- |
| item_size                      | Numeric | 결과 수      | 예)               |
| item_list                      | Array   | 단지조회결과 목록 | [ ]              |
| item_list.kb_complx_no         | Numeric | KB단지일련번호  | 40395            |
| item_list.kb_object_identifier | Text    | KB물건식별자   | KBA031841        |
| item_list. complex_name        | Text    | 시도명       | 과천 센트럴파크 푸르지오 써밋 |
| item_list. cortar_no           | Text    | 시군구명      | 4129011000       |
| item_list. dong_seq            | Numeric | 구명        | 6                |
| item_list. dong_name           | Text    | 읍면동명      | 101동             |
| item_list.ho_seq               | Text    | 호일련번호     | 1                |
| item_list.ho_name              | Text    | 호명        | 101호             |
| item_list.flr_name             | Text    | 층명        | 1                |
| item_list.ledg_prp_no          | Text    | 대장번호      | 11680-175421     |
| item_list.reg_prp_no           | Text    | 등기번호      | 11461996067294   |



- _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{"kb_complx_no":40395,"kb_object_identifier":"KBA031841","dong_seq":6}'
  'https://api.homeq.kr/rest/v1/real-estate/price/t010004'

```

- _응답예제 (JSON)_

```json
{
    "trace_id": "1697421218512e0mDaTC",
    "request_time": 1697421218512,
    "response_time": 1697421218673,
    "elapsed_time": 161,
    "error_code": "S000",
    "error_message": "성공",
    "data": {
        "item_list": [
            {
                "kb_complx_no": 40395,
                "kb_object_identifier": "KBA031841",
                "complex_name": "과천 센트럴파크 푸르지오 써밋",
                "cortar_no": "4129011000",
                "dong_seq": "6",
                "dong_name": "706동",
                "ho_seq": "1",
                "ho_name": "201호",
                "flr_name": "2",
                "ledg_prp_no": "41290-100195105",
                "reg_prp_no": "13412021012008"
            },
     		...
        ]
    }
}
```

</br>

### T010005. 시세 조회 [[목록]](#API-목록)

대장관리번호로 시세 조회

> **POST** https://api.homeq.kr/rest/v1/real-estate/price/t010005

</br>

- #### HTTP Request Body

| 이름          | 타입   | 필수   | 설명   | 비고                 |
| :---------- | :--- | :--- | :--- | ------------------ |
| ledg_prp_no | Text | O    | 주소   | 예) 41290-100195115 |

- #### HTTP Response Body Data

| 이름                                       | 타입      | 설명                         | 비고                                       |
| ---------------------------------------- | ------- | :------------------------- | ---------------------------------------- |
| kb_complx_no                             | Numeric | KB단지일련번호                   | 11432                                    |
| kb_object_identifier                     | Text    | KB물건식별자                    | KBA000002                                |
| area_no                                  | Numeric | 면적일련번호                     | 2                                        |
| ho_no                                    | Numeric | 호일련번호                      | 7996076                                  |
| reg_prp_no                               | Text    | 등기번호                       | 11461996067294                           |
| ledg_prp_no                              | Text    | 대장번호                       | 11680-175421                             |
| cortar_no                                | Text    | 법정동코드                      | 1168011500                               |
| complex_name                             | Text    | 단지명                        | 까치마을                                     |
| dong_name                                | Text    | 동                          | 1005                                     |
| flr_name                                 | Text    | 층                          | 6                                        |
| ho_name                                  | Text    | 호                          | 609                                      |
| exclusive_area                           | Text    | 호전용면적                      | 34.4400                                  |
| public_area                              | Text    | 호공용면적                      | 14.5700                                  |
| pnu_cd                                   | Text    | PNU코드                      | 1168011500107460000                      |
| kb_complx_info                           | Object  | 단지정보                       | { }                                      |
| kb_complx_info.kb_object_identifier      | Text    | KB물건식별자                    | KBA000002                                |
| kb_complx_info.sido_name                 | Text    | 시도명                        | 충청남도                                     |
| kb_complx_info.sgg_name                  | Text    | 시군구명                       | 천안시                                      |
| kb_complx_info.gu_name                   | Text    | 구명                         | 서북구                                      |
| kb_complx_info.umd_name                  | Text    | 읍면동명                       | 신당동                                      |
| kb_complx_info.ri_name                   | Text    | 리명                         |                                          |
| kb_complx_info.bunji                     | Text    | 번지                         | 377-10 외 3필지                             |
| kb_complx_info.etc_bunji                 | Text    | 기타번지                       | 377-2,377-3,377-6                        |
| kb_complx_info.complex_name              | Text    | 단지명                        | 조승                                       |
| kb_complx_info.total_household_count     | Numeric | 총세대수                       | 164                                      |
| kb_complx_info.total_dong_count          | Numeric | 총동수                        | 1                                        |
| kb_complx_info.high_floor                | Numeric | 최고층수                       | 15                                       |
| kb_complx_info.low_floor                 | Numeric | 최저층수                       | 13                                       |
| kb_complx_info.completion_year_month     | Text    | 준공년월                       | 199403                                   |
| kb_complx_info.construction_company_name | Text    | 건설업체명                      | 동남레져건설㈜                                  |
| kb_complx_info.heat_method_type          | Text    | 난방방식구분명                    | 개별                                       |
| kb_complx_info.heat_fuel_type            | Text    | 난방연료구분명                    | 도시가스                                     |
| kb_complx_info.parking_count             | Numeric | 주차대수                       | 80                                       |
| kb_complx_info.office_phone_no           | Text    | 관리사무소전화번호                  | 041-567-9874                             |
| kb_complx_info.is_sale_ticket            | Text    | 분양권여부                      | F                                        |
| kb_complx_info.occupancy_year_month      | Text    | 입주년월                       | 199403                                   |
| kb_complx_info.complx_feature            | Text    | 단지특징                       | 부평초 .부평여중. 안남중. 계산고. 계산여고.인천교대.경인여전홈플러스. 월마트.그랜드마트 까르푸 인천지하철 3분내 |
| kb_complx_info.zip_code                  | Text    | 우편번호                       | 31083                                    |
| kb_complx_info.is_commercial_complex     | Text    | 주상복합여부                     | F                                        |
| kb_complx_info.is_villa                  | Text    | 빌라연립여부                     | F                                        |
| kb_complx_info.cortar_no                 | Text    | 법정동코드                      | 4413311000                               |
| kb_complx_info.is_remodeling             | Text    | 리모델링여부                     | F                                        |
| kb_complx_info.road_zip_code             | Text    | 도로명우편번호                    | 441334550430                             |
| kb_complx_info.road_zip_um_no            | Text    | 도로명우편읍면번호                  | 01                                       |
| kb_complx_info.road_zip_sub_no           | Text    | 도로명우편보조번호                  | 00001                                    |
| kb_complx_info.road_zone_no              | Text    | 도로명구역번호                    |                                          |
| kb_complx_info.underground_type          | Text    | 지하구분                       | 0                                        |
| kb_complx_info.road_bon_no               | Text    | 도로명건물본번                    | 29                                       |
| kb_complx_info.road_sub_no               | Text    | 도로명건물부번                    | 12                                       |
| kb_complx_info.road_building_no          | Text    | 도로명건물관리번호                  | 4413311000103770002041298                |
| kb_complx_info.road_address              | Text    | 도로기본주소                     | 충청남도 천안시 서북구 신당새터2길 29-12                |
| kb_complx_info.road_address_detail       | Text    | 도로상세주소                     | (신당동, 조승아파트)                             |
| kb_complx_info.longitude                 | Text    | wgs84경도                    | 127.1579                                 |
| kb_complx_info.latitude                  | Text    | wgs84위도                    | 36.85267                                 |
| kb_complx_info.is_price_unexposed        | Text    | 시세미노출여부                    | 0                                        |
| kb_complx_info.reg_date_time             | Text    | 등록일시 (YYYY-MM-DD hh:mm:ss) | 2023-08-18 09:03:36                      |
| kb_complx_info.pnu_code                  | Text    | PNU코드                      | 1168011500107460000                      |
| kb_complex_type_info                     | Array   | 단지평형정보                     | [ ]                                      |
| kb_complex_type_info.kb_object_identifier | Text    | KB물건식별자                    | KBA000002                                |
| kb_complex_type_info.area_no             | Numeric | 면적일련번호                     | 2                                        |
| kb_complex_type_info.supply_area         | Text    | 공급면적                       | 102.05                                   |
| kb_complex_type_info.area_name           | Text    | 평형구분                       | A                                        |
| kb_complex_type_info.exclusive_area      | Text    | 전용면적                       | 84.99                                    |
| kb_complex_type_info.households          | Numeric | 세대수                        | 30                                       |
| kb_complex_type_info.room_count          | Numeric | 방수                         | 3                                        |
| kb_complex_type_info.bathroom_count      | Numeric | 욕실수                        | 2                                        |
| kb_complex_type_info.sale_amt            | Numeric | 분양가 (단위:만원)                | 13900                                    |
| kb_complex_type_info.direction           | Text    | 방향                         | 남동                                       |
| kb_complex_type_info.entrance_type       | Text    | 현관구조                       | 계단식                                      |
| kb_complex_type_info.etc_exclusive_area  | Text    | 기타전용면적                     | 53.97,54.02                              |
| kb_complex_type_info.area_number         | Numeric | 평형                         | 34                                       |
| kb_complex_type_info.house_type          | Text    | 주택형                        | 102.47                                   |
| kb_complex_type_info.is_price_unexposed  | Text    | 시세미노출여부                    | 0                                        |
| kb_complex_type_info.reg_date_time       | Text    | 등록일시 (YYYY-MM-DD hh:mm:ss) | 2023-08-18 09:03:36                      |
| kb_price_info                            | Object  | KB 시세 정보                   | { }                                      |
| kb_price_info.base_ymd                   | Text    | 시세기준년월일                    | 20230814                                 |
| kb_price_info.kb_object_identifier       | Text    | KB물건식별자                    | KBA000002                                |
| kb_price_info.area_no                    | Numeric | 면적일련번호                     | 2                                        |
| kb_price_info.sido_name                  | Text    | 시도명                        | 충청남도                                     |
| kb_price_info.sgg_name                   | Text    | 시군구명                       | 천안시                                      |
| kb_price_info.gu_name                    | Text    | 구명                         | 서북구                                      |
| kb_price_info.umd_name                   | Text    | 읍면동명                       | 신당동                                      |
| kb_price_info.ri_name                    | Text    | 리명                         |                                          |
| kb_price_info.bunji                      | Text    | 번지                         | 377-10 외 3필지                             |
| kb_price_info.etc_bunji                  | Text    | 기타번지                       | 377-2,377-3,377-6                        |
| kb_price_info.complex_name               | Text    | 단지명                        | 조승                                       |
| kb_price_info.deal_price_lower_limit     | Numeric | 매매하한가                      | 9500                                     |
| kb_price_info.general_deal_price         | Numeric | 매매일반거래가                    | 10000                                    |
| kb_price_info.deal_price_upper_limit     | Numeric | 매매상한가                      | 10500                                    |
| kb_price_info.warranty_price_lower_limit | Numeric | 전세하한가                      | 8000                                     |
| kb_price_info.general_warranty_price     | Numeric | 전세일반거래가                    | 8500                                     |
| kb_price_info.warranty_price_upper_limit | Numeric | 전세상한가                      | 9000                                     |
| kb_price_info.rent_guarantee_price       | Numeric | 월세보증금액                     | 1000                                     |
| kb_price_info.max_rent_price             | Numeric | 월임대최고금액                    | 50                                       |
| kb_price_info.min_rent_price             | Numeric | 월임대최저금액                    | 55                                       |
| kb_price_info.cortar_no                  | Text    | 법정동코드                      | 4413311000                               |
| kb_price_info.agent_name1                | Text    | 중개업소명1                     | 직산대박공인중개사사무소                             |
| kb_price_info.agent_tel_no1              | Text    | 중개업소전화번호1                  | 041-583-2900                             |
| kb_price_info.agent_name2                | Text    | 중개업소명2                     |                                          |
| kb_price_info.agent_tel_no2              | Text    | 중개업소전화번호2                  |                                          |
| kb_price_info.price_valid_ymd            | Text    | 시세마감년월일                    | 20230818                                 |
| kb_price_info.is_price_unexposed         | Text    | 시세미노출여부                    | 0                                        |
| kb_price_info.reg_date_time              | Text    | 등록일시 (YYYY-MM-DD hh:mm:ss) | 2023-08-18 09:03:36                      |


- _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{"ledg_prp_no":"41290-100195115"}'
  'https://api.homeq.kr/rest/v1/real-estate/price/t010005'

```

- _응답예제 (JSON)_

```json
{
    "trace_id": "1697181557843uhtNVpg",
    "request_time": 1697181557843,
    "response_time": 1697181558584,
    "elapsed_time": 741,
    "error_code": "S000",
    "error_message": "성공",
    "data": {
        "kb_complx_no": 40395,
        "kb_object_identifier": "KBA031841",
        "area_no": 14,
        "ho_no": 11,
        "reg_prp_no": "13412021012018",
        "ledg_prp_no": "41290-100195115",
        "cortar_no": "4129011000",
        "complex_name": "과천 센트럴파크 푸르지오 써밋",
        "dong_name": "706동",
        "flr_name": "4",
        "ho_name": "403호",
        "exclusive_area": "84.99",
        "public_area": "29.41",
        "pnu_cd": "4129011000100960000",
        "kb_complx_info": {
            "kb_object_identifier": "KBA031841",
            "sido_name": "경기도",
            "sgg_name": "과천시",
            "gu_name": "",
            "umd_name": "부림동",
            "ri_name": "",
            "bunji": "96",
            "etc_bunji": "",
            "complex_name": "과천 센트럴파크 푸르지오 써밋",
            "total_household_count": 1317,
            "total_dong_count": 15,
            "high_floor": 32,
            "low_floor": 15,
            "completion_year_month": "202012",
            "construction_company_name": "(주)대우건설",
            "heat_method_type": "지역",
            "heat_fuel_type": "열병합",
            "parking_count": 0,
            "office_phone_no": "",
            "is_sale_ticket": "F",
            "occupancy_year_month": "202012",
            "complx_feature": " 과천주공7-1단지 재건축 ,일반분양 575세대, 조합원분양 742세대",
            "zip_code": "13832",
            "is_commercial_complex": "F",
            "is_villa": "F",
            "cortar_no": "4129011000",
            "is_remodeling": "F",
            "road_zip_code": "412903195020",
            "road_zip_um_no": "03",
            "road_zip_sub_no": "00001",
            "road_zone_no": "",
            "underground_type": "0",
            "road_bon_no": "164",
            "road_sub_no": "0",
            "road_building_no": "4129011000100490000000668",
            "road_address": "경기도 과천시 별양로 164",
            "road_address_detail": "(부림동, 과천센트럴파크푸르지오써밋)",
            "longitude": "127.0011311",
            "latitude": "37.4316497",
            "is_price_unexposed": "0",
            "reg_date_time": "2023-10-13 10:25:20",
            "pnu_code": "4129011000100960000"
        },
        "kb_complex_type_info": {
            "kb_object_identifier": "KBA031841",
            "area_no": 14,
            "supply_area": "114.40",
            "area_name": "",
            "exclusive_area": "84.99",
            "households": 752,
            "room_count": 3,
            "bathroom_count": 2,
            "sale_amt": 0,
            "direction": " ",
            "entrance_type": "계단식",
            "etc_exclusive_area": "84.98, 84.94",
            "area_number": 34,
            "house_type": "114.40",
            "is_price_unexposed": "0",
            "reg_date_time": "2023-10-13 10:25:20"
        },
        "kb_price_info": {
            "base_ymd": "20231009",
            "kb_object_identifier": "KBA031841",
            "area_no": "14",
            "sido_name": "경기도",
            "sgg_name": "과천시",
            "gu_name": "",
            "umd_name": "부림동",
            "ri_name": "",
            "bunji": "96",
            "etc_bunji": "",
            "complex_name": "과천 센트럴파크 푸르지오 써밋",
            "deal_price_lower_limit": 161000,
            "general_deal_price": 165000,
            "deal_price_upper_limit": 174000,
            "warranty_price_lower_limit": 85000,
            "general_warranty_price": 88000,
            "warranty_price_upper_limit": 90000,
            "rent_guarantee_price": 0,
            "max_rent_price": 0,
            "min_rent_price": 0,
            "cortar_no": "4129011000",
            "agent_name1": "더시티 공인중개사사무소",
            "agent_tel_no1": "02-503-9111",
            "agent_name2": "다나와부동산 공인중개사사무소",
            "agent_tel_no2": "02-503-1119",
            "price_valid_ymd": "20231013",
            "is_price_unexposed": "0",
            "reg_date_time": "2023-10-13 10:25:20"
        }
    }
}
```

</br>

### T010006. 시세 이력 조회 [[목록]](#API-목록)

대장관리번호로 시세이력 조회

> **POST** https://api.homeq.kr/rest/v1/real-estate/price/t010006

</br>

- #### HTTP Request Body

| 이름          | 타입   | 필수   | 설명   | 비고                 |
| :---------- | :--- | :--- | :--- | ------------------ |
| ledg_prp_no | Text | O    | 주소   | 예) 41290-100195115 |

#### 

- #### HTTP Response Body Data

| 이름                                       | 타입      | 설명                         | 비고                  |
| ---------------------------------------- | ------- | :------------------------- | ------------------- |
| kb_price_list                            | Array   | KB부동산 시세 정보                | [ ]                 |
| kb_price_list.base_ymd                   | Text    | 시세기준년월일                    | 20230814            |
| kb_price_list.kb_object_identifier       | Text    | KB물건식별자                    | KBA000002           |
| kb_price_list.area_no                    | Text    | 면적일련번호                     | 2                   |
| kb_price_list.complex_name               | Text    | 단지명                        | 조승                  |
| kb_price_list.deal_price_lower_limit     | Numeric | 매매하한가                      | 9500                |
| kb_price_list.general_deal_price         | Numeric | 매매일반거래가                    | 10000               |
| kb_price_list.deal_price_upper_limit     | Numeric | 매매상한가                      | 10500               |
| kb_price_list.warranty_price_lower_limit | Numeric | 전세하한가                      | 8000                |
| kb_price_list.general_warranty_price     | Numeric | 전세일반거래가                    | 8500                |
| kb_price_list.warranty_price_upper_limit | Numeric | 전세상한가                      | 9000                |
| kb_price_list.rent_guarantee_price       | Numeric | 월세보증금액                     | 1000                |
| kb_price_list.max_rent_price             | Numeric | 월임대최고금액                    | 50                  |
| kb_price_list.min_rent_price             | Numeric | 월임대최저금액                    | 55                  |
| kb_price_list.cortar_no                  | Text    | 법정동코드                      | 4413311000          |
| kb_price_list.agent_name1                | Text    | 중개업소명1                     | 직산대박공인중개사사무소        |
| kb_price_list.agent_tel_no1              | Text    | 중개업소전화번호1                  | 041-583-2900        |
| kb_price_list.agent_name2                | Text    | 중개업소명2                     |                     |
| kb_price_list.agent_tel_no2              | Text    | 중개업소전화번호2                  |                     |
| kb_price_list.price_valid_ymd            | Text    | 시세마감년월일                    | 20230818            |
| kb_price_list.is_price_unexposed         | Text    | 시세미노출여부                    | 0                   |
| kb_price_list.reg_date_time              | Text    | 등록일시 (YYYY-MM-DD hh:mm:ss) | 2023-08-18 09:03:36 |


- _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{"ledg_prp_no":"41290-100195115"}'
  'https://api.homeq.kr/rest/v1/real-estate/price/t010006'

```



- 응답예제 (JSON)_

```json
{
    "trace_id": "1697433836226KAeu8W5",
    "request_time": 1697433836226,
    "response_time": 1697433861137,
    "elapsed_time": 24911,
    "error_code": "S000",
    "error_message": "성공",
    "data": {
        "kb_price_list": [
            {
                "base_ymd": "20231009",
                "kb_object_identifier": "KBA031841",
                "area_no": 14,
                "complex_name": "과천센트럴파크푸르지오써밋",
                "deal_price_lower_limit": 161000,
                "general_deal_price": 165000,
                "deal_price_upper_limit": 174000,
                "warranty_price_lower_limit": 85000,
                "general_warranty_price": 88000,
                "warranty_price_upper_limit": 90000,
                "rent_guarantee_price": 0,
                "max_rent_price": 0,
                "min_rent_price": 0,
                "cortar_no": "4129011000",
                "agent_name1": "더시티 공인중개사사무소",
                "agent_tel_no1": "02-503-9111",
                "agent_name2": "다나와부동산 공인중개사사무소",
                "agent_tel_no2": "02-503-1119",
                "price_valid_ymd": "20231013",
                "is_price_unexposed": "0",
                "reg_date_time": "2023-10-13 10:25:20"
            },
     		...
        ]
    }
}
```

</br>

### T010007. 시세 통계 조회 [[목록]](#API-목록)

대장관리번호로 시세이력 조회

> **POST** https://api.homeq.kr/rest/v1/real-estate/price/t010006

</br>

- #### HTTP Request Body

| 이름          | 타입   | 필수   | 설명   | 비고                 |
| :---------- | :--- | :--- | :--- | ------------------ |
| ledg_prp_no | Text | O    | 주소   | 예) 41290-100195115 |



- #### HTTP Response Body Data

| 이름                                       | 타입      | 설명                         | 비고                  |
| ---------------------------------------- | ------- | :------------------------- | ------------------- |
| price_stats_info                         | Array   | 시세 통계 정보                   | [ ]                 |
| price_stats_info.base_ymd                | Text    | 시세기준년월일                    | 20230814            |
| price_stats_info.kb_object_identifier    | Text    | KB물건식별자                    | KBA000002           |
| price_stats_info.area_no                 | Numeric | 면적일련번호                     | 2                   |
| price_stats_info.area_number             | Numeric | 평형                         | 34                  |
| price_stats_info.complex_name            | Text    | 단지명                        | 조승                  |
| price_stats_info.deal_price              | Numeric | 매매시세                       | 9500                |
| price_stats_info.last_week_deal_price    | Numeric | 지난주 매매시세                   | 10000               |
| price_stats_info.deal_price_gap          | Numeric | 매매시세 증감액                   | 500                 |
| price_stats_info.wrant_price             | Numeric | 전세시세                       | 10500               |
| price_stats_info.last_week_wrant_price   | Numeric | 지난주 전세시세                   | 8000                |
| price_stats_info.wrant_price_gap         | Numeric | 전세시세 증감액                   | -2500               |
| price_stats_info.rent_deposit_price      | Numeric | 월세 보증금시세                   | 1000                |
| price_stats_info.rent_price_min          | Numeric | 월세 최소가                     | 100                 |
| price_stats_info.rent_price_max          | Numeric | 월세 최대가                     | 250                 |
| price_stats_info.last_rent_deposit_price | Numeric | 지난주 월세 보증금시세               | 1000                |
| price_stats_info.last_rent_price_min     | Numeric | 지난주 월세 최소가                 | 90                  |
| price_stats_info.last_rent_price_max     | Numeric | 지난주 월세 최대가                 | 200                 |
| price_stats_info.reg_date_time           | Text    | 등록일시 (YYYY-MM-DD hh:mm:ss) | 2023-08-18 09:03:36 |
| price_past_info                          | Array   | 과거 6개월, 12개월 내 최대최소 시세 정보  | [ ] - 월세는 제공하지 않음   |
| price_past_info.base_ymd                 | Text    | 시세기준년월일                    | 20230814            |
| price_past_info.hfyr_deal_min_ymd        | Text    | 6개월내 매매 최저등록금액 일자          | 20230314            |
| price_past_info.hfyr_deal_price_min      | Numeric | 6개월내 매매 최저등록금액             | 5500                |
| price_past_info.hfyr_deal_max_ymd        | Text    | 6개월내 매매 최고등록금액 일자          | 20230514            |
| price_past_info.hfyr_deal_price_max      | Numeric | 6개월내 매매 최고등록금액             | 6500                |
| price_past_info.hfyr_wrant_min_ymd       | Text    | 6개월내 전세 최저등록금액 일자          | 20230314            |
| price_past_info.hfyr_wrant_price_min     | Numeric | 6개월내 전세 최저등록금액             | 5500                |
| price_past_info.hfyr_wrant_max_ymd       | Text    | 6개월내 전세 최고등록금액 일자          | 20230514            |
| price_past_info.hfyr_wrant_price_max     | Numeric | 6개월내 전세 최고등록금액             | 7500                |
| price_past_info.yr_deal_min_ymd          | Text    | 1년내 매매 최저등록금액 일자           | 20221114            |
| price_past_info.yr_deal_price_min        | Numeric | 1년내 매매 최저등록금액              | 5500                |
| price_past_info.yr_deal_max_ymd          | Text    | 1년내 매매 최고등록금액 일자           | 20221121            |
| price_past_info.yr_deal_price_max        | Numeric | 1년내 매매 최고등록금액              | 6500                |
| price_past_info.yr_wrant_min_ymd         | Text    | 1년내 전세 최저등록금액 일자           | 20221114            |
| price_past_info.yr_wrant_price_min       | Numeric | 1년내 전세 최저등록금액              | 5500                |
| price_past_info.yr_wrant_max_ymd         | Text    | 1년내 전세 최고등록금액 일자           | 20221121            |
| price_past_info.yr_wrant_price_max       | Numeric | 1년내 전세 최고등록금액              | 7500                |
| price_past_info.reg_date_time            | Text    | 등록일시 (YYYY-MM-DD hh:mm:ss) | 2023-08-18 09:03:36 |
| price_monthly                            | Array   | 과거 1년 월별 시세 정보             | [ ]                 |
| price_monthly.base_ym                    | Text    | 기준월                        | 202209              |
| price_monthly.month_deal_price_avg_min   | Numeric | 월별 매매 하위 평균 시세             | 120000              |
| price_monthly.month_deal_price_avg_gen   | Numeric | 월별 매매 일반 평균 시세             | 135000              |
| price_monthly.month_deal_price_avg_max   | Numeric | 월별 매매 상위 평균 시세             | 150000              |
| price_monthly.month_wrant_price_avg_min  | Numeric | 월별 전세 하위 평균 시세             | 70000               |
| price_monthly.month_wrant_price_avg_gen  | Numeric | 월별 전세 일반 평균 시세             | 80000               |
| price_monthly.month_wrant_price_avg_max  | Numeric | 월별 전세 상위 평균 시세             | 90000               |
| price_chart_data                         | Array   | 시세 차트 데이터 (주별 데이터)         | [ ] - 월세는 제공하지 않음   |
| price_chart_data.week_dt                 | Text    | 기준일자                       | 2023-08-14          |
| price_chart_data.deal_price_low          | Numeric | 매매시세하한                     | 10000               |
| price_chart_data.deal_price_avg          | Numeric | 매매시세평균                     | 11000               |
| price_chart_data.deal_price_upp          | Numeric | 매매시세상한                     | 12000               |
| price_chart_data.wrant_price_low         | Numeric | 전세시세하한                     | 7000                |
| price_chart_data.wrant_price_avg         | Numeric | 전세시세평균                     | 8000                |
| price_chart_data.wrant_price_upp         | Numeric | 전세시세상한                     | 9000                |


- _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{"ledg_prp_no":"41290-100195115"}'
  'https://api.homeq.kr/rest/v1/real-estate/price/t010007'

```

- _응답예제 (JSON)_

```json
{
    "trace_id": "16974351057219lwTMea",
    "request_time": 1697435105721,
    "response_time": 1697435164123,
    "elapsed_time": 58402,
    "error_code": "S000",
    "error_message": "성공",
    "data": {
        "price_stats_info": [
            {
                "base_ymd": "20231009",
                "kb_object_identifier": "KBA031841",
                "area_no": 14,
                "area_number": 34,
                "complex_name": "과천센트럴파크푸르지오써밋",
                "deal_price": 165000,
                "last_week_deal_price": 165000,
                "deal_price_gap": 0,
                "wrant_price": 88000,
                "last_week_wrant_price": 88000,
                "wrant_price_gap": 0,
                "rent_deposit_price": 0,
                "rent_price_min": 0,
                "rent_price_max": 0,
                "last_week_rent_deposit_price": 0,
                "last_week_rent_price_min": 0,
                "last_week_rent_price_max": 0,
                "reg_date_time": "2023-10-16 14:45:07"
            },
     		...
        ],
        "price_past_info": [
            {
                "base_ymd": "20221205",
                "hfyr_deal_min_ymd": "20230302",
                "hfyr_deal_price_min": 160000,
                "hfyr_deal_max_ymd": "20230101",
                "hfyr_deal_price_max": 160000,
                "hfyr_wrant_min_ymd": "20230302",
                "hfyr_wrant_price_min": 84000,
                "hfyr_wrant_max_ymd": "20230101",
                "hfyr_wrant_price_max": 84000,
                "yr_deal_min_ymd": "20221201",
                "yr_deal_max_ymd": "20221230",
                "yr_wrant_min_ymd": "20221201",
                "yr_wrant_max_ymd": "20221230",
                "reg_date_time": "2023-10-16 14:45:51"
            },
            ...
        ],
        "price_monthly": [
            {
                "base_ym": "202310",
                "month_deal_price_avg_min": 161000,
                "month_deal_price_avg_gen": 165000,
                "month_deal_price_avg_max": 174000,
                "month_wrant_price_avg_min": 85000,
                "month_wrant_price_avg_gen": 88000,
                "month_wrant_price_avg_max": 90000
            },
            ...
        ],
        "price_chart_data": [
            {
                "week_dt": "2023-10-09",
                "deal_price_low": 161000,
                "deal_price_avg": 165000,
                "deal_price_upp": 174000,
                "wrant_price_low": 85000,
                "wrant_price_avg": 88000,
                "wrant_price_upp": 90000
            },
            ...
        ]
    }
}
```

</br>

### T010008. KB단지 대장 매핑 조회 [[목록]](#API-목록)

업무구분코드(work_type)에 따라 KB단지 대장 매핑을 조회

</br>

#### work_type
##### 1. 전체 단지 건수 조회
##### 2. 단지 번호 기준 조회
##### 3. 기준일 이후 변경건 조회



> **POST** https://api.homeq.kr/rest/v1/real-estate/price/t010008

</br>

- #### HTTP Request Body

| 이름                   | 타입      | 필수   | 설명       | 비고                                       |
| :------------------- | :------ | :--- | :------- | ---------------------------------------- |
| work_type            | Numeric | O    | 업무 구분    | 결과는 1,000건 씩 조회(5.제외)<br />1:전체 단지 건수 조회<br/>2.단지번호 기준 조회<br/>3:기준일 이후 변경건 조회 <br/> |
| base_dt              | Text    | △    | 기준일시     | work_type=3의 경우 필수<br />YYYY-MM-DD hh:mi:ss <br />예) 2023-09-20 14:10:10 |
| kb_complx_no         | Numeric | △    | KB단지일련번호 | work_type=2의 경우 <br />둘중 한 개는 필수입력<br/>예) 40395 |
| kb_object_identifier | Text    | △    | KB물건식별자  | work_type=2의 경우 <br />둘중 한 개는 필수입력<br/>예) KBA031841 |



- #### HTTP Response Body Data.work_type = 1

| 이름                                       | 타입      | 설명               | 비고                  |
| ---------------------------------------- | ------- | :--------------- | ------------------- |
| total_count                              | Numeric | 전체매핑건수           | 예) 22408            |
| complx_map_info_list                     | Array   | KB단지별 대장매핑 건수 정보 | [ ]                 |
| complx_map_info_list.kb_complx_no        | Numeric | KB단지일련번호         | 1                   |
| complx_map_info_list.kb_object_identifier | Text    | KB물건식별자          | KBA007884           |
| complx_map_info_list.map_count           | Numeric | 매핑건수             | 548                 |
| complx_map_info_list.last_upd_dt         | Text    | 마지막 매핑 일자        | 2023-07-31 16:23:21 |



- _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{"work_type":1}' #work_type=1
  'https://api.homeq.kr/rest/v1/real-estate/price/t010008'

```

- _응답예제 (JSON)_

```json
{
    "trace_id": "1697189753002bjTb0Ku",
    "request_time": 1697189753004,
    "response_time": 1697189753336,
    "elapsed_time": 332,
    "error_code": "S000",
    "error_message": "성공",
    "data": { 
        "total_count": 22408,
        "total_page_count": 23,
        "current_page_no": 1,
        "complx_map_info_list": [ 
            {
                "kb_complx_no": 1,
                "kb_object_identifier": "KBA007884",
                "map_count": 548,
                "last_upd_dt": "2023-07-31 16:23:21"
            },
     		...
        ]
    }
}
```

<br/>

- #### HTTP Response Body Data.work_type = 2

| 이름                                 | 타입      | 설명        | 비고                  |
| ---------------------------------- | ------- | :-------- | ------------------- |
| total_count                        | Numeric | 전체매핑건수    | 예)                  |
| map_info_list                      | Array   | 단지조회결과 목록 | [ ]                 |
| map_info_list.kb_complx_no         | Numeric | KB단지일련번호  | 40395               |
| map_info_list.kb_object_identifier | Text    | KB물건식별자   | KBA031841           |
| map_info_list.ledg_prp_no          | Text    | 대장번호      | 41290-100195629     |
| map_info_list.reg_prp_no           | Text    | 등기번호      | 13412021011745      |
| map_info_list.mapping_dt           | Text    | 매핑일시      | 2023-07-31 16:16:52 |



- _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{"work_type":2,"kb_complx_no":40395}' #work_type=2
  'https://api.homeq.kr/rest/v1/real-estate/price/t010008'

```

- _응답예제 (JSON)_

```json
{
    "trace_id": "1697189753002bjTb0Ku",
    "request_time": 1697189753004,
    "response_time": 1697189753336,
    "elapsed_time": 332,
    "error_code": "S000",
    "error_message": "성공",
    "data": { 
        "total_count": 1068,
        "total_page_count": 1,
        "current_page_no": 1,
        "map_info_list": [
            {
                "kb_complx_no": 40395,
                "kb_object_identifier": "KBA031841",
                "ledg_prp_no": "41290-100195629",
                "reg_prp_no": "13412021011745",
                "mapping_dt": "2023-07-31 16:16:52"
            },
     		...
        ]
    }
}
```

</br>

- #### HTTP Response Body Data.work_type = 3

| 이름                                 | 타입      | 설명        | 비고                  |
| ---------------------------------- | ------- | :-------- | ------------------- |
| total_count                        | Numeric | 전체매핑건수    | 예) 5732             |
| map_info_list                      | Array   | 단지조회결과 목록 | [ ]                 |
| map_info_list.kb_complx_no         | Numeric | KB단지일련번호  | 2355                |
| map_info_list.kb_object_identifier | Text    | KB물건식별자   | KBA002277           |
| map_info_list.ledg_prp_no          | Text    | 대장번호      | 11170-65320         |
| map_info_list.reg_prp_no           | Text    | 등기번호      | 11421996046509      |
| map_info_list.mapping_dt           | Text    | 매핑일시      | 2023-10-16 17:12:37 |



- _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{"work_type":3,"base_dt":"2023-10-01 14:10:10"}' #work_type=3
  'https://api.homeq.kr/rest/v1/real-estate/price/t010008'

```

- _응답예제 (JSON)_

```json
{
    "trace_id": "1697189753002bjTb0Ku",
    "request_time": 1697189753004,
    "response_time": 1697189753336,
    "elapsed_time": 332,
    "error_code": "S000",
    "error_message": "성공",
    "data": {
        "total_count": 5732,
        "total_page_count": 6,
        "current_page_no": 1,
        "map_info_list": [
            {
                "kb_complx_no": 2355,
                "kb_object_identifier": "KBA002277",
                "ledg_prp_no": "11170-65320",
                "reg_prp_no": "11421996046509",
                "mapping_dt": "2023-10-16 17:12:37"
            },
     		...
        ]
    }
}
```

#### 

#### 

