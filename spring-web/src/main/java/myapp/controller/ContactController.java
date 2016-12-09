package myapp.controller;

import myapp.bean.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static myapp.WebConstant.TEMP_DIR;

/**
 * Contact Controller
 * <p>
 *     处理 multipart 请求
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
@Controller
@RequestMapping("/contact")
public class ContactController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm() {
        return "registerContactForm";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    // 编写控制器方法来接收上传的文件，最常见的方式是在方法参数上添加 @RequestPart 注解
    // 当注册表单提交的时候，picture 属性将会给定一个 MultipartFile 对象，这个对象包含了
    // 请求中对应 part（即 picture）的数据，它为处理 multipart 数据提供了内容丰富的接口。
    public String register(@RequestPart MultipartFile picture, Contact contact) {
        // 原始的文件名
        String pictureOriginalFilename = picture.getOriginalFilename();
        try {
            // 可以用来将数据以流的方式进行读取
//            InputStream pictureInputStream = picture.getInputStream();

            String filePath = TEMP_DIR + File.separator + contact.getName();
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 可以直接将上传的文件写入到文件系统中
            picture.transferTo(new File(filePath + File.separator + pictureOriginalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "home";
    }

    @RequestMapping(value = "/register2",method = RequestMethod.POST)
    // 如果需要将应用部署到 Servlet3.0 的容器中，可以使用
    // Servlet 规范中的 Part 来替换上面的 MultipartFile。
    public String register2(@RequestPart("picture") Part picturePart, Contact contact) throws IOException {
        // Part 接口与 MultipartFile 没有太大区别，主要的方法都是类似的。
        // 另外，如果采用 Part 参数的形式接受文件上传，那就没有必要配置 MultipartResolver 了。
        String pictureName = picturePart.getName();
        InputStream pictureInputStream = picturePart.getInputStream();
        picturePart.write(TEMP_DIR);
        return "home";
    }
}
