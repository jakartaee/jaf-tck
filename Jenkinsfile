env.label = "activation-tck-ci-pod-${UUID.randomUUID().toString()}"
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
  - name: activation-tck-ci
    image: jakartaee/cts-jaf-base:0.3
    command:
    - cat
    tty: true
    imagePullPolicy: Always
    resources:
      limits:
        memory: "3.5Gi"
        cpu: "1.00"
"""
    }
  }
  parameters {
    string(name: 'ACTIVATION_BUNDLE_URL', 
           defaultValue: 'https://jakarta.oss.sonatype.org/content/repositories/staging/jakarta/activation/jakarta.activation-api/2.1.2/jakarta.activation-api-2.1.2.jar',
           description: 'URL required for downloading JAF vendor implementation jar' )
    string(name: 'ANGUS_BUNDLE_URL', 
           defaultValue: 'https://jakarta.oss.sonatype.org/content/repositories/staging/org/eclipse/angus/angus-activation/2.0.1/angus-activation-2.0.1.jar',
           description: 'URL required for downloading JAF compatible implementation jar' )
    string(name: 'TCK_BUNDLE_BASE_URL',
           defaultValue: '',
           description: 'Base URL required for downloading prebuilt binary TCK Bundle from a hosted location' )
    string(name: 'TCK_BUNDLE_FILE_NAME', 
           defaultValue: 'jakarta-activation-tck-2.1.0.zip', 
	   description: 'Name of bundle file to be appended to the base url' )
    string(name: 'GF_BUNDLE_URL', 
           defaultValue: 'https://download.eclipse.org/ee4j/glassfish/glassfish-7.0.0.zip', 
           description: 'URL required for downloading GlassFish Full/Web profile bundle' )
    choice(name: 'RUNTIME', choices: 'Glassfish\nANGUS',
           description: 'Run JAF Tests with Angus/Glassfish' )
    choice(name: 'JDK', choices: 'JDK21',
           description: 'Java SE Version to be used for running TCK either JDK21' )
    choice(name: 'LICENSE', choices: 'EPL\nEFTL',
           description: 'License file to be used to build the TCK bundle(s) either EPL(default) or Eclipse Foundation TCK License' )
  }
  environment {
    ANT_OPTS = "-Djavax.xml.accessExternalStylesheet=all -Djavax.xml.accessExternalSchema=all -Djavax.xml.accessExternalDTD=file,http" 
    DEFAULT_GF_BUNDLE_URL="https://download.eclipse.org/ee4j/glassfish/glassfish-7.0.0-SNAPSHOT-nightly.zip"
  }
  stages {
    stage('activation-tck-build') {
      steps {
        container('activation-tck-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/build_activationtck.sh
          """
          archiveArtifacts artifacts: 'bundles/*.zip'
          stash includes: 'bundles/*.zip', name: 'activation-bundles'
        }
      }
    }
  
    stage('activation-tck-run') {
      steps {
        container('activation-tck-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/run_activationtck.sh
          """
          archiveArtifacts artifacts: "*tck-results.tar.gz"
          junit testResults: 'results/junitreports/*.xml', allowEmptyResults: true
        }
      }
    }
  }
}
