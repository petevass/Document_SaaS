package com.mycompany.app.newgoogldocs.Data.Services;

import com.mycompany.app.newgoogldocs.Data.Repositories.DocumentRepository;
import com.mycompany.app.newgoogldocs.Models.Document;
import lombok.AllArgsConstructor;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DocumentService {

    DocumentRepository documentRepository;

    public void addDocument(Document document){
        documentRepository.save(document);
    }
    public void deleteDocument(Document document){
        documentRepository.delete(document);
    }
    public void deleteDocument(String id){
        documentRepository.deleteById(id);
    }
    public List<Document> getDocuments(){
        List<Document> list =  documentRepository.findAll();
        Collections.reverse(list);
        return list;
    }

    public Document getDocument(String id){
        return documentRepository.findById(id).get();
    }
    public List<Document> getByOwnerIds(Set<String> ids){
        ArrayList<Document> list =  documentRepository.findAllByAuthorIds(ids);
        Collections.reverse(list);
        return list;
    }

    public List<Document> getByOwnerIdsAndTitle(Set<String> ids, String title){
        ArrayList<Document> list =  documentRepository.findByTitleAndAuthorIds(title,ids);
        Collections.reverse(list);

        return list;
    }





    public void updateDocument(Document document){
        documentRepository.save(document);
    }

}
