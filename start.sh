#!/usr/bin/env bash
set -e

# 🎨 Colors
RED='\033[0;31m'; GREEN='\033[0;32m'; YELLOW='\033[1;33m'; BLUE='\033[0;34m'; NC='\033[0m'
log() { echo -e "${BLUE} $1${NC}"; }
warn() { echo -e "${YELLOW} $1${NC}"; }
fail() { echo -e "${RED} $1${NC}"; exit 1; }
ok() { echo -e "${GREEN} $1${NC}"; }

OTEL_AGENT="otel/opentelemetry-javaagent.jar"
ARTIFACT_NAME=$(xmllint --xpath "/*[local-name()='project']/*[local-name()='artifactId']/text()" pom.xml)
VERSION=$(xmllint --xpath "/*[local-name()='project']/*[local-name()='version']/text()" pom.xml)
APP_JAR="target/${ARTIFACT_NAME}-${VERSION}.jar"

[ -f "$OTEL_AGENT" ] || fail "Missing OTEL agent: $OTEL_AGENT"
[ -f "$APP_JAR" ] || { log "Building app..."; ./mvnw clean package; }


set -x
log "Launching $APP_JAR with OpenTelemetry agent..."
java -javaagent:$OTEL_AGENT \
  -Dotel.exporter.otlp.logs.protocol=grpc \
  -Dotel.exporter.otlp.metrics.protocol=grpc \
  -Dotel.exporter.otlp.traces.protocol=grpc \
  -Dotel.logs.exporter=otlp \
  -Dotel.service.name=$ARTIFACT_NAME \
  -Dotel.exporter.otlp.endpoint=http://localhost:4317 \
  -jar $APP_JAR
