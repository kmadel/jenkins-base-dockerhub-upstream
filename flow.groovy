stage 'build image'
node('dind') {
	docker.withServer('tcp://127.0.0.1:1234'){
	  docker.withRegistry('https://registry.hub.docker.com/', 'docker-registry-kmadel-login') {
	  	
	  	parallel(jenkinsSlaveBuildPush: {
	  		git url: 'https://github.com/kmadel/jenkins-slave.git'
	  		def jenkinsSlaveImage = docker.build "kmadel/jenkins-slave:latest"
	  		stage 'push kmadel/jenkins-slave image'
	  		jenkinsSlaveImage.push 'latest'
    		}, jenkinsEnterpriseBuildPush: {
    			stage 'build kmadel/jenkins-enterprise image'
	  		git url: 'https://github.com/kmadel/jenkins-enterprise.git'
	  		def jenkinsEnterpriseImage = docker.build "kmadel/jenkins-enterprise:latest"
	  		stage 'push kmadel/jenkins-enterprise image'
	  		jenkinsEnterpriseImage.push 'latest'
    		})
	  }
	}
}
