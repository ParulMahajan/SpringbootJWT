#dockerBuild is the folder containg the Dockerfile and events exe in the artifacts folder
# Command to build sequent kafka image

docker build --tag="mahajan777/streaming-base:latest" --no-cache="true" --rm=true .
