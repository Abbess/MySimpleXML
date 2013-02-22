package dcll.absm.mysimplexml;
import java.io.*;
import nu.xom.*;
public class Xom {

  public static void main(String[] args) {
      Builder builder = new Builder();
    try {
      Document doc = builder.build("d:\\abbes.xml");// instance du document XML
      Element root = doc.getRootElement();// saisie de la racine
      listChildren(root, 0);//charger les enfants :) de la racine      
    }
    // error de formatage xml
    catch (ParsingException ex) { 
      System.out.println("d:\\abbes.xml" + " n'est pas bien formatter :( ");
      System.out.println(ex.getMessage());
    }  
    catch (IOException ex) { 
      System.out.println(ex);
    }  
  
  }
  
  public static void listChildren(Node current, int depth) {
      Tokeniser t;
      String [] s;
    printSpaces(depth);
    String data = "";
    // parcourt des enfants (feuille de l'arborescence)
    if (current instanceof Element) {
        Element temp = (Element) current;
            data = ": " + temp.getQualifiedName();
            // getQualifiedName= valeur de la balise,
            // comme on peut aussi saisir les namespace etc
        if (temp.getAttributeCount()>0){
            // cette condition anisi que la boucle servent pour traiter 
            // (manuellement certe) les attribut des identificateurs
            // xml
            for (int i=0; i<temp.getAttributeCount();i++){
                t = new Tokeniser();
                s=t.Tokenizer(temp.getAttribute(i).toString(),"=");
                
                data +="\n" + s[0]+" = "+t.Tokenizer(s[1],"\"")[0];
                
            }
            
        }
         
    }
    else if (current instanceof ProcessingInstruction) {
        ProcessingInstruction temp = (ProcessingInstruction) current;
        data = ": " + temp.getTarget();   
    }
    else if (current instanceof DocType) {
        DocType temp = (DocType) current;
        data = ": " + temp.getRootElementName();   
    }
    else if (current instanceof Text || current instanceof Comment) {
        String value = current.getValue();
        value = value.replace('\n', ' ').trim();
        if (value.length() <= 20) data = ": " + value;
        else data = ": " + current.getValue().substring(0, 17) + "...";   
    }
    // les attributs ne sont jammais retourné par getChild
   
        System.out.println(current.getClass().getName() + data);
    
    for (int i = 0; i < current.getChildCount(); i++) {
      listChildren(current.getChild(i), depth+1);
    }
    
  }
  
  private static void printSpaces(int n) {
    
    for (int i = 0; i < n; i++) {
      System.out.print(' '); 
    }
    
  }

}