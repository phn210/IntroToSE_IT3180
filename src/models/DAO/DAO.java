package models.DAO;

import java.util.List;

public interface DAO<T> {
    // danh sach cac doi tuong
    List<T> getAll();

    // them doi tuong vao danh sach
    void save(T t);

    // sua mot doi tuong
    void update(T t);

    // xoa mot doi tuong
    void delete(T t);
}
