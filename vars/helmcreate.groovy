def call(String chart-name){

	sh "echo ${chart-name}";
	
	#create the helm chart
	sh "helm create ${chart-name}"
	
	#make changes in values.yaml
	sh '''
	cat <<EOF > helmcreate.sh
	#!/bin/bash

	image=soulreaper48/sample-web
	tag="1.0"
	version=0.1.0
	container-port=8000


	sed -i "s/nginx/$image/g" ./${chart-name}/values.yaml
	sed -i "s/stable/$tag/g" ./${chart-name}/values.yaml
	sed -i "s/0.1.0/$version/g" ./${chart-name}/Chart.yaml
	sed -i "s/80/$container-port/g" ./${chart-name}/templates/deployment.yaml
	'''
	sh 'chmod +x helmcreate.sh'
	sh './helmcreate.sh'
	sh 'rm helmcreate.sh'
	

}
