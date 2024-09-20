# ufril-medtran

Backend Service Project for MedTran application


**Build and push docker images**

for docker build to work need java_home & docker_host ENV var to be set  
`$ export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_271.jdk/Contents/Home`  
`$ export DOCKER_HOST=unix:///var/run/docker.sock`
                                
    -login to AWS docker registry. this is needed to push docker image from local to remote
    $ aws ecr get-login-password --region us-west-2 --profile ksazzad | docker login --username AWS --password-stdin 820351998531.dkr.ecr.us-west-2.amazonaws.com
    
    -push image to aws repository
    $ docker push 820351998531.dkr.ecr.us-west-2.amazonaws.com/marcitran-backend:latest


**old commands**  
You have to login to ECR first using this following command.

`aws ecr get-login --region us-west-2`

To run this above command successfully, you have to install the aws ecr cli tools.

You can build an image with the above configurations by running this command.

`mvn clean package docker:build`

To push the image you just built to the registry, specify the pushImage flag.

`mvn clean package -DskipTests docker:build -DpushImage`

To push only specific tags of the image to the registry, specify the pushImageTag flag.

`mvn clean package docker:build -DpushImageTag`

Tags-to-be-pushed can also be specified directly on the command line with

`mvn ... docker:build -DpushImageTags -DdockerImageTag=latest -DdockerImageTag=another-tag`

Reference URL: https://github.com/spotify/docker-maven-plugin
