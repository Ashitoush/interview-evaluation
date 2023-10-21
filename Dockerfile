FROM openjdk:8-jdk
MAINTAINER interviewevaluation
COPY target/interview_evaluation.jar interview_evaluation.jar
ENTRYPOINT ["java", "-jar", "/interview_evaluation.jar"]