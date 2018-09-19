FROM openjdk:8

LABEL maintainer="kadan.li kadanstudio <kadan.li@kadan1984.com>"

VOLUME ["/logsFolder"]

# Update the repository and patch system
#RUN apt-get update -y && \
#    apt-get install bash libc6 libc-bin openssl wget ntp nginx="1.6.2-5+deb8u5" xz-utils -y

# delete all the apt list files since they're big and get stale quickly
RUN rm -rf /var/lib/apt/lists/*

# Replace by aliyun ntp server
#ADD ntp.conf /etc/ntp.conf
#COPY US_export_policy.jar $JAVA_HOME/lib/US_export_policy.jar
#COPY local_policy.jar $JAVA_HOME/lib/local_policy.jar

# change default time zone
# ls -R --group-directories-first /usr/share/zoneinfo
#RUN rm /etc/localtime && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# change dns server
#RUN echo 'nameserver 223.5.5.5' >> /etc/resolv.conf


#COPY nginx.conf /etc/nginx/nginx.conf
COPY log.jar /root/log.jar
COPY startup.sh /root/startup.sh
EXPOSE 8080
CMD ["sh","/root/startup.sh"]
