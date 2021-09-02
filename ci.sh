sbt clean dist
unzip target/universal/prescription-drugs-*.zip -d docker
mv docker/prescription-drugs-* docker/prescription-drugs
docker build --rm -t prescription-drugs docker
