package log.usage;

import log.Log;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <p>
 * Created by liuchenwei on 2017/7/14.
 */
@Service
public class SaveService {

    @Log(value = "#id")
    public void save(String id) {
        System.out.println("id=" + id + " has been saved.");
    }
}
