import java.util.Iterator;
import java.util.LinkedList;

public class SupportedProgList{
    private final LinkedList<Prog> progs;
    SupportedProgList(LinkedList<Prog> progs){
        this.progs = progs;
    }
    SupportedProgList(){this.progs = new LinkedList<>();}
    Prog getProg(String name){

        Iterator iterator = progs.iterator();
        while (iterator.hasNext()) {
            Prog prog = (Prog)(iterator.next());
            if( prog.getName() == name)
                return prog;
        }
        return null;
    }
    void add(Prog prog){progs.add(prog);}
}
