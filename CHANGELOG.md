# Changelog

Todos los cambios notables en este proyecto se documentarán en este archivo.

El formato se basa en [Mantenga un changelog](https://keepachangelog.com/es/1.0.0/),
y este proyecto se adhiere a [Versionado semántico](https://semver.org/spec/v2.0.0.html). 

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


