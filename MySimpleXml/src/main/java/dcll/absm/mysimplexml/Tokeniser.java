package dcll.absm.mysimplexml;
import java.util.Vector;
import java.util.StringTokenizer;
public class Tokeniser {
	Vector v;
    String[] V;
	public String[] Tokenizer(String chaine,String separateur)
		{
			v = new Vector();
			StringTokenizer t = new StringTokenizer(chaine,separateur);
			while (t.hasMoreTokens()) {
					v.add(t.nextToken());
			}
            V = new String[v.size()];
            for (int i = 0;i<v.size();i++){
                V[i]=v.get(i).toString();
            }
			return V;
		}

}
