pipeline {
    agent any

    stages {
        stage('Récupérer le code depuis GitHub') {
            steps {
                sh 'git clone https://github.com/kalou103/Akkar.git'
            }
        }

        stage('Compilation avec Maven') {
            steps {
                dir('Akkar') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage('Analyse de code avec SonarQube') {
            steps {
                dir('Akkar') {
                    script {
                        // Définir vos informations d'identification SonarQube
                        def sonarUsername = 'admin'
                        def sonarPassword = 'admin28'

                        // Spécifier la révision à analyser (remplacez '1a4ef01d773559838ba0065bb7c321486fed3f3a' par la révision souhaitée)
                        def sonarRevision = '1a4ef01d773559838ba0065bb7c321486fed3f3a'

                        // Exécutez l'analyse de code avec SonarQube en utilisant le nom d'utilisateur, le mot de passe et la révision
                        sh "mvn sonar:sonar -Dsonar.login=$sonarUsername -Dsonar.password=$sonarPassword -Dsonar.branch.name=$sonarRevision"
                    }
                }
            }
        }
    }
}
