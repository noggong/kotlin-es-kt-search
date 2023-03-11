//package com.example.demo.repository
//
//import com.example.demo.document.Document
//import com.example.demo.repository.es.DocumentRepository
//import org.junit.jupiter.api.DisplayName
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.junit.jupiter.api.Assertions.*
//
//@SpringBootTest
//class DocumentRepositoryTests {
//
//    @Autowired
//    private lateinit var documentRepository: DocumentRepository
//
//    @Test
//    @DisplayName("저장")
//    fun save() {
//        val document: Document = Document(1, "첫 테스트 document")
//        documentRepository.save(document)
//    }
//
//    @Test
//    @DisplayName("조회")
//    fun findById() {
//        val savedDocument: Document = Document(2, "첫 테스트 document2")
//        documentRepository.save(savedDocument)
//
//        val searchDocument = documentRepository.findById(2).orElseGet(null)
//        println("😀 searchDocument : ${searchDocument}")
//
//        assertNotNull(searchDocument)
//        assertEquals(savedDocument.id, searchDocument.id)
//        assertEquals(savedDocument.text, searchDocument.text)
//    }
//}