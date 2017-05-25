package org.monarch.hphenote.io;

/*
 * #%L
 * HPhenote
 * %%
 * Copyright (C) 2017 Peter Robinson
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.File;

public class HPODownloader extends Downloader {

    private String hpo_urlstring="https://raw.githubusercontent.com/obophenotype/human-phenotype-ontology/master/hp.obo";

    /**
     * Download HP.obo file to given dataDir
     */
    public HPODownloader() {
        File dir = org.monarch.hphenote.gui.Platform.getHPhenoteDir();
        File hpoPath = new File(dir + File.separator + "hp.obo");
        setFilePath(hpoPath);
        setURL(hpo_urlstring);
    }

}