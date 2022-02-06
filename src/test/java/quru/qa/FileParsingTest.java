package quru.qa;


import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Selenide;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;


public class FileParsingTest {
    private ClassLoader cl = FileParsingTest.class.getClassLoader();

    @Test
    void parsePdfTest() throws Exception {
        open("https://litportal.ru/avtory/sergey-mihalkov/kniga-tri-porosenka-skazki-777008.html");
        File pdfDownload = Selenide.$(byText("Скачать pdf")).download();
        PDF parsed = new PDF(pdfDownload);
        assertThat(parsed.text).contains("ТРИ ПОРОСЁНКА. СКАЗКИ");
    }

//    @Test
//    void parseXlsTest() throws Exception {
//        try (InputStream stream = cl.getResourceAsStream("src/test/resources/files/svodnaya_tablica.xlsx")) {
//            XLS parsed = new XLS(stream);
//            System.out.println("");
//            assertThat(parsed.excel.getSheetAt(0).getRow(6).getCell(1).getStringCellValue())
//                    .isEqualTo("Картофель");
//        }
//    }

    @Test
    void parseCsvTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("files/example.csv")) {
            CSVReader reader = new CSVReader(new InputStreamReader(stream));
            List<String[]> list = reader.readAll();
            assertThat(list)
                    .hasSize(2)
                    .contains(
                            new String[]{"Ночь", "Улица"},
                            new String[]{"Фонарь", "Аптека"}

                    );
        }
    }
}


