package org.monarch.hphenote.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by peter on 23.05.17.
 * A class to parse the MedGen file.
 * Note that I checked that the encoding of this file
 * <pre>
 * file -i MedGen_HPO_OMIM_Mapping.txt
 * MedGen_HPO_OMIM_Mapping.txt: text/plain; charset=us-ascii
 * </pre>
 * @author Peter Robinson
 */
public class MedGenParser extends Parser{
    /** Value: e.g., 613962, Key e.g., ACTIVATED PI3K-DELTA SYNDROME */
    private Map<String,String> omimName2IdMap;

/** The constructor sets {@link #absolutepath} to
 * the absolute path of  MedGen_HPO_OMIM_Mapping.txt.gz
 * and calls the function to parse the file.
 * */
    public MedGenParser(){
        this.omimName2IdMap = new HashMap<>();
        File dir = org.monarch.hphenote.gui.Platform.getHPhenoteDir();
        String basename="MedGen_HPO_OMIM_Mapping.txt.gz";
        absolutepath = new File(dir + File.separator + basename);

            parseFile();
    }

    /** @return OMIM Map (name:ID). Will always be initialized but can be empty.*/
    public Map<String,String> getOmimName2IdMap() { return omimName2IdMap; }


    private void parseFile() {
        if (! inputFileExists())
            return;
        try {
            InputStream fileStream = new FileInputStream(absolutepath);
            InputStream gzipStream = new GZIPInputStream(fileStream);
            Reader decoder = new InputStreamReader(gzipStream, java.nio.charset.StandardCharsets.US_ASCII);
            BufferedReader br = new BufferedReader(decoder);
            String line=null;
            while ((line=br.readLine())!=null){
                if (line.startsWith("#"))
                    continue; // skip header
                String F[] = line.split("\\|");
                if (F.length < 2)
                    continue;
                try {
                    String idString = F[1];
                    // Note we just parse this field to check the format
                    // but it is more convenient to store the String representation.
                    Integer id = Integer.parseInt(idString);
                    String name = F[2];
                    omimName2IdMap.put(name,idString);
                } catch (NumberFormatException n) {
                    n.printStackTrace();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
