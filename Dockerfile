FROM java:7
EXPOSE 9005
ADD /target/HierarchicalConfigManagementDocker.jar hierarchicalconfigmanagementdockerapp.jar
RUN sh -c 'touch /hierarchicalconfigmanagementdockerapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/hierarchicalconfigmanagementdockerapp.jar"]