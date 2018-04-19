FROM docker.adeo.no:5000/fo/maven as builder
ADD / /source
WORKDIR /source
RUN mvn package -DskipTests

FROM docker.adeo.no:5000/pus/nais-java-app
COPY --from=builder /source/target/veilarbdemo /app

# overskriv baseimagets run.sh
ADD appdynamics /appdynamics
ADD run-with-appd.sh /run.sh
RUN chmod +x /run.sh
CMD sh /run.sh