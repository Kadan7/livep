FROM openjdk:8

LABEL maintainer="kadan.li kadanstudio <kadan.li@kadan1984.com>"

VOLUME ["/logsFolder"]

# delete all the apt list files since they're big and get stale quickly
RUN rm -rf /var/lib/apt/lists/*

# Replace by aliyun ntp server
#ADD ntp.conf /etc/ntp.conf
#COPY US_export_policy.jar $JAVA_HOME/lib/US_export_policy.jar
#COPY local_policy.jar $JAVA_HOME/lib/local_policy.jar

# change dns server
#RUN echo 'nameserver 223.5.5.5' >> /etc/resolv.conf
#RUN rm -f /etc/hosts
#COPY hosts /etc/hosts
COPY svc2.jar /root/svc.jar
COPY startup.sh /root/startup.sh
EXPOSE 8080
CMD ["sh","/root/startup.sh"]
