# build image
#mvn -Pnative spring-boot:build-image
# Tag
docker tag meeting-service:1.0.0-SNAPSHOT winty.io:5000/winty/meeting-service:1.0.0-SNAPSHOT
# Push
docker push winty.io:5000/winty/meeting-service:1.0.0-SNAPSHOT