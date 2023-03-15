# GITHUB
The project has been divided into :
- controller, 
- model, 
- repository,
- service

To test it, run the project and enter the following address in the browser address bar:
http://localhost:8080/users/test

As a result, you will receive a response.

```json
{
"id": 383316,
"login": "test",
"name": null,
"type": "User",
"avatarUrl": "https://avatars.githubusercontent.com/u/383316?v=4",
"createdAt": "2010-09-01T10:39:12Z",
"calculations": 0.0182370820668693
}
```
The database used is H2.

After running the project, you need to go to the following address:
http://localhost:8080/h2-console
- Driver class: ```org.h2.Driver```
- JDBC URL: ```jdbc:h2:mem:githubdb```
- User Name: ```sa```
- Password: ```no password required :)```

After establishing a connection, click on ```USER_REQUEST_COUNT``` in the tree, which will create a select statement 
```
SELECT * FROM USER_REQUEST_COUNT
```
then just click run to see the results

In the test/java/UserServiceTest directory, there is a test.

# README

Thank you for giving me the opportunity to take part in the interview process! 
I am grateful for the chance to showcase my skills and experience.
I appreciate the chance.
Please let me know if you need any additional information from me. Once again, thank you for considering me for this opportunity and I look forward to hearing back from you soon.

Best regards,
[Emil Szczepaniak]