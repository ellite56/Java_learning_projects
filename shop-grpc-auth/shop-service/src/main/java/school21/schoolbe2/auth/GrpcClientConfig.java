package school21.schoolbe2.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.ImportGrpcClients;
import school21.auth.grpc.AuthGrpc;

@Configuration
@ImportGrpcClients(target = "auth", types = AuthGrpc.AuthBlockingStub.class)
public class GrpcClientConfig {
}