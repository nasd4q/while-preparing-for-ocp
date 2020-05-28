package remotefilesystem;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class TestClass {
    public static void main(String[] args) throws URISyntaxException {
        FileSystem fileSystem = FileSystems.getFileSystem( new URI("http://www.selikoff.net"));
        Path path = fileSystem.getPath("duck.txt");
    }
}
