FROM 940330296631.dkr.ecr.ap-southeast-1.amazonaws.com/base:bizapps

ENV ENVIRONMENT $ENVIRONMENT
ENV AWS_REGION $AWS_REGION
ENV CLOUD $CLOUD

WORKDIR /opt
COPY deploy /opt/deploy

COPY target/*.war /opt/deploy/

COPY /src/main/resources/application.properties /opt/deploy/banking-integration.properties

RUN apt-get update \
&& apt-get -y install nginx \
&& rm -rf /var/lib/apt/lists/*

RUN bash -x -c "ln -s /opt/deploy/run /usr/local/bin/run && chmod +x /usr/local/bin/run"

EXPOSE 8080

## Define default command.
CMD /usr/local/bin/run
