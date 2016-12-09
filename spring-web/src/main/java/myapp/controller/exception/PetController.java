package myapp.controller.exception;

import myapp.bean.Pet;
import myapp.dao.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Pet Controller
 * <p>
 *     测试 SpringMVC 对异常的处理
 * <p>
 * Created by liuchenwei on 2016/12/9.
 */
@Controller
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @RequestMapping(value = "/pet1/{id}", method = RequestMethod.GET)
    public String pet1(@PathVariable("id") String id, Model model) {
        Pet pet = petRepository.findById(id);
        // 没有找到 Pet 则直接抛出 RuntimeException 异常
        // 如果出现任何没有映射的异常，响应都会带有 500 状态码。
        if (pet == null) {
            throw new RuntimeException("Pet not found.");
        }
        model.addAttribute("pet", pet);
        return "pet";
    }

    @RequestMapping(value = "/pet2/{id}", method = RequestMethod.GET)
    public String pet2(@PathVariable("id") String id, Model model) {
        Pet pet = petRepository.findById(id);
        // 没有找到 Pet 则直接抛出 PetNotFoundException 异常
        // 因为已经将 PetNotFoundException 映射到 404 状态，所以响应将会具备 404 状态码
        if (pet == null) {
            throw new PetNotFoundException();
        }
        model.addAttribute("pet", pet);
        return "pet";
    }

    /**
     * 编写异常处理的方法
     *
     * 如果想在响应中不仅要包括状态码，还要包含所产生的错误，
     * 就不能将异常视为 HTTP 错误，而是要按照处理请求的方式来处理异常。
     */

    /** 1，在处理请求的方法中直接处理异常 */
    @RequestMapping(value = "/pet/delete1/{id}", method = RequestMethod.GET)
    public String delete1(@PathVariable("id") String id) {
        try {
            petRepository.deleteById(id);
            return "OK";// 如果正常删除则转到 OK 页
        } catch (PetAlreadyRemovedException e) {
            return "error";// 如果出现异常则转到 error 页
        }
    }

    /** 2，通过指定的方法处理异常 */
    @RequestMapping(value = "/pet/delete2/{id}", method = RequestMethod.GET)
    public String delete2(@PathVariable("id") String id) {
        petRepository.deleteById(id);
        return "OK";// 如果正常删除则转到 OK 页
    }

    /**
     * @ExceptionHandler 注解标注的方法，可以处理同一个 Controller
     * 中所有处理器方法抛出的指定异常（本例是 PetAlreadyRemovedException）。
     * 当处理器方法抛出该异常的时候，将会委托该方法来处理，它返回一个字符串指定要渲染的逻辑视图名。
     * 这样就不用每一个处理器方法都需要单独处理异常了。（跨 Controller 的异常处理请见下面的示例）
     */
    @ExceptionHandler(PetAlreadyRemovedException.class)
    public String handlePetNotFound(){
        return "error";
    }

    /** 3，为控制器添加通知处理异常，详见 AppExceptionHandler */
    @RequestMapping(value = "/pet/save", method = RequestMethod.GET)
    public String save(@RequestParam("name") String name) {
        petRepository.save(name);
        return "pet";
    }
}
