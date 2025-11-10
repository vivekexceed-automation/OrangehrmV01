pipeline 
{
    agent any
    
    tools{
        maven 'maven'
        }

    stages 
    {       
	stage('Checkout') {
            steps {
                git 'https://github.com/vivekexceed-automation/OrangehrmV01.git'
            }
        }
 
        
        stage("Deploying... to QA Env"){
            steps{
                echo("deploy to qa done")
            }
        }
        
        stage('Regression Automation Test on QA Env') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunner/regression.xml -Denv=qa"
                    
                }
            }
        }
        stage('Publish Allure Reports for QA Env') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
                
        stage("Deploy to UAT Env"){
            steps{
                echo("deploy to UAT done")
            }
        }
        
        stage('Sanity Automation Test on UAT Env') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunner/sanity.xml -Denv=uat"
                    
                }
            }
        }
    }
}