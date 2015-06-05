stage 'build image'
node('docker') {
	docker.withServer('tcp://127.0.0.1:1234'){
	  git url: 'https://github.com/kmadel/jenkins-slave.git'
	  def jenkinsSlaveImage = docker.build "kmadel/jenkins-slave:latest"
	  stage 'push image'
	  jenkinsSlaveImage.push 'latest'
	}
}
