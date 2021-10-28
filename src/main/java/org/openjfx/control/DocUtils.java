package org.openjfx.control;

import org.openjfx.model.entity.User;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

@Component
public class DocUtils {

    public void generateDoc(User user){
        final String pdfTemplate = "C:/Users/gumco/Desktop/Casa/template.rtf";
        final String pdfOut = "C:/Users/gumco/Desktop/Casa/out.rtf";
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
    }

}
