
# gradle example

- from https://github.com/jfrog/project-examples/tree/master/gradle-examples/gradle-example

- local

        - install
        
                wget https://services.gradle.org/distributions/gradle-6.8.2-bin.zip
                unzip gradle-6.8.2-bin.zip
                sudo mv gradle-6.8.2 /opt/gradle-6.8.2
                sudo vi /etc/profile
                export PATH=$PATH:/opt/gradle-6.8.2/bin
                source /etc/profile

        - config

                check build.gradle
        
        - test
        
                vi build.gradle
                vi gradle.properties -> set user/password in 

                gradle clean build
                gradle artifactoryPublish

        - gradle vs maven
        
                https://gradle.org/maven-vs-gradle/

- pipeline

        - install
        
                install gradle repo plugin

                        - if brew install gradle
                        gradle directory = /usr/local (which gradle = /usr/local/bin/gradle)

                        - if sdkman
                        gradle directory = /Users/kyle/.sdkman/candidates/gradle/current

        - test
        
                from project-examples/gradle-example/gradle-example-ci-server
                check ./jenkins/gradle.groovy

                        - fix
                        change no 'install' task in root project ->  change pipeline script to 'clean build artifactoryPublish'

                        