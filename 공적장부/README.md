BigTechPlus API Service Guide
===========


## 부동산등기 열람 API


#### API 목록

* ##### [부동산등기 열람 요청](#T010000-부동산등기-열람-요청-목록)
* ##### [부동산등기 열람 콜백 요청](#T010000-1-부동산등기-열람-콜백-요청-목록)
* ##### [부동산등기 열람 결과조회](#T010001-부동산등기-열람-결과조회-목록)
* ##### [부동산등기 분석데이터 조회](#T010002-부동산등기-분석데이터-조회-목록)
* ##### [부동산등기 분석데이터 상세내용](#T010002-부동산등기-분석데이터-상세내용-목록)


---

<br/>

### T010000. 부동산등기 열람 요청 [[목록]](#API-목록)

부동산 등기 열람 요청건을 생성합니다.

입력한 고유번호에 대한 검증을 진행한 후 열람이 진행됩니다. 열람완료까지는 일정시간이 소요되며, 진행상태 및 결과는 [부동산등기 열람 결과조회](#T010001. 부동산등기 열람 결과조회) 에서 확인할 수 있습니다.

> POST https://api.homeq.kr/rest/v1/issuance/estate/t010000


* #### HTTP Request Body
| 이름            | 타입      | 길이   | 필수   | 설명                   | 비고                                       |
| :------------ | :------ | ---- | :--- | :------------------- | ---------------------------------------- |
| reg_no        | Text    | 16   | ㅇ    | 등기고유번호               | 예)  1341-2021-011745                     |
| issue_reason  | Text    | 1    |      | 발급용도                 | B:열람용 <br>기본값: B                         |
| issue_cls     | Text    | 1    |      | 발급옵션                 | A:말소사항포함, C:유효사항만, <br>기본값: C            |
| incl_cmort    | Text    | 1    |      | 공동담보 포함여부            | 0:미포함, 1:포함,<br>기본값: 0                   |
| incl_trade    | Text    | 1    |      | 매매목록 포함여부            | 0:미포함, 1:포함,<br>기본값: 0                   |
| callback_type | Text    | 1    |      | 콜백데이터구분              | 0: 성공여부 (default) <br>1: 성공여부 + PDF<br>2: 성공여부 + 분석데이터<br>3: 성공여부 + PDF + 분석데이터 |
| data_tmpl     | Text    | 5    |      | 콜백 시 받을<br>분석데이터 템플릿 | EDT01: BTP포맷(default)<br>EDT02: NICE포맷   |
| endpoint      | Text    |      |      | URL                  |                                          |
| timeout       | Numeric |      |      | connection timeout   | 기본값: 3000ms                              |


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
  --header "x-btp-signature-v1: {signature}" \
  --data '{
            "reg_no": "2241-2014-003375",
            "issue_reason": "B",
            "issue_cls": "A",
            "incl_cmort": "0",
            "incl_trade": "0",
            "callback_type": "3",
            "data_tmpl": "EDT01",
            "endpoint": "http://endpoint.com/callback/someurl"
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
  --header "x-btp-signature-v1: {signature}" \
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

### T010000-1. 부동산등기 열람 콜백 요청 [[목록]](#API-목록)

열람 요청에 대한 응답을 POST API방식으로 Callback받으실 수 있습니다. <br>요청시 `endpoint` 파라미터에 콜백 URI를 입력하시면 `callback_type` 파라미터별 응답이 전송됩니다.

전체 데이터는 [부동산등기 분석데이터 상세내용](#T010002-부동산등기-분석데이터-상세내용-목록) 에서 확인가능하며, 내용은 추가 될 수 있습니다.
<br>
* #### callback_type = 0 (성공여부)
| 이름            | 타입   | 길이   | 설명    | 비고   |
| ------------- | ---- | :--- | :---- | ---- |
| error_code    | Text | 5    | 응답코드  |      |
| error_message | Text | 512  | 에러메세지 |      |
| apply_code    | Text | 32   | 신청코드  |      |

* _응답예제 (JSON)_

  ```json
  {
      "error_code": "CS000",
      "error_message": "",
      "apply_code": "f7e76659f3031997c76446bde06b981f"
  }
  ```


* #### callback_type = 1 (성공여부 + PDF)
| 이름            | 타입     | 길이   | 설명      | 비고                    |
| ------------- | ------ | :--- | :------ | --------------------- |
| error_code    | Text   | 5    | 응답코드    |                       |
| error_message | Text   | 512  | 에러메세지   |                       |
| apply_code    | Text   | 32   | 신청코드    |                       |
| data          | Object |      |         |                       |
| ㄴ pdf_base64  | Text   |      | pdf 데이터 | Base64 인코딩된 byte 배열   |
| ㄴ issue_date  | Text   | 19   | 열람일시    | YYYY-MM-DD HH24:MI:SS |

* _응답예제 (JSON)_


  ```json
  {
      "error_code": "CS000",
      "error_message": "",
      "apply_code": "f7e76659f3031997c76446bde06b981f",
      "data": {
          "pdf_base64": "JVBERi0xLjUKJcfsj6IKNSAwIG9iago8PC9MZW5ndGggNiAwIFIvRmlsdGVyIC9GbGF0ZURlY29kZT4+CnN0cmVhbQp4nNVdTcslx3UGLWbxGrQJeOGFdUFhdF+ht6e+P0bGcSRZY5SVxwMBW8kiji0QloImYC+0tEmUzEgjaZSNf4QWWpisEpAgEC+yyMKLGLzQNpBNIIsYkpzqj6qnT/e93dV3JrpGiGkdPfdUddVznlN1urrnrZ1o5E6kf/o/v//GxY3bfvfaX168tdPeNKa1dldSa9PouPP ...",
          "issue_date": "2024-02-15 22:28:11"
      }
  }
  ```

* #### callback_type = 2 (성공여부 + 분석데이터)
  분석데이터는 `data_tmpl` 값에 따라 분석데이터 템플릿을 다르게 받을 수 있습니다.
| 이름            | 타입     | 길이   | 설명               | 비고                    |
| ------------- | ------ | :--- | :--------------- | --------------------- |
| error_code    | Text   | 5    | 응답코드             |                       |
| error_message | Text   | 512  | 에러메세지            |                       |
| apply_code    | Text   | 32   | 신청코드             |                       |
| data          | Object |      |                  |                       |
| ㄴ issue_date  | Text   | 19   | 열람일시             | YYYY-MM-DD HH24:MI:SS |
| ㄴ version     | Text   | 16   | 분석 버전정보          | 데이터분석 모듈버전            |
| ㄴ d015100     | Object |      | 부동산등기 - 기본정보     |                       |
| ㄴd015100_01   | Object |      | 부동산등기 - 등기 주소 상세 |                       |



* _응답예제 (JSON)_


  ```json
  {
      "error_code": "CS000",
      "error_message": "",
      "apply_code": "f7e76659f3031997c76446bde06b981f",
      "data": {
          "version": "1.1.4",
          "issue_date": "2024-02-15 22:28:11",
          "pdf_base64": "JVBERi0xLjUKJcfsj6IKNSAwIG9iago8PC9MZW5ndGgg ... ",
          "d015100": { ... },
          "d015100_01": { ... },
          "d015101": [ {...}, ... ],
        ...
    }
  }
  ```
* #### callback_type = 3 (성공여부 + PDF + 분석데이터 )
* _응답예제 (JSON)_


  ```json
  {
      "error_code": "CS000",
      "error_message": "",
      "apply_code": "f7e76659f3031997c76446bde06b981f",
      "data": {
          "version": "1.1.4",
          "issue_date": "2024-02-15 22:28:11",
          "d015100": { ... },
          "d015100_01": { ... },
          "d015101": [ {...}, ... ],
          "d015102": [ {...}, ... ],
          ...
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
  <span class="red">❗️전체 데이터는 [부동산등기 분석데이터 상세내용](#T010002-부동산등기-분석데이터-상세내용-목록) 에서 확인가능하며, 내용은 추가 될 수 있습니다.</span>


  * ##### data_tmpl = EDT01
| 이름         | 타입     | 길이   | 설명                        | 비고   |
| ---------- | ------ | :--- | :------------------------ | ---- |
| version    | Text   | 16   | 분석모듈 버전정보                 |      |
| d015100    | Object |      | 부동산등기 - 기본정보              |      |
| d015100_01 | Object |      | 부동산등기 - 등기 주소 상세          |      |
| d015101    | Array  |      | 갑구 중 최종 소유자               |      |
| d015102    | Array  |      | 갑구 중 소유권 이외 권리            |      |
| d015103    | Array  |      | 을구 권리자                    |      |
| d015104    | Array  |      | 토지의 표시                    |      |
| d015105    | Array  |      | 건물의 표시                    |      |
| d015106    | Array  |      | 전유부분 건물의 표시               |      |
| d015107    | Array  |      | 매매목록                      |      |
| d015108    | Array  |      | 공동담보목록                    |      |
| d015109    | Array  |      | 소유자 이력 정보                 |      |
| d01511000  | Array  |      | 표제부: 토지의표시                |      |
| d01511001  | Array  |      | 표제부: 토지의 표시 중 소재지 리스트     |      |
| d01511100  | Array  |      | 표제부: 건물의표시                |      |
| d01511101  | Array  |      | 표제부: 층별정보                 |      |
| d01511200  | Array  |      | 표제부: 토지의표시(집합건물)          |      |
| d01511201  | Array  |      | 표제부: 토지의표시(집합건물) 상세       |      |
| d01511300  | Array  |      | 표제부: 전유부분의 건물의 표시(집합건물)   |      |
| d01511301  | Array  |      | 표제부: 전유부분의 건물의 표시 상세(복층등) |      |
| d01511400  | Array  |      | 표제부: 대지권의 표시(집합)          |      |
| d01511401  | Array  |      | 표제부: 대지권의 표시 상세           |      |
| d01512000  | Array  |      | 갑구: 기본정보                  |      |
| d01512001  | Array  |      | 갑구: 등기 목적 상세(지분별 상세)      |      |
| d01512100  | Array  |      | 갑구: 소유정보                  |      |
| d01512101  | Array  |      | 갑구: 소유자 정보                |      |
| d01512200  | Array  |      | 갑구: 소유권 외 권리정보            |      |
| d01512201  | Array  |      | 갑구: 소유권 외 권리 권리자          |      |
| d01512202  | Array  |      | 갑구: 소유권 외 권리 권리금          |      |
| d01512300  | Array  |      | 갑구: 주차장 정보                |      |
| 01513000   | Array  |      | 을구: 기본정보                  |      |
| d01513100  | Array  |      | 을구: 권리정보                  |      |
| d01513101  | Array  |      | 을구: 권리자                   |      |
| d01513102  | Array  |      | 을구: 권리금액                  |      |
| d01513103  | Array  |      | 을구: 채무자                   |      |
| d01513104  | Array  |      | 을구: 공동담보목록                |      |
| d01514000  | Array  |      | 공동담보목록: 기본정보              |      |
| d01514001  | Array  |      | 공동담보목록: 상세정보              |      |
| d01515000  | Array  |      | 매매목록: 기본정보                |      |
| d01515010  | Array  |      | 매매목록: 거래가액 변경내역           |      |
| d01515020  | Array  |      | 매매목록: 상세정보                |      |
| d01519000  | Array  |      | 대위권리자                     |      |
<br>

* ##### data_tmpl = EDT02
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
  --header "x-btp-signature-v1: {signature}" \
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

공통 데이터를 제외하고 **문서구분** (ex.토지, 건물, 집합건물) 에 해당하는 오브젝트에만 값이 포함됩니다.<br>
요청한 `data_tmpl`에 따라 다른 결과가 출력됩니다.

* ##### [data_tmpl = EDT01](#data_tmpl-edt01)
* ##### [data_tmpl = EDT02](#data_tmpl-edt02-nice-목록)

### data_tmpl EDT01
#### d015100 - 기본정보

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


#### d015100_01 - 등기 주소 상세

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



#### d015101 - 갑구 중 최종 소유자

| 이름                 | 타입      | 길이   | 설명        | 비고   |
| ------------------ | ------- | :--- | :-------- | ---- |
| rett_prp_no        | Text    | 16   | 부동산 고유번호  |      |
| seq                | Numeric |      | 순번        |      |
| own_type           | Text    | 2    | 소유형태      |      |
| own_type_nm        | Text    | 10   | 소유형태 명    |      |
| own_share_n        | Text    | 50   | 소유지분 분자   |      |
| own_share_d        | Text    | 50   | 소유지분 분모   |      |
| owner_type         | Text    | 2    | 소유자 유형    |      |
| owner_type_nm      | Text    | 20   | 소유자 유형 명  |      |
| owner_nation       | Text    | 30   | 소유자 국적    |      |
| owner_nm           | Text    | 100  | 소유자 명     |      |
| owner_no           | Text    | 30   | 소유자 고유번호  |      |
| owner_addr         | Text    | 200  | 소유자 주소    |      |
| manager_type       | Text    | 2    | 관리자 구분    |      |
| manager_type_nm    | Text    | 30   | 관리자 구분 명  |      |
| manager_nm         | Text    | 100  | 관리자 명     |      |
| ref_rank_no        | Text    | 512  | 관련 순위번호   |      |
| land_right_content | Text    | 1000 | 대지권 관련 내용 |      |

#### d015102 - 갑구 중 소유권 이외 권리

| 이름                    | 타입      | 길이   | 설명           | 비고   |
| --------------------- | ------- | :--- | :----------- | ---- |
| rett_prp_no           | Text    | 16   | 부동산 고유번호     |      |
| seq                   | Numeric |      | 순번           |      |
| rank_no               | Text    | 10   | 순위번호         |      |
| sub_rank_no           | Text    | 10   | 부기 순위번호      |      |
| pre_rank_no           | Text    | 10   | 이전 순위번호      |      |
| reg_purpose_cd        | Text    | 3    | 등기 목적 코드     |      |
| reg_purpose           | Text    | 1000 | 등기 목적        |      |
| sub_reg_purpose_cd    | Text    | 3    | 부기등기 목적 코드   |      |
| sub_reg_purpose       | Text    | 300  | 부기등기 목적      |      |
| accept_date           | Text    | 8    | 접수 날짜        |      |
| accept_no             | Text    | 10   | 접수 일련번호      |      |
| right_cost_close_yn   | Text    | 1    | 권리금액 유효 여부   |      |
| right_cost_type       | Text    | 2    | 권리금액 구분      |      |
| right_cost_type_nm    | Text    | 50   | 권리금액 구분 명    |      |
| right_cost            | Numeric | 20   | 권리금(원화)      |      |
| right_cost_dtl        | Text    | 2000 | 권리금액 상세내용    |      |
| iso4217_cd            | Text    | 3    | ISO4217 통화코드 |      |
| right_person_close_yn | Text    | 1    | 권리자 유효 여부    |      |
| right_type            | Text    | 2    | 권리 구분        |      |
| right_type_nm         | Text    | 30   | 권리 구분 명      |      |
| right_person_type     | Text    | 2    | 권리자 유형       |      |
| right_person_type_nm  | Text    | 20   | 권리자 유형 명     |      |
| right_person_nation   | Text    | 50   | 권리자 국적       |      |
| right_person_nm       | Text    | 100  | 권리자 이름       |      |
| right_person_no       | Text    | 20   | 권리자 고유번호     |      |
| right_person_addr     | Text    | 200  | 권리자 주소       |      |
| manager_type          | Text    | 2    | 관리자 구분       |      |
| manager_type_nm       | Text    | 30   | 관리자 구분 명     |      |
| manager_nm            | Text    | 100  | 관리자 명        |      |
| prsrv_right           | Text    | 1000 | 피보전권리        |      |
| prohibition           | Text    | 200  | 금지사항         |      |
| restrict_cd           | Text    | 3    | 제한사항 코드      |      |
| restrict_nm           | Text    | 50   | 제한사항(간략화)    |      |
| restrict_content      | Text    | 1000 | 제한사항 내용      |      |
| annx_park_loc         | Text    | 200  | 부설주차장 소재지    |      |
| reg_date              | Text    | 8    | 등기날짜         |      |
| obj_owner             | Text    | 256  | 대상소유자        |      |

#### d015103 - 을구 권리자

| 이름                    | 타입      | 길이   | 설명            | 비고   |
| --------------------- | ------- | :--- | :------------ | ---- |
| rett_prp_no           | Text    | 16   | 부동산 고유번호      |      |
| seq                   | Numeric |      | 순번            |      |
| rank_no               | Text    | 10   | 순위번호          |      |
| sub_rank_no           | Text    | 10   | 부기 순위번호       |      |
| pre_rank_no           | Text    | 10   | 이전 순위번호       |      |
| reg_purpose_cd        | Text    | 3    | 등기 목적 코드      |      |
| reg_purpose           | Text    | 1000 | 등기 목적         |      |
| sub_reg_purpose_cd    | Text    | 3    | 부기 등기 목적 코드   |      |
| sub_reg_purpose       | Text    | 300  | 부기 등기 목적      |      |
| accept_date           | Text    | 8    | 접수 날짜         |      |
| accept_no             | Text    | 10   | 접수 일련번호       |      |
| right_cost_close_yn   | Text    | 1    | 권리금액 유효 여부    |      |
| right_cost_type       | Text    | 2    | 권리금액 구분       |      |
| right_cost_type_nm    | Text    | 50   | 권리금액 구분 명     |      |
| right_cost            | Numeric | 20   | 권리금(원화)       |      |
| right_cost_dtl        | Text    | 500  | 권리금액 상세내용     |      |
| iso4217_cd            | Text    | 3    | ISO4217 통화 코드 |      |
| right_person_close_yn | Text    | 1    | 권리자 유효 여부     |      |
| right_type            | Text    | 2    | 권리 구분         |      |
| right_type_nm         | Text    | 30   | 권리 구분 명       |      |
| right_person_type     | Text    | 2    | 권리자 유형        |      |
| right_person_type_nm  | Text    | 20   | 권리자 유형 명      |      |
| right_person_nation   | Text    | 50   | 권리자 국적        |      |
| right_person_nm       | Text    | 100  | 권리자 이름        |      |
| right_person_no       | Text    | 20   | 권리자 고유번호      |      |
| right_person_addr     | Text    | 200  | 권리자 주소        |      |
| manager_type          | Text    | 2    | 관리자 구분        |      |
| manager_type_nm       | Text    | 30   | 관리자 구분 명      |      |
| manager_nm            | Text    | 100  | 관리자 명         |      |
| prsrv_right           | Text    | 1000 | 피보전권리         |      |
| right_purpose         | Text    | 100  | 권리 목적         |      |
| reg_date              | Text    | 8    | 등기 날짜         |      |
| obj_owner             | Text    | 256  | 대상소유자         |      |

<div style="page-break-after: always;"></div>

#### d015104 - 토지의 표시

| 이름                   | 타입      | 길이   | 설명       | 비고   |
| -------------------- | ------- | :--- | :------- | ---- |
| rett_prp_no          | Text    | 16   | 부동산 고유번호 |      |
| seq                  | Numeric |      | 순번       |      |
| land_loc_no          | Text    | 2    | 소재지번번호   |      |
| accept_date          | Text    | 8    | 접수일      |      |
| land_loc             | Text    | 200  | 소재지번     |      |
| land_cls_cd          | Text    | 3    | 지목코드     |      |
| land_cls_nm          | Text    | 30   | 지목       |      |
| land_area_m2         | Text    | 30   | 토지면적_m2  |      |
| land_area_py         | Text    | 30   | 토지면적_평   |      |
| reg_date             | Text    | 8    | 등기날짜     |      |
| right_cls_cd         | Text    | 3    | 대지권구분코드  |      |
| right_cls_nm         | Text    | 30   | 대지권구분명   |      |
| right_share_n        | Text    | 50   | 대지권비율분자  |      |
| right_share_d        | Text    | 50   | 대지권비율분모  |      |
| land_right_caus_date | Text    | 8    | 대지권발생날짜  |      |
| land_right_reg_date  | Text    | 8    | 대지권등기날짜  |      |

<div style="page-break-after: always;"></div>
#### d015105 - 건물의 표시

| 이름                  | 타입      | 길이   | 설명        | 비고   |
| ------------------- | ------- | :--- | :-------- | ---- |
| rett_prp_no         | Text    | 16   | 부동산 고유번호  |      |
| seq                 | Numeric |      | 순번        |      |
| accept_date         | Text    | 8    | 접수날짜      |      |
| doro_addr           | Text    | 200  | 도로명주소     |      |
| jibun_addr          | text    |      | 지번주소      |      |
| bldg_nm             | Text    | 100  | 건물명       |      |
| bldg_detail_text    | text    |      | 건물내역전체텍스트 |      |
| main_cnstrct_strct  | Text    | 50   | 주건축물구조    |      |
| roof_strct          | Text    | 50   | 지붕구조      |      |
| roof_style          | Text    | 50   | 지붕스타일     |      |
| under_floor_cnt     | Text    | 10   | 지하최저층     |      |
| over_floot_cnt      | Text    | 10   | 지상최고층     |      |
| main_usg_cd         | Text    | 3    | 주용도코드     |      |
| main_usg_nm         | Text    | 50   | 주용도       |      |
| total_floor_area_m2 | Text    | 30   | 연면적_m2    |      |
| total_floor_area_py | Text    | 30   | 연면적_py    |      |
| reg_date            | Text    | 8    | 등기날짜      |      |

<div style="page-break-after: always;"></div>
#### d015106 - 전유부분 건물의 표시

| 이름                 | 타입      | 길이   | 설명        | 비고   |
| ------------------ | ------- | :--- | :-------- | ---- |
| rett_prp_no        | Text    | 16   | 부동산 고유번호  |      |
| seq                | Numeric |      | 순번        |      |
| accept_date        | Text    | 8    | 접수날짜      |      |
| flr_nm             | Text    | 30   | 층명        |      |
| ho_nm              | Text    | 50   | 호명        |      |
| bldg_detail_text   | Text    | 1000 | 건물내역전체텍스트 |      |
| main_cnstrct_strct | Text    | 50   | 주건축구조     |      |
| main_usg_cd        | Text    | 3    | 주용도코드     |      |
| main_usg_nm        | Text    | 30   | 주용도       |      |
| etc_usg_nm         | Text    | 100  | 기타용도      |      |
| expos_area_m2      | Text    | 30   | 전유면적_m2   |      |
| expos_area_py      | Text    | 30   | 전유면적_평    |      |
| reg_date           | Text    | 8    | 등기날짜      |      |

#### d015107 - 매매목록

| 이름             | 타입      | 길이   | 설명       | 비고   |
| -------------- | ------- | :--- | :------- | ---- |
| rett_prp_no    | Text    | 16   | 부동산 고유번호 |      |
| seq            | Numeric |      | 순번       |      |
| idx            | Numeric |      | 일련번호     |      |
| trade_list_no  | Text    | 20   | 매매목록번호   |      |
| trade_cost     | Text    | 30   | 거래액      |      |
| addr           | Text    | 200  | 주소       |      |
| rett_type      | Text    | 1    | 부동산구분코드  |      |
| rett_type_nm   | Text    | 10   | 부동산구분명   |      |
| ref_rank_no    | Text    | 20   | 관련순위번호   |      |
| cret_caus_date | Text    | 8    | 생성원인날짜   |      |
| cret_caus_no   | Text    | 10   | 생성접수번호   |      |
| cret_caus      | Text    | 100  | 생성원인     |      |

#### d015108 - 공동담보목록

| 이름             | 타입      | 길이   | 설명       | 비고   |
| -------------- | ------- | :--- | :------- | ---- |
| rett_prp_no    | Text    | 16   | 부동산 고유번호 |      |
| seq            | Numeric |      | 순번       |      |
| idx            | Numeric |      | 일련번호     |      |
| list_no        | Text    | 20   | 공동담보목록번호 |      |
| list_type      | Text    | 1    | 공동담보타입   |      |
| list_type_nm   | Text    | 10   | 공동담보타입명  |      |
| addr           | Text    | 200  | 주소       |      |
| rett_type      | Text    | 1    | 부동산구분코드  |      |
| rett_type_nm   | Text    | 10   | 부동산구분명   |      |
| jurd_rego_cd   | Text    | 4    | 관할등기소코드  |      |
| jurd_rego_nm   | Text    | 50   | 관할등기소명   |      |
| ref_rank_no    | Text    | 20   | 관련순위번호   |      |
| cret_caus_date | Text    | 8    | 생성원인날짜   |      |
| cret_caus_no   | Text    | 10   | 생성접수번호   |      |
| cret_caus      | Text    | 100  | 생성원인     |      |

#### d015109 - 소유자 이력 정보

| 이름            | 타입      | 길이   | 설명       | 비고   |
| ------------- | ------- | :--- | :------- | ---- |
| rett_prp_no   | Text    | 16   | 부동산 고유번호 |      |
| seq           | Numeric |      | 순번       |      |
| sub_seq       | Numeric |      | 내부순번     |      |
| rank_no       | Text    | 10   | 갑구순위번호   |      |
| reg_purpose   | Text    | 4000 | 등기목적     |      |
| reg_caus      | Text    | 1000 | 등기원인     |      |
| accept_date   | Text    | 8    | 등기접수일    |      |
| reg_caus_date | Text    | 8    | 등기원인발생일  |      |
| reg_date      | Text    | 8    | 등기일      |      |
| trade_list_no | Text    | 30   | 매매목록번호   |      |
| trade_cost    | Text    | 30   | 매매금액     |      |
| own_type_nm   | Text    | 30   | 소유구분명    |      |
| own_share_n   | Text    | 50   | 소유지분분자   |      |
| own_share_d   | Text    | 50   | 소유지분분모   |      |
| owner_type_nm | Text    | 30   | 소유자구분명   |      |
| owner_nation  | Text    | 50   | 소유자국적    |      |
| owner_nm      | Text    | 100  | 소유자명     |      |
| owner_no      | Text    | 20   | 소유자고유번호  |      |
| owner_addr    | Text    | 200  | 소유자주소    |      |
| bfr_owner_nm  | Text    | 100  | 이전소유자명   |      |

#### d01511000 - 표제부: 토지의표시

| 이름                    | 타입      | 길이   | 설명         | 비고   |
| --------------------- | ------- | :--- | :--------- | ---- |
| rett_prp_no           | Text    | 16   | 부동산 고유번호   |      |
| seq                   | Numeric |      | 순번         |      |
| idx                   | Text    | 10   | 표시번호       |      |
| pre_idx               | Text    | 10   | 이전 표시번호    |      |
| accept_date           | Text    | 8    | 접수일        |      |
| land_loc              | Text    | 200  | 소재지번       |      |
| land_cls_cd           | Text    | 3    | 지목 코드      |      |
| land_cls_nm           | Text    | 30   | 지목 명       |      |
| land_area_m2          | Text    | 30   | 면적m2       |      |
| land_area_py          | Text    | 30   | 면적 평       |      |
| reg_caus_cd           | Text    | 3    | 등기원인 코드    |      |
| reg_caus_nm           | Text    | 30   | 등기원인 명     |      |
| reg_caus              | Text    | 4000 | 등기원인       |      |
| reg_caus_date         | Text    | 8    | 등기원인 발생날짜  |      |
| reg_date              | Text    | 8    | 등기날짜       |      |
| drawing_no            | Text    | 30   | 도면번호       |      |
| compute_drawing_no    | Text    | 30   | 전산도면번호     |      |
| drawing_book_no       | Text    | 30   | 도면편철장      |      |
| replot_drawing_no     | Text    | 30   | 사업확정도      |      |
| sub_right_caus        | Text    | 100  | 대위원인       |      |
| trnscrptn_caus_cd     | Text    | 3    | 이기원인 코드    |      |
| trnscrptn_caus_nm     | Text    | 30   | 이기원인       |      |
| trnscrptn_accept_no   | Text    | 30   | 이기 접수번호    |      |
| trnscrptn_info        | Text    | 500  | 이기 내용      |      |
| trnscrptn_accept_date | Text    | 8    | 이기 접수날짜    |      |
| trnscrptn_reg_date    | Text    | 8    | 이기 등기날짜    |      |
| cmptriztion_info      | Text    | 500  | 전산이기 관련 정보 |      |
| close_yn              | Text    | 1    | 등기말소여부     |      |

#### d01511001 - 표제부: 토지의 표시 중 소재지 리스트

| 이름           | 타입      | 길이   | 설명       | 비고   |
| ------------ | ------- | :--- | :------- | ---- |
| rett_prp_no  | Text    | 16   | 부동산 고유번호 |      |
| seq          | Numeric |      | 순번       |      |
| sub_seq      | Numeric |      | 서브순번     |      |
| land_loc     | Text    | 200  | 소재지번     |      |
| land_cls_cd  | Text    | 3    | 지목 코드    |      |
| land_cls_nm  | Text    | 30   | 지목 명     |      |
| land_area_m2 | Text    | 30   | 면적m2     |      |
| land_area_py | Text    | 30   | 면적 평     |      |
| close_yn     | Text    | 1    | 등기말소여부   |      |

#### d01511100 - 표제부: 건물의표시

| 이름                    | 타입      | 길이   | 설명         | 비고   |
| --------------------- | ------- | :--- | :--------- | ---- |
| rett_prp_no           | Text    | 16   | 부동산 고유번호   |      |
| seq                   | Numeric |      | 순번         |      |
| idx                   | Text    | 10   | 표시번호       |      |
| pre_idx               | Text    | 10   | 이전 표시번호    |      |
| accept_date           | Text    | 8    | 접수 날짜      |      |
| doro_addr             | Text    | 200  | 도로명 주소     |      |
| jibun_addr            | text    |      | 지번 주소      |      |
| bldg_nm               | Text    | 100  | 건물 명       |      |
| bldg_detail_text      | text    |      | 건물내역전체텍스트  |      |
| main_cnstrct_strct    | Text    | 50   | 주 건축물 구조   |      |
| roof_strct            | Text    | 50   | 지붕 구조      |      |
| roof_style            | Text    | 50   | 지붕 스타일     |      |
| under_floor_cnt       | Text    | 10   | 지하 최저층     |      |
| over_floot_cnt        | Text    | 10   | 지상 최고층     |      |
| main_usg_cd           | Text    | 3    | 주 용도 코드    |      |
| main_usg_nm           | Text    | 30   | 주 용도       |      |
| total_floor_area_m2   | Text    | 30   | 총연면적_m2    |      |
| total_floor_area_py   | Text    | 30   | 총연면적_평     |      |
| reg_caus_cd           | Text    | 3    | 등기원인 코드    |      |
| reg_caus_nm           | Text    | 30   | 등기원인 명     |      |
| reg_caus              | Text    | 4000 | 등기원인       |      |
| reg_caus_date         | Text    | 8    | 등기원인 발생날짜  |      |
| reg_date              | Text    | 8    | 등기날짜       |      |
| drawing_no            | Text    | 30   | 도면번호       |      |
| compute_drawing_no    | Text    | 30   | 전산도면번호     |      |
| drawing_book_no       | Text    | 30   | 도면편철장      |      |
| sub_right_caus        | Text    | 200  | 대위원인       |      |
| trnscrptn_caus_cd     | Text    | 3    | 이기원인코드     |      |
| trnscrptn_caus_nm     | Text    | 30   | 이기원인       |      |
| trnscrptn_accept_no   | Text    | 30   | 이기 접수번호    |      |
| trnscrptn_info        | Text    | 500  | 이기내용       |      |
| trnscrptn_accept_date | Text    | 8    | 이기접수날짜     |      |
| trnscrptn_reg_date    | Text    | 8    | 이기등기날짜     |      |
| cmptriztion_info      | Text    | 500  | 전산이기 관련 정보 |      |
| close_yn              | Text    | 1    | 등기 말소 여부   |      |

<div style="page-break-after: always;"></div>
#### d01511101 - 표제부: 층별정보

| 이름                 | 타입      | 길이   | 설명       | 비고   |
| ------------------ | ------- | :--- | :------- | ---- |
| rett_prp_no        | Text    | 16   | 부동산 고유번호 |      |
| seq                | Numeric |      | 순번       |      |
| sub_seq            | Numeric |      | 서브순번     |      |
| under_floor_cnt    | Text    | 10   | 지하최저층    |      |
| over_floot_cnt     | Text    | 10   | 지상최대층    |      |
| dong_nm            | Text    | 50   | 동명       |      |
| flr_nm             | Text    | 30   | 층명       |      |
| main_cnstrct_strct | Text    | 50   | 주 건축물 구조 |      |
| roof_strct         | Text    | 50   | 지붕 구조    |      |
| roof_style         | Text    | 50   | 지붕 스타일   |      |
| area_m2            | Text    | 30   | 면적_m2    |      |
| area_py            | Text    | 30   | 면적_평     |      |
| main_usg_cd        | Text    | 3    | 주 용도 코드  |      |
| main_usg_nm        | Text    | 50   | 주 용도     |      |
| etc_usg_nm         | Text    | 100  | 기타 용도    |      |

<div style="page-break-after: always;"></div>
#### d01511200 - 표제부: 토지의표시(집합건물)

| 이름                    | 타입      | 길이   | 설명       | 비고   |
| --------------------- | ------- | :--- | :------- | ---- |
| rett_prp_no           | Text    | 16   | 부동산 고유번호 |      |
| seq                   | Numeric |      | 순번       |      |
| idx                   | Text    | 10   | 표시번호     |      |
| pre_idx               | Text    | 10   | 이전표시번호   |      |
| reg_caus_cd           | Text    | 3    | 등기원인코드   |      |
| reg_caus_nm           | Text    | 30   | 등기원인명    |      |
| reg_caus              | Text    | 4000 | 등기원인     |      |
| reg_caus_date         | Text    | 8    | 등기원인발생날짜 |      |
| reg_date              | Text    | 8    | 등기날짜     |      |
| sub_right_caus        | Text    | 200  | 대위원인     |      |
| trnscrptn_caus_cd     | Text    | 3    | 이기원인코드   |      |
| trnscrptn_caus_nm     | Text    | 50   | 이기원인     |      |
| trnscrptn_accept_no   | Text    | 50   | 이기접수번호   |      |
| trnscrptn_info        | Text    | 500  | 이기내용     |      |
| trnscrptn_accept_date | Text    | 8    | 이기접수날짜   |      |
| trnscrptn_reg_date    | Text    | 8    | 이기등기날짜   |      |
| cmptriztion_info      | Text    | 500  | 전산이기관련정보 |      |
| close_yn              | Text    | 1    | 등기말소여부   |      |

<div style="page-break-after: always;"></div>
#### d01511201 - 표제부: 토지의표시(집합건물) 상세

| 이름           | 타입      | 길이   | 설명       | 비고   |
| ------------ | ------- | :--- | :------- | ---- |
| rett_prp_no  | Text    | 16   | 부동산 고유번호 |      |
| seq          | Numeric |      | 순번       |      |
| sub_seq      | Numeric |      | 내부순번     |      |
| land_loc_no  | Text    | 10   | 소재지번번호   |      |
| land_loc     | Text    | 200  | 소재지번     |      |
| land_cls_cd  | Text    | 3    | 지목코드     |      |
| land_cls_nm  | Text    | 30   | 지목명      |      |
| land_area_m2 | Text    | 30   | 토지면적_m2  |      |
| land_area_py | Text    | 30   | 토지면적_평   |      |
| close_yn     | Text    | 1    | 등기말소여부   |      |

#### d01511300 - 표제부: 전유부분의 건물의 표시(집합건물)

| 이름                    | 타입      | 길이   | 설명         | 비고   |
| --------------------- | ------- | :--- | :--------- | ---- |
| rett_prp_no           | Text    | 16   | 부동산 고유번호   |      |
| seq                   | Numeric |      | 순번         |      |
| idx                   | Text    | 10   | 표시번호       |      |
| pre_idx               | Text    | 10   | 이전표시번호     |      |
| accept_date           | Text    | 8    | 접수날짜       |      |
| expos_no_text         | Text    | 400  | 건물번호전체텍스트  |      |
| flr_nm                | Text    | 50   | 층명         |      |
| ho_nm                 | Text    | 50   | 호명         |      |
| bldg_detail_text      | Text    | 1000 | 건물내역전체텍스트  |      |
| main_cnstrct_strct    | Text    | 50   | 주 건축구조     |      |
| main_usg_cd           | Text    | 3    | 주용도코드      |      |
| main_usg_nm           | Text    | 30   | 주용도명       |      |
| expos_area_m2         | Text    | 30   | 전유면적_m2    |      |
| expos_area_py         | Text    | 30   | 전유면적_py    |      |
| reg_caus_cd           | Text    | 3    | 등기원인코드     |      |
| reg_caus_nm           | Text    | 50   | 등기원인명      |      |
| reg_caus              | Text    | 4000 | 등기원인       |      |
| reg_caus_date         | Text    | 8    | 등기원인 발생날짜  |      |
| reg_date              | Text    | 8    | 등기날짜       |      |
| sub_right_caus        | Text    | 200  | 대위원인       |      |
| drawing_no            | Text    | 50   | 도면번호       |      |
| compute_drawing_no    | Text    | 50   | 전산도면번호     |      |
| drawing_book_no       | Text    | 50   | 도면편철장      |      |
| trnscrptn_caus_cd     | Text    | 3    | 이기원인코드     |      |
| trnscrptn_caus_nm     | Text    | 50   | 이기원인       |      |
| trnscrptn_accept_no   | Text    | 50   | 이기 접수번호    |      |
| trnscrptn_info        | Text    | 500  | 이기내용       |      |
| trnscrptn_accept_date | Text    | 8    | 이기 접수날짜    |      |
| trnscrptn_reg_date    | Text    | 8    | 이기 등기날짜    |      |
| cmptriztion_info      | Text    | 500  | 전산이기 관련 정보 |      |
| close_yn              | Text    | 1    | 등기말소여부     |      |

#### d01511301 - 표제부: 전유부분의 건물의 표시 상세(복층등)

| 이름                 | 타입       | 길이   | 설명       | 비고   |
| ------------------ | -------- | :--- | :------- | ---- |
| rett_prp_no        | Text     | 16   | 부동산 고유번호 |      |
| seq                | smallint |      | 순번       |      |
| sub_seq            | integer  |      | 서브순번     |      |
| flr_nm             | Text     | 50   | 층명       |      |
| ho_nm              | Text     | 50   | 호명       |      |
| floor_cls_cd       | Text     | 3    | 층구분코드    |      |
| floor_cls_nm       | Text     | 10   | 층구분      |      |
| area_m2            | Text     | 30   | 전유면적_m2  |      |
| area_py            | Text     | 30   | 전유면적_평   |      |
| main_usg_cd        | Text     | 3    | 주용도코드    |      |
| main_usg_nm        | Text     | 30   | 주용도      |      |
| main_cnstrct_strct | Text     | 50   | 주건축구조    |      |
| etc_usg_nm         | Text     | 100  | 기타용도     |      |

#### d01511400 - 표제부: 대지권의 표시(집합)

| 이름                    | 타입      | 길이   | 설명       | 비고   |
| --------------------- | ------- | :--- | :------- | ---- |
| rett_prp_no           | Text    | 16   | 부동산 고유번호 |      |
| seq                   | Numeric |      | 순번       |      |
| idx                   | Text    | 10   | 표시번호     |      |
| pre_idx               | Text    | 10   | 이전표시번호   |      |
| right_cls_cd          | Text    | 3    | 대지권구분코드  |      |
| right_cls_nm          | Text    | 20   | 대지권구분명   |      |
| reg_caus_cd           | Text    | 3    | 등기원인코드   |      |
| reg_caus_nm           | Text    | 50   | 등기원인명    |      |
| reg_caus              | Text    | 4000 | 등기원인     |      |
| reg_caus_date         | Text    | 8    | 등기원인발생날짜 |      |
| reg_date              | Text    | 8    | 등기날짜     |      |
| drawing_no            | Text    | 50   | 도면번호     |      |
| compute_drawing_no    | Text    | 50   | 전산도면번호   |      |
| drawing_book_no       | Text    | 50   | 도면편철장    |      |
| sub_right_caus        | Text    | 200  | 대위원인     |      |
| trnscrptn_caus_cd     | Text    | 3    | 이기원인코드   |      |
| trnscrptn_caus_nm     | Text    | 50   | 이기원인     |      |
| trnscrptn_accept_no   | Text    | 50   | 이기접수번호   |      |
| trnscrptn_info        | Text    | 500  | 이기내용     |      |
| trnscrptn_accept_date | Text    | 8    | 이기접수날짜   |      |
| trnscrptn_reg_date    | Text    | 8    | 이기등기날짜   |      |
| cmptriztion_info      | Text    | 500  | 전산이기관련정보 |      |
| close_yn              | Text    | 1    | 등기말소여부   |      |

<div style="page-break-after: always;"></div>
#### d01511401 - 표제부: 대지권의 표시 상세

| 이름               | 타입      | 길이   | 설명            | 비고   |
| ---------------- | ------- | :--- | :------------ | ---- |
| rett_prp_no      | Text    | 16   | 부동산 고유번호      |      |
| seq              | Numeric |      | 순번            |      |
| sub_seq          | Numeric |      | 내부순번          |      |
| land_loc_no      | Text    | 10   | 소재지번번호        |      |
| cnstract_type    | Text    | 3    | 주부속구분코드       |      |
| cnstract_type_nm | Text    | 10   | 주부속구분명        |      |
| right_share_n    | Text    | 50   | 대지권비율분자       |      |
| right_share_d    | Text    | 50   | 대지권비율분모       |      |
| right_share_n_fp | Text    | 50   | 대지권비율분자_부동소수점 |      |
| right_share_d_fp | Text    | 50   | 대지권비율분모_부동소수점 |      |
| land_right_date  | Text    | 8    | 대지권발생날짜       |      |
| close_yn         | Text    | 1    | 대지권말소여부       |      |

#### d01512000 - 갑구: 기본정보

| 이름                    | 타입      | 길이   | 설명        | 비고   |
| --------------------- | ------- | :--- | :-------- | ---- |
| rett_prp_no           | Text    | 16   | 부동산 고유번호  |      |
| seq                   | Numeric |      | 순번        |      |
| rank_no               | Text    | 10   | 순위번호      |      |
| sub_rank_no           | Text    | 10   | 부기순위번호    |      |
| pre_rank_no           | Text    | 10   | 이전순위번호    |      |
| ref_rank_no           | Text    | 100  | 관련순위번호    |      |
| add_ref_rank_no       | Text    | 100  | 추가관련순위번호  |      |
| reg_purpose_cd        | Text    | 3    | 등기목적코드    |      |
| reg_purpose_nm        | Text    | 50   | 등기목적명     |      |
| reg_purpose           | Text    | 4000 | 등기목적      |      |
| accept_date           | Text    | 8    | 접수일       |      |
| accept_no             | Text    | 20   | 접수번호      |      |
| reg_caus_cd           | Text    | 3    | 등기원인코드    |      |
| reg_caus_nm           | Text    | 30   | 등기원인명     |      |
| reg_caus              | Text    | 1000 | 등기원인      |      |
| reg_caus_dtl          | Text    | 4000 | 등기원인상세    |      |
| reg_caus_date         | Text    | 8    | 등기원인발생일   |      |
| reg_date              | Text    | 8    | 등기일       |      |
| sub_reg_purpose_cd    | Text    | 3    | 부기등기목적코드  |      |
| sub_reg_purpose_nm    | Text    | 50   | 부기등기목적명   |      |
| sub_reg_purpose       | Text    | 1000 | 부기등기목적    |      |
| sub_accept_date       | Text    | 8    | 부기접수일     |      |
| sub_accept_no         | Text    | 20   | 부기접수번호    |      |
| sub_reg_caus_cd       | Text    | 3    | 부기등기원인코드  |      |
| sub_reg_caus_nm       | Text    | 50   | 부기등기원인명   |      |
| sub_reg_caus          | Text    | 4000 | 부기등기원인    |      |
| sub_reg_caus_dtl      | Text    | 1000 | 부기등기원인상세  |      |
| sub_reg_caus_date     | Text    | 8    | 부기등기원인발생일 |      |
| sub_reg_date          | Text    | 8    | 부기등기일     |      |
| prohibition           | Text    | 500  | 금지사항      |      |
| case_no               | Text    | 50   | 사건번호      |      |
| trust_doc_no          | Text    | 50   | 신탁원부번호    |      |
| trust_reg_yn          | Text    | 1    | 신탁등기여부    |      |
| close_yn              | Text    | 1    | 말소여부      |      |
| bldg_info_yn          | Text    | 1    | 건물의표시정보유무 |      |
| bldg_mark_info        | Text    | 4000 | 건물의표시정보   |      |
| own_info_yn           | Text    | 1    | 소유정보유무    |      |
| right_info_yn         | Text    | 1    | 소유권외권리유무  |      |
| sub_right_caus        | Text    | 300  | 대위원인      |      |
| trnscrptn_caus_cd     | Text    | 3    | 이기원인코드    |      |
| trnscrptn_caus_nm     | Text    | 50   | 이기원인      |      |
| trnscrptn_accept_no   | Text    | 30   | 이기접수번호    |      |
| trnscrptn_info        | Text    | 1000 | 이기내용      |      |
| trnscrptn_accept_date | Text    | 8    | 이기접수날짜    |      |
| trnscrptn_reg_date    | Text    | 8    | 이기등기날짜    |      |
| cmptriztion_info      | Text    | 1000 | 전산이기관련정보  |      |
| special_contract      | Text    | 500  | 특약        |      |
| restrict_content      | Text    | 1000 | 제한사항      |      |
| share_cancel_text     | Text    | 4000 | 말소할지분관련내용 |      |
| seizure_share_text    | Text    | 1000 | 압류할지분관련내용 |      |
| parking_lot_content   | Text    | 500  | 부설주차장관련내용 |      |
| add_reg_purpose_cd    | Text    | 3    | 추가등기목적코드  |      |
| add_reg_purpose_nm    | Text    | 50   | 추가등기목적명   |      |
| add_reg_purpose       | Text    | 1000 | 추가등기목적    |      |
| add_accept_date       | Text    | 8    | 추가등기접수일   |      |
| add_accept_no         | Text    | 20   | 추가등기접수번호  |      |
| add_reg_caus_cd       | Text    | 3    | 추가등기원인코드  |      |
| add_reg_caus_nm       | Text    | 50   | 추가등기원인명   |      |
| add_reg_caus          | Text    | 4000 | 추가등기원인    |      |
| add_reg_caus_dtl      | Text    | 1000 | 추가등기원인상세  |      |
| add_reg_caus_date     | Text    | 8    | 추가등기원인발생일 |      |
| add_reg_date          | Text    | 8    | 추가등기일     |      |

#### d01512001 - 갑구: 등기 목적 상세(지분별 상세)

| 이름            | 타입      | 길이   | 설명         | 비고   |
| ------------- | ------- | :--- | :--------- | ---- |
| rett_prp_no   | Text    | 16   | 부동산 고유번호   |      |
| seq           | Numeric |      | 순번         |      |
| sub_seq       | Numeric |      | 서브순번       |      |
| ref_rank_no   | Text    | 30   | 관련순위번호     |      |
| owner_type    | Text    | 3    | 소유자구분      |      |
| owner_type_nm | Text    | 30   | 소유자구분명     |      |
| ref_owner_nm  | Text    | 100  | 관련소유자명     |      |
| own_share_n   | Text    | 50   | 소유자지분분자    |      |
| own_shard_d   | Text    | 50   | 소유자지분분모    |      |
| trd_share_n   | Text    | 50   | 거래지분분자     |      |
| trd_share_d   | Text    | 50   | 거래지분분모     |      |
| share_text    | Text    | 100  | 관련지분 전체텍스트 |      |

#### d01512100 - 갑구: 소유정보

| 이름                    | 타입      | 길이   | 설명       | 비고   |
| --------------------- | ------- | :--- | :------- | ---- |
| rett_prp_no           | Text    | 16   | 부동산 고유번호 |      |
| seq                   | Numeric |      | 순번       |      |
| own_type              | Text    | 3    | 소유형태     |      |
| own_type_nm           | Text    | 30   | 소유형태명    |      |
| trade_list_no         | Text    | 30   | 매매목록번호   |      |
| trade_cost            | Text    | 30   | 거래가액     |      |
| ref_rank_no           | Text    | 30   | 관련순위번호   |      |
| special_contract_text | Text    | 500  | 특약       |      |
| laps_contract_text    | Text    | 500  | 소멸약정     |      |

<div style="page-break-after: always;"></div>
#### d01512101 - 갑구: 소유자 정보

| 이름                  | 타입      | 길이   | 설명       | 비고   |
| ------------------- | ------- | :--- | :------- | ---- |
| rett_prp_no         | Text    | 16   | 부동산 고유번호 |      |
| seq                 | Numeric |      | 순번       |      |
| sub_seq             | Numeric |      | 내부순번     |      |
| own_share_n         | Text    | 50   | 소유지분분자   |      |
| own_share_d         | Text    | 50   | 소유지분분모   |      |
| owner_type          | Text    | 3    | 소유자유형    |      |
| owner_type_nm       | Text    | 30   | 소유자유형명   |      |
| owner_nation        | Text    | 50   | 소유자국적    |      |
| owner_nm            | Text    | 100  | 소유자명     |      |
| owner_no            | Text    | 20   | 소유자생년월일등 |      |
| owner_addr          | Text    | 200  | 소유자주소    |      |
| jijum_nm            | Text    | 30   | 지점명      |      |
| manager_type        | Text    | 3    | 관리자구분    |      |
| manager_type_nm     | Text    | 30   | 관리자구분명   |      |
| manager_nation      | Text    | 50   | 관리자국적    |      |
| manager_nm          | Text    | 100  | 관리자명     |      |
| manager_no          | Text    | 30   | 관리자생년월일등 |      |
| manager_addr        | Text    | 200  | 관리자주소    |      |
| bfr_owner_nm        | Text    | 100  | 변경전소유자명  |      |
| bfr_manager_nm      | Text    | 100  | 변경전관리자명  |      |
| owner_nm_close_yn   | Text    | 1    | 소유자명말소여부 |      |
| owner_no_close_yn   | Text    | 1    | 생년월일말소여부 |      |
| owner_addr_close_yn | Text    | 1    | 주소말소여부   |      |
| manager_close_yn    | Text    | 1    | 관리자말소여부  |      |
| close_yn            | Text    | 1    | 말소여부     |      |

#### d01512200 - 갑구: 소유권 외 권리정보

| 이름                 | 타입      | 길이   | 설명          | 비고   |
| ------------------ | ------- | :--- | :---------- | ---- |
| rett_prp_no        | Text    | 16   | 부동산 고유번호    |      |
| seq                | Numeric |      | 순번          |      |
| right_type         | Text    | 3    | 권리구분        |      |
| right_type_nm      | Text    | 30   | 권리구분명       |      |
| right_cost_type    | Text    | 3    | 권리금액구분      |      |
| right_cost_type_nm | Text    | 30   | 권리금액구분명     |      |
| foreign_right_cost | Text    | 200  | 외화전체금액      |      |
| right_cost_dtl     | Text    | 1000 | 권리금액예외      |      |
| right_cost         | Text    | 30   | 총권리금액       |      |
| iso4217_cd         | Text    | 4    | ISO4217통화코드 |      |
| prsrv_right        | Text    | 1000 | 피보전권리       |      |
| prohibition        | Text    | 500  | 금지사항        |      |
| restrict_content   | Text    | 500  | 제한사항        |      |
| sub_right_caus     | Text    | 300  | 대위원인        |      |
| repurchas_duration | Text    | 100  | 환매기간        |      |
| repurchas_expense  | Text    | 100  | 환매계약비용      |      |
| repurchas_content  | Text    | 100  | 환매내용        |      |
| interest           | Text    | 100  | 이자          |      |

#### d01512201 - 갑구: 소유권 외 권리 권리자

| 이름                         | 타입      | 길이   | 설명        | 비고   |
| -------------------------- | ------- | :--- | :-------- | ---- |
| rett_prp_no                | Text    | 16   | 부동산 고유번호  |      |
| seq                        | Numeric |      | 순번        |      |
| sub_seq                    | Numeric |      | 내부순번      |      |
| right_share_n              | Text    | 50   | 권리지분분자    |      |
| right_share_d              | Text    | 50   | 권리지분분모    |      |
| right_cost                 | Text    | 30   | 권리자권리금액   |      |
| right_person_type          | Text    | 3    | 권리자구분     |      |
| right_person_type_nm       | Text    | 50   | 권리자구분명    |      |
| right_person_nation        | Text    | 50   | 권리자국적     |      |
| right_person_nm            | Text    | 100  | 권리자명      |      |
| right_person_no            | Text    | 30   | 권리자번호     |      |
| right_person_addr          | Text    | 200  | 권리자주소     |      |
| jijum_nm                   | Text    | 50   | 권리자지점명    |      |
| manager_type               | Text    | 3    | 관리자구분     |      |
| manager_type_nm            | Text    | 50   | 관리자구분명    |      |
| manager_nation             | Text    | 50   | 관리자국적     |      |
| manager_nm                 | Text    | 100  | 관리자명      |      |
| manager_no                 | Text    | 50   | 관리자번호     |      |
| manager_addr               | Text    | 200  | 관리자주소     |      |
| bfr_right_prsn_nm          | Text    | 100  | 변경전소유자명   |      |
| bfr_manager_nm             | Text    | 100  | 변경전관리자명   |      |
| right_person_nm_close_yn   | Text    | 1    | 권리자명말소여부  |      |
| right_person_no_close_yn   | Text    | 1    | 권리자번호말소여부 |      |
| right_person_addr_close_yn | Text    | 1    | 권리자주소말소여부 |      |
| manager_close_yn           | Text    | 1    | 관리자말소여부   |      |
| close_yn                   | Text    | 1    | 권리자말소여부   |      |

#### d01512202 - 갑구: 소유권 외 권리 권리금

| 이름                  | 타입      | 길이   | 설명        | 비고   |
| ------------------- | ------- | :--- | :-------- | ---- |
| rett_prp_no         | Text    | 16   | 부동산 고유번호  |      |
| seq                 | Numeric |      | 순번        |      |
| sub_seq             | Numeric |      | 내부순번      |      |
| right_cls_cd        | Text    | 3    | 권리유형      |      |
| right_cls_nm        | Text    | 50   | 권리유형명     |      |
| right_person_nm     | Text    | 100  | 권리자명      |      |
| right_cost          | Text    | 30   | 권리금액      |      |
| iso4217_cd          | Text    | 4    | ISO4217코드 |      |
| right_cost_close_yn | Text    | 1    | 권리금액말소여부  |      |

#### d01512300 - 갑구: 주차장 정보

| 이름          | 타입      | 길이   | 설명       | 비고   |
| ----------- | ------- | :--- | :------- | ---- |
| rett_prp_no | Text    | 16   | 부동산 고유번호 |      |
| seq         | Numeric |      | 순번       |      |
| sub_seq     | Numeric |      | 내부순번     |      |
| parking_loc | Text    | 200  | 주차장소재지   |      |

#### 01513000 - 을구: 기본정보

| 이름                    | 타입      | 길이   | 설명        | 비고   |
| --------------------- | ------- | :--- | :-------- | ---- |
| rett_prp_no           | Text    | 16   | 부동산 고유번호  |      |
| seq                   | Numeric |      | 순번        |      |
| rank_no               | Text    | 10   | 순위번호      |      |
| sub_rank_no           | Text    | 10   | 부기순위번호    |      |
| pre_rank_no           | Text    | 10   | 이전순위번호    |      |
| ref_rank_no           | Text    | 100  | 관련순위번호    |      |
| add_ref_rank_no       | Text    | 100  | 추가관련순위번호  |      |
| reg_purpose_cd        | Text    | 3    | 등기목적코드    |      |
| reg_purpose_nm        | Text    | 50   | 등기목적명     |      |
| reg_purpose           | Text    | 4000 | 등기목적      |      |
| accept_date           | Text    | 8    | 접수일       |      |
| accept_no             | Text    | 20   | 접수번호      |      |
| reg_caus_cd           | Text    | 3    | 등기원인코드    |      |
| reg_caus_nm           | Text    | 50   | 등기원인명     |      |
| reg_caus              | Text    | 300  | 등기원인      |      |
| reg_caus_dtl          | Text    | 4000 | 등기원인상세    |      |
| reg_caus_date         | Text    | 8    | 등기원인발생일   |      |
| reg_date              | Text    | 8    | 등기날짜      |      |
| sub_reg_purpose_cd    | Text    | 3    | 부기등기목적코드  |      |
| sub_reg_purpose_nm    | Text    | 50   | 부기등기목적명   |      |
| sub_reg_purpose       | Text    | 1000 | 부기등기목적    |      |
| sub_accept_date       | Text    | 8    | 부기접수일     |      |
| sub_accept_no         | Text    | 20   | 부기접수번호    |      |
| sub_reg_caus_cd       | Text    | 3    | 부기등기원인코드  |      |
| sub_reg_caus_nm       | Text    | 50   | 부기등기원인명   |      |
| sub_reg_caus          | Text    | 4000 | 부기등기원인    |      |
| sub_reg_caus_date     | Text    | 8    | 부기등기원인발생일 |      |
| sub_reg_caus_dtl      | Text    | 4000 | 부기등기원인상세  |      |
| sub_reg_date          | Text    | 8    | 부기등기일     |      |
| case_no               | Text    | 50   | 사건번호      |      |
| close_yn              | Text    | 1    | 말소여부      |      |
| right_info_yn         | Text    | 1    | 을구권리유무    |      |
| sub_right_caus        | Text    | 300  | 대위원인      |      |
| trnscrptn_caus_cd     | Text    | 3    | 이기원인코드    |      |
| trnscrptn_caus_nm     | Text    | 50   | 이기원인      |      |
| trnscrptn_accept_no   | Text    | 30   | 이기접수번호    |      |
| trnscrptn_info        | Text    | 2000 | 이기내용      |      |
| trnscrptn_accept_date | Text    | 8    | 이기접수날짜    |      |
| trnscrptn_reg_date    | Text    | 8    | 이기등기날짜    |      |
| trust_doc_no          | Text    | 30   | 신탁원부번호    |      |
| trust_reg_yn          | Text    | 1    | 신탁등기여부    |      |
| cmptriztion_info      | Text    | 4000 | 전산이기관련정보  |      |

#### d01513100 - 을구: 권리정보

| 이름                       | 타입       | 길이   | 설명        | 비고   |
| ------------------------ | -------- | :--- | :-------- | ---- |
| rett_prp_no              | Text     | 16   | 부동산 고유번호  |      |
| seq                      | smallint |      | 순번        |      |
| right_type               | Text     | 3    | 권리구분      |      |
| right_type_nm            | Text     | 50   | 권리구분명     |      |
| right_cost_type          | Text     | 3    | 권리금액구분    |      |
| right_cost_type_nm       | Text     | 50   | 권리금액구분명   |      |
| foreign_right_cost       | Text     | 200  | 외화전체금액    |      |
| right_cost_dtl           | Text     | 500  | 권리금액상세    |      |
| right_cost               | Text     | 30   | 총권리금액     |      |
| iso4217_cd               | Text     | 4    | ISO4217코드 |      |
| preserve_right           | Text     | 1000 | 피보전권리     |      |
| prohibition              | Text     | 300  | 금지사항      |      |
| sub_right_caus           | Text     | 300  | 대위원인      |      |
| srvnt_estt_loc           | Text     | 200  | 승역지소재지    |      |
| right_purpose            | Text     | 100  | 권리목적      |      |
| right_range              | Text     | 4000 | 권리범위      |      |
| right_duration           | Text     | 300  | 권리존속기간    |      |
| return_date              | Text     | 100  | 반환기       |      |
| land_fee                 | Text     | 100  | 지료        |      |
| land_fee_pay_time        | Text     | 100  | 지료지급시기    |      |
| lease_fee                | Text     | 1000 | 임차료       |      |
| lease_fee_pay_time       | Text     | 100  | 차임지급시기    |      |
| repayment_date           | Text     | 100  | 변제기       |      |
| interest                 | Text     | 100  | 이자        |      |
| interest_pay_time        | Text     | 100  | 이자지급시기    |      |
| interest_pay_loc         | Text     | 100  | 원본및이자지급장소 |      |
| contract_date            | Text     | 300  | 임대차계약일자   |      |
| resident_reg_date        | Text     | 100  | 주민등록일자    |      |
| business_reg_date        | Text     | 100  | 사업자등록신청일자 |      |
| possess_start_date       | Text     | 100  | 점유개시일     |      |
| confirmed_date           | Text     | 500  | 확정일자      |      |
| special_contract         | Text     | 1000 | 특약        |      |
| joint_security_list_text | Text     | 400  | 공동담보목록텍스트 |      |
| joint_security_list_no   | Text     | 50   | 공동담보목록번호  |      |
| plant_security_list_no   | Text     | 50   | 공장및광업재단목록 |      |
| machine_security_list_no | Text     | 50   | 기계목록      |      |
| drawing_no               | Text     | 50   | 도면번호      |      |
| compute_drawing_no       | Text     | 50   | 전산도면번호    |      |
| drawing_book_text        | Text     | 50   | 도면편철장     |      |
| close_yn                 | Text     | 1    | 권리말소여부    |      |

#### d01513101 - 을구: 권리자

| 이름                         | 타입      | 길이   | 설명        | 비고   |
| -------------------------- | ------- | :--- | :-------- | ---- |
| rett_prp_no                | Text    | 16   | 부동산 고유번호  |      |
| seq                        | Numeric |      | 순번        |      |
| sub_seq                    | Numeric |      | 내부순번      |      |
| right_share_n              | Text    | 50   | 권리지분분자    |      |
| right_share_d              | Text    | 50   | 권리지분분모    |      |
| right_person_type          | Text    | 3    | 권리자구분     |      |
| right_person_type_nm       | Text    | 50   | 권리자구분명    |      |
| right_person_nation        | Text    | 50   | 권리자국적     |      |
| right_person_nm            | Text    | 100  | 권리자명      |      |
| right_person_no            | Text    | 30   | 권리자번호     |      |
| right_person_addr          | Text    | 200  | 권리자주소     |      |
| jijum_nm                   | Text    | 50   | 권리자지점명    |      |
| manager_type               | Text    | 3    | 관리자구분     |      |
| manager_type_nm            | Text    | 30   | 관리자구분명    |      |
| manager_nation             | Text    | 50   | 관리자국적     |      |
| manager_nm                 | Text    | 100  | 관리자명      |      |
| manager_no                 | Text    | 30   | 관리자번호     |      |
| manager_addr               | Text    | 200  | 관리자주소     |      |
| bfr_right_prsn_nm          | Text    | 100  | 변경전소유자명   |      |
| bfr_manager_nm             | Text    | 100  | 변경전관리자명   |      |
| right_person_nm_close_yn   | Text    | 1    | 권리자명말소여부  |      |
| right_person_no_close_yn   | Text    | 1    | 권리자번호말소여부 |      |
| right_person_addr_close_yn | Text    | 1    | 권리자주소말소여부 |      |
| manager_close_yn           | Text    | 1    | 관리자말소여부   |      |
| close_yn                   | Text    | 1    | 말소여부      |      |

#### d01513102 - 을구: 권리금액

| 이름                  | 타입      | 길이   | 설명        | 비고   |
| ------------------- | ------- | :--- | :-------- | ---- |
| rett_prp_no         | Text    | 16   | 부동산 고유번호  |      |
| seq                 | Numeric |      | 순번        |      |
| sub_seq             | Numeric |      | 내부순번      |      |
| right_cls_cd        | Text    | 3    | 권리유형      |      |
| right_cls_nm        | Text    | 30   | 권리유형명     |      |
| right_person_nm     | Text    | 100  | 권리자명      |      |
| right_cost          | Text    | 30   | 권리금액      |      |
| iso4217_cd          | Text    | 4    | ISO4217코드 |      |
| right_cost_close_yn | Text    | 1    | 말소여부      |      |

#### d01513103 - 을구: 채무자

| 이름                   | 타입      | 길이   | 설명        | 비고   |
| -------------------- | ------- | :--- | :-------- | ---- |
| rett_prp_no          | Text    | 16   | 부동산 고유번호  |      |
| seq                  | Numeric |      | 순번        |      |
| sub_seq              | Numeric |      | 내부순번      |      |
| debtor_type          | Text    | 3    | 채무자구분     |      |
| debtor_type_nm       | Text    | 30   | 채무자구분명    |      |
| debtor_nation        | Text    | 50   | 채무자국적     |      |
| debtor_nm            | Text    | 100  | 채무자명      |      |
| debtor_no            | Text    | 30   | 채무자번호     |      |
| debtor_addr          | Text    | 200  | 채무자주소     |      |
| jijum_nm             | Text    | 50   | 지점명       |      |
| bfr_debtor_nm        | Text    | 100  | 이전채무자명    |      |
| debtor_nm_close_yn   | Text    | 1    | 채무자명말소여부  |      |
| debtor_no_close_yn   | Text    | 1    | 채무자번호말소여부 |      |
| debtor_addr_close_yn | Text    | 1    | 채무자주소말소여부 |      |
| close_yn             | Text    | 1    | 말소여부      |      |

<div style="page-break-after: always;"></div>
#### d01513104 - 을구: 공동담보목록

| 이름           | 타입       | 길이   | 설명       | 비고   |
| ------------ | -------- | :--- | :------- | ---- |
| rett_prp_no  | Text     | 16   | 부동산 고유번호 |      |
| seq          | smallint |      | 순번       |      |
| sub_seq      | integer  |      | 내부순번     |      |
| list_type    | Text     | 3    | 공동담보구분타입 |      |
| list_type_nm | Text     | 30   | 공동담보구분   |      |
| rett_type    | Text     | 3    | 부동산구분타입  |      |
| rett_type_nm | Text     | 30   | 부동산구분명   |      |
| addr         | Text     | 4000 | 주소       |      |
| close_yn     | Text     | 1    | 말소여부     |      |

#### d01514000 - 공동담보목록: 기본정보

| 이름           | 타입      | 길이   | 설명        | 비고   |
| ------------ | ------- | :--- | :-------- | ---- |
| rett_prp_no  | Text    | 16   | 부동산 고유번호  |      |
| seq          | Numeric |      | 순번        |      |
| list_no      | Text    | 30   | 공동담보목록번호  |      |
| list_type    | Text    | 3    | 공동담보목록타입  |      |
| list_type_nm | Text    | 20   | 공동담보목록타입명 |      |
| list_cnt     | Numeric |      | 공동담보목록수   |      |
| close_yn     | Text    | 1    | 말소여부      |      |

<div style="page-break-after: always;"></div>
#### d01514001 - 공동담보목록: 상세정보

| 이름             | 타입      | 길이   | 설명       | 비고   |
| -------------- | ------- | :--- | :------- | ---- |
| rett_prp_no    | Text    | 16   | 부동산 고유번호 |      |
| seq            | Numeric |      | 순번       |      |
| idx            | Numeric |      | 일련번호     |      |
| addr           | Text    | 200  | 주소       |      |
| rett_type      | Text    | 3    | 부동산구분코드  |      |
| rett_type_nm   | Text    | 10   | 부동산구분    |      |
| jurd_rego_cd   | Text    | 4    | 관할등기소코드  |      |
| jurd_rego_nm   | Text    | 50   | 관할등기소명   |      |
| ref_rank_no    | Text    | 50   | 관련순위번호   |      |
| cret_caus_date | Text    | 8    | 생성원인날짜   |      |
| cret_caus_no   | Text    | 20   | 생성접수번호   |      |
| cret_caus      | Text    | 200  | 생성원인     |      |
| chg_caus_date  | Text    | 8    | 변경소멸원인날짜 |      |
| chg_caus_no    | Text    | 20   | 변경소멸접수번호 |      |
| chg_caus       | Text    | 200  | 변경소멸원인   |      |
| close_yn       | Text    | 1    | 말소여부     |      |

#### d01515000 - 매매목록: 기본정보

| 이름            | 타입      | 길이   | 설명       | 비고   |
| ------------- | ------- | :--- | :------- | ---- |
| rett_prp_no   | Text    | 16   | 부동산 고유번호 |      |
| seq           | Numeric |      | 순번       |      |
| trade_list_no | Text    | 20   | 매매목록번호   |      |
| trade_cost    | Text    | 30   | 거래액      |      |
| list_cnt      | Numeric |      | 매매목록수    |      |
| close_yn      | Text    | 1    | 말소여부     |      |

#### d01515010 - 매매목록: 거래가액 변경내역

| 이름               | 타입      | 길이   | 설명        | 비고   |
| ---------------- | ------- | :--- | :-------- | ---- |
| rett_prp_no      | Text    | 16   | 부동산 고유번호  |      |
| seq              | Numeric |      | 순번        |      |
| sub_seq          | Numeric |      | 내부순번      |      |
| trade_cost       | Text    | 30   | 거래액       |      |
| cost_chg_date    | Text    | 8    | 거래액변경날짜   |      |
| cost_chg_caus_cd | Text    | 3    | 거래액변경사유코드 |      |
| cost_chg_caus    | Text    | 200  | 거래액변경사유   |      |
| close_yn         | Text    | 1    | 말소여부      |      |

#### d01515020 - 매매목록: 상세정보

| 이름             | 타입      | 길이   | 설명       | 비고   |
| -------------- | ------- | :--- | :------- | ---- |
| rett_prp_no    | Text    | 16   | 부동산 고유번호 |      |
| seq            | Numeric |      | 순번       |      |
| idx            | Numeric |      | 일련번호     |      |
| addr           | Text    | 200  | 주소       |      |
| rett_type      | Text    | 3    | 부동산구분코드  |      |
| rett_type_nm   | Text    | 20   | 부동산구분명   |      |
| ref_rank_no    | Text    | 50   | 관련순위번호   |      |
| cret_caus_date | Text    | 8    | 생성원인날짜   |      |
| cret_caus_no   | Text    | 20   | 생성접수번호   |      |
| cret_caus      | Text    | 200  | 생성원인     |      |
| chg_caus_date  | Text    | 8    | 변경원인날짜   |      |
| chg_caus_no    | Text    | 20   | 변경접수번호   |      |
| chg_caus       | Text    | 200  | 변경원인     |      |
| close_yn       | Text    | 1    | 말소여부     |      |

#### d01519000 - 대위권리자

| 이름                    | 타입      | 길이   | 설명       | 비고   |
| --------------------- | ------- | :--- | :------- | ---- |
| rett_prp_no           | Text    | 16   | 부동산 고유번호 |      |
| reg_cls_type          | Text    | 3    | 등기구분타입   |      |
| seq                   | Numeric |      | 순번       |      |
| sub_seq               | Numeric |      | 내부순번     |      |
| reg_cls_nm            | Text    | 20   | 등기구분명    |      |
| sub_right_person_nm   | Text    | 100  | 대위자명     |      |
| sub_right_person_no   | Text    | 50   | 대위자생년월일  |      |
| sub_right_person_addr | Text    | 200  | 대위자주소    |      |


### data_tmpl EDT02 [[목록]](#T010002-부동산등기-분석데이터-상세내용-목록)
#### d015100 - 기본정보

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


#### d015100_01 - 등기 주소 상세

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



#### d015200 - 등기 기본정보 (나이스 제공 형태)

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

#### d015201 - 주요 등기사항 요약 중 1. 소유지분현황(갑구)

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

#### d015202 - 주요 등기사항 요약 중 2. 소유지분제외(갑구) 3. (근)저당권 및 전세권 등

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

#### d01521000 - 등기부 갑구, 을구 (권리자 및 기타사항 제외)

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

#### d01521001 - 등기부 갑구, 을구의 권리자 및 기타사항

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

#### d01522000 - 표제부 - 전체데이터

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

#### d01522001 - 표제부 - 상세

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

#### d01522002 - 표제부 - 층별 정보

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

#### d01522003 - 표제부 - 대지권의 목적인 토지의 표시

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

#### d01523000 - 공동담보목록

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

`
