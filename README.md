# 🍸 01 | 프로젝트 소개 [알딸딸]
<div align = "center">
<img width="80%" src="https://github.com/klettermi/alttalttal/assets/95194606/9e229920-20a6-4fef-81a7-af990df56319"/>
</div>
<br>


```
🍸 모두가 함께 공유할 수 있는 칵테일 플랫폼
- 모든 사람이 칵테일 레시피를 조회할 수 있는 서비스
- 원하는 레시피를 찜할 수 있는 서비스
- 신뢰되는 사용자들의 의견 나눔 서비스
```
- 모든 사람이 인증 과정 없이 칵테일 레시피를 조회할 수 있습니다.
- 회원은 원하는 레시피를 찜하여 마이페이지에서 볼 수 있습니다.
- 회원은 모두와 소통할 수 있는 라운지를 이용할 수 있습니다.
- 회원가입, 로그인 및 로그아웃을 통해 회원 관리가 가능합니다.
- 마이페이지에서 닉네임을 수정하거나 찜목록을 볼 수 있습니다.


# 🥂 02 | 프로젝트 목표

1️⃣ **레시피 조회**
<div align = "center">
<img width="70%" src="https://github.com/klettermi/alttalttal/assets/95194606/bf8a3f85-4a93-4a0e-b3d1-faffb80f0f4c"/>
</div>    
<br>

2️⃣ **원하는 레시피를 찜하고 해제하기**
<div align = "center">
<img width="70%" src="https://github.com/klettermi/alttalttal/assets/95194606/f20d91a4-03a9-4448-b089-8f02ca6894d5"/>
</div> 
<br>

3️⃣ **라운지**
<div align = "center">
<img width="70%" src="https://github.com/klettermi/alttalttal/assets/95194606/6ed81291-c0da-4abd-a4ce-a50234a42b56"/>
</div> 
<br>

4️⃣ **회원가입, 로그인 및 로그아웃**
- 회원가입/로그인
<div align = "center">
<img width="70%" src="https://github.com/klettermi/alttalttal/assets/95194606/1797aacc-cc70-45d2-842d-aa21230ceee0"/>
</div> 

- 로그아웃
<div align = "center">
<img width="70%" src="https://github.com/klettermi/alttalttal/assets/95194606/2ab0b3bc-3848-407f-a317-5936b2d06040"/>
</div> 

5️⃣ **마이페이지**
<div align = "center">
<img width="70%" src="https://github.com/klettermi/alttalttal/assets/95194606/f41b86b9-be73-4289-9071-9dbe3c22d17f"/>
</div> 

# ⚙️ 03 | 아키텍쳐
## 서비스 아키텍쳐
<div align = "center">
<img width="70%" src="https://github.com/klettermi/alttalttal/assets/95194606/e053cafb-aedb-4d38-bbe0-a733ef0a0a98"/>
</div> 

## ERD
<div align = "center">
<img width="70%" src="https://github.com/klettermi/alttalttal/assets/95194606/6c8f9c94-3035-4caa-894d-877770842a2e"/>
</div>
<div align = "center">
<img width="40%" src="https://github.com/klettermi/alttalttal/assets/95194606/80c745d2-9134-411a-84dd-6ac3d636a996">
</div> 


## API 명세서 (swagger)
<img width="819" alt="image" src="https://github.com/klettermi/alttalttal/assets/95194606/bcee5734-58c8-49f0-858e-2b3844b8c6c4">
<img width="949" alt="image" src="https://github.com/klettermi/alttalttal/assets/95194606/51d641f5-cafa-4865-ab52-2f50a962035c">


# 🎯 04 | 트러블 슈팅

- 로그아웃 구현
  - 문제: Front-end에서 토큰을 삭제해주는 방식이 보안상 문제가 될 수 있다고 판단
  - 해결 방안: Back-end에서도 보안 유지를 위해 로그아웃 로직을 구현
            로그아웃 요청이 오면 Refresh Token을 Redis에서 삭제 시킨 후 AccessToken을 BlackList로 Redis에 저장
            로그아웃 후 해당 유저가 요청을 보낼 때 접근이 불가능하도록 구현
- 기존의 파일 구조를 도메인 별로 변경
  - 문제: 비즈니스 도메인을 명확하게 구분하고 확정성, 유지보수성을 향상시킬 수 있도록 파일 구조 변경 필요
  - 해결 방안: 도메인 별로 리팩토링 진행
    불필요한 @EnableJpaRepositories, @EnableMongoRepositoies 삭제
- Spring Security 적용
  - 문제: Spring Security가 적용되어 있지 않아 
    웹 응용 프로그램 및 서비스에서 보안을 효과적으로 관리하고 강화하기 위해 적용 필요
  - 해결 방안: Spring Security 적용

# 🛠️ 05 | 기술 스택

| Backend | Tech | Spring Boot Spring JPA Spring Security  |
| --- | --- | --- |
|  | Platform | Ubuntu |
|  | DB | AWS RDS(Mysql) Redis MongoDB |
|  | DevOps | AWS EC2 Docker |



