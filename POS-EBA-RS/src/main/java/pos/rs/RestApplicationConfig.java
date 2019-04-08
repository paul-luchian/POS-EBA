package pos.rs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import pos.util.RestPaths;

@ApplicationPath(RestPaths.SERVICES)
public class RestApplicationConfig extends Application {

}
