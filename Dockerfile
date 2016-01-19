FROM java:8

WORKDIR /var/datascrubber
COPY target /var/datascrubber
#Might need to remap ports
EXPOSE 4567
CMD java -jar DataScrubber.jar
