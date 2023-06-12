pipeline {
  agent any
  tools {
    gradle "gradle"
    jdk "jdk11"
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
        sh 'sed -i \'s/java/openjdk:8/g\' Dockerfile'
        //sh 'sed -i \'s/8080/8081/g\' Dockerfile'
        sh 'gradle clean build --refresh-dependencies'
      }
    }
    stage('Code Test') {
      steps {
        sh 'mvn test'
      }
    }
    stage('Code Ananlysis') {
      steps {
        sh 'mvn checkstyle:checkstyle'
      }
    }
    stage('Docker Build') {
      steps {
        script {
          sh 'docker build -t devops-image:latest .'

        }
      }
    }
    stage('Docker Login') {
      steps {
        sh 'echo "dckr_pat_v3zT_M7zyxDmV-iAuK3BnWgCQME" | docker login -u "smitakhedkar30"'
        sh 'docker tag devops-training-repo:latest smitakhedkar30/devopstrainingrepo:latest'
        sh 'docker push smitakhedkar30/devops-training-repo:latest'
      }
    }
    stage('Run Docker Container') {
      steps {
        sh "docker run -d -p 8082:8080 smitakhedkar30/devops-training-repoe"

      }
    }
  }
}