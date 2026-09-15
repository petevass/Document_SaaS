package com.mycompany.app.newgoogldocs.Data.Repositories;

import com.mycompany.app.newgoogldocs.Models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public interface DocumentRepository extends JpaRepository<Document,String> {

    ArrayList<Document> findAllByAuthorIds(Set<String>  authorIds);
    ArrayList<Document> findAllByAuthorIdsAndTitle(Set<String>  authorIds, String title);
    ArrayList<Document> findByTitleAndAuthorIds(String title, Set<String> authorIds);

}
