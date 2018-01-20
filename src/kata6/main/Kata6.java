package kata6.main;

import java.io.IOException;
import java.util.List;
import kata6.model.Histogram;
import kata6.model.Mail;
import kata6.view.HistogramDisplay;
import kata6.view.MailHistogramBuilder;
import kata6.view.MailListReader;

public class Kata6 {
    private final String filename = "emailsFile.txt";
    private List<Mail> mailList;
    private Histogram<String> domains;
    private Histogram<Character> letters;
    private MailHistogramBuilder<Mail> builder;

    public static void main(String[] args) throws IOException {
       Kata6 kata6 = new Kata6();
       kata6.execute();
    }
    
    public void execute() throws IOException{
        input();
        process();
        output();
    }
    
    public void input() throws IOException{
        mailList = MailListReader.read(filename);
        builder = new MailHistogramBuilder<>(mailList);
        
    }
    
    public void process(){
        domains = builder.build(new Attribute<Mail,String>(){
            @Override
            public String get(Mail item){
                return item.getMail().split("@")[1];
            }
        });
        
        letters = builder.build(new Attribute<Mail,Character>(){
            @Override
            public Character get(Mail item) {
                return item.getMail().charAt(0);
            }   
        });
        
        
    }
    
    public void output(){
        new HistogramDisplay(domains, "Dominios").execute();
        new HistogramDisplay(letters,"Primer Caracter").execute();
    }
    
    
}
