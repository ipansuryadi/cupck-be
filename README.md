cupck-be

- http://localhost:8080/?name=ipan
- run mongo db docker
- create local db with db name cupcake and table person in mongo db
- then add manually data with field name = name and value = ipan
- cd docker 
- docker compose up -d buat install kafka
- now in docker should running is mongodb kafka and zookeeper
- http://localhost:8080/swagger-ui/index.html
- klik api test di swagger masukkan message nya "ipan berpesan
- lihat di log nya intellij 
- ...kafkaexample.ExampleConsumer    : Message received: [Ipan berpesan]

fcm notes:
- when success push we cant see in google console
- in mobile must handle background process because the notif is received in mobile but the push notification status bar not showing
- the firebaseAccount.json i gitignore