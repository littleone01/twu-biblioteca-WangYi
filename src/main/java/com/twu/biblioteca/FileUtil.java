package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yibwang on 2/27/17.
 */
public class FileUtil {
    public static List<String> getFileContext(String filePath) {
        List<String> lineList = new ArrayList<String>();
        try {
            File file = new File(filePath);
            FileInputStream stream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lineList.add(line);
            }
            stream.close();
        } catch (IOException e) {
            System.out.println("Fail to open file " + filePath);
        }

        return lineList;
    }

    public static boolean writeToFile(String fileName, List<String> contextToWrite) {
        boolean ifSuccess = true;
        try {
            FileOutputStream output = new FileOutputStream(fileName);
            OutputStreamWriter streamWriter = new OutputStreamWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);
            for (String line : contextToWrite) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fail to write to file " + fileName);
            ifSuccess = false;
        } finally {
            return ifSuccess;
        }
    }
}
