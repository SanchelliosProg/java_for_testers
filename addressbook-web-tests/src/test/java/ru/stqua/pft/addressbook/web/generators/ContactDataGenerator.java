package ru.stqua.pft.addressbook.web.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqua.pft.addressbook.web.appmanager.utils.RandomPhoneNumberProvider;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 27.04.2017.
 */
public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Количество контактов для генерации")
    public int count;

    @Parameter(names = "-f", description = "Путь к файлу, в котором мы сохраним сгенерированные данные")
    public String file;

    @Parameter(names = "-d", description = "Формат данных (csv, xml, json)")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander commander = new JCommander(generator);
        try {
            commander.parse(args);
            generator.run();
        } catch (ParameterException ex) {
            commander.usage();
        }
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            throw new IllegalArgumentException("Unrecognized format " + format);
        } else if (format.equals("xml")) {
            throw new IllegalArgumentException("Unrecognized format " + format);
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            throw new IllegalArgumentException("Unrecognized format " + format);
        }

    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String json = gson.toJson(contacts);
        writeFile(file, json);
    }

    private void writeFile(File file, String json) throws IOException {
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }


    private static List<ContactData> generateContacts(int count) {
        List<ContactData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(ContactData.newBuilder()
                    .firstName(String.format("firstName %s", i))
                    .lastName(String.format("lastName %s", i))
                    .address(String.format("address %s", i))
                    .homePhone(RandomPhoneNumberProvider.generateRandomNumber(i))
                    .mobilePhone(RandomPhoneNumberProvider.generateRandomNumber(i))
                    .workPhone(RandomPhoneNumberProvider.generateRandomNumber(i))
                    .email(String.format("email-%s@testemail.un", i))
                    .noGroup()
                    .noPhoto()
                    .build());
            System.out.println(groups.toString());
        }
        return groups;
    }
}
