package com.mycompany.app.newgoogldocs.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="documents")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    String id;

    @Column(nullable=false)
    String title;

    @Column(nullable=false)
    String body;

    @Column(nullable = false)
    Set<String> authorIds;



}
