FROM 940330296631.dkr.ecr.ap-southeast-1.amazonaws.com/base:bizapps

WORKDIR /opt
COPY deploy /opt/deploy

COPY target/*.war /opt/deploy/

ENV ENVIRONMENT $ENVIRONMENT

RUN bash -x -c "ln -s /opt/deploy/run /usr/local/bin/run && chmod +x /usr/local/bin/run"

EXPOSE 8080

## Define default command.
CMD /usr/local/bin/run
