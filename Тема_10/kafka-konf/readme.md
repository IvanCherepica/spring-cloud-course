Заходим в .../kafka-konf
Запускаем в терминале: sudo docker-compose up -d

В логе должны появиться:
Starting kafka_conf_zookeeper-server_1 ... done
Creating kafka_conf_kafka-server_1     ... done

Проверить доступность kafka можно так:
sudo lsof -i:9092
docker-pr 28229 root    4u  IPv6 404957      0t0  TCP *:9092 (LISTEN)



