[![Java CI](https://github.com/danuser2018/blockchain-node/actions/workflows/build.yml/badge.svg)](https://github.com/danuser2018/blockchain-node/actions/workflows/build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=danuser2018_blockchain-node&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=danuser2018_blockchain-node)

# Blockchain-node

Mi propia implementación de un módulo de blockchain con fines educativos.

Esta librería se desarrolla a partir de los ejercicios realizados durante el curso _Blockchain de la A a la Z:
Crea tu propia criptomoneda en python_ que puedes encontrar en
[Udemy](https://www.udemy.com/course/blockchain-de-la-a-a-la-z-crea-tu-criptomoneda-en-python).

## Propiedades de configuración

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

| Propiedad                  | Valor por defecto | Descripción                                                              |
|----------------------------|-------------------|--------------------------------------------------------------------------|
| spring.datasource.username | sa                | Usuario de acceso a la base de datos                                     |
| spring.datasource.password | password          | Password de acceso a la base de datos. **Es muy recomendable cambiarlo** |
| spring.h2.console.enabled  | false             | Activa/Desactiva la consola de H2                                        |

## Para construir el proyecto

### Previo

Este proyecto utiliza Java 17. Debes tener configurado en tu entorno un SDK de la versión
17 y la variable JAVA_HOME apuntando a esta SDK.

### Construyecto el proyecto

En la raiz del proyecto, ejecuta:

```
$ ./mvnw clean install
```

### Ejecutando el proyecto

#### Kafka broker local

Para ejecutar el proyecto necesitas un clúster kafka con que poder interactuar.
En la carpeta ```.local-env/kafka``` encontrarás un clúster de kafka dockerizado
que puedes utilizar para ejecutar el servicio en local.

Para levantar el clúster, desde la carpeta ```.local-env/kafka``` :

```
docker-compose up
```

Para detener el servicio:

```
docker-compose down
```

#### Levantando el servicio en local

En la raiz del proyecto, ejecuta:

```
$ ./mvnw spring-boot:run
```

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
