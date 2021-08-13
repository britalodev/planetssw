FROM tutum/mongodb
EXPOSE 27017
COPY mongo-init.js /docker-entrypoint-initdb.d/