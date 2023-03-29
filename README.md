## Workshop 37

### Server side

```
DO_ACCESS_KEY=```<your access key>```
DO_SECRET_KEY=```<your secretkey>```
DO_STORAGE_ENDPOINT=sgp1.digitaloceanspaces.com
DO_STORAGE_ENDPOINT_REGION=sgp1
mvn spring-boot:run
```

### Client Side

```
ng serve --proxy-config proxy-config.js
```

### Railway deployment

```
ng build
```

copy dist files to the spring boot static folder.
