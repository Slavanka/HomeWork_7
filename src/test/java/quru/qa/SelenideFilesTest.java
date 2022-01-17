package quru.qa;

import com.codeborne.selenide.Selenide;
import com.sun.prism.shader.Solid_ImagePattern_Loader;
import org.junit.jupiter.api.Test;

import java.io.File;

public class SelenideFilesTest {

    @Test
    void downloadTest() throws Exception {
        Selenide.open("https://github.com/junit-team/junit5/blob/main/LICENSE.md");
        File downloadedFile = Selenide.$("#raw-url").download();
        System.out.println("");
    }
}
