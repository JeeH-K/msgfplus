package edu.ucsd.msjava.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class TSVParser {
    public TSVParser() {

    }

    private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

    public ArrayList<String> getList(String label) {
        return map.get(label);
    }

    public void parse(String fileName) {
        BufferedLineReader in = null;
        try {
            in = new BufferedLineReader(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String labelRow = in.readLine();
        String[] labelArr = labelRow.split("\t");
        for (String label : labelArr)
            map.put(label, new ArrayList<String>());

        String s;
        while ((s = in.readLine()) != null) {
            if (s.startsWith("#"))
                continue;
            String[] token = s.split("\t");
            if (token.length != labelArr.length)
                continue;
            for (int i = 0; i < labelArr.length; i++)
                map.get(labelArr[i]).add(token[i]);
        }
    }
}
