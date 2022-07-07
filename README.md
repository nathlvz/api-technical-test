# Desarrollo de la prueba tecnica

### Como ejecutar este proyecto

Crear un archivo **docker-compose.yml** dentro de una carpeta llamada **prueba_finerio** con la siguiente configuracion tal cual como aparece en el recuado de codigo:

```
version: '3.8'

services:
 
  svc-bd-technicaltest:
    image: mongo:4.2.21
    restart: always
    ports:
      - "9000:27017"
    environment:
      - MONGO_INITDB_ROOT_PASSWORD=Zky8Lu3
      - MONGO_INITDB_ROOT_USERNAME=demousr
      - MONGO_INITDB_DATABASE=bd_technicaltest
```
Ya creada la carpeta y el archivo con la configuracion mostrada ejecutar el comando:

```
docker-compose up
```


Ahora se debe de ejecutar esta aplicacion con el comando:

```
mvn spring-boot:run
```

y por ultimo para ejecutar la peticion en un postman se utiliza la siguiente configuracion:

```
curl --location --request GET 'http://localhost:9090/runTest' \
--header 'Authorization: Basic c3NpbmdlcjIyMUBnbWFpbC5jb206dGVzdGluZzEyMw=='
```