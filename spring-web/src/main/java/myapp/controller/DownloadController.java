package myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Download Controller
 * <p>
 *     使用三种不同的实现方式处理资源下载请求。
 * <p>
 * <p>
 * Created by liuchenwei on 2017/1/16.
 */
@Controller
@RequestMapping("/download")
public class DownloadController {

    private static final String FILE_PATH = "/resources/image/cross.jpg";
    private static final String APPLICATION_JPG = "application/jpg";

    // 将 ServletContext 注入到 Controller
    @Autowired
    private ServletContext servletContext;

    /**
     * Download File via HttpServletResponse
     */
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public @ResponseBody void downloadA(HttpServletResponse response) throws IOException {
        File file = getFile();
        InputStream in = new FileInputStream(file);

        response.setContentType(APPLICATION_JPG);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
    }

    /**
     * Download File via HttpEntity
     */
    @RequestMapping(value = "/b", method = RequestMethod.GET)
    public @ResponseBody
    HttpEntity<byte[]> downloadB() throws IOException {
        File file = getFile();
        byte[] document = FileCopyUtils.copyToByteArray(file);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "jpg"));
        header.set("Content-Disposition", "inline; filename=" + file.getName());
        header.setContentLength(document.length);

        return new HttpEntity<>(document, header);
    }

    /**
     * Download File via Resource
     */
    @RequestMapping(value = "/c", method = RequestMethod.GET)
    public @ResponseBody Resource downloadC(HttpServletResponse response) throws FileNotFoundException {
        File file = getFile();
        response.setContentType(APPLICATION_JPG);
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        return new FileSystemResource(file);
    }

    private File getFile() {
        return new File(servletContext.getRealPath(FILE_PATH));
    }
}
