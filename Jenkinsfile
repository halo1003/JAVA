node {    
    try{
        docker.image('maven').inside{
            stage('Clone sources'){          
                FAILED_STAGE=env.STAGE_NAME  
                checkout scm
            }
            stage('Check environment'){    
                FAILED_STAGE=env.STAGE_NAME        
                sh "mvn -v"
                sh "java -version"
            }
            stage ('Test'){            
                FAILED_STAGE=env.STAGE_NAME
                sh "mvn test"            
            }    
            stage ('Package'){
                FAILED_STAGE=env.STAGE_NAME
                sh "mvn package"
            }    
            stage ('Report'){
                FAILED_STAGE=env.STAGE_NAME
                step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
            }    
            stage ('Artifact'){
                FAILED_STAGE=env.STAGE_NAME
                step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar', fingerprint: true])
            }   
            stage ('AutoTag'){
                FAILED_STAGE=env.STAGE_NAME
                sh "./script.sh"
            }

            stage('Deploy'){                
                sh "ansible-playbook"
            }

            stage('Report'){
                FAILED_STAGE=env.STAGE_NAME
                sh "curl -X POST -H 'Content-type: application/json' --data '{\"text\":\"JenkinsPipeline execution successfully at ${getDateTime()}!!\"}' https://hooks.slack.com/services/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"                
            }     
        }   
    }catch(exc){
        sh "curl -X POST -H 'Content-type: application/json' --data '{\"text\":\"JenkinsPipeline execution failed, at ${getDateTime()}, at Stage: ${FAILED_STAGE} because of ${exc} !!\"}' https://hooks.slack.com/services/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"
    }
}

def FAILED_STAGE

def getDateTime(){
    DATE = sh (
        script: 'date',
        returnStdout: true
    ).trim()
return DATE
}

def executeTask(){
    STATUS = sh (
        script: './script.sh',
        returnStdout: true
    ).trim()
return STATUS
}