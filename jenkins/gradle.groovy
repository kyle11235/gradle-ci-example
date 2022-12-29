node {
    def server = Artifactory.server 'art1'
    def rtGradle = Artifactory.newGradleBuild()
    def buildInfo

    stage ('Clone') {
        git url: 'https://github.com/kyle11235/gradle-ci-example'
        
    }

    stage ('Artifactory configuration') {
        rtGradle.tool = 'gradle' // Tool name from Jenkins configuration
        // rtGradle.useWrapper = true  
        rtGradle.deployer repo: 'app1-gradle-dev-local', server: server
        rtGradle.resolver repo: 'app1-maven-release-virtual', server: server
    }

    stage ('Exec Gradle') {
        buildInfo = rtGradle.run rootDir: "", buildFile: 'build.gradle', tasks: 'clean build artifactoryPublish'
    }

    stage ('Publish build info') {
        server.publishBuildInfo buildInfo  
    }

    // stage ('Xray Scan') {
    //     def scanConfig = [
    //             'buildName'      : buildInfo.name,
    //             'buildNumber'    : buildInfo.number,
    //             'failBuild'      : false
    //     ]
    //     echo scanConfig as String
    //     def scanResult = server.xrayScan scanConfig
    //     echo scanResult as String
    // }
}
