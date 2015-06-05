stage 'build image'
node('docker') {
	docker.withServer('tcp://127.0.0.1:1234'){
	  docker.withRegistry('https://registry.hub.docker.com/', 'docker-registry-kmadel-login') {	
	  	git url: 'https://github.com/kmadel/jenkins-slave.git'
	  	def jenkinsSlaveImage = docker.build "kmadel/jenkins-slave:latest"
	  	stage 'push image'
	  	jenkinsSlaveImage.push 'latest'
	  }
	}
}
