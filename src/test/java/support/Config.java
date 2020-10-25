package support;

import java.util.List;
import java.util.Map;
// set of variables existing in config.yml file You have to name filds here the same as they named in
//your source (database, file, etc.,)
    public class Config {
        public String browser;
        public String testEnv;
        public boolean isHeadless;
        public int implicitTimeout;
        public int pageLoadTimeout;
        public int explicitTimeout;
        public List<String> supportedOSList;
        public Map<String, String> admin;
        public Map<String, String> user;
    } //end of the class