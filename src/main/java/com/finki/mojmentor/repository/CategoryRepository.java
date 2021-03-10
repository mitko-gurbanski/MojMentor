package com.finki.mojmentor.repository;

import com.finki.mojmentor.Model.Category;
import com.finki.mojmentor.Model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findCategoryByName(String name);
}
