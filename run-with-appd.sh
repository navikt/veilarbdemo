#!/bin/sh
set -e
set -x

# [ -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap ]
# https://blogs.oracle.com/java-platform-group/java-se-support-for-docker-cpu-and-memory-limits

HOSTNAME=`hostname`

exec java \
-XX:+UnlockExperimentalVMOptions \
-XX:+UseCGroupMemoryLimitForHeap \
-javaagent:/appdynamics/javaagent.jar \
-Dappdynamics.agent.applicationName=${APP_NAME} \
-Dappdynamics.agent.nodeName=${HOSTNAME} \
-Dappdynamics.agent.tierName=${FASIT_ENVIRONMENT_NAME}_${APP_NAME} \
-server \
-classpath "${APP_DIR}/WEB-INF/lib/*:${APP_DIR}/WEB-INF/classes" \
Main