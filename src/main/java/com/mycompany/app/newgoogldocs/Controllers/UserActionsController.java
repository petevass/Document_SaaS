package com.mycompany.app.newgoogldocs.Controllers;

import com.mycompany.app.newgoogldocs.Data.Services.DocumentService;
import com.mycompany.app.newgoogldocs.Models.Document;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/users/actions")
public class UserActionsController {

    record deleteDocReq(String docId){}
    record renameDocReq(String docId, String docTitle){}
    record EditDocReq(String docBody){}

    DocumentService documentService;

    @PostMapping("/new_document")
    public ResponseEntity<String> newDocument(Authentication authentication) {

        Document document = new Document();
        Set<String> ids = new HashSet<String>();
        ids.add(authentication.getPrincipal().toString());
        System.out.println(authentication.getPrincipal().toString());
        document.setAuthorIds(ids);
        document.setBody("");
        document.setTitle("Untitled Document");

        documentService.addDocument(document);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/delete_doc")
    public ResponseEntity<String> deleteDocument(Authentication authentication, @RequestBody  deleteDocReq deleteDocReq) {
        Document doc = documentService.getDocument(deleteDocReq.docId);
        if(!doc.getAuthorIds().contains(authentication.getPrincipal().toString())){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        documentService.deleteDocument(doc);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/rename_doc")
    public ResponseEntity<String> renameDocument(Authentication authentication, @RequestBody renameDocReq rDR){

        Document doc = documentService.getDocument(rDR.docId);
        if(!doc.getAuthorIds().contains(authentication.getPrincipal().toString())){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        doc.setTitle(rDR.docTitle);
        documentService.updateDocument(doc);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    @PostMapping("/edit_document/{docId}")
    public ResponseEntity<String> editDocument(Authentication authentication, @RequestBody EditDocReq editDocReq, @PathVariable String docId) {
        Document doc = documentService.getDocument(docId);
        if(!doc.getAuthorIds().contains(authentication.getPrincipal().toString())){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        System.out.println("Body: "+editDocReq.docBody);
        System.out.println("Doc Id: "+docId);
        doc.setBody(editDocReq.docBody);
        documentService.updateDocument(doc);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


}


