package frontend.lib;

public interface IPropertiesHandler {

    /**
     * Saves new parameter {@code key} with value {@code value}.
     * If parameter exists, reassigns it's value to given value.
     * @param key - parameter to save
     * @param value - value to set
     */
    void save(String key, String value);

    /**
     * Gets the value assigned to the given key
     * @param key - key to search for
     * @return value of the given key
     * @throws IllegalStateException if property was not found by the given key
     */
    String get(String key);
}
