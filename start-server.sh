#!/bin/sh
nohup java -jar -Dserver.port=8000 publisher/target/publisher-0.0.1-SNAPSHOT.jar > publisher.log 2>&1 &
nohup java -jar -Dserver.port=9000 subscriber/target/subscriber-0.0.1-SNAPSHOT.jar > subscriber.log 2>&1 &

#curl -X POST -H "Content-Type: application/json" -d '{ "url": "http://localhost:9000/test1"}' http://localhost:8000/subscribe/topic1 
#curl -X POST -H "Content-Type: application/json" -d '{ "url": "http://localhost:9000/test2"}' http://localhost:8000/subscribe/topic1 
#curl -X POST -H "Content-Type: application/json" -d '{ "url": "http://localhost:9000/test1"}' http://localhost:8000/subscribe/topic2 
#curl -X POST -H "Content-Type: application/json" -d '{ "url": "http://localhost:9000/test2"}' http://localhost:8000/subscribe/topic2 
#curl -X POST -H "Content-Type: application/json" -d '{"message": "hello"}' http://localhost:8000/publish/topic1
#curl -X POST -H "Content-Type: application/json" -d '{"message": "hello2"}' http://localhost:8000/publish/topic2

tail -f publisher.log subscriber.log
