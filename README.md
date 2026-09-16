Document SaaS
A perfect open source replacement to all coorporation owned cloud document storage and editors

Demo Link: [Click Here](https://jarvis-ubuntu.tail5b0cb9.ts.net/documents)

Get Started:
- Click demo link
- create account
- get to typing

Features:
- Session Authentication system through User and Password
- UUID based user identification
- Modern Spring Security + Thymleaf for pages and authorization
- Postgresql for storage (locally hosted on machine rig)
- Spring Boot for controller library

Run it locally:
- Download jdk (I used microsoft distrobution of MS-17)
- run postgres(I used a docker container)
- Download maven(most JDK distrobutions come with maven)
- look up run command for Spring Boot Application using your JDK distrobution

How It works:
  To run this application, I used a kubernetes-container based structure 
  using docker cli to have two machines, one for the Spring MVC Application
  and the other to run postgresql. I used kubernetes to make sure neither of the machines 
  failed and I used kubernetes to have each machine spin up new container instances as needed
  for incoming traffic. 
  
