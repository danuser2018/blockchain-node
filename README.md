[![Java CI](https://github.com/danuser2018/blockchain-node/actions/workflows/build.yml/badge.svg)](https://github.com/danuser2018/blockchain-node/actions/workflows/build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=danuser2018_blockchain-node&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=danuser2018_blockchain-node)

# Blockchain-node

Mi propia implementación de un módulo de blockchain con fines educativos.

Esta librería se desarrolla a partir de los ejercicios realizados durante el curso _Blockchain de la A a la Z:
Crea tu propia criptomoneda en python_ que puedes encontrar en
[Udemy](https://www.udemy.com/course/blockchain-de-la-a-a-la-z-crea-tu-criptomoneda-en-python).

## Propiedades de configuración

El servicio está basado en [Spring Boot](https://spring.io/projects/spring-boot), y está pensado
para ser ejecutado como una aplicación dockerizada. El puerto de escucha (dentro del contenedor) 
está predefinido al **8080**.

### Información del propio nodo

#### Obligatorias

| Propiedad                  | Descripción                                                                                  |
|----------------------------|----------------------------------------------------------------------------------------------|
| blockchain.nodes.self.id   | id único que identifica al nodo                                                              |
| blockchain.nodes.self.host | host de acceso a nuestro nodo. Ten en cuenta que tiene que ser accesible desde el exterior   |
| blockchain.nodes.self.port | puerto de acceso a nuestro nodo. Ten en cuenta que tiene que ser accesible desde el exterior |

#### Opcionales

| Propiedad                             | Valor por defecto | Descripción                                                                             |
|---------------------------------------|-------------------|-----------------------------------------------------------------------------------------|
| blockchain.nodes.self.publish-rate-ms | 60000             | Especifica cada cuanto tiempo se envía a la red una actualización de los datos del nodo |
| blockchain.nodes.topic.name           | node-topic        | Nombre del topic de kafka utilizado para la sincronización de nodos                     |

### Kafka

#### Obligatorias

| Propiedad                      | Descripción                                   |
|--------------------------------|-----------------------------------------------|
| spring.kafka.bootstrap-servers | Lista de URLs para acceso al clúster de kafka |

### H2

#### Opcionales

| Propiedad                                   | Valor por defecto | Descripción                                                              |
|---------------------------------------------|-------------------|--------------------------------------------------------------------------|
| spring.datasource.username                  | sa                | Usuario de acceso a la base de datos                                     |
| spring.datasource.password                  | password          | Password de acceso a la base de datos. **Es muy recomendable cambiarlo** |
| spring.h2.console.enabled                   | false             | Activa/Desactiva la consola de H2                                        |
| spring.h2.console.settings.web-allow-others | false             | Permite el acceso desde el exterior a la consola de H2                   |

## Para construir el proyecto

### Previo

Para evitar que tengas que instalar todo el software necesario para construir y ejecutar el proyecto en local,
este proyecto utiliza [Vagrant](https://www.vagrantup.com/).

Para que [Vagrant](https://www.vagrantup.com/) funcione, necesitarás instalar:

* [VirtualBox](https://www.virtualbox.org/wiki/Downloads)
* [Vagrant](https://www.vagrantup.com/downloads.html)

Una vez tengas instalado [Vagrant](https://www.vagrantup.com/), ejectuando

```
$ vagrant up
```

levantarás una máquina virtual Ubuntu con:

* Java 17
* Docker

A la máquina virtual se accede mediante ```ssh```, ejecutando sobre la raiz del proyecto

```
$ vagrant ssh
```

La máquina virtual se puede detener, utilizando el comando

```
$ vagrant halt
```

o bien destruir, usando

```
$ vagrant destroy
```

### Construyecto el proyecto

Dentro de la máquina virtual, el código del proyecto se encuentra en la carpeta ```/vagrant```. 
Para construir el proyecto, tendremos que ejecutar:

```
$ ./mvnw clean package 
```

o si solo queremos verificar que se pasan los tests

```
$ ./mvnw clean verify
```

### Ejecutando el proyecto

Este servicio está pensado para ejecutar como una aplicación dockerizada.

#### Ejecución local

El proyecto contiene todo lo necesario para levantar en el entorno de desarrollo que generamos 
con [Vagrant](https://www.vagrantup.com/):

* Un clúster de kafka, compuesto por un nodo y una base de datos Zookeeper.
* Tres nodos mineros

La imagen docker que se utilizará en el despliegue de los tres nodos mineros está actualizada con la última versión
generada del paquete **blockchain-node**.

Para arrancar el sistema, deberemos movernos a la carpeta ```/vagrant/.local-env```, donde encontraremos un archivo
```docker-compose.yml``` que nos ayudará a levantar el sistema.

Para levantar el sistema ejecutaremos

```
$ docker-compose up -d
```

y para detenerlo

```
$ docker-compose down
```

Mediante el uso de comandos ```docker``` podremos realizar distintas acciones sobre los
contenedores creados. Dejo aquí los más útiles:

```
$ docker ps -a                              # Lista todos los contenedores
$ docker stop $container_id                 # Detiene un contenedor
$ docker start $container_id                # Arranca un contener que se detuvo previamente
$ docker rm $container_id                   # Borra un contenedor (previamente debe estar parado)
$ docker container logs $container_id       # Muestra los logs de un contenedor. Con -f los sigue (follow mode)
$ docker exec -it $container_id /bin/bash   # Abre una sesión interactiva contra el contenedor
$ docker inspect $container_id -f "{{json .NetworkSettings.Networks }}" # Da detalles de la red a la que se encuentra conectado el contenedor 
```

#### Acceso a la base de datos de los nodos

Los nodos mineros tienen activo el acceso a la consola de h2. El acceso a la misma se realiza desde local, mediante
un navegador web, accediendo a las siguientes URLs:

```
http://localhost:8080/h2-console    # Nodo minero 1
http://localhost:8081/h2-console    # Nodo minero 2
http://localhost:8082/h2-console    # Nodo minero 3
```

La cadena de conexión a la base de datos es ```jdbc:h2:mem:nodesdb```, el usuario ```sa``` 
y el password ```password```

## Licencia

Copyright © 2022 danuser2018

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the “Software”), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
