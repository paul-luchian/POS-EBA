package pos.rs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import pos.util.RestPaths;

@ApplicationPath(RestPaths.DATABASE1_NAME)
public class RestApplicationDatabaseConfig extends Application {

}
