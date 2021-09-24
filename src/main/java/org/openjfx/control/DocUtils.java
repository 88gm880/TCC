package org.openjfx.control;

import com.tutego.jrtf.Rtf;
import org.openjfx.model.entity.Student;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

@Component
public class DocUtils {

    private String pdfTemplate = "C:/Users/gumco/Desktop/Casa/template.rtf";

    public void generateDoc(Student student){

        try {
            Rtf.template(new FileInputStream(pdfTemplate))
                    .inject("%%st_name%%", "funcionou")
                    .out(new FileOutputStream("C:/Users/gumco/Desktop/Casa/"+student.getName()+"_teste.rtf"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
