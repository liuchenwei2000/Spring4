package myapp.dao;

import myapp.bean.Pet;

/**
 * Pet DAO 接口
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/6.
 */
public interface PetRepository {

    Pet findById(String id);

    String save(String name);

    void deleteById(String id);
}
