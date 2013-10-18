package org.apache.lucene.analysis.ko.dic;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.util.IOUtils;

/**
 * file utility class
 */
public class DictionaryResources {
  
  public static final String FILE_SYLLABLE_FEATURE = "syllable.dic";
  
  public static final String FILE_DICTIONARY = "dictionary.dic";  
  
  public static final String FILE_JOSA = "josa.dic";
  
  public static final String FILE_EOMI = "eomi.dic";
  
  public static final String FILE_EXTENSION = "extension.dic";
  
  public static final String FILE_PREFIX = "prefix.dic";
  
  public static final String FILE_SUFFIX = "suffix.dic";  
  
  public static final String FILE_COMPOUNDS = "compounds.dic";  
  
  public static final String FILE_UNCOMPOUNDS = "uncompounds.dic";
    
  public static final String FILE_ABBREV = "abbreviation.dic";
  
  public static final String FILE_CJ = "cj.dic";
  
  public static final String FILE_TAG_DIC = "tagger.dic";
  
  public static final String FILE_MAP_HANJA_DIC = "mapHanja.dic";


  private DictionaryResources() {}

  public static List<String> readLines(String file) throws IOException {
    InputStream in = null;
    try {
      in = DictionaryResources.class.getResourceAsStream(file);
      if (in == null)
        throw new FileNotFoundException(file);
      return readLines(new InputStreamReader(in, IOUtils.CHARSET_UTF_8));
    } finally {
      IOUtils.closeWhileHandlingException(in);
    }
  }

  /**
   * Get the contents of a <code>Reader</code> as a list of Strings,
   * one entry per line, removing comment lines starting with '!'.
   * @param input  the <code>Reader</code> to read from, not null
   * @return the list of Strings, never null
   * @throws IOException if an I/O error occurs
   */
  private static List<String> readLines(Reader input) throws IOException {
    BufferedReader reader = new BufferedReader(input);
    List<String> list = new ArrayList<String>();
    String line;
    while ((line = reader.readLine()) != null) {
      if (line.startsWith("!") || line.startsWith("\uFEFF!")) { // Skip comment lines starting with '!'
        continue;
      }
      list.add(line);
    }
    return list;
  }

}