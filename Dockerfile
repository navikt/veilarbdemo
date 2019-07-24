FROM navikt/java:8-appdynamics
ADD /target/veilarbdemo /app
ENV APPD_ENABLED=true