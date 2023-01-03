// This link contains all jenkins env variables: http://localhost:8080/env-vars.html/
// CODE_CHANGES = getGitChanges()
def gv
pipeline {

  agent any

  tools {
    maven 'Maven'
  }

  parameters {
    choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
    booleanParam(name: 'executeTests', defaultValue: true, description: '')
  }

  environment { // Here we define our own environmental variables
    NEW_VERSION = '1.3.0'
    SERVER_CREDENTIALS = credentials('GitHub') // Used to define credentials in jenkinsfile
  }

  stages {

    stage("Install dependencies") {
      // when { // define a condition to be met before the execution of the branch
      //   expression {
      //     BRANCH_NAME == 'main' && CODE_CHANGES == true
      //   }
      // }
      steps {
        script {
          gv = load "script.groovy"
        }
        echo 'Installing dependencies...'
        // sh 'npm install'
      }
    }

    stage("build") {
      // when { // define a condition to be met before the execution of the branch
      //   expression {
      //     BRANCH_NAME == 'main' && CODE_CHANGES == true
      //   }
      // }
      steps {

        script {
          gv.buildApp()
        }

        withCredentials([
          usernamePassword(credentialsId: 'GitHub', usernameVariable: 'USER', passwordVariable: 'PWD')
        ]) {
          echo "some script ${USER} : ${PWD}"
        }
      }
    }

    stage("test") {
      when { // define a condition to be met before the execution of the branch
        expression {
          BRANCH_NAME == 'main' && params.executeTests
        }
      }
      steps {

        script {
          gv.testApp()
        }

      }
    }

    stage("deploy") {
      steps {

        script {
          gv.deployApp()
        }

        // sh "echo $SERVER_CREDENTIALS"
      }
    }
  }
  // post { // This group execute some logic after all stages executed
  //   always {
  //     // Will be executed even if the build failed
  //   }
  //   success {
  //     // Will be executed if the build succeded
  //   }
  //   failure {
  //     // Will be executed if the build failed
  //   }
  // }
}
