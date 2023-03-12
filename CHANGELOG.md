# Changelog

Todos los cambios notables en este proyecto se documentarán en este archivo.

El formato se basa en [Mantenga un changelog](https://keepachangelog.com/es/1.0.0/),
y este proyecto se adhiere a [Versionado semántico](https://semver.org/spec/v2.0.0.html). 

## [1.14.0] - 2023-03-12

### Cambiado

- Comunicación asíncrona de eventos dentro de la capa de aplicación


## [1.13.5] - 2023-03-12

### Arreglado

- Segundo arreglo de la pipeline de continuos delivery, que deja de funcionar tras la refactorización de paquetes

## [1.13.4] - 2023-03-12

### Arreglado

- Se arregla la pipeline de continuos delivery, que deja de funcionar tras la refactorización de paquetes

## [1.13.3] - 2023-03-11

### Cambiado

- Refactorización de paquetes

## [1.13.2] - 2023-03-08

### Arreglado

- El entorno local se actualiza con las imágenes provenientes de GitHub

## [1.13.1] - 2023-03-08

### Arreglado

- Cambio de nombre en la pipeline de despliegue continuo

## [1.13.0] - 2023-03-08

### Añadido

- Nueva pipeline para despliegue continuo

## [1.12.0] - 2023-03-06

### Añadido

- Nueva configuración para dockerizar la aplicación (```Dockerfile```)
- Nuevo entorno de ejecución local

## [1.11.0] - 2023-03-01

### Cambiado

- Se definen aquellas propiedades que son obligatorias para que un nodo funcione

## [1.10.0] - 2023-02-27

### Añadido

- A partir de ahora se pasa sonar también en las PRs

## [1.9.2] - 2023-02-27

### Modificado

- Se refactoriza la carpeta ```local-env```, que ahora pasa a ser ```.local-env```
- Se completa la información del ```README.md``` para incluir como levantar el clúster local de kafka

## [1.9.1] - 2023-02-19

### Arreglado

- Se solucionan algunos code smells encontrados por sonar

## [1.9.0] - 2023-02-15

### Añadido

- Registro de los datos de otros nodos

## [1.8.0] - 2023-02-15

### Añadido

- Nuevo VagrantFile para levantar un entorno de desarrollo estandarizado

## [1.7.0] - 2023-02-03

### Modificado

- Nueva propiedad 'blockchain.nodes.self.id' para especificar el id del nodo

## [1.6.1] - 2023-01-30

### Modificado

- Refactor del dominio de nodos, se separan los handlers de comandos de los puertos secundarios

## [1.6.0] - 2023-01-11

### Añadido

- Nueva propiedad 'blockchain.nodes.self.publish-rate-ms' para especificar cada cuanto se publica
  la información del propio nodo. 

## [1.5.2] - 2023-01-09

### Arreglado

- Se arreglan dos code smells sobre la versión 1.5.0

## [1.5.1] - 2023-01-09

### Arreglado

- Se incrementa la cobertura de pruebas para superar el quality gate (> 80%)

## [1.5.0] - 2023-01-09

### Añadido

- Nueva task que publica periódicamente los datos de conexión del nodo

## [1.4.1] - 2022-12-29

### Añadido

- Quality gate badge

### Arreglado

- Code Smell encontrados por el análisis de SonarCloud

## [1.4.0] - 2022-12-29

### Añadido

- Análisis de código estático en SonarCloud

## [1.3.0] - 2022-12-29

### Añadido 

- Nueva github action para hacer auto-tag de la versión subida a la rama main

## [1.2.1] - 2022-12-29

### Arreglado

- Licencia anonimizada

## [1.2.0] - 2022-12-29

### Añadido

- Añadida status badge

## [1.1.0] - 2022-12-29

### Añadido

- Nueva github action para CI

## [1.0.0] - 2022-12-29

### Añadido

- Readme con descripción del proyecto e instrucciones para su construcción y arranque
- Licencia MIT
- Este changelog
- Configuración para definir el puerto y el host del servicio

### Arreglado

- Configuración maven correcta para poder arrancar el proyecto con spring-boot:run


