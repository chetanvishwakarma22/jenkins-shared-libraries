@Library('my-shared-library@main') _  // Correct syntax

pipeline {
    agent { label 'slave1' }

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        MAVEN_HOME = '/usr/share/maven'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkoutCode()
            }
        }

        stage('Set up Java 17') {
            steps {
                setupJava()
            }
        }

        stage('Set up Maven') {
            steps {
                setupMaven()
            }
        }

        stage('Build with Maven') {
            steps {
                buildProject()
            }
        }

        stage('Upload Artifact') {
            steps {
                uploadArtifact('target/bus-booking-app-1.0-SNAPSHOT.jar')
            }
        }

        stage('Run Application') {
            steps {
                runApplication()
            }
        }

        stage('Validate App is Running') {
            steps {
                validateApp()
            }
        }

        stage('Gracefully Stop Spring Boot App') {
            steps {
                stopApplication()
            }
        }
    }

    post {
        always {
            cleanup()
        }
    }
}
