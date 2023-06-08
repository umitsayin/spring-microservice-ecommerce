package com.turkcell.documentservice.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GlobalConstant {

    public static class File{
        public static String IMAGES_PATH = "document-service/src/main/resources/upload/";
        public static String FILE_SPLIT_CHARACTER = ".";

        public static String FILE_NOT_FOUND = "FILE_NOT_FOUND_IN_FOLDER";
        public static String INVALID_FILE_TYPE = "Invalid mediaType!";
        public static Set<String> FILE_TYPES = new HashSet<>(Arrays.asList("jpg", "jpeg", "png"));
    }
}
