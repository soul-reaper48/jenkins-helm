def call(String chart-name){

	sh '''
	cat <<EOF > rbac-config.yaml
		apiVersion: v1
		kind: ServiceAccount
		metadata:
		  name: tiller
		  namespace: kube-system
		---
		apiVersion: rbac.authorization.k8s.io/v1
		kind: ClusterRoleBinding
		metadata:
		  name: tiller
		roleRef:
		  apiGroup: rbac.authorization.k8s.io
		  kind: ClusterRole
		  name: cluster-admin
		subjects:
		  - kind: ServiceAccount
			name: tiller
			namespace: kube-system
		EOF
	'''
	sh 'kubectl apply -f rbac-config.yaml'
	sh 'helm init --service-account tiller --upgrade'
	sh 'rm rbac-config.yaml'
	sh 'helm install $(chartname)'
}
