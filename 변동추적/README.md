BigTechPlus API Service Guide
===========


## 변동추적 서비스 API


#### API 목록
* ##### [부동산등기 변동추적 등록 요청](#T010010-부동산등기-변동추적-등록-요청-목록)
* ##### [부동산등기 변동추적 등록 상태 콜백](#T010010-1-부동산등기-변동추적-등록-상태-콜백-목록)
* ##### [부동산등기 변동발생 결과 콜백](#T010010-2-부동산등기-변동발생-결과-콜백-목록)
* ##### [부동산등기 변동추적 등록 상태 조회](#T010011-부동산등기-변동추적-등록-상태-조회-목록)
* ##### [부동산등기 변동추적 등록 목록 조회](#T010012-부동산등기-변동추적-등록-목록-조회-목록)
* ##### [부동산등기 변동추적 삭제](#T010014-부동산등기-변동추적-삭제-목록)


---

## 응답코드
<span class="red">❗️응답코드는 추가될 수 있습니다.</span>
#### 공통 응답코드
| 응답코드  | 응답메세지                        |
| :---- | :--------------------------- |
| CS000 | 요청 성공                        |
| CE404 | 신청코드를 찾을 수 없습니다.             |
| CE503 | 현재 해당서비스를 이용할 수 없습니다.        |
| CE999 | 확인되지 않는 에러입니다. 담당자에게 문의해주세요. |


#### 변동추적 응답코드
| 응답코드  | 응답메세지             |
| :---- | :---------------- |
| IE503 | 등기소 서비스 점검중입니다.   |
| IE510 | 소유자를 확인할 수 없습니다.  |
| TE409 | 중복 등록 건입니다.       |
| TE501 | 추적 일시정지되었습니다.     |
| TE503 | 추적 불가한 주소입니다.     |
| FE506 | 진행 중인 신청사건이 있습니다. |

#### 부동산등기 열람 응답코드
| 응답코드  | 응답메세지                          |
| :---- | :----------------------------- |
| BS000 | 열람 성공                          |
| IE405 | 과다등기는 열람이 불가능합니다.              |
| IE501 | 인근 등기소로 직접 방문하여 열람하시기 바랍니다.    |
| IE505 | 해당등기는 열람이 불가능합니다. 담당자에게 문의해주세요 |
| BE500 | 열람중 오류가 발생하였습니다. 담당자에게 문의해주세요. |

---
<br/>



### T010010. 부동산등기 변동추적 등록 요청 [[목록]](#API-목록)

부동산등기 변동추적 목록에 등록을 요청합니다.

입력한 고유번호에 대한 검증을 진행한 후, 부동산등기 변동추적 관리 목록에 등록됩니다.
등록에 일정시간이 소요되며, 등록 상태는 [부동산등기 변동추적 등록 상태 조회](#T010011-부동산등기-변동추적-등록-상태-조회-목록) 에서 확인할 수 있습니다.

요청 시 입력한 `endpoint`로 등록 상태가 변동되면 콜백을 받을 수 있습니다.

> POST https://api.homeq.kr/rest/v1/trace/estate/t010010


* #### HTTP Request Body
| 이름         | 타입      | 길이   | 필수   | 설명                 | 비고                                       |
| :--------- | :------ | ---- | :--- | :----------------- | ---------------------------------------- |
| reg_prp_no | Text    | 16   | ㅇ    | 부동산등기 고유번호         | 예)1342-2017-008171                       |
| owner_nm   | Array   |      | ㅇ    | 소유자명               | 예) ["홍길동","주식회사카카오"]<br>모를 경우 빈 배열 []    |
| date_from  | Text    | 8    |      | 추적 시작일자            | YYYYMMDD, <br>기본값: 오늘                    |
| date_to    | Text    | 8    |      | 추적 종료일자            | YYYYMMDD, <br>기본값: 계약종료일                 |
| endpoint   | Text    |      |      | 콜백 URL             | 등록 상태 콜백 URL                             |
| timeout    | Numeric |      |      | connection timeout | 콜백시 응답 대기시간<br>기본값: 3,000ms, 최댓값: 10,000ms |


* #### HTTP Response Body Data
| 이름         | 타입   | 길이   | 설명   | 비고   |
| ---------- | ---- | :--- | :--- | ---- |
| apply_code | Text | 32   | 신청코드 |      |

* _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-signature-v1: {signature}" \
  --data '{
            "reg_prp_no": "1342-2013-004559",
            "owner_nm": ["홍길동","주식회사카카오"]
            "date_to": "20241231",
            "endpoint": "http://endpoint.com/callback/someurl
  		}'
  'https://api.homeq.kr/rest/v1/trace/estate/t010000'
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
        "apply_code": "f7e76659f3031997c76446bde06b981f"
    }
}
```


</br>

### T010010-1. 부동산등기 변동추적 등록 상태 콜백 [[목록]](#API-목록)

부동산등기 변동추적 등록 상태에 대한 응답을 POST API방식으로 Callback 받을 수 있습니다. <br>상태가 변경될 콜백이 발생합니다.

응답코드 및 메세지는 [변동추적 응답코드](#변동추적-응답코드)에서 확인할 수 있습니다.

| 이름            | 타입     | 길이   | 설명     | 비고                                       |
| ------------- | ------ | :--- | :----- | ---------------------------------------- |
| error_code    | Text   | 5    | 응답코드   | 성공: CS000                                |
| error_message | Text   | 500  | 응답메세지  |                                          |
| data          | Object |      | 응답데이터  |                                          |
| ㄴapply_code   | Text   | 32   | 신청코드   |                                          |
| ㄴregist_date  | Text   | 19   | 등록일시   | YYYY-MM-DD HH24:MI:SS                    |
| ㄴreg_no       | Text   | 16   | 등기고유번호 |                                          |
| ㄴtrace_state  | Text   | 1    | 추적상태   | W: Waiting (검증 중)<br>A: Active (추적 중)<br>I: Inactive (추적 일시정지)<br>D: Deactivation (추적 불가)" |
| ㄴdate_from    | Text   | 8    | 시작일자   |                                          |
| ㄴdate_to      | Text   | 8    | 종료일자   | 추적 정지 시 추적 실패 일자                         |

* _추적 중 응답예제 (JSON)_
```json
{  
    "error_code": "CS000",
    "error_message": "",
    "data": {
    	"apply_code": "4231c98394094d0cdf94c486a60dbd8b",
        "regist_date": "2024-02-15 22:27:34",
        "reg_no": "1341-2021-012018",
      	"trace_state": "A",
        "date_from": "20240215",
        "date_to": "20241231",
    }
}
```

* _추적 불가 응답예제 (JSON)_


```json
{
    "error_code": "TE503",
    "error_message": "추적 불가한 주소입니다.",
    "data": {
    	"apply_code": "a4d77d4b10578d041519389e4b8a01ba",
        "regist_date": "2024-07-10 22:27:34",
        "reg_no": "7777-7777-777777",
      	"trace_state": "D",
        "date_from": "20240710",
        "date_to": "20240710"
    }
}
```




### T010010-2. 부동산등기 변동발생 결과 콜백 [[목록]](#API-목록)

부동산등기 변동발생 결과에 대한 응답을 POST API방식으로 Callback 받을 수 있습니다. <br>응답을 받을 URL과 callback_type은 별도로 등록해야 합니다.

callback_type 2와 3의 차이는 신청사건이 완료된 후 등기열람을 진행하는지 여부에 있습니다.

<br>
* #### callback_type = 2 (변동목록 + 상세내용)

* #### callback_type = 3 (변동목록 + 상세내용 + PDF링크)

| 이름               | 타입      | 길이   | 설명                   | 비고   |
| ---------------- | ------- | :--- | :------------------- | ---- |
| data             | Object  |      |                      |      |
| ㄴ total_segment  | Numeric |      | 총 패킷 수               |      |
| ㄴ segment_offset | Numeric |      | 현재 패킷 순번             |      |
| ㄴ summary        | Object  |      | 요약                   |      |
| ㄴ d017000        | Array   |      | 등기신청사건 내역            |      |
| ㄴ d017010        | Array   |      | 관련 부동산 소재지번 내역       |      |
| ㄴ d017020        | Array   |      | 관련 국민주택 채권 매입/환급액 내역 |      |

#### SUMMARY - 요약
| 이름            | 타입      | 길이   | 설명                                       |
| ------------- | ------- | :--- | :--------------------------------------- |
| job_date      | Text    | 8    | 작업일시, YYYYMMDD                           |
| total_teg_cnt | Numeric |      | 등록 등기 수 : 변동추적에 등록한 등기 수                 |
| change_cnt    | Numeric |      | 변동 수 : 변동추적에 등록된 등기 중<br>                작업일시에 발생한 변동 총 개수 |
| addr_cnt      | Numeric |      | 관련지번 수 : 변동에 포함된 관련 부동산 소재지번 내역 총 개수     |
| bond_cnt      | Numeric |      | 관련매입액 수 : 변동에 포함된 관련 국민주택 채권 매입/환급액 내역 총 개수 |
<div style="page-break-after: always;"></div>

#### d017000 - 등기신청사건 내역
부동산등기 변동추적에 등록된 등기목록중 해당 작업일시에 수집한 등기신청사건 목록이 포함됩니다.
| 이름                     | 타입     | 길이   | 설명                                       |
| ---------------------- | ------ | :--- | :--------------------------------------- |
| reg_no                 | String | 16   | 등기번호, 4자리-4자리-6자리                        |
| sel_regt               | String | 4    | 등기소 코드                                   |
| inp_recev_no           | String | 8    | 접수번호                                     |
| inp_recev_date         | String | 8    | 접수일자, YYYYMMDD                           |
| sel_regt_nm            | String | 64   | 등기소명                                     |
| inp_recev_dept         | String | 32   | 계, 등기처리 부서                               |
| regt_purp_code         | String | 8    | 등기목적코드                                   |
| regt_purp              | String | 64   | 등기목적                                     |
| inp_recev_step         | String | 32   | 처리상태                                     |
| inp_recev_step_cd      | String | 2    | 처리상태코드       <br>00: 신청, 10: 취하/각하, 20: 완료 |
| bond_purch             | String | 16   | 채권매입액                                    |
| bond_refund            | String | 16   | 채권환급액                                    |
| appl_name              | String | 16   | 신청구분                                     |
| notice_step            | String | 32   | 교부상태                                     |
| pdf_info               | Object |      | PDF 파일 관련 내용<br>callback_type=3 일 경우만 제공 |
| pdf_info.file_id       | String | 32   | PDF 파일 ID                                |
| pdf_info.error_code    | String | 5    | PDF 파일 에러코드                              |
| pdf_info.error_message | String | 512  | PDF 파일 에러메세지                             |

#### d017010 - 관련 부동산 소재지번 내역
발생한 등기신청사건에 해당하는 관련 부동산 소재지번 목록이 포함됩니다.
| 이름           | 타입     | 길이   | 설명                |
| ------------ | ------ | :--- | :---------------- |
| reg_no       | String | 16   | 등기번호, 4자리-4자리-6자리 |
| sel_regt     | String | 4    | 등기소 코드            |
| inp_recev_no | String | 8    | 접수번호              |
| item_addr    | String | 256  | 부동산소재지번           |

#### d017020 - 관련 국민주택 채권 매입/환급액 내역
발생한 등기신청사건에 해당하는 국민주택 채권 매입/환급액 목록이 포함됩니다.

| 이름           | 타입     | 길이   | 설명                |
| ------------ | ------ | :--- | :---------------- |
| reg_no       | String | 16   | 등기번호, 4자리-4자리-6자리 |
| sel_regt     | String | 4    | 등기소 코드            |
| inp_recev_no | String | 8    | 접수번호              |
| txt_bond_no  | String | 16   | 채권번호              |
| bank_branch  | String | 32   | 은행                |
| bond_purch   | String | 16   | 채권매입액             |
| bond_refund  | String | 16   | 채권환급액             |

* _응답예제 (JSON)_

 ```json
{
	"data": {
	      "total_segment": 10,
          "segment_offset": 1,
          "summary": { 
          	  "job_date": "20240401"
              , "total_reg_cnt": 80
              , "change_cnt": 5
              , "addr_cnt": 5
              , "bond_cnt": 7
          },
          "d017000": [ {
          		"reg_no": "1847-1996-102382",
                "sel_regt": "1843",
                "inp_recev_no": "31330",
                "inp_recev_date": "20240710",
                "sel_regt_nm": "부산지방법원 동부지원 남부산등기소",
                "inp_recev_dept": "등기6계",
                "regt_purp": "가압류기입",
                "inp_recev_step": "조사대기접수증출력",
                "inp_recev_step_cd": "00",
                "bond_purch": "",
                "bond_refund": "",
                "appl_name": "전자촉탁",
                "notice_step": "미출력",
                "pdf_info": {
                    "file_id": null,
                    "error_msg": "FE506",
                    "error_code": "진행 중인 신청사건이 있습니다."
                }
          	}, 
            { 	...	  },
              ...
          ],
          "d017010": [ {
                "reg_no": "1847-1996-102382",
                "sel_regt": "1843",
                "inp_recev_no": "31330",
                "item_addr": "[전유]북구 성북로 70 제1001동 제7층 제703호 [침산동 1761 침산화성파크드림]"
            }, 
            { 
            	...	
            },
              ...
          ],
          "d017020": [ {
          	    "reg_no": "1847-1996-102382",
                "sel_regt": "1843",
                "inp_recev_no": "31330",
                , "txt_bond_no": "21081011489341"
                , "bank_branch": "농협은행 안성시청"
                , "bond_purch": "2200000"
                , "bond_refund": "0"
          	}, 
              ...     
          ]
    }
}
```


</br>

### T010011. 부동산등기 변동추적 등록 상태 조회 [[목록]](#API-목록)

부동산등기 변동추적 관리 목록에 등록된 상태를 확인할 수 있습니다.

요청시 `endpoint` 파라미터에 콜백 URI를 입력하시면 등록 상태에 대한 응답이 전송됩니다.

> POST https://api.homeq.kr/rest/v1/trace/estate/t010011


* #### HTTP Request Body
| 이름         | 타입   | 길이   | 필수   | 설명   | 비고   |
| :--------- | :--- | ---- | :--- | :--- | ---- |
| apply_code | Text | 32   | ㅇ    | 신청코드 |      |


* #### HTTP Response Body Data
| 이름            | 타입     | 길이   | 설명     | 비고                                       |
| ------------- | ------ | :--- | :----- | ---------------------------------------- |
| error_code    | Text   | 5    | 응답코드   | 성공: CS000                                |
| error_message | Text   | 500  | 응답메세지  |                                          |
| data          | Object |      | 응답데이터  |                                          |
| ㄴregist_date  | Text   | 19   | 등록일시   | YYYY-MM-DD HH24:MI:SS                    |
| ㄴreg_no       | Text   | 16   | 등기고유번호 |                                          |
| ㄴtrace_state  | Text   | 1    | 추적상태   | W: Waiting (검증 중)<br>A: Active (추적 중)<br>I: Inactive (추적 일시정지)<br>D: Deactivation (추적 불가)" |
| ㄴdate_from    | Text   | 8    | 시작일자   |                                          |
| ㄴdate_to      | Text   | 8    | 종료일자   | 추적 정지 시 추적 실패 일자                         |

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
  'https://api.homeq.kr/rest/v1/trace/estate/t010011'

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
        "regist_date": "2024-02-15 22:27:34",
        "reg_no": "1341-2021-012018",
      	"trace_state": "A",
        "date_from": "20240101",
        "date_to": "20241231"
    }
}
```
</br>



### T010012. 부동산등기 변동추적 등록 목록 조회 [[목록]](#API-목록)

등록된 변동추적 관리목록을 조회합니다.

> POST https://api.homeq.kr/rest/v1/trace/estate/t010012


* #### HTTP Request Body
| 이름         | 타입   | 길이   | 필수   | 설명   | 비고   |
| :--------- | :--- | ---- | :--- | :--- | ---- |
| apply_code | Text | 32   | ㅇ    | 신청코드 |      |


* #### HTTP Request Body
| 이름        | 타입      | 길이   | 필수   | 설명    | 비고                     |
| :-------- | :------ | ---- | :--- | :---- | ---------------------- |
| page_no   | Numeric |      | ㅇ    | 페이지번호 |                        |
| page_size | Numeric |      |      | 자료 크기 | max: 1,000 <br>기본값: 10 |


* #### HTTP Response Body Data

| 이름           | 타입      | 길이   | 설명     | 비고                                       |
| ------------ | ------- | :--- | :----- | ---------------------------------------- |
| item_size    | Numeric |      | 결과 수   |                                          |
| item_list    | Array   |      | 추적목록   |                                          |
| ㄴapply_code  | Text    | 32   | 신청코드   |                                          |
| ㄴregist_date | Text    | 19   | 등록일시   | YYYY-MM-DD HH24:MI:SS                    |
| ㄴreg_no      | Text    | 16   | 등기고유번호 |                                          |
| ㄴtrace_state | Text    | 1    | 추적상태   | W: Waiting (검증 중)<br>A: Active (추적 중)<br>I: Inactive (추적 일시정지)<br>D: Deactivation (추적 불가)" |
| ㄴdate_from   | Text    | 8    | 시작일자   |                                          |
| ㄴdate_to     | Text    | 8    | 종료일자   | 추적 정지 시 추적 실패 일자                         |



* _요청예제 (cURL)_

```bash
$ curl -v --request POST \
  --header "Content-Type: application/json" \
  --header "x-btp-timestamp: {timestamp}" \
  --header "x-btp-access-key: {accessKey}" \
  --header "x-btp-signature-v1: {signature}" \
  --data '{
            "page_no": 1,
            "page_size": 20
  		}'
  'https://api.homeq.kr/rest/v1/trace/estate/t010012'

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
        "item_size": 2,
        "item_list":[
          {
              "apply_code": "f7e76659f3031997c76446bde06b981f",
              "regist_date": "2024-02-15 22:27:34",
              "reg_no": "1341-2021-012018",
              "trace_state": "A",
              "date_from": "20240101",
              "date_to": "20241231"
          },
          ...
        ]
    }
}
```

</br>

### T010014. 부동산등기 변동추적 삭제 [[목록]](#API-목록)

변동추적에 등록된 등기 고유번호를 관리목록에서 제거합니다.

> https://api.homeq.kr/rest/v1/trace/estate/t010014


* #### HTTP Request Body
| 이름         | 타입   | 길이   | 필수   | 설명   | 비고   |
| :--------- | :--- | ---- | :--- | :--- | ---- |
| apply_code | Text | 32   | ㅇ    | 신청코드 |      |


* #### HTTP Response Body Data
| 이름         | 타입   | 길이   | 설명   | 비고   |
| ---------- | ---- | :--- | :--- | ---- |
| apply_code | Text | 32   | 신청코드 |      |




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
  'https://api.homeq.kr/rest/v1/trace/estate/t010014'

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
        "apply_code": "f7e76659f3031997c76446bde06b981f"
    }
}
```


