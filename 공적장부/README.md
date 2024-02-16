
BigTechPlus API Service Guide
===========


## 부동산등기 열람 API


### API 목록

* ##### [부동산등기 열람 요청](#T010000. 부동산등기 열람 요청)<br/>
* ##### [부동산등기 열람 결과조회](#T010001. 부동산등기 열람 결과조회)<br/>
* ##### [부동산등기 분석데이터 조회](#T010002. 부동산등기 분석데이터 조회)<br/>
* ##### [부동산등기 분석데이터 상세내용](#T010002. 부동산등기 분석데이터 상세내용)<br/>


---

<br/>

### T010000. 부동산등기 열람 요청 [[목록]](#API-목록)

부동산 등기 열람 요청건을 생성합니다.

입력한 고유번호에 대한 검증을 진행한 후 열람이 진행됩니다. 열람완료까지는 일정시간이 소요되며, 진행상태 및 결과는 [부동산등기 열람 결과조회](#T010001. 부동산등기 열람 결과조회) 에서 확인할 수 있습니다.

> POST https://api.homeq.kr/rest/v1/issuance/estate/t010000


* #### HTTP Request Body
| 이름           | 타입   | 길이   | 필수   | 설명        | 비고                            |
| :----------- | :--- | ---- | :--- | :-------- | ----------------------------- |
| reg_no       | Text | 16   | ㅇ    | 등기고유번호    | 예)  1341-2021-011745          |
| issue_reason | Text | 1    |      | 발급용도      | B:열람용 <br>기본값: B              |
| issue_cls    | Text | 1    |      | 발급옵션      | A:말소사항포함, C:유효사항만, <br>기본값: C |
| incl_cmort   | Text | 1    |      | 공동담보 포함여부 | 0:미포함, 1:포함,<br>기본값: 0        |
| incl_trade   | Text | 1    |      | 매매목록 포함여부 | 0:미포함, 1:포함,<br>기본값: 0        |


* #### HTTP Response Body Data

| 이름         | 타입   | 길이   | 설명     | 비고   |
| ---------- | ---- | :--- | :----- | ---- |
| apply_code | Text | 32   | 신청코드   |      |
| reg_no     | Text | 16   | 등기고유번호 |      |

* _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{
            "reg_no": "2241-2014-003375",
            "issue_reason": "B",
            "issue_cls": "A",
            "incl_cmort": "0",
            "incl_trade": "0"
  		}'
  'https://api.homeq.kr/rest/v1/issuance/estate/t010000'

```



* _응답예제 (JSON)_


```json
{
    "trace_id": "17080512010548fE5FAA",
    "request_time": 1708051201054,
    "response_time": 1708051201167,
    "elapsed_time": 113,
    "error_code": "CS000",
    "error_message": "",
    "data": {
        "apply_code": "f7e76659f3031997c76446bde06b981f",
        "reg_no": "2241-2014-003375"
    }
}
```


</br>

### T010001. 부동산등기 열람 결과조회 [[목록]](#API-목록)

요청한 부동산등기 열람 결과를 조회합니다. 

신청정보와 열람정보가 포함되며, PDF 파일 포함 유/무를 선택하실수있습니다.

> POST https://api.homeq.kr/rest/v1/issuance/estate/t010001


* #### HTTP Request Body
| 이름         | 타입   | 길이   | 필수   | 설명       | 비고                      |
| :--------- | :--- | ---- | :--- | :------- | ----------------------- |
| apply_code | Text | 32   | ㅇ    | 신청코드     |                         |
| incl_pdf   | Text | 1    |      | PDF 포함여부 | 0:미포함, 1:포함, <br>기본값: 0 |


* #### HTTP Response Body Data

| 이름           | 타입   | 길이   | 설명        | 비고                     |
| ------------ | ---- | :--- | :-------- | ---------------------- |
| apply_code   | Text | 32   | 신청코드      |                        |
| apply_date   | Text | 19   | 등기고유번호    | YYYY-MM-DD HH24:MI:SS  |
| reg_no       | Text | 16   | 등기고유번호    |                        |
| doc_cls      | Text | 2    | 구분        | 10: 건물, 20:토지, 30:집합건물 |
| addr         | Text |      | 주소        |                        |
| issue_reason | Text | 1    | 발급용도      | B:열람용                  |
| issue_cls    | Text | 1    | 발급옵션      | A:말소사항포함, C:유효사항만      |
| incl_cmort   | Text | 1    | 공동담보 포함여부 | 0:미포함, 1:포함            |
| incl_trade   | Text | 1    | 매매목록 포함여부 | 0:미포함, 1:포함            |
| browse_date  | Text | 19   | 열람일시      | YYYY-MM-DD HH24:MI:SS  |
| pdf_base64   | Text |      | PDF 데이터   | Base64 인코딩된 byte 배열    |

* _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{
            "apply_code": "f7e76659f3031997c76446bde06b981f",
            "incl_pdf": "1",
  		}'
  'https://api.homeq.kr/rest/v1/issuance/estate/t010001'

```



* _응답예제 (JSON)_


```json
{
    "trace_id": "1708060202991VL2ggnS",
    "request_time": 1708060202991,
    "response_time": 1708060203007,
    "elapsed_time": 16,
    "error_code": "CS000",
    "error_message": "",
    "data": {
        "apply_date": "2024-02-15 22:27:34",
        "reg_no": "1358-2008-005468",
        "doc_cls": "30",
        "addr": "경기도 수원시 영통구 효원로 363, 제129동 제22층 제2206호 [매탄동 1352 매탄 위브 하늘채]",
        "issue_reason": "B",
        "issue_cls": "A",
        "incl_cmort": "0",
        "incl_trade": "0",
        "issue_date": "2024-02-15 22:28:11",
         "pdf_base64": "JVBERi0xLjUKJcfsj6IKNSAwIG9iago8PC9MZW5ndGggNiAwIFIvRmlsdGVyIC9GbGF0ZURlY29kZT4+CnN0cmVhbQp4nNVdTcslx3UGLWbxGrQJeOGFdUFhdF+ht6e+P0bGcSRZY5SVxwMBW8kiji0QloImYC+0tEmUzEgjaZSNf4QWWpisEpAgEC+yyMKLGLzQNpBNIIsYkpzqj6qnT/e93dV3JrpGiGkdPfdUddVznlN1urrnrZ1o5E6kf/o/v//GxY3bfvfaX168tdPeNKa1dldSa9PouPP ..."
    }
}
```



</br>

### T010002. 부동산등기 분석데이터 조회 [[목록]](#API-목록)

열람이 완료된 부동산등기의 파싱 데이터를 조회합니다.

> POST https://api.homeq.kr/rest/v1/issuance/estate/t010002


* #### HTTP Request Body
| 이름         | 타입   | 길이   | 필수   | 설명   | 비고   |
| :--------- | :--- | ---- | :--- | :--- | ---- |
| apply_code | Text | 32   | ㅇ    | 신청코드 |      |


* #### HTTP Response Body Data
  <span class="red">❗️전체 데이터는 [부동산등기 분석데이터 상세내용](#T010002. 부동산등기 분석데이터 상세내용) 에서 확인가능하며, 내용은 추가 될 수 있습니다.</span>

| 이름         | 타입     | 길이   | 설명                                       | 비고   |
| ---------- | ------ | :--- | :--------------------------------------- | ---- |
| version    | Text   | 16   | 분석모듈 버전정보                                |      |
| d015100    | Object |      | 부동산등기 - 기본정보                             |      |
| d015100_01 | Object |      | 부동산등기 - 등기 주소 상세                         |      |
| d015200    | Object |      | 부동산등기 - 등기 기본 정보 2 (나이스 버전)              |      |
| d015201    | Array  |      | 부동산등기 - 주요 등기사항 요약 중 1. 소유지분현황(갑구)       |      |
| d015202    | Array  |      | 부동산등기 - 주요 등기사항 요약 중 2. 소유지분제외(갑구) 3. (근)저당권 및 전세권 등 |      |
| d01521000  | Array  |      | 부동산등기 - 등기부 갑구, 을구 (권리자 및 기타사항 제외)       |      |
| d01521001  | Array  |      | 부동산등기 - 등기부 갑구, 을구의 권리자 및 기타사항           |      |
| d01522000  | Array  |      | 부동산등기 - 표제부 (단순 줄별로 모아놓은 데이터)            |      |
| d01522001  | Array  |      | 부동산등기 - 표제부(상세)                          |      |
| d01522002  | Array  |      | 부동산등기 - 표제부 (층별 정보)                      |      |
| d01522003  | Array  |      | 부동산등기 - 표제부 (대지권의 목적인 토지의 표시)            |      |
| d01523000  | Array  |      | 부동산등기 - 공동담보목록                           |      |

* _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-secret-key: {signature}" \
  --data '{
            "apply_code": "f7e76659f3031997c76446bde06b981f"
  		}'
  'https://api.homeq.kr/rest/v1/issuance/estate/t010001'

```



* _응답예제 (JSON)_


```json
{
    "trace_id": "1708060779475OPzXEkl",
    "request_time": 1708060779475,
    "response_time": 1708060779618,
    "elapsed_time": 143,
    "error_code": "CS000",
    "error_message": "",
    "data": {
        "version": "1.1.4",
        "d015100": {
            "rett_prp_no": "1358-2008-005468",
            "view_date": "2024-02-15 22:25:06",
            "reg_title": "",
            "rett_type": "3",
            "rett_type_nm": "집합건물",
            "reg_rep_addr": "경기도 수원시 영통구 매탄동 1352 매탄 위브 하늘채 제129동 제22층 제2206호",
            "jurd_rego_cd": "1358",
            "jurd_rego_nm": "수원지방법원 동수원등기소",
            "reg_case_yn": "0",
            "reg_case_text": "",
            "reg_case_cd": "",
            "reg_case_title": "",
            "reg_case_no": "",
            "trust_reg_yn": "0"
        },
        "d015100_01": { ... },
        "d015200": { ... },
        "d015201": [ {...}, ... ],
        ...
    }
}
```

</br>

### T010002. 부동산등기 분석데이터 상세내용 [[목록]](#API-목록)

`seq` 는 상위테이블과 하위테이블과 **1:n 관계**를 의미합니다.

공통 데이터를 제외하고 **문서구분** (ex.토지, 건물, 집합건물) 에 해당하는 오브젝트에만 값이 포함됩니다.

### d015100 - 기본정보

| 이름                   | 타입      | 길이   | 설명              | 비고   |
| -------------------- | ------- | :--- | :-------------- | ---- |
| rett_prp_no          | Text    | 16   | 부동산 고유번호        |      |
| view_date            | Text    | 17   | 열람일시            |      |
| reg_title            | Text    | 100  | 등기제목            |      |
| rett_type            | Text    | 1    | 부동산 구분코드        |      |
| rett_type_nm         | Text    | 10   | 부동산 구분코드 명      |      |
| reg_rep_addr         | Text    | 200  | 등기주소            |      |
| jurd_rego_cd         | Text    | 4    | 관할등기소 코드        |      |
| jurd_rego_nm         | Text    | 40   | 관할등기소           |      |
| reg_case_yn          | Text    | 1    | 신청사건 진행 여부      |      |
| reg_case_text        | Text    | 200  | 신청사건 진행 텍스트     |      |
| reg_case_cd          | Text    | 2    | 신청사건 사건 코드      |      |
| reg_case_title       | Text    | 100  | 신청사건 사건 제목      |      |
| reg_case_no          | Text    | 20   | 신청사건 진행 접수번호    |      |
| trust_reg_yn         | Text    | 1    |                 |      |
| share_owner_yn       | Text    | 1    | 공유자 소유 여부       |      |
| mgg_priv_right_yn    | Text    | 1    | 개인 권리자 담보 설정 여부 |      |
| mgg_lender_right_yn  | Text    | 1    | 대부업체 담보 설정 여부   |      |
| mgg_trust_right_yn   | Text    | 1    | 신탁 권리자 설정 여부    |      |
| mgg_right_person_cnt | Numeric |      | 근저당 건수          |      |
| mgg_lender_right_cnt | Numeric |      | 대부업체 권리자 건수     |      |


### d015100_01 - 등기 주소 상세

| 이름              | 타입      | 길이   | 설명            | 비고   |
| --------------- | ------- | :--- | :------------ | ---- |
| rett_prp_no     | Text    | 16   | 부동산 고유번호      |      |
| rett_addr       | Text    | 200  | 등기상_기본주소      |      |
| full_jibun_addr | Text    | 200  | 법정동_전체_주소     |      |
| full_doro_addr  | Text    | 200  | 도로명_전체_주소     |      |
| doro_addr_etc   | Text    | 200  | 도로명주소_기타주소    |      |
| pnu             | Text    | 19   | PNU           |      |
| sido            | Text    | 20   | 시도            |      |
| sgg             | Text    | 20   | 시군구           |      |
| umd             | Text    | 20   | 읍면동           |      |
| ri              | Text    | 20   | 리             |      |
| bon_no          | Text    | 4    | 본번            |      |
| bu_no           | Text    | 4    | 부번            |      |
| bldg_nm         | Text    | 100  | 건물명           |      |
| dong            | Text    | 100  | 동             |      |
| flr             | Text    | 30   | 층             |      |
| ho              | Text    | 30   | 호             |      |
| lcode           | Text    | 10   | 법정동코드         |      |
| doro_nm         | Text    | 50   | 도로명           |      |
| bldg_bon_bu_no  | Text    | 10   | 건물본부번호        |      |
| bldg_cls_cd     | Text    | 2    | 물건지종류코드       |      |
| bldg_cls_nm     | Text    | 30   | 물건지종류         |      |
| bldg_pk         | Text    | 25   | 도로명주소_건물관리;번호 |      |
| zip             | Text    | 5    | 우편번호          |      |
| latitude        | Numeric |      | 위도            |      |
| longitude       | Numeric |      | 경도            |      |



### d015200 - 등기 기본정보 (나이스 제공 형태)

| 이름           | 타입      | 길이   | 설명                          | 비고   |
| ------------ | ------- | :--- | :-------------------------- | ---- |
| rett_prp_no  | Text    | 16   | 부동산 고유번호                    |      |
| rerg_cd      | Text    | 5    | 파일거래구분코드                    |      |
| data_type    | Text    | 1    | Data구분                      |      |
| req_prp_no   | Text    | 20   | 발급신청번호                      |      |
| rett_prp_no  | Text    | 14   | 부동산고유번호                     |      |
| rett_type    | Text    | 1    | 부동산구분 - 1: 토지 2: 건물 5: 집합건물 |      |
| view_date    | Text    | 14   | 열람일시                        |      |
| convert_date | Text    | 14   | 변환일시                        |      |
| rett_addr    | Text    | 300  | 등기부소재지                      |      |
| req_case     | Text    | 200  | 주의사항                        |      |
| jurd_rego_nm | Text    | 50   | 관할 등기소명                     |      |
| total_page   | Numeric |      | 전체 페이지 개수                   |      |
| valid_page   | Numeric |      | 추출 페이지 개수                   |      |

### d015201 - 주요 등기사항 요약 중 1. 소유지분현황(갑구)

| 이름            | 타입      | 길이   | 설명       | 비고   |
| ------------- | ------- | :--- | :------- | ---- |
| rett_prp_no   | Text    | 16   | 부동산 고유번호 |      |
| seq           | Numeric |      | 순번       |      |
| rerg_cd       | Text    | 5    | 파일거래구분코드 |      |
| data_type     | Text    | 1    | Data구분   |      |
| req_prp_no    | Text    | 20   | 발급요청번호   |      |
| rank_no       | Text    | 512  | 순위번호     |      |
| owner_nm      | Text    | 50   | 등기명의인    |      |
| own_type      | Text    | 10   | 소유구분     |      |
| owner_no      | Text    | 20   | 등록번호     |      |
| owner_type    | Text    | 2    | 소유자 유형   |      |
| owner_type_nm | Text    | 20   | 소유자 유형 명 |      |
| own_share     | Text    | 100  | 최종지분     |      |
| owner_addr    | Text    | 300  | 주소       |      |

<div style="page-break-after: always;"></div>

### d015202 - 주요 등기사항 요약 중 2. 소유지분제외(갑구) 3. (근)저당권 및 전세권 등

| 이름                   | 타입      | 길이   | 설명                                       | 비고   |
| -------------------- | ------- | :--- | :--------------------------------------- | ---- |
| rett_prp_no          | Text    | 16   | 부동산 고유번호                                 |      |
| seq                  | Numeric |      | 순번                                       |      |
| rerg_cd              | Text    | 5    | 파일거래구분코드                                 |      |
| data_type            | Text    | 1    | Data구분                                   |      |
| req_prp_no           | Text    | 20   | 발급요청번호                                   |      |
| reg_type             | Text    | 1    | 등기분류 - 1: 표제부 2: 갑구 3: 을구                |      |
| rank_no              | Text    | 20   | 순위번호                                     |      |
| reg_purpose_no       | Text    | 3    | 등기목적번호                                   |      |
| reg_purpose          | Text    | 1000 | 등기목적                                     |      |
| accept_date          | Text    | 8    | 접수일자                                     |      |
| accept_no            | Text    | 12   | 접수번호                                     |      |
| obj_owner            | Text    | 256  | 대상소유자                                    |      |
| right_person         | Text    | 100  | 권리자                                      |      |
| right_person_type    | Text    | 2    | 권리자 유형                                   |      |
| right_person_type_nm | Text    | 20   | 권리자 유형 명                                 |      |
| right_cost_str       | Text    | 100  | 채권액1                                     |      |
| right_cost_num       | Text    | 20   | 채권액2                                     |      |
| crncy_cd             | Text    | 1    | 화폐구분 - 1: 원화 2: 달러 3: 엔 4: 위안 5: 유로 9: 기타 |      |
| reg_info_text        | Text    | 3000 | 주요등기사항                                   |      |

<div style="page-break-after: always;"></div>

### d01521000 - 등기부 갑구, 을구 (권리자 및 기타사항 제외)

| 이름              | 타입      | 길이   | 설명                                       | 비고   |
| --------------- | ------- | :--- | :--------------------------------------- | ---- |
| rett_prp_no     | Text    | 16   | 부동산 고유번호                                 |      |
| seq             | Numeric |      | 순번                                       |      |
| rerg_cd         | Text    | 5    | 파일거래구분코드                                 |      |
| data_type       | Text    | 1    | Data구분                                   |      |
| req_prp_no      | Text    | 20   | 발급신청번호                                   |      |
| reg_prp_seq     | Text    | 11   | 등기추출번호 - t_iros_d01521001 테이블 관련 키       |      |
| reg_type        | Text    | 1    | 등기분류 - 1: 표제부 2: 갑구 3: 을구                |      |
| rank_no         | Text    | 20   | 순위번호                                     |      |
| reg_purpose_no  | Text    | 3    | 등기목적번호                                   |      |
| reg_purpose     | Text    | 4000 | 등기목적                                     |      |
| accept_date     | Text    | 8    | 접수일자                                     |      |
| accept_no       | Text    | 12   | 접수번호                                     |      |
| reg_caus_date   | Text    | 8    | 등기원인일자                                   |      |
| reg_caus        | Text    | 1000 | 등기원인                                     |      |
| reg_state       | Text    | 1    | 상태 - 0: 미말소(요약부에 표시된 순위번호), 1: 말소 (요약부에 미표시 or 순위번호 삭선) |      |
| convert_date    | Text    | 14   | 처리일시                                     |      |
| valid_party_cnt | Numeric |      | 등기부당사자 추출건수                              |      |

<div style="page-break-after: always;"></div>

### d01521001 - 등기부 갑구, 을구의 권리자 및 기타사항

| 이름                   | 타입      | 길이   | 설명                                       | 비고   |
| -------------------- | ------- | :--- | :--------------------------------------- | ---- |
| rett_prp_no          | Text    | 16   | 부동산 고유번호                                 |      |
| seq                  | Numeric |      | 순번                                       |      |
| rerg_cd              | Text    | 5    | 파일거래구분코드                                 |      |
| data_type            | Text    | 1    | Data구분                                   |      |
| req_prp_no           | Text    | 20   | 발급요청번호                                   |      |
| reg_prp_seq          | Text    | 11   | 등기추출번호                                   |      |
| party_type_cd        | Text    | 2    | 당사자구분코드                                  |      |
| party_type           | Text    | 20   | 당사자구분                                    |      |
| party_person_nm      | Text    | 100  | 당사자명                                     |      |
| chg_party_person_nm  | Text    | 100  | 변경당사자명                                   |      |
| party_no             | Text    | 20   | 당사자번호                                    |      |
| party_person_type    | Text    | 2    | 당사자 유형                                   |      |
| party_person_type_nm | Text    | 20   | 당사자 유형 명                                 |      |
| party_cost_str       | Text    | 2000 | 당사자금액(문자)                                |      |
| party_cost_num       | Text    | 20   | 당자사금액(숫자)                                |      |
| party_addr           | Text    | 300  | 주소                                       |      |
| jijum_nm             | Text    | 100  | 지점명                                      |      |
| crncy_cd             | Text    | 1    | 화폐구분 - 1: 원화 2: 달러 3: 엔 4: 위안 5: 유로 9: 기타 |      |
| parse_pat_cd         | Text    | 3    | 추출패턴코드                                   |      |
| party_share          | Text    | 100  | 지분                                       |      |

<div style="page-break-after: always;"></div>

### d01522000 - 표제부 - 전체데이터

| 이름          | 타입      | 길이   | 설명                                   | 비고   |
| ----------- | ------- | :--- | :----------------------------------- | ---- |
| rett_prp_no | Text    | 16   | 부동산 고유번호                             |      |
| seq         | Numeric |      | 순번                                   |      |
| rerg_cd     | Text    | 5    | 파일거래구분코드                             |      |
| data_type   | Text    | 1    | Data구분                               |      |
| req_prp_no  | Text    | 20   | 발급신청번호                               |      |
| rett_type   | Text    | 1    | 부동산구분- 1: 토지 2: 건물 5: 집합건물           |      |
| reg_cls_cd  | Text    | 1    | 등기분류 - 1: 표제부 2: 갑구 3: 을구            |      |
| parse_type  | Text    | 1    | 전체상세구분 - 1: 표제부 전체추출 2: 표제부 상세추출(요약) |      |
| mark_num    | Text    | 20   | 표시번호                                 |      |
| accept_date | Text    | 8    | 접수일자                                 |      |
| tit_cls_cd  | Text    | 3    | 표제부코드                                |      |
| tit_seq     | Text    | 4    | 표제부 일련번호                             |      |
| tit_text    | Text    | 100  | 표제부 항목                               |      |

<div style="page-break-after: always;"></div>

### d01522001 - 표제부 - 상세

| 이름              | 타입      | 길이   | 설명       | 비고   |
| --------------- | ------- | :--- | :------- | ---- |
| rett_prp_no     | Text    | 16   | 부동산 고유번호 |      |
| seq             | Numeric |      | 순번       |      |
| rerg_cd         | Text    | 5    | 파일거래구분코드 |      |
| data_type       | Text    | 1    | Data구분   |      |
| req_prp_no      | Text    | 20   | 발급신청번호   |      |
| rett_type       | Text    | 1    | 부동산구분    |      |
| reg_cls_cd      | Text    | 1    | 등기분류     |      |
| parse_type      | Text    | 1    | 전체상세구분   |      |
| mark_num        | Text    | 20   | 표시번호     |      |
| accept_date     | Text    | 8    | 접수일자     |      |
| tit_cls_cd      | Text    | 3    | 표제부코드    |      |
| tit_seq         | Text    | 4    | 표제부일련번호  |      |
| tit_detail_text | Text    | 4000 | 표제부 항목   |      |

<div style="page-break-after: always;"></div>

### d01522002 - 표제부 - 층별 정보

| 이름          | 타입      | 길이   | 설명                                   | 비고   |
| ----------- | ------- | :--- | :----------------------------------- | ---- |
| rett_prp_no | Text    | 16   | 부동산 고유번호                             |      |
| seq         | Numeric |      | 순번                                   |      |
| rerg_cd     | Text    | 5    | 파일거래구분코드                             |      |
| data_type   | Text    | 1    | Data구분                               |      |
| req_prp_no  | Text    | 20   | 발급신청번호                               |      |
| rett_type   | Text    | 1    | 부동산구분 - 1: 토지 2: 건물 5: 집합건물          |      |
| reg_cls_cd  | Text    | 1    | 등기분류 - 1: 표제부 2: 갑구 3: 을구            |      |
| parse_type  | Text    | 1    | 전체상세구분 - 1: 표제부 전체추출 2: 표제부 상세추출(요약) |      |
| mark_num    | Text    | 20   | 표시번호                                 |      |
| accept_date | Text    | 8    | 접수일자                                 |      |
| tit_cls_cd  | Text    | 3    | 표제부코드                                |      |
| tit_seq     | Text    | 4    | 표제부일련번호                              |      |
| flr_str     | Text    | 20   | 층(문자)                                |      |
| flr_num     | Text    | 10   | 층(숫자)                                |      |
| area_str    | Text    | 20   | 층면적(문자)                              |      |
| area_num    | Text    | 10   | 층면적(숫자)                              |      |
| flr_usg_nm  | Text    | 100  | 층용도                                  |      |

<div style="page-break-after: always;"></div>

### d01522003 - 표제부 - 대지권의 목적인 토지의 표시

| 이름              | 타입      | 길이   | 설명         | 비고   |
| --------------- | ------- | :--- | :--------- | ---- |
| rett_prp_no     | Text    | 16   | 부동산 고유번호   |      |
| seq             | Numeric |      | 순번         |      |
| rerg_cd         | Text    | 5    | 파일거래구분코드   |      |
| data_type       | Text    | 1    | Data구분     |      |
| req_prp_no      | Text    | 20   | 발급신청번호     |      |
| rett_type       | Text    | 1    | 부동산구분      |      |
| reg_cls_cd      | Text    | 1    | 등기분류       |      |
| parse_type      | Text    | 1    | 전체상세구분     |      |
| mark_num        | Text    | 20   | 표시번호       |      |
| accept_date     | Text    | 8    | 접수일자       |      |
| tit_cls_cd      | Text    | 3    | 표제부코드      |      |
| tit_seq         | Text    | 4    | 표제부일련번호    |      |
| land_right_addr | Text    | 300  | 대지권 목적인 토지 |      |
| land_cls        | Text    | 10   | 지목         |      |
| area_str        | Text    | 20   | 면적(문자)     |      |
| area_num        | Text    | 10   | 면적(숫자)     |      |

<div style="page-break-after: always;"></div>

### d01523000 - 공동담보목록

| 이름              | 타입      | 길이   | 설명             | 비고   |
| --------------- | ------- | :--- | :------------- | ---- |
| rett_prp_no     | Text    | 16   | 부동산 고유번호       |      |
| seq             | Numeric |      | 순번             |      |
| rerg_cd         | Text    | 5    | 파일거래구분코드       |      |
| data_type       | Text    | 1    | Data구분         |      |
| req_prp_no      | Text    | 20   | 발급신청번호         |      |
| rett_type       | Text    | 1    | 부동산구분          |      |
| list_no         | Text    | 20   | 목록번호           |      |
| idx             | Text    | 4    | 일련번호           |      |
| rett_right_text | Text    | 300  | 부동산에 관한 권리의 표시 |      |
| jurd_rego_nm    | Text    | 50   | 관할등기소명         |      |
| rank_no         | Text    | 20   | 순위번호           |      |
| cret_caus_date  | Text    | 8    | 생성원인 일자        |      |
| cret_caus_no    | Text    | 20   | 생성원인 번호        |      |
| cret_caus_info  | Text    | 100  | 생성원인 내용        |      |
| chg_del_date    | Text    | 8    | 변경/소멸 일자       |      |
| chg_del_no      | Text    | 20   | 변경/소멸 번호       |      |
| chg_del_info    | Text    | 100  | 변경/소멸 내용       |      |
| is_chg_del      | Text    | 1    | 변경/소멸 여부       |      |

