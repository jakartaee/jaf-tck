env.label = "jaf-tck-ci-pod-${UUID.randomUUID().toString()}"
pipeline {
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  agent {
    kubernetes {
      label "${env.label}"
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
spec:
  hostAliases:
  - ip: "127.0.0.1"
    hostnames:
    - "localhost.localdomain"
  containers:
  - name: jaf-tck-ci
    image: jakartaee/cts-jaf-base:0.1
    command:
    - cat
    tty: true
    imagePullPolicy: Always
    resources:
      limits:
        memory: "8Gi"
        cpu: "2.0"
"""
    }
  }
  parameters {
    string(name: 'JAF_BUNDLE_URL', 
           defaultValue: 'http://central.maven.org/maven2/com/sun/activation/javax.activation/1.2.0/javax.activation-1.2.0.jar',
           description: 'URL required for downloading JAF implementation jar' )
  }
  environment {
    ANT_OPTS = "-Djavax.xml.accessExternalStylesheet=all -Djavax.xml.accessExternalSchema=all -Djavax.xml.accessExternalDTD=file,http" 
  }
  stages {
    stage('jaf-tck-build') {
      steps {
        container('jaf-tck-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/build_jaftck.sh
          """
          archiveArtifacts artifacts: 'bundles/*.zip'
          stash includes: 'bundles/*.zip', name: 'jaf-bundles'
        }
      }
    }
  
    stage('jaf-tck-run') {
      steps {
        container('jaf-tck-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/run_jaftck.sh
          """
          archiveArtifacts artifacts: "jaftck-results.tar.gz"
          junit testResults: 'results/junitreports/*.xml', allowEmptyResults: true
        }
      }
    }
  }
}
