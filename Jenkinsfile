pipeline {
  agent any
  tools {
    gradle "gradle"
    jdk "OracleJDK11"
  }
  stages {
    stage('Code Build') {
      steps {
        checkout scmGit(branches: [
            [name: '*/master']
          ],
          extensions: [], userRemoteConfigs: [
            [url:
              'https://github.com/anaghabide/boilerplate'
            ]
          ])
        sh 'sed -i \'s/java/openjdk:11/g\' Dockerfile'
        //sh 'sed -i \'s/8080/8081/g\' Dockerfile'
        sh 'gradle clean build --refresh-dependencies'
      }
    }
    stage('Code Test') {
      steps {
        sh 'gradle test'
      }
    }
    stage('Code Ananlysis') {
      steps {
        sh 'gradle checkstyle:checkstyle'
      }
    }
    stage('Docker Build') {
      steps {
        script {
          sh 'docker build -t boilerplate:test .'

        }
      }
    }
      stage('Docker Login') {
       steps {
        sh 'echo "dckr_pat_5dfZZ4hwyLnyX-kiEZw3ZHIcMPo" | docker login -u "anaghabide" -p "dckr_pat_5dfZZ4hwyLnyX-kiEZw3ZHIcMPo"'
        sh 'docker tag boilerplate:test anaghabide/boilerplate-repo:test'
        sh 'docker push anaghabide/boilerplate-repo:test'
       }
      }
      stage('Run Docker Container') {
       steps {
        sh "docker run -d -p 8082:8080 anaghabide/boilerplate-repo"

       }
      }
  }
}