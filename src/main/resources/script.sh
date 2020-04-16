#Prometheus
docker pull prom/prometheus
sudo docker run -d --network host prom/prometheus
sudo docker exec -it 0546c9cc6e32 vi /etc/prometheus/prometheus.yml
sudo docker exec -it 0546c9cc6e32 kill -HUP 1
acessar http://localhost:9090

#Grafana
docker pull grafana/grafana
sudo docker run -d --network host grafana/grafana

#Keycloak
docker pull jboss/keycloak
docker run -d -p 8180:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin jboss/keycloak