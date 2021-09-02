FROM anapsix/alpine-java:8_server-jre

EXPOSE 8080
WORKDIR /opt

COPY ./docker-entrypoint.sh /usr/local/bin/
COPY ./prescription-drugs .

RUN addgroup -S psw \
    && adduser -SG psw psw \
    && apk add --no-cache 'su-exec>=0.2' \
    && ln -s /opt/bin/prescription-drugs /usr/local/bin/prescription-drugs

ENTRYPOINT [ "docker-entrypoint.sh" ]
CMD [ "prescription-drugs" ]
