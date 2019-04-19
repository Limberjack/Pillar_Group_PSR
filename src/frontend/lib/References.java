package frontend.lib;

public class References {

    public static final String CMD_GET_APPS_NAMES_COMMAND = "cmd /c start powershell.exe -Command Get-ItemProperty HKLM:\\Software\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\* " +
            "| Select-Object DisplayName, DisplayVersion " +
            "| Format-Table -AutoSize > C:\\Users\\ПК\\Desktop\\JavaLessonsITIS\\java_itis\\ComFix\\apps-names.txt";

    public static final String CONFIX_SETTINGS_FILE_PATH = "src/bin/confix.properties";

    public static final String SETTINGS_PAGE_FXML_PATH = "/pages/settings_page.fxml";
    public static final String MAIN_PAGE_FXML_PATH =  "/pages/main_page.fxml";
    public static final String RESET_PAGE_FXML_PATH = "/pages/reset_page.fxml";
    public static final String RESET_FIRSTTIME_PAGE_FXML_PATH = "/pages/reset_page_without_settings.fxml";
}
