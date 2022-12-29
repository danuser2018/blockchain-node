[![Java CI](https://github.com/danuser2018/blockchain-node/actions/workflows/build.yml/badge.svg)](https://github.com/danuser2018/blockchain-node/actions/workflows/build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=danuser2018_blockchain-node&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=danuser2018_blockchain-node)
# Blockchain-node

Mi propia implementación de un módulo de blockchain con fines educativos. 

Esta librería se desarrolla a partir de los ejercicios realizados durante el curso _Blockchain de la A a la Z:
Crea tu propia criptomoneda en python_ que puedes encontrar en
[Udemy](https://www.udemy.com/course/blockchain-de-la-a-a-la-z-crea-tu-criptomoneda-en-python).

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
