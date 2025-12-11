FROM tomcat:9.0-jdk11

# Quitamos la app ROOT por defecto
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copiamos tu WAR como ROOT (ajusta el nombre si es distinto)
COPY target/gomez_garcia_fco_javier_GestoCar-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
