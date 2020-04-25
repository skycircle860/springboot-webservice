# springboot-webservice
스프링부트와 aws로 구현하는 웹서비스 서적 실습

책만 보고 따라해보았는데 오류가 많이 났다. 보통 설정파일 오타가 많았고, 거의 종장쯤 travis 배포 부분에서 nohub.out 로그를 열어보았을때

Protocol handler start failed 에러가 났던것은 해당 서비스가 돌아가는 ec2 서버의 8080 포트가 전에 실습하던게 남아 있던건지 뭔지 사용하고 있어서 충돌
해서 안된것이였다. (ec2 콘솔에서 netstat -ano 명령어를 통해 사용중인 포트 확인) 

ec2 서버를 재부팅하고 클라이언트 재배포 했더니 동작되는것을 확인 할 수 있었다.

nginx 실습 도중 404 url 주소창에 profile 치고 텍스트값 확인하는 부분에서 404 에러가 떴었는데, ec2서버 재시작하고 클라재배포, 

nginx 재시작 했더니 정상적으로 출력되었다.

실습 사이트 aws 접근 URL

http://ec2-52-79-73-48.ap-northeast-2.compute.amazonaws.com/