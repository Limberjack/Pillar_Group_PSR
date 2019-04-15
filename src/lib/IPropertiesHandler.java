package lib;

public interface IPropertiesHandler {


    void save(String key, String value);

    String get(String key);
}
