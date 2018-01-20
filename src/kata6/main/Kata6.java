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
    private Histogram<String> hist;

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
        
    }
    
    public void process(){
        hist = MailHistogramBuilder.build(mailList);
    }
    
    public void output(){
        HistogramDisplay histoDisplay = new HistogramDisplay(hist);
        histoDisplay.execute();
    }
    
    
}
