package insideChek;

import java.io.File;
import java.util.LinkedList;

public class ChekTelegram extends InsideChek {
    /**
     * костыль, убеждающийся, что он нашел именно телеграм
     * @return
     */
    @Override
    public boolean makeSearch(LinkedList<String> paths, File initRoot){

        File f[] = initRoot.listFiles();
        int counter = 0;
        for (int i = 0; i < f.length; i++) {
            if(f[i].getName().equals("663") || f[i].getName().equals("715") || f[i].getName().equals("current"))
                counter++;
        }

        if(counter == 3){
            paths.add(initRoot.getAbsolutePath());
            return  true;
        }

        return false;
    }
}

