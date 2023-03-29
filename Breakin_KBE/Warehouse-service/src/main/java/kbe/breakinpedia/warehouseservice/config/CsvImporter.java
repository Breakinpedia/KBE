package kbe.breakinpedia.warehouseservice.config;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import kbe.breakinpedia.warehouseservice.model.Product;
import kbe.breakinpedia.warehouseservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class CsvImporter implements CommandLineRunner {
    @Autowired
    private final ProductRepository productRepository;

    public void importCsv(String filename) throws IOException, CsvException {
        Reader reader = new FileReader(filename);

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withQuoteChar('"')
                .withIgnoreQuotations(false)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withCSVParser(parser)
                .build();

        List<String[]> records = csvReader.readAll();

        for (String[] record : records) {
            System.out.println(record);
            Product product = new Product();
            product.setId(UUID.randomUUID().toString());
            product.setTitle(record[0]);
            product.setDescription(record[1]);
            product.setPrice(new BigDecimal(record[2]));
            product.setAuthor(record[3]);
            product.setImageUrl(record[4]);

            productRepository.save(product);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        String filename = "/Users/tdper/Desktop/Persönlich/TD/HTW/5.Semester/KBE/Breakin_KBE/Warehouse-service/src/main/resources/ProductCsv.csv";
        File file = new File(filename);
        importCsv(filename);
    }
}
