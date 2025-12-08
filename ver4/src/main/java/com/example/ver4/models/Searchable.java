package com.example.ver4.models;

import java.util.ArrayList;

public interface Searchable {
    ArrayList<Post> search(String keyword);
}