env.label = "jaf-ci-pod-${UUID.randomUUID().toString()}"
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
  - name: jaf-ci
    image: anajosep/cts-jaf:0.1
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
    string(name: 'httpProxyHost', 
           defaultValue: '',
           description: 'Proxy host for connecting to http urls')
    string(name: 'httpProxyPort', 
           defaultValue: '',
           description: 'Proxy port for connecting to http urls')
    string(name: 'httpsProxyHost', 
           defaultValue: '',
           description: 'Proxy host for connecting to https urls')
    string(name: 'httpsProxyPort', 
           defaultValue: '',
           description: 'Proxy port for connecting to https urls')
  }
  environment {
    http_proxy = "http://${httpProxyHost}:${httpProxyPort}"
    https_proxy = "https://${httpsProxyHost}:${httpsProxyPort}"
    ANT_OPTS = "-Djavax.xml.accessExternalStylesheet=all -Djavax.xml.accessExternalSchema=all -Djavax.xml.accessExternalDTD=file,http" 
    MAIL_USER="user01@james.local"
  }
  stages {
    stage('jaf-build') {
      steps {
        container('jaf-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/build_jaf.sh
          """
          archiveArtifacts artifacts: 'bundles/*.zip'
          stash includes: 'bundles/*.zip', name: 'jaf-bundles'
        }
      }
    }
  
    stage('jaf-run') {
      steps {
        container('jaf-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/run_jaf.sh
          """
          archiveArtifacts artifacts: "jaftck-results.tar.gz"
          junit testResults: 'results/junitreports/*.xml', allowEmptyResults: true
        }
      }
    }
  }
}
