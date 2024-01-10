cupck-be

### For first time
- `cd docker`
- `docker compose up -d` buat install kafka
- Now in docker should running is mongodb kafka and zookeeper
- Run mongo db docker
- Create local db with db name `cupcake` and table `person` in mongo db
- Then add manually data with field `name = name` and `value = ipan`

### Swagger
- Make sure docker is running and don't forget to `bootRun`
- Open swagger with url: http://localhost:8080/swagger-ui/index.html
- Click api test in Swagger, then input the message ex: "ipan berpesan"
- Look at log in Intellij
- ...kafkaexample.ExampleConsumer : Message received: [ipan berpesan]
![img.png](img.png)

### fcm notes:
- When success push we cant see in google console
- In mobile must handle background process because the notification is received in mobile but the push notification status bar not showing
- I put the firebaseAccount.json to gitignore

### For running cypress:
- If you are the first timer running cypress here, you should:
  - Install node_modules using ```pnpm install```