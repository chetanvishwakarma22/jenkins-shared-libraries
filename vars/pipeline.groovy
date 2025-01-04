@Library('my-shared-library@main') _

pipeline {
    agent { label 'slave1' }

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
        MAVEN_HOME = '/usr/share/maven'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
    }
stages { 	
stage('Checkout Code') 
		{
            steps {
                script {
		pipeline.checkoutCode()
            }
		}
        }
stage('Set up Java 17') 
		{
                steps {
		  script {
                  pipeline.setupJava()
            }
		}
        }
stage('Set up Maven') 
			{
                steps {
		  script {
                  pipeline.setupMaven()
            }
		}
        }
stage('Build with Maven') 
       {
            steps {
		script {
                pipeline.buildProject()
            }
		}    
        }
stage('Upload Artifact') 
			{
                steps {
		   script {
                   pipeline.uploadArtifact('target/bus-booking-app-1.0-SNAPSHOT.jar')
            }
		}
        }
stage('Run Application') 
			{
                steps {
		  script {
                  pipeline.runApplication()
            }
		}
        }
stage('Validate App is Running') 
			{
                steps {
		 script {
                  pipeline.validateApp()
            }
		}
        }
stage('Gracefully Stop Spring Boot App') 
			{
                steps {
		 script {
                  pipeline.stopApplication()
            }
		}
        }
post {
        always {
            cleanup()
        }
    }
}

