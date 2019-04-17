package insideChek.searchRealisation.telegram_desktop;

import insideChek.searchRealisation.I_TypeOfSearch;

import java.io.File;
import java.util.LinkedList;

public class TelegramDesktop implements I_TypeOfSearch {

    @Override
    public boolean search(LinkedList<String> paths, File directory) {

        File f[] = directory.listFiles();
        int counter = 0;
        for (int i = 0; i < f.length; i++) {
            if(f[i].getName().equals("663") || f[i].getName().equals("715") || f[i].getName().equals("current"))
                counter++;
        }

        if(counter == 3){
            paths.add(directory.getAbsolutePath());
            return  true;
        }

        return false;
    }
}
