package quru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class ZipTest {

    @Test
    void parseZipFilesTest() throws Exception {
        ZipFile zipFile = new ZipFile("files/zipFiles.zip");
        ZipEntry CsvEntry = zipFile.getEntry("example.csv");
        ZipEntry XlsEntry = zipFile.getEntry("svodnaya_tablica.xlsx");
        ZipEntry PdfEntry = zipFile.getEntry("28518148.pdf");

        try (InputStream stream = zipFile.getInputStream(PdfEntry)) {
            PDF parsed = new PDF(stream);
            assertThat(parsed.text).contains("ТРИ ПОРОСЁНКА. СКАЗКИ");
        }

        try (InputStream stream = zipFile.getInputStream(CsvEntry)) {
            CSVReader reader = new CSVReader(new InputStreamReader(stream));
            List<String[]> list = reader.readAll();
            assertThat(list).hasSize(2).contains(
                    new String[]{"Ночь", "Улица"},
                    new String[]{"Фонарь", "Аптека"}
            );
        }


//        try (InputStream stream = zipFile.getInputStream(XlsEntry)) {
//            XLS parsed = new XLS(stream);
//            assertThat(parsed.excel.getSheetAt(0).getRow(6).getCell(1).getStringCellValue())
//                    .isEqualTo("Картофель");
//        }
    }

}
