package insideChek.searchRealisation;

import insideChek.searchRealisation.telegram_desktop.TelegramDesktop;

public class GetSearchRealisation {
    /**
     * в зависимости от имени программы возвращает тип осуществляемого поиска
     * @return
     */
    public static I_TypeOfSearch getSearch(String name){
        switch(name){
            case "telegram-desktop":
                return new TelegramDesktop();
        }
        return null;
    }
}
