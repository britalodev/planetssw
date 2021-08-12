FROM mongo:latest
EXPOSE 27017
COPY mongo-init.js /docker-entrypoint-initdb.d/
#COPY conf/mongod.conf /etc/mongo/mongod.conf