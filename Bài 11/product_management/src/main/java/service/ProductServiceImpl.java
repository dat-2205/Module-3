package service;

import model.Product;

import java.util.*;

public class ProductServiceImpl implements ProductService {
    private static Map<Integer, Product> product;

    static {
        product = new HashMap<>();
        product.put(1,new Product(1,"Thit heo",21000,"thit heo ngon"));
        product.put(2,new Product(2,"Thit ga",13000,"thit ga ngon"));
        product.put(3,new Product(3,"Thit bo",33000,"thit bo ngon"));
        product.put(4,new Product(4,"Ca hoi",16000,"Ca hoi ngon"));
        product.put(5,new Product(5,"Ca chep",11000,"ca chep ngon"));
        product.put(6,new Product(6,"Uc ga",7000,"Uc ga ngon"));
        product.put(7,new Product(7,"Rau cai",1800,"Rau tuoi"));
        product.put(8,new Product(8,"Xa lach",4000,"Xa lach ngon"));
        product.put(9,new Product(9,"Ruoc",25000,"Ruoc ngon"));
        product.put(10,new Product(10,"Trung ga",10000,"Trung ngon"));
        product.put(11,new Product(11,"Cam",2000,"cam ngon"));
        product.put(12,new Product(12,"Dua hau",8000,"Dua hau ngon"));
    }


    @Override
    public List<Product> findAll() {
        return new ArrayList<>(product.values());
    }

    @Override
    public void save(Product product) {
        this.product.put(product.getId(),product);
    }

    @Override
    public Product findById(int id) {
        return this.product.get(id);
    }

    @Override
    public void update(int id, Product product) {
        this.product.put(id,product);
    }

    @Override
    public void remove(int id) {
        this.product.remove(id);
    }
}
