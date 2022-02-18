package lms.corejava.assignment2;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Q16 {

    static class Criteria {
        String path;
        boolean includeSubFolder;
        String extension;

        public Criteria() {
        }

        public Criteria(String path, boolean includeSubFolder, String extension) {
            this.path = path;
            this.includeSubFolder = includeSubFolder;
            this.extension = extension;
        }

        public int getMaxDepth() {
            return includeSubFolder ? Integer.MAX_VALUE : 1;
        }
    }

    public static void main(String[] args) {
        String path = ".";
        boolean includeSubFolder = true;
        String extension = "java";

        Criteria criteria = new Criteria(path, includeSubFolder, extension);
        count(criteria);
    }

    public static void count(Criteria criteria) {

        // file name pattern match *.extension
        Pattern namePatern = Pattern.compile("(.*)\\." + criteria.extension);
        long fileCount = 0, dirCount = 0;

        // try to get the count of file and directory
        try {
            fileCount = Files.find(
                    Paths.get(criteria.path),
                    criteria.getMaxDepth(),
                    (filePath, matcher) -> matcher.isRegularFile() && namePatern.matcher(filePath.toString()).matches()).count();

            dirCount = Files.find(
                    Paths.get(criteria.path),
                    criteria.getMaxDepth(),
                    (filePath, matcher) -> matcher.isDirectory() && namePatern.matcher(filePath.toString()).matches()).count();
        } catch (NoSuchFileException e) {
            System.err.println("Directory could not be found!");
            return;
        } catch (AccessDeniedException e) {
            System.err.println("Unable to access the path");
            return;
        } catch (UncheckedIOException | IOException e) {
            e.printStackTrace();
            return;
        }

        // print the result
        System.out.printf(
                "There are %d file(s) and %d folder(s) inside folder %s with extension %s\n",
                fileCount,
                dirCount,
                criteria.path,
                criteria.extension);

    }
}
