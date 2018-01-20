package kata6.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import kata6.model.Histogram;
import kata6.model.Mail;
import kata6.model.Person;
import kata6.view.DataBaseList;
import kata6.view.HistogramDisplay;
import kata6.view.MailHistogramBuilder;
import kata6.view.MailListReader;

public class Kata6 {
    private final String filename = "emailsFile.txt";
    private List<Mail> mailList;
    private Histogram<String> domains;
    private Histogram<Character> letters;
    private Histogram<Character> gender;
    private Histogram<Float> weight;
    private MailHistogramBuilder<Mail> builder;
    private List<Person> people;
    private MailHistogramBuilder<Person> builderPerson;

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
       Kata6 kata6 = new Kata6();
       kata6.execute();
    }
    
    public void execute() throws IOException, ClassNotFoundException, SQLException{
        input();
        process();
        output();
    }
    
    public void input() throws IOException, ClassNotFoundException, SQLException{
        mailList = MailListReader.read(filename);
        builder = new MailHistogramBuilder<>(mailList);
        people = DataBaseList.read();
        builderPerson = new MailHistogramBuilder<>(people);
        
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
        
        weight = builderPerson.build(new Attribute<Person,Float>(){
            @Override
            public Float get(Person item){
                return item.getWeight();
            }
        });
        
        gender = builderPerson.build(new Attribute<Person,Character>(){
            @Override
            public Character get(Person item){
                return item.getGender();
            }
        });
        
        
        
        
    }
    
    public void output(){
        new HistogramDisplay(domains, "Dominios").execute();
        new HistogramDisplay(letters,"Primer Caracter").execute();
        new HistogramDisplay(gender,"genero").execute();
        new HistogramDisplay(weight,"peso").execute();
    }
    
    
}
