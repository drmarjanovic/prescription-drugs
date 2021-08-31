# Prescription Drugs Service

Manages drugs both through GRPC service. Also, supports prescription retrieving using HTTP and SFTP.

### Running

Application has following environment variables:

| Variable      | Description                                       | Required | Default |
|---------------|---------------------------------------------------|----------|---------|
| GRPC_PORT     | Port of the GRPC service is listening on.         | NO       | 9000    |
| HTTP_PORT     | Port of the HTTP service is listening on.         | NO       | 8080    |
| SFTP_HOST     | Host of the SFTP server.                          | YES      | /       |
| SFTP_PORT     | Port of the SFTP server.                          | NO       | 22      |
| SFTP_USERNAME | Username required for SFTP server authenticating. | YES      | /       |
| SFTP_PASSWORD | Password required for SFTP server authenticating. | YES      | /       |

To setup environment variables in the application, please provide `.env` file in the root of the project.

After configuring environment variables application can be started via command:
```console
sbt run
```

