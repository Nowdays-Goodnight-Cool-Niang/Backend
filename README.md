# 도커 컴포즈를 이용하고 싶은 경우

> 로컬에 java가 설치되어 있지 않은 경우 유용합니다.\
> docker 설치 후 진행해 주세요.

디스코드에 올려둔 파일들을 `src/main/resources`에 첨부해주세요.

그 후

`docker compose --profile docker up --build`

해당 명령어 사용하시면 됩니다.

# 엔드포인트

## 카카오 로그인

### 로그인 버튼 클릭 시 리다이렉트시켜야 하는 주소

http://localhost:8080/oauth2/authorization/kakao

해당 주소로 이동시켜 주시면 카카오 로그인 페이지가 뜹니다.

로그인이 처리되고 나면 `http://localhost:5173/kakao` 로 이동됩니다. 파라미터로 `code`와 `state`가 포함됩니다.

### 카카오 로그인 이후 서버측 로그인 요청할 api

http://localhost:8080/login/oauth2/code/kakao

해당 url에 앞서 전달받은 파라미터인 `code`와 `state`를 넣어주시면 로그인 처리가 수행됩니다.

```json
{
  "githubUrl": null,
  "linkedinUrl": null,
  "name": "김주호",
  "instagramUrl": null,
  "isAuthenticated": false,
  "id": "91d91cce-c46c-41f2-9b2e-c67a1f5476a8",
  "email": null
}
```

응답으로는 위와 같은 데이터가 반환됩니다.

## 로그아웃

http://localhost:8080/logout

## 그 외 엔드포인트

http://localhost:8080/swagger
