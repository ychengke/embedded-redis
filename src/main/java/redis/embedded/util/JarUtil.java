package redis.embedded.util;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

public class JarUtil {

    public static File extractExecutableFromJar(String executable) throws IOException {
        //File tmpDir = Files.createTempDir();
        //将redis.exe动态的存放路径调整为固定路径，以方便设置window防火墙。不然每次启动都需要手动 “允许” 一次。
        String tempDirPath = URLDecoder.decode(System.getProperty("user.home"), "UTF-8");
        File tmpDir = new File(tempDirPath);
        tmpDir.deleteOnExit();

        File command = new File(tmpDir, executable);
        FileUtils.copyURLToFile(Resources.getResource(executable), command);
        command.deleteOnExit();
        command.setExecutable(true);

        return command;
    }
}
