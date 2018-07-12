package net.friend.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyProperties {

  private Properties prop = new Properties();

  private static MyProperties ourInstance = new MyProperties();

  public static MyProperties getInstance() {
    return ourInstance;
  }

  private MyProperties() {
    InputStream input = null;

    try {

      input = this.getClass().getClassLoader().getResourceAsStream("config.properties");

      // load a properties file
      prop.load(input);
    } catch (IOException ex) {
      log.error(ex.getMessage());
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          log.error(e.getMessage());
        }
      }
    }
  }

  public String getProperty(String key){
    return prop.getProperty(key);
  }
}
