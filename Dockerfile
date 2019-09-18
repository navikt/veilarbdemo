FROM navikt/java:8-appdynamics
ENV APPD_ENABLED=true
ADD /target/veilarbdemo /app