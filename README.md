![scala-version][scala-version-badge]

# Prescription Drugs Service

Manages drugs both through GRPC service. Also, supports prescription retrieving using HTTP and SFTP.

## Running

Application has following environment variables:

| Variable      | Description                                       | Required | Default |
|---------------|---------------------------------------------------|----------|---------|
| HTTP_PORT     | Port of the HTTP service is listening on.         | NO       | 8080    |
| SFTP_HOST     | Host of the SFTP server.                          | YES      | /       |
| SFTP_PORT     | Port of the SFTP server.                          | NO       | 22      |
| SFTP_USERNAME | Username required for SFTP server authenticating. | YES      | /       |
| SFTP_PASSWORD | Password required for SFTP server authenticating. | YES      | /       |

To setup environment variables in the application, please provide `.env` file in the root of the project.

After configuring environment variables application can be started via command:
```bash
sbt run
```

## Data

In order to use a GRPC service that tests whether the drug is available or not, please provide data in the `src/main/resources/db/drugs.json` file.

_**Note:** Data is not persisted and will be restarted every time service is up and running._

## Infrastructure

Third-party services that our service depends on are defined in the `docker-compose.yml` file. Start it using following command:
```bash
docker-compose -f docker-compose.yml up -d
```

### SFTP

[`emberstack/sftp`](https://hub.docker.com/r/emberstack/sftp) represents a Docker image for hosting a SFTP server.
SFTP configuration is defined in the `sftp.json` file.

## Building and running a Docker image

Building a Docker image is automated through the shell script:
```bash
./ci.sh
```

In order to start a container use a following command:
```bash
docker run -p 8080:8080 -p 9000:9000 --env-file .env prescription-drugs:latest
```

_**Note:** It's recommended to use 8080 for an HTTP port._

[scala-version-badge]: https://img.shields.io/badge/scala-2.13.6-red?logo=scala&color=red
