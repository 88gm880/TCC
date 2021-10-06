package org.openjfx.control;

import com.tutego.jrtf.Rtf;
import org.openjfx.model.entity.User;
import org.springframework.stereotype.Component;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class DocUtils {

    private String pdfTemplate = "C:/Users/gumco/Desktop/Casa/template.rtf";
    private String pdfOut = "C:/Users/gumco/Desktop/Casa/out.rtf";

    public void generateDoc(User user){
        String line;
        try
        {
            File inFile = new File(pdfTemplate);
            File outFile = new File(pdfOut);
            BufferedReader br = new BufferedReader(new FileReader(inFile));
            FileWriter writer = new FileWriter(outFile);

            while ((line = br.readLine()) != null)
            {
                if(line.contains("$st_name"))
                    line = line.replace("$st_name", user.getName());
                writer.append(line);
            }
            writer.close();
        }
        catch (java.io.IOException ex) {

        }
        /*try {
            Rtf.template(new FileInputStream(pdfTemplate))
                    .inject("%%st_name%%", "funcionou")
                    .out(new FileOutputStream("C:/Users/gumco/Desktop/Casa/"+ user.getName()+"_teste.rtf"));
            RTFEditorKit rtfEditorKit = new RTFEditorKit();
            Document document = rtfEditorKit.createDefaultDocument();
            rtfEditorKit.read(new ByteArrayInputStream(pdfTemplate.getBytes()), document, 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }*/
    }

}
