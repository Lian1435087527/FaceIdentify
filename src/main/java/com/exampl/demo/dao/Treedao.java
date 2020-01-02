package com.exampl.demo.dao;

import com.exampl.demo.model.Tree;

import java.util.List;

public interface Treedao {
    public int savet(Tree tree);
    public List gett();
    public int deletet(String tree_id);
}
