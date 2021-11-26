package gmacias.control;

import gmacias.model.entity.User;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DocUtils {

    Map<String, String> map = new HashMap<>();

    public void generateDoc(User user) {
        initMap(user);
        final String pdfTemplate = "C:/Users/gumco/Desktop/Casa/template.rtf";
        final String pdfOut = "C:/Users/gumco/Desktop/Casa/out.rtf";
        String line;


        try {
            File inFile = new File(pdfTemplate);
            File outFile = new File(pdfOut);
            BufferedReader br = new BufferedReader(new FileReader(inFile));
            FileWriter writer = new FileWriter(outFile);
            Pattern pattern = Pattern.compile("(\\$)(.*)(\\$)");
            Matcher matcher;

            while ((line = br.readLine()) != null) {
                matcher = pattern.matcher(line);
                while (matcher.find()) {
                    line = line.replace(matcher.group(1) + matcher.group(2) + matcher.group(3), map.get(matcher.group(2)));
                }
                /*if(line.matches("\\$.*\\$")) {
                    line = line.replaceAll("\\$.*\\$", map.getOrDefault(pattern., ""));
                }*/
                writer.append(line);
            }
            writer.close();
        } catch (java.io.IOException ex) {

        }
    }

    private void initMap(User user) {
        map.put("user_name", user.getName());
        map.put("day", String.valueOf(user.getBirthday().getDayOfMonth()));
        map.put("month", String.valueOf(user.getBirthday().getMonth().getValue()));
        map.put("year", String.valueOf(user.getBirthday().getYear()));
        map.put("age", String.valueOf(user.getAge()));
        map.put("naturality", user.getNaturality());
        map.put("fatherName", user.getFatherName());
        map.put("motherName", user.getMotherName());
        map.put("phone", user.getPhone());
        map.put("sexo_m", " ");
        map.put("sexo_f", " ");
        map.put("sexo_" + Character.toLowerCase(user.getSexo()), "X");
        map.put("address_street", user.getAddress().getStreet());
        map.put("address_number", user.getAddress().getNumber());
        map.put("address_district", user.getAddress().getDistrict());
        map.put("address_reference", user.getAddress().getReference());
    }

}
