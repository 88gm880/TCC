package org.openjfx.control;

import com.tutego.jrtf.Rtf;
import org.openjfx.model.entity.User;
import org.springframework.stereotype.Component;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class DocUtils {

    private String pdfTemplate = "C:/Users/gumco/Desktop/Casa/template.rtf";

    public void generateDoc(User user){

        try {
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
        }
    }

}
