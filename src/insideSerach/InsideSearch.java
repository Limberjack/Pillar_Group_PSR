package insideSerach;

public class InsideSearch extends Thread{
    private boolean makeSearch(){
        return false;
    }

    @Override
    public void run(){
        makeSearch();
    }
}