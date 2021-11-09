package org.openjfx.control;

import org.openjfx.model.entity.User;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Component
public class DocUtils {

    Map <String, String> map = new HashMap<>();

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
                    line = line.replace("$%fst_name%", user.getName());
                writer.append(line);
            }
            writer.close();
        }
        catch (java.io.IOException ex) {

        }
    }

    private void initMap(User user) {
        map.put("stName", user.getName());
        map.put("birthday", "");
        map.put("age", "");
        map.put("naturality", "");
        map.put("fatherName", "");
        map.put("motherName", "");
        map.put("startDate", "");

    }

}
