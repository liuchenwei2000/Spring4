package myapp.dao;

import myapp.bean.Pet;
import myapp.controller.exception.DuplicationException;
import myapp.controller.exception.PetAlreadyRemovedException;
import org.springframework.stereotype.Component;

/**
 * Pet DAO 实现类
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/9.
 */
@Component
public class PetRepositoryImpl implements PetRepository {

    @Override
    public Pet findById(String id) {
        return null;// 始终返回 null 以测试 Controller 处理异常的能力
    }

    @Override
    public String save(String name) {
        throw new DuplicationException("Pet " + name + " was already existed.");
    }

    @Override
    public void deleteById(String id) {
        throw new PetAlreadyRemovedException("Pet " + id + " was already removed.\nSo you cannot delete.");
    }
}
