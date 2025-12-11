########################
# 1ª FASE: COMPILAR EL WAR
########################
FROM maven:3.9-eclipse-temurin-11 AS build

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el POM primero (para aprovechar cache de dependencias)
COPY pom.xml .

# Descargamos dependencias (sin compilar todavía)
RUN mvn dependency:go-offline

# Copiamos el código fuente
COPY src ./src

# Compilamos y generamos el WAR
RUN mvn clean package -DskipTests

########################
# 2ª FASE: TOMCAT + WAR
########################
FROM tomcat:9.0-jdk11

# Quitamos la app por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copiamos el WAR generado en la fase anterior y lo desplegamos como ROOT
COPY --from=build /app/target/gomez_garcia_fco_javier_GestoCar-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Exponemos el puerto de Tomcat
EXPOSE 8080

# Comando para arrancar Tomcat
CMD ["catalina.sh", "run"]
