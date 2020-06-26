FROM navikt/java:12
COPY /target/veilarbdemo.jar app.jar
COPY debug.sh /init-scripts/03-debug.sh